package com.automaatio.controller.mainpage;

import com.automaatio.components.WeekdayLabel;
import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.RoutineUtils;
import com.dlsc.gemsfx.TimePicker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.controlsfx.control.ToggleSwitch;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import com.automaatio.components.TimeSelector;

/**
 * Controller for the routines listing
 *
 * @author Nikita Nossenko
 * @author Matleena Kankaanpää
 *
 * 1.10.2023
 */

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
    private Button automateAllButton, addRoutineButton, deleteAllButton;

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

    private final Map<Routine, ToggleSwitch> toggleSwitches = new LinkedHashMap<>();
    private final Map<Button, Routine> deleteButtons = new LinkedHashMap<>();
    private final RoutineUtils util = new RoutineUtils();

    private WeekdayDAO weekdayDAO = new WeekdayDAO();

    private Map<Weekday, CheckBox> weekdayCheckBoxes = new LinkedHashMap<>();
    private LinkedHashMap<String, ArrayList<Routine>> routinesByWeekday;
    private TimePicker startTimePicker, endTimePicker;
    private List<Weekday> weekdays;
    private boolean noRoutinesToShow;

    private final String FONT = "System";
    private final int FONT_SIZE_TEXT = 24;
    private final int ICON_DIMENSIONS = 18;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        VBox.setVgrow(routineVBox, Priority.ALWAYS);
        VBox.setVgrow(routineScrollPane, Priority.ALWAYS);
        noRoutinesToShow = true;
        try {
            routines = util.sortByTime(fetchRoutines()); // Sort fetched routines by time
            updateUI();
            resetWeekdays(); // voi poistaa sit ku ei tarvi enää tehdä testejä
            weekdays = weekdayDAO.getAll();
            initializeForm();
            loadRoutines();
        } catch (Exception e) {
            e.printStackTrace();
            displayErrorText();
            updateUI();
        }
        routineNameField.setText(cache.getDevice().getName());
        routineScrollPane.setStyle("-fx-background-color:transparent;");
        hideForm();
    }

    private void loadRoutines() {
        routineVBox.getChildren().clear();
        routineErrorText.setVisible(false);
        noRoutinesToShow = true;
        LinkedHashMap<String, ArrayList<Routine>> map = new LinkedHashMap<>();

        boolean fetchedOk = false;

        try {
            // Refetch routines from db
            map = util.getRoutinesByWeekday(weekdays, routines);
            fetchedOk = true;
        } catch (Exception e) {
            e.printStackTrace();
            displayErrorText();
            updateUI();
        }

        // Display routines if fetched successfully
        if (fetchedOk) {

            // For keeping track of whether there are any routines saved or not
            boolean noSavedRoutines = true;

            for (Map.Entry<String, ArrayList<Routine>> entry : map.entrySet()) {
                String weekday = entry.getKey();
                ArrayList<Routine> routines = entry.getValue();

                if (!routines.isEmpty()) {
                    noSavedRoutines = false; // Set to false
                    routineVBox.setAlignment(Pos.TOP_LEFT);
                    automateAllButton.setVisible(true);
                    deleteAllButton.setVisible(true);
                    HBox splitBox = new HBox();
                    Label weekdayLabel = new WeekdayLabel(weekday).getWeekdayLabel();
                    weekdayLabel.setMinWidth(60);
                    noRoutinesToShow = false;

                    VBox routinesContainer = new VBox(); // Routines are listed here
                    routinesContainer.setSpacing(10);
                    HBox.setHgrow(routinesContainer, Priority.ALWAYS); // Make routine row fill up available space
                    splitBox.getChildren().addAll(weekdayLabel, routinesContainer);

                    for (Routine routine : routines) {
                        HBox routineRow = createRoutineRow(routine);
                        splitBox.getChildren().add(routineRow);
                        routinesContainer.getChildren().add(routineRow);
                    }
                    routineVBox.getChildren().add(splitBox);

                    // Separate days with a line (except after Sunday)
                    if (!weekday.equals(weekdays.get(weekdays.size()-1).getName())) {
                        Separator s = new Separator(Orientation.HORIZONTAL);
                        s.setStyle("-fx-border-width: 1px; -fx-padding: 0;");
                        routineVBox.getChildren().add(s);
                    }
                }
            }

            /*
             If the database connection was ok but there were no saved routines,
             display the "You don't have any routines" text
             */
            if (noSavedRoutines) {
                noRoutinesText.setVisible(true);
                routineVBox.getChildren().add(noRoutinesText);
                routineVBox.setAlignment(Pos.CENTER);
            }
        }
        updateUI();
    }

    private HBox createRoutineRow(Routine routine) {
        Label startTime = new Label(util.getFormattedTime(routine.getEventTime().getStartTime()));
        Label endTime = new Label(util.getFormattedTime(routine.getEventTime().getEndTime()));
        Label dash = new Label("-");

        Button editButton = new Button();
        ImageView editView = new ImageView();
        ImageView saveView = new ImageView();
        Image edit = new Image("images/edit-svgrepo-com.png");
        Image save = new Image("images/save-svgrepo-com.png");
        Image delete = new Image("images/trash-svgrepo-com.png");
        editView.setImage(edit);
        editView.setPreserveRatio(true);
        editView.setFitHeight(ICON_DIMENSIONS);
        saveView.setImage(save);
        saveView.setPreserveRatio(true);
        saveView.setFitHeight(ICON_DIMENSIONS);
        editButton.setGraphic(editView);

        // Automation toggle
        ToggleSwitch toggle = getToggleSwich(routine);

        // Times
        HBox clockTimes = new HBox(endTime, dash, startTime); // Start and end times
        HBox timeContainer = new HBox(clockTimes); // Wrapper box
        clockTimes.setSpacing(10);
        timeContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(timeContainer, Priority.ALWAYS);


        // Buttons
        Button deleteButton = new Button();
        ImageView deleteView = new ImageView(delete);
        deleteView.setPreserveRatio(true);
        deleteView.setFitHeight(ICON_DIMENSIONS);
        deleteButton.setGraphic(deleteView);
        deleteButton.setOnAction(deleteRoutine);
        deleteButtons.put(deleteButton, routine);
        deleteButton.setVisible(false);

        Button cancelButton = new Button("Cancel");
        cancelButton.setVisible(false);
        cancelButton.setOnAction(cancelEditing);


        // Time pickers
        TimePicker newStartTime = (new TimeSelector()).getTimePicker();
        TimePicker newEndTime = (new TimeSelector()).getTimePicker();
        newStartTime.setTime(routine.getEventTime().getStartTime().toLocalTime());
        newEndTime.setTime(routine.getEventTime().getEndTime().toLocalTime());
        HBox timePickers = new HBox(newEndTime, dash, newStartTime);
        timePickers.setSpacing(10);

        // When edit/save is clicked
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteButton.setVisible(!deleteButton.isVisible());
                cancelButton.setVisible(!cancelButton.isVisible());
                timeContainer.getChildren().clear();

                if (deleteButton.isVisible()) {
                    // Editing
                    editButton.setGraphic(saveView);
                    timeContainer.getChildren().add(timePickers);

                } else {
                    // Not editing
                    editButton.setGraphic(editView);
                    timeContainer.getChildren().add(clockTimes);

                    // save new times + automation

                }
            }
        });

        HBox routineRow = new HBox(10, timeContainer, toggle, cancelButton, deleteButton, editButton);
        HBox.setHgrow(routineRow, Priority.ALWAYS);
        routineRow.setStyle("-fx-background-color: #e1e1e1; -fx-background-radius: 4;");

        Label[] labels = new Label[] {startTime, dash, endTime};
        for (Label label : labels) {
            label.setFont(new Font(FONT, FONT_SIZE_TEXT));
        }

        routineRow.setPadding(new Insets(10));
        routineRow.setAlignment(Pos.CENTER_RIGHT);
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

        /*
         If there are no routines to show,
         hide the "Automate all" and "Delete all" buttons
         */
        if (noRoutinesToShow) {
            automateAllButton.setVisible(false);
            deleteAllButton.setVisible(false);
        } else {
            automateAllButton.setVisible(true);
            deleteAllButton.setVisible(true);

            // FIX
            // Change the text on the button
            if (util.allAutomated(fetchRoutines())) {
                automateAllButton.setText("Deselect all");

            } else {
                automateAllButton.setText("Automate all");
            }
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
            alert.setContentText("Delete routine for " + cache.getDevice().getName() + " on " + time.getWeekday().getName() + "s at "
                    + util.getFormattedTime(time.getStartTime()) + "-" +
                    util.getFormattedTime(time.getStartTime()) + "?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Delete");

            // Respond to user input
            if (alert.showAndWait().get() == ButtonType.OK) {
                try {
                    routineDAO.deleteRoutine(routineToDelete.getRoutineID());
                    routineErrorText.setText("");
                    loadRoutines();
                } catch(Exception e) {
                    e.printStackTrace();
                    routineErrorText.setText("An error occurred");
                }
            }
        }
    };

    // When the "Cancel" button is clicked while editing
    private final EventHandler<ActionEvent> cancelEditing = new EventHandler<>() {
        public void handle(ActionEvent event) {
            System.out.println("cancel");
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
                // If a weekday is selected, create a new routine for that day

                try {
                    // Get the corresponding weekday from the database
                    Weekday selectedWeekday = weekdayDAO.getWeekday(entry.getKey().getWeekdayId());
                    // Create event time
                    EventTime eventTime = eventTimeDAO.addEventTime(new EventTime(startTime, endTime, selectedWeekday));

                    User user = cache.getUser();
                    Device device = cache.getDevice();

                    /*
                     Save the routine to the database
                     Feature is null for now, routines added by users
                     are automatically on (tää kävis järkeen?)
                     */
                    Routine routine = new Routine(user, device, null, eventTime, true);
                    routineDAO.addRoutine(routine);
                    System.out.println("saved routine");
                    loadRoutines();
                    hideForm();
                } catch (Exception e) {
                    e.printStackTrace();
                    routineErrorText.setText("An error occurred");
                }
            }
        }
    }

    public void initializeForm() {
        addRoutineForm.setPadding(new Insets(10));
        addRoutineForm.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
        formTitle.setText("Add a custom routine for " + cache.getDevice().getName());

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



    // ettei tarvi aina käydä lisäämässä tietokantaan uudestaan kun on ajettu testejä :D
    private void resetWeekdays() {
        if (weekdayDAO.getAll().size() != 7) {
            weekdayDAO.deleteAll();
            weekdayDAO.addWeekday(new Weekday("Monday"));
            weekdayDAO.addWeekday(new Weekday("Tuesday"));
            weekdayDAO.addWeekday(new Weekday("Wednesday"));
            weekdayDAO.addWeekday(new Weekday("Thursday"));
            weekdayDAO.addWeekday(new Weekday("Friday"));
            weekdayDAO.addWeekday(new Weekday("Saturday"));
            weekdayDAO.addWeekday(new Weekday("Sunday"));
        }
    }

    @FXML
    private void deleteAll() {
        try {
            // Define the popup
            Alert alert = new Alert(Alert.AlertType.NONE, null, ButtonType.OK,
                    ButtonType.CANCEL);
            alert.setContentText("Delete " + fetchRoutines().size() + " routines for " + cache.getDevice().getName() + "?");
            ((Button) alert.getDialogPane().lookupButton(ButtonType.OK)).setText("Delete all");

            // Respond to user input
            if (alert.showAndWait().get() == ButtonType.OK) {
                routineDAO.deleteByDeviceAndUser(cache.getDevice(), cache.getUser());
                loadRoutines();
            }
        } catch (Exception e) {
            e.printStackTrace();
            routineErrorText.setText("An error occurred");
        }
    }

    private void displayErrorText() {
        routineErrorText.setVisible(true);
        routineErrorText.setText("Error. Unable to load routines.");
        noRoutinesText.setVisible(false); // Don't display both disclaimers
    }
}