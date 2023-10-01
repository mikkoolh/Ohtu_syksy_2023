package com.automaatio.controller.mainpage;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.effect.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class CreateDeviceRow {
    private CacheSingleton cache = CacheSingleton.getInstance();
    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private DeviceDAO deviceDAO = new DeviceDAO();
    private Pane mainPane = cache.getMainPane();
    public VBox create(Device device, VBox devicesVBox, boolean edit) {
        Label deviceLabel = new Label(device.getName());
        deviceLabel.setTextFill(Color.web("#070707"));
        deviceLabel.setFont(new Font(30));

        Button editButton = new Button("Edit");
        editButton.setStyle("-fx-background-color: #344347; -fx-text-fill: white;");
        editButton.setOnAction(event -> editDevice(device));


        VBox deviceRow = new VBox(10);
        deviceRow.setStyle("-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #353535;");


        Button deleteButton = new Button("Delete");
        deleteButton.setStyle("-fx-background-color: #344347; -fx-text-fill: white;");
        deleteButton.setOnAction(event -> {
            deviceGroupDAO.removeDeviceFromGroup(cache.getRoom(), device);
            devicesVBox.getChildren().remove(deviceRow);
        });

        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setPrefWidth(50);
        setOnOff(deviceDAO.getDevice(device.getDeviceID()).isOnOff(), device, toggleButton);
        toggleButton.setStyle("-fx-background-color: #353535; -fx-text-fill: white;");

        toggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            setOnOff(isSelected,device, toggleButton);
        });

        Pane spacer = new Pane();

        HBox buttonsRow = new HBox(20);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        if(edit){
            buttonsRow.getChildren().addAll(editButton, deleteButton, spacer, toggleButton);
        } else {
            buttonsRow.getChildren().addAll(deleteButton, spacer, toggleButton);
        }
        buttonsRow.setAlignment(Pos.TOP_LEFT);

        deviceRow.setStyle("-fx-border-width: 2;" + "-fx-padding: 5;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #353535;");
        deviceRow.getChildren().addAll(deviceLabel, buttonsRow);

        return deviceRow;
    }

    private void setOnOff(boolean isSelected, Device device, ToggleButton toggleButton){
        if (isSelected) {
            switchOnOff(device);
            toggleButton.setText("On");
            toggleButton.setStyle("-fx-background-color: #fff2b3; -fx-text-fill: #000000; -fx-effect: dropshadow(three-pass-box, rgb(0,0,0), 10, 0, 0, 0);");
        } else {
            switchOnOff(device);
            toggleButton.setText("Off");
            toggleButton.setStyle("-fx-background-color: #353535; -fx-text-fill: white;");
        }
    }

    public void switchOnOff(Device device) {
        deviceDAO.updateDeviceOnOff(device.getDeviceID());

    }

    private void editDevice(Device device) {
        System.out.println("show device\n");
        cache.setDevice(device);
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/device.fxml"));
            Parent newView = loader.load();
            mainPane.getChildren().clear();
            mainPane.getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

