package com.automaatio.controller.mainpage.menu;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoutinesController implements Initializable {

    private final CacheSingleton cache = CacheSingleton.getInstance();

    private Pane mainPane;

    @FXML
    private VBox routinesVBox;

    @FXML
    private TextField routineNameField;

    public void showRoutines() {
        routinesVBox.getChildren().clear();
        DeviceDAO deviceDAO = new DeviceDAO();
        //List<Device> devices = deviceDAO.getAutoDevices();

        // Testejä varten
        List<Device> devices = deviceDAO.getAll();

        for (Device d : devices) {
            routinesVBox.getChildren().add(createRoutineRow(d));
        }
    }

    private VBox createRoutineRow(Device d) {
        Label routineLabel = new Label(d.getName());
        routineLabel.setTextFill(Color.web("#070707"));
        routineLabel.setFont(new Font(30));

        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-background-color: #344347; -fx-text-fill: white;");

        editButton.setOnAction(event -> onEditClick(d));

        // Handle edit button action here

        VBox routineRow = new VBox(10);
        routineRow.setStyle("-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #353535;");

        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #344347; -fx-text-fill: white;");
        deleteButton.setOnAction(event -> {
            // Handle delete action here
            routinesVBox.getChildren().remove(routineRow);
        });

        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setText("Off");
        toggleButton.setStyle("-fx-background-color: #353535; -fx-text-fill: white;");
        toggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            if (isSelected) {
                toggleButton.setText("On");
                toggleButton.setStyle("-fx-background-color: #344347; -fx-text-fill: white;");
            } else {
                toggleButton.setText("Off");
                toggleButton.setStyle("-fx-background-color: #353535; -fx-text-fill: white;");
            }
        });

        Pane spacer = new Pane();
        HBox buttonsRow = new HBox(20);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        buttonsRow.getChildren().addAll(editButton, deleteButton, spacer, toggleButton);
        buttonsRow.setAlignment(Pos.TOP_LEFT);

        routineRow.setStyle("-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #353535;");
        routineRow.getChildren().addAll(routineLabel, buttonsRow);

        return routineRow;
    }

    private void onEditClick(Device d) {
        System.out.println("show routine\n");
        cache.setDevice(d);
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/routine.fxml"));
            Parent newView = loader.load();
            mainPane.getChildren().clear();
            mainPane.getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane = cache.getMainPane();
        showRoutines();
    }
}
