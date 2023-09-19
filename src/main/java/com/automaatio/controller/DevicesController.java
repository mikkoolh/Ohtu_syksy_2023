package com.automaatio.controller;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;

/**
 * Controller for managing devices within the application's UI
 *
 * @Author Elmo Erla
 *
 */
public class DevicesController {
    DeviceDAO dao = new DeviceDAO();

    @FXML
    private TextField deviceNameField;

    /**
     * Handles the addition of a new device.
     *
     * @param event the action event triggering the method.
     */
    @FXML
    public void addDevice(ActionEvent event) {
        String deviceName = deviceNameField.getText();
        if (deviceName == null || deviceName.trim().isEmpty()) {
            System.out.println("virhe nimeä antaessa");
            return;
        }

        Device device = new Device();
        device.setName(deviceName);

        device.setOnOff(true);
        device.setAutomation(false);
        device.setUsageData(0);
        device.setModelCode(null);

        dao.addDevice(device);
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

    /**
     * Creates a UI row for a given device.
     *
     * @param device the device for which the row is created.
     * @return an HBox containing the device details and associated buttons.
     */
    private HBox createDeviceRow(Device device) {
        Label deviceLabel = new Label(device.getName());

        Button editButton = new Button("Edit");
        editButton.setOnAction(event -> editDevice(device));

        HBox deviceRow = new HBox(10);

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(event -> {
            dao.deleteDevice(device.getDeviceID());
            devicesVBox.getChildren().remove(deviceRow);
        });

        deviceRow.getChildren().addAll(deviceLabel, editButton, deleteButton);

        return deviceRow;
    }

    /**
     * Method for editing a device.
     *
     * @param device the device to be edited.
     */
    private void editDevice(Device device) {

    }

    /**
     * Initializes the controller by populating devices on UI load.
     */
    public void initialize() {
        showDevices();
    }


    /**
     * Handles the click event of the "Rooms" titled pane.
     * Switches the scene to the main view.
     *
     * @param mouseEvent the mouse event triggering the method.
     * @throws IOException if there's an error loading the main.fxml resource.
     */
    @FXML
    private void handleRoomsTitledPaneClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        Stage stage = (Stage)((Node)mouseEvent.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
