package com.automaatio.controller.mainpage;

import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.RoutineUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.controlsfx.control.ToggleSwitch;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class RoutineController implements Initializable {

    @FXML
    private TextArea routineNameField;

    public RoutineController() {}

    CacheSingleton cache = CacheSingleton.getInstance();

    RoutineDAO routineDAO = new RoutineDAO();

    @FXML
    private VBox routineVBox;

    @FXML
    private Button automateAllBtn;

    @FXML
    private Text noRoutinesText, routineErrorText;

    @FXML
    private ScrollPane routineScrollPane;

    private List<Routine> routines;
    private final int ID = cache.getDevice().getDeviceID();

    private Map<Routine, ToggleSwitch> toggleSwitches = new HashMap<>();
    private Map<Button, Routine> deleteButtons = new HashMap<>();
    private RoutineUtils util = new RoutineUtils();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        routines = util.sortByWeekday(util.sortByTime(fetchRoutines())); // Sort fetched routines by time
        routineNameField.setText(cache.getDevice().getName());
        loadRoutines(routines);
        updateUI();
        routineScrollPane.setStyle("-fx-background-color:transparent;");
    }

    private void loadRoutines(List<Routine> r) {
        routineVBox.getChildren().clear();

        if (r.isEmpty()) {
            routineVBox.setAlignment(Pos.CENTER);
            routineVBox.getChildren().add(noRoutinesText);
        } else {
            routineVBox.setAlignment(Pos.TOP_LEFT);
            routineVBox.getChildren().remove(noRoutinesText);

            for (Routine routine : r) {
                String routineName = routine.getUser().getEmail();
                HBox routineRow = createRoutineRow(routine);
                routineVBox.getChildren().add(routineRow);
            }
        }
    }


    private HBox createRoutineRow(Routine routine) {
        Label nameLabel = new Label("Routine " + routine.getRoutineID());
        Label startTime = new Label(getFormattedTime(routine.getEventTime().getStartTime()));
        Label endTime = new Label(getFormattedTime(routine.getEventTime().getEndTime()));
        Label weekday = new Label(routine.getEventTime().getWeekday().getName());
        Button editButton = new Button("Edit");
        ToggleSwitch toggle = getToggleSwich(routine);

        HBox timeContainer = new HBox(new Label(" - "), endTime, new Label(" - "), startTime, new Label(" - "), weekday);
        timeContainer.setAlignment(Pos.CENTER);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(deleteRoutine);
        deleteButtons.put(deleteButton, routine);

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

    private String getFormattedTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
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
            automateAllBtn.setText("Deselect all");

        } else {
            automateAllBtn.setText("Automate all ✨");
        }
    }

    @FXML
    private void addRoutine(ActionEvent actionEvent) {
        System.out.println("add routine");
    }

    private final EventHandler<ActionEvent> deleteRoutine = new EventHandler<>() {
        public void handle(ActionEvent event) {
            Routine routineToDelete = deleteButtons.get((Button) event.getTarget());

            try {
                routineDAO.deleteRoutine(routineToDelete.getRoutineID());
                routineErrorText.setText("");
            } catch(Exception e) {
                e.printStackTrace();
                routineErrorText.setText("An error occurred");
            }
            loadRoutines(fetchRoutines());
        }
    };
}