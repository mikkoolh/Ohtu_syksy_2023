package com.automaatio.controller.mainpage;

import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class RoutineController implements Initializable {

    @FXML
    private TextArea routineNameField;

    public RoutineController() {}

    CacheSingleton cache = CacheSingleton.getInstance();

    @FXML
    private VBox routineVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        routineNameField.setText(cache.getDevice().getName());
        CacheSingleton cache = CacheSingleton.getInstance();
        List<String> routines = new ArrayList<String>();

        routines.add("Rutiini1");
        routines.add("Rutiini2");
        routines.add("Rutiini3");

        for (String s : routines) {
            HBox routineRow = createRoutineRow(s);
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
