package com.automaatio.controller.mainpage;

import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.controlsfx.control.ToggleSwitch;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

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

    private List<Routine> routines;
    private final int ID = cache.getDevice().getDeviceID();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        routines = fetchRoutines();
        routineNameField.setText(cache.getDevice().getName());
        loadRoutines();
        updateUI();
    }

    private void loadRoutines() {
        for (Routine routine : routines) {
            String routineName = routine.getUser().getEmail();
            HBox routineRow = createRoutineRow(routine);
            routineVBox.getChildren().add(routineRow);
        }
    }


    private HBox createRoutineRow(Routine routine) {
        Label nameLabel = new Label("Routine " + routine.getRoutineID());

        // Aloitus- ja lopetusajat HBoxissa
        Label startTime = new Label(getFormattedTime(routine.getEventTime().getStartTime()));
        Label endTime = new Label(getFormattedTime(routine.getEventTime().getEndTime()));
        HBox timeContainer = new HBox();
        timeContainer.getChildren().addAll(endTime, new Label(" - "), startTime);

        Label weekday = new Label(routine.getEventTime().getWeekday().getName());

        Button editButton = new Button("Edit");

        ToggleSwitch toggle = getToggleSwich(routine);

        HBox routineRow = new HBox(10);
        routineRow.getChildren().addAll(nameLabel, weekday, timeContainer, editButton, toggle);

        routineRow.setStyle("-fx-border-color: black;");

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
        return toggle;
    }

    public void automateAll(ActionEvent actionEvent) {
        System.out.println("switch all on/off");
    }

    // Returns true only if all routines are automated
    private boolean allAutomated() {
        for (Routine routine : fetchRoutines()) {
            if (!routine.getAutomated()) {
                return false;
            }
        }
        return true;
    }

    private void updateUI() {
        System.out.println("update ui");

        if (allAutomated()) {
            automateAllBtn.setText("Deselect all");

        } else {
            automateAllBtn.setText("Automate all ✨");
        }
    }
}
