package com.automaatio.controller.mainpage.menu;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DevicesController implements Initializable {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    DeviceDAO dao = new DeviceDAO();


    private Pane mainPane;

    @FXML
    private TextField deviceNameField;

    @FXML
    public void addDevice(ActionEvent event) {
        String deviceName = deviceNameField.getText();
        if (deviceName == null || deviceName.trim().isEmpty()) {
            System.out.println("virhe nimeä antaessa");
            return;
        }

        Device device = new Device(0, deviceName, "01", null);

        dao.addDevice(device);
        System.out.println(device);
        deviceNameField.clear();
        System.out.println(device.getName() + ", id: " + device.getDeviceID());
        showDevices();
    }

    @FXML
    private VBox devicesVBox;

    /**
     * Populates the VBox with device rows fetched from the database.
     */
    public void showDevices() {
        devicesVBox.getChildren().clear();

        List<Device> devices = dao.getAll();
        for (Device device : devices) {
            devicesVBox.getChildren().add(createDeviceRow(device));
        }
    }

    private VBox createDeviceRow(Device device) {
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
            dao.deleteDevice(device.getDeviceID());
            devicesVBox.getChildren().remove(deviceRow);
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

        deviceRow.setStyle("-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #353535;");
        deviceRow.getChildren().addAll(deviceLabel, buttonsRow);

        return deviceRow;
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane = cache.getMainPane();
        showDevices();
    }
}
