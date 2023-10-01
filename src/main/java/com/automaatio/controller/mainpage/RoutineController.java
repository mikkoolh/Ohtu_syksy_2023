package com.automaatio.controller.mainpage;

import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.RoutineUtils;
import com.dlsc.gemsfx.TimePicker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.ToggleSwitch;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import com.automaatio.components.TimeSelector;
import org.sonatype.guice.bean.reflect.LoadedClass;


public class RoutineController implements Initializable {

    @FXML
    private TextArea routineNameField;

    public RoutineController() {}

    CacheSingleton cache = CacheSingleton.getInstance();

    RoutineDAO routineDAO = new RoutineDAO();
    EventTimeDAO eventTimeDAO = new EventTimeDAO();

    @FXML
    private VBox routineVBox, weekdaysVBox;

    @FXML
    private Button automateAllButton, addRoutineButton;

    @FXML
    private Text noRoutinesText, routineErrorText, formTitle;

    @FXML
    private ScrollPane routineScrollPane;

    @FXML
    private VBox addRoutineForm;

    @FXML
    private GridPane formGrid;


    private List<Routine> routines;
    private final int ID = cache.getDevice().getDeviceID();

    private final Map<Routine, ToggleSwitch> toggleSwitches = new HashMap<>();
    private final Map<Button, Routine> deleteButtons = new HashMap<>();
    private final RoutineUtils util = new RoutineUtils();

    private WeekdayDAO weekdayDAO = new WeekdayDAO();

    private Map<Weekday, CheckBox> weekdayCheckBoxes = new HashMap<>();
    private TimePicker startTimePicker, endTimePicker;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        routines = util.sortByWeekday(util.sortByTime(fetchRoutines())); // Sort fetched routines by time
        routineNameField.setText(cache.getDevice().getName());
        loadRoutines(routines);
        updateUI();
        routineScrollPane.setStyle("-fx-background-color:transparent;");
        initializeForm();
        hideForm();
    }

    private void loadRoutines(List<Routine> r) {
        routineVBox.getChildren().clear();

        if (r.isEmpty()) {
            routineVBox.setAlignment(Pos.CENTER);
            routineVBox.getChildren().add(noRoutinesText);
            automateAllButton.setVisible(false);
        } else {
            routineVBox.setAlignment(Pos.TOP_LEFT);
            routineVBox.getChildren().remove(noRoutinesText);
            automateAllButton.setVisible(true);

            for (Routine routine : r) {
                String routineName = routine.getUser().getEmail();
                HBox routineRow = createRoutineRow(routine);
                routineVBox.getChildren().add(routineRow);
            }
        }
    }


    private HBox createRoutineRow(Routine routine) {
        Label nameLabel = new Label("Routine " + routine.getRoutineID());
        Label startTime = new Label(util.getFormattedTime(routine.getEventTime().getStartTime()));
        Label endTime = new Label(util.getFormattedTime(routine.getEventTime().getEndTime()));
        Label weekday = new Label(routine.getEventTime().getWeekday().getName());
        Button editButton = new Button("Edit");
        ToggleSwitch toggle = getToggleSwich(routine);

        HBox timeContainer = new HBox(new Label(" - "), endTime, new Label(" - "), startTime, new Label(" - "), weekday);
        timeContainer.setAlignment(Pos.CENTER);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(deleteRoutine);
        deleteButtons.put(deleteButton, routine);
        deleteButton.setVisible(false);

        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteButton.setVisible(!deleteButton.isVisible());

                if (deleteButton.isVisible()) {
                    editButton.setText("Save");
                } else {
                    editButton.setText("Edit");
                }
            }
        });

        HBox routineRow = new HBox(10, nameLabel, timeContainer, editButton, toggle, deleteButton);
        HBox.setHgrow(routineRow, Priority.ALWAYS);

        routineRow.setStyle("-fx-border-color: black;");
        routineRow.setPadding(new Insets(5));
        return routineRow;
    }

    // Refetches routines from the database
    private List<Routine> fetchRoutines() {
        return routineDAO.getRoutinesByDeviceId(ID);
    }

    // Creates a toggle switch with an event handler to change the state of the current routine
    private ToggleSwitch getToggleSwich(Routine routine) {
        ToggleSwitch toggle = new ToggleSwitch();
        toggle.setSelected(routine.getAutomated());
        toggle.setText("Automate");

        toggle.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                routineDAO.toggleOnOff(routine.getRoutineID(), routine.getAutomated());
                updateUI();
                // Pitää lisätä deviceen joku tarkistus et se sit kans menee päälle sillon
            }
        });

        toggleSwitches.put(routine, toggle);

        return toggle;
    }

    @FXML
    public void automateAll(ActionEvent actionEvent) {
        boolean allAutomated = util.allAutomated(fetchRoutines());

        for (Routine routine : routines) {
            routine.setAutomated(!allAutomated);
            routineDAO.toggleOnOff(routine.getRoutineID(), routine.getAutomated());
        }


        updateUI();

        for (Routine routine : routines) {
            ToggleSwitch toggle = toggleSwitches.get(routine);
            if (toggle != null) {
                toggle.setSelected(!allAutomated);
            }
        }
    }

    private void updateUI() {
        System.out.println("update ui");

        if (util.allAutomated(fetchRoutines())) {
            automateAllButton.setText("Deselect all");

        } else {
            automateAllButton.setText("Automate all ✨");
        }
    }

    // Shows a confirmation popup when the "Delete routine" button is clicked
    private final EventHandler<ActionEvent> deleteRoutine = new EventHandler<>() {
        public void handle(ActionEvent event) {
            Routine routineToDelete = deleteButtons.get((Button) event.getTarget());

            // Define the popup
            Alert alert = new Alert(Alert.AlertType.NONE, null, ButtonType.OK,
                    ButtonType.CANCEL);
            EventTime time = routineToDelete.getEventTime();
            alert.setContentText("Delete routine on " + time.getWeekday().getName() + "s at "
            + util.getFormattedTime(time.getStartTime()) + "-" +
                    util.getFormattedTime(time.getStartTime()) + "?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Delete");

            // Respond to user input
            if (alert.showAndWait().get() == ButtonType.OK) {
                try {
                    routineDAO.deleteRoutine(routineToDelete.getRoutineID());
                    routineErrorText.setText("");
                } catch(Exception e) {
                    e.printStackTrace();
                    routineErrorText.setText("An error occurred");
                }
                loadRoutines(fetchRoutines());
            }
        }
    };

    @FXML
    private void hideForm() {
        addRoutineForm.setVisible(false);
        addRoutineForm.setManaged(false);
        addRoutineButton.setVisible(true);
    }

    @FXML
    private void showForm() {
        addRoutineForm.setVisible(true);
        addRoutineForm.setManaged(true);
        addRoutineButton.setVisible(false);

        // Reset checkboxes
        for (CheckBox checkBox : weekdayCheckBoxes.values()) {
            checkBox.setSelected(false);
        }
    }

    @FXML
    private void handleSaveRoutine() {

        /*
         Get start and end times from time pickers
         (Ottaa muut tiedot nykyhetkestä atm, ei pitäis haitata jos
         tarvitaan vaan kellonajat?)
         */
        LocalDateTime startTime = startTimePicker.getTime().atDate(LocalDate.now());
        LocalDateTime endTime = endTimePicker.getTime().atDate(LocalDate.now());

        // Iterate through weekday checkboxes
        for(Map.Entry<Weekday, CheckBox> entry : weekdayCheckBoxes.entrySet()){
            if (entry.getValue().isSelected()) {
                /*
                 If a weekday is selected, create a new routine for that day
                 Get the corresponding weekday from the database
                 */
                Weekday selectedWeekday = weekdayDAO.getWeekday(entry.getKey().getWeekdayId());

                User user = cache.getUser();
                Device device = cache.getDevice();

                try {
                    // Create event time
                    EventTime eventTime = eventTimeDAO.addEventTime(new EventTime(startTime, endTime, selectedWeekday));
                    Routine routine = new Routine(user, device, null, eventTime); // Feature is null for now

                    // Save the routine to the database
                    routineDAO.addRoutine(routine);
                    System.out.println("saved routine");
                } catch (Exception e) {
                    e.printStackTrace();
                    routineErrorText.setText("An error occurred");
                }
            }
        }
        hideForm();
    }

    public void initializeForm() {
        addRoutineForm.setPadding(new Insets(10));
        addRoutineForm.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
        formTitle.setText("Add custom routine for " + cache.getDevice().getName());

        // Weekday checkboxes
        for (Weekday weekday : weekdayDAO.getAll()) {
            weekdayCheckBoxes.put(weekday, new CheckBox(weekday.getName()));
        }
        weekdaysVBox.getChildren().addAll(weekdayCheckBoxes.values());

        // Time pickers
        startTimePicker = (new TimeSelector()).getTimePicker();
        endTimePicker = (new TimeSelector()).getTimePicker();
        formGrid.add(startTimePicker, 1, 0);
        formGrid.add(endTimePicker, 1, 1);
    }
}