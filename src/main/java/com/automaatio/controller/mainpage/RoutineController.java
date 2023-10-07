package com.automaatio.controller.mainpage;

import com.automaatio.components.TimeSelectorGrid;
import com.automaatio.components.WeekdayLabel;
import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.DatabaseTool;
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
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import org.controlsfx.control.ToggleSwitch;
import java.net.URL;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import com.automaatio.components.TimeSelector;
import org.hibernate.annotations.Check;

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
    private VBox routineVBox;

    @FXML
    private Button automateAllButton, addRoutineButton, deleteAllButton, saveButton;

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
    private final WeekdayDAO weekdayDAO = new WeekdayDAO();
    private final Map<Weekday, CheckBox> weekdayCheckBoxes = new LinkedHashMap<>();
    private Label weekdayTooltip;
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
            DatabaseTool.resetWeekdays(); // voi poistaa sit ku ei tarvi enää tehdä testejä
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
        //hideForm();
    }

    private void loadRoutines() {
        routineVBox.getChildren().clear();
        routineErrorText.setVisible(false);
        noRoutinesToShow = true;
        LinkedHashMap<String, ArrayList<Routine>> map = new LinkedHashMap<>();

        boolean fetchedOk = false;

        try {
            // Fetch routines from db
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
                    routinesContainer.setSpacing(10); // Spacing between routines on the same weekday
                    HBox.setHgrow(routinesContainer, Priority.ALWAYS); // Make routine row fill up available space
                    splitBox.getChildren().addAll(weekdayLabel, routinesContainer);

                    for (Routine routine : routines) {
                        HBox routineRow = createRoutineRow(routine);
                        splitBox.getChildren().add(routineRow);
                        routinesContainer.getChildren().add(routineRow);
                    }
                    routineVBox.getChildren().add(splitBox);
                    routineVBox.setSpacing(20);  // Spacing between weekday groups

                    // Separate days with a line
                    /*
                    if (!weekday.equals(weekdays.get(map.keySet().size()-1).getName())) { //
                        Separator s = new Separator(Orientation.HORIZONTAL);
                        s.setStyle("-fx-border-width: 1px; -fx-padding: 0;");
                        routineVBox.getChildren().add(s);
                    }
                     */
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
        Image cancel = new Image("images/cancel-svgrepo-com.png");
        editView.setImage(edit);
        editView.setPreserveRatio(true);
        editView.setFitHeight(ICON_DIMENSIONS);
        saveView.setImage(save);
        saveView.setPreserveRatio(true);
        saveView.setFitHeight(ICON_DIMENSIONS);
        editButton.setGraphic(editView);

        // Automation toggle
        ToggleSwitch toggle = getToggleSwich(routine);

        // Container for start and end times
        GridPane clockTimes = new GridPane();
        clockTimes.add(startTime, 0, 0);
        clockTimes.add(new Label("-"), 1, 0);
        clockTimes.add(endTime, 2, 0);

        HBox timeContainer = new HBox(clockTimes); // Wrapper box
        timeContainer.setAlignment(Pos.CENTER);
        HBox.setHgrow(timeContainer, Priority.ALWAYS);
        clockTimes.setHgap(5);

        // Time pickers (editing mode)
        TimePicker newStartTime = (new TimeSelector()).getTimePicker();
        TimePicker newEndTime = (new TimeSelector()).getTimePicker();
        newStartTime.setTime(routine.getEventTime().getStartTime().toLocalTime());
        newEndTime.setTime(routine.getEventTime().getEndTime().toLocalTime());

        // Grid containing time pickers (editing mode)
        GridPane newClockTimes = new TimeSelectorGrid(newStartTime, newEndTime).getGrid();

        // Store time pickers in an array
        List<TimePicker> editingTimePickers = Arrays.asList(newStartTime, newEndTime);

        // Change listeners for time pickers (editing mode)
        for (TimePicker timePicker : editingTimePickers) {
            timePicker.timeProperty().addListener((observable, oldValue, newValue) -> {
                editButton.setDisable(!validateTimeInput(newStartTime, newEndTime));
            });
        }

        // Buttons
        Button deleteButton = new Button();
        ImageView deleteView = new ImageView(delete);
        deleteView.setPreserveRatio(true);
        deleteView.setFitHeight(ICON_DIMENSIONS);
        deleteButton.setGraphic(deleteView);
        deleteButton.setOnAction(deleteRoutine);
        deleteButtons.put(deleteButton, routine);
        deleteButton.setVisible(false);

        // Cancel button
        Button cancelButton = new Button();
        ImageView cancelView = new ImageView(cancel);
        cancelView.setPreserveRatio(true);
        cancelView.setFitHeight(ICON_DIMENSIONS);
        cancelButton.setGraphic(cancelView);
        cancelButton.setVisible(false);
        cancelButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("cancel clicked");
                deleteButton.setVisible(!deleteButton.isVisible());
                cancelButton.setVisible(!cancelButton.isVisible());
                timeContainer.getChildren().clear();
                editButton.setGraphic(editView);
                timeContainer.getChildren().add(clockTimes);
            }
        });

        // When edit/save is clicked
        editButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                deleteButton.setVisible(!deleteButton.isVisible());
                cancelButton.setVisible(!cancelButton.isVisible());
                timeContainer.getChildren().clear();

                if (deleteButton.isVisible()) {
                    // Switch out of editing mode
                    editButton.setGraphic(saveView);
                    timeContainer.getChildren().add(newClockTimes);
                } else {
                    // Switch into editing mode
                    editButton.setGraphic(editView);
                    timeContainer.getChildren().add(clockTimes);
                }
            }
        });

        HBox routineRow = new HBox(10, timeContainer, toggle, cancelButton, deleteButton, editButton);
        HBox.setHgrow(routineRow, Priority.ALWAYS);
        routineRow.setStyle("-fx-background-color: #e1e1e1; -fx-background-radius: 4;");

        Label[] labels = new Label[] {startTime, endTime};
        for (Label label : labels) {
            label.setFont(new Font(FONT, FONT_SIZE_TEXT));
        }

        routineRow.setPadding(new Insets(10));
        routineRow.setAlignment(Pos.CENTER_RIGHT);
        return routineRow;
    }

    // Fetches routines from the database
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

    @FXML
    private void hideForm() {
        addRoutineForm.setVisible(false);
        addRoutineForm.setManaged(false);
        addRoutineButton.setVisible(true);
        noRoutinesText.setVisible(noRoutinesToShow);
    }

    @FXML
    private void showForm() {
        addRoutineForm.setVisible(true);
        addRoutineForm.setManaged(true);
        addRoutineButton.setVisible(false);
        noRoutinesText.setVisible(!noRoutinesText.isVisible());

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
                     Save the routine to the database.
                     Feature is null for now, routines added by users are automatically on.
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

    private void initializeForm() {
        addRoutineForm.setPadding(new Insets(10));
        addRoutineForm.setStyle("-fx-border-color: black; -fx-border-radius: 10;");
        formTitle.setText("Add a custom routine for " + cache.getDevice().getName());

        // Weekday checkboxes
        for (Weekday weekday : weekdayDAO.getAll()) {
            weekdayCheckBoxes.put(weekday, new CheckBox(weekday.getName()));
        }
        VBox weekdaysVBox = new VBox();
        weekdaysVBox.getChildren().addAll(weekdayCheckBoxes.values());
        weekdaysVBox.setSpacing(5);

        // Time pickers
        startTimePicker = (new TimeSelector()).getTimePicker();
        endTimePicker = (new TimeSelector()).getTimePicker();
        GridPane clockTimes = new TimeSelectorGrid(startTimePicker, endTimePicker).getGrid();
        clockTimes.add(weekdaysVBox, 1, 2);
        formGrid.add(clockTimes, 0,0);

        // Tooltip
        weekdayTooltip = new Label("Select at least one day of the week");
        weekdayTooltip.setStyle("-fx-text-fill: #5E5E5E; -fx-font-family: Verdana; -fx-font-style: italic; -fx-font-size: 11px;");
        clockTimes.add(weekdayTooltip, 1, 3);

        // Prevent saving a routine where the start and end times are the same (default values)
        saveButton.setDisable(true);

        List<TimePicker> timePickers = Arrays.asList(startTimePicker, endTimePicker);

        // Change listeners for time pickers
        for (TimePicker timePicker : timePickers) {
            timePicker.timeProperty().addListener((observable, oldValue, newValue) -> {
                checkSaveButtonState();
            });
        }

        // Change listeners for weekday checkboxes
        for (Map.Entry<Weekday, CheckBox> set : weekdayCheckBoxes.entrySet()) {
            set.getValue().selectedProperty().addListener((observable, oldValue, newValue) -> {
                checkSaveButtonState();
            });
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

    // Checks if the values of two time pickers are in successive order
    private boolean validateTimeInput(TimePicker startTime, TimePicker endTime) {
        boolean timeInputOk = util.compareTimes(startTime.getTime(), endTime.getTime());

        if (timeInputOk) {
            startTime.getStyleClass().remove("inputErrorState");
            endTime.getStyleClass().remove("inputErrorState");
        } else {
            startTime.getStyleClass().add("inputErrorState");
            endTime.getStyleClass().add("inputErrorState");
        }

        return timeInputOk;
    }

    // Returns true if at least one checkbox is selected
    private boolean validateWeekdaySelection() {
        validateTimeInput(startTimePicker, endTimePicker);
        for (Map.Entry<Weekday, CheckBox> set : weekdayCheckBoxes.entrySet()) {
            if (set.getValue().isSelected()) {
                weekdayTooltip.setVisible(false);
                return true;
            }
        }
        weekdayTooltip.setVisible(true);
        return false;
    }

    // Controls the clickability of the save button depending on input validations
    private void checkSaveButtonState() {
        boolean weekdaySelectionOk = validateWeekdaySelection();
        boolean timeInputOk = validateTimeInput(startTimePicker, endTimePicker);
        saveButton.setDisable(!timeInputOk || !weekdaySelectionOk);
    }
}