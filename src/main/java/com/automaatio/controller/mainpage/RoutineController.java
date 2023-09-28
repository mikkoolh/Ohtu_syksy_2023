package com.automaatio.controller.mainpage;

import com.automaatio.model.database.Routine;
import com.automaatio.model.database.RoutineDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        routineNameField.setText(cache.getDevice().getName());
        loadRoutines();
    }

    private void loadRoutines() {
        int id = cache.getDevice().getDeviceID();

        List<Routine> routines = routineDAO.getRoutinesByDeviceId(id);

        for (Routine routine : routines) {
            String routineName = routine.getUser().getEmail();
            HBox routineRow = createRoutineRow(routineName);
            routineVBox.getChildren().add(routineRow);
        }
    }

    private HBox createRoutineRow(String routineName) {
        Label nameLabel = new Label(routineName);

        Button editButton = new Button("Edit");

        HBox routineRow = new HBox(10);
        routineRow.getChildren().addAll(nameLabel, editButton);

        return routineRow;
    }
}
