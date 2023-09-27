package com.automaatio.controller.mainpage;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


/**
 * @author Elmo Erla
 */
public class RoomController implements Initializable {
    @FXML
    private Text roomName;
    @FXML
    private ComboBox<Device> deviceComboBox;
    @FXML
    private VBox devicesVBox;

    private final CacheSingleton cache = CacheSingleton.getInstance();
    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private DeviceDAO deviceDAO = new DeviceDAO();

    public RoomController() {}
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CacheSingleton cache = CacheSingleton.getInstance();
        roomName.setText(cache.getRoom().getName());

        populateDevicesDropdown();

        deviceComboBox.setOnAction(event -> {
            Device selectedDevice = deviceComboBox.getSelectionModel().getSelectedItem();
            if (selectedDevice != null) {
                deviceDAO.updateDeviceGroup(selectedDevice.getDeviceID(), cache.getRoom().getDeviceGroupId());
                showDevices();
            }
        });

        deviceComboBox.setPromptText("Select your device to be added");

        showDevices();
    }

    private void populateDevicesDropdown() {
        String currentUser = cache.getUser().getUsername();
        List<Device> devices = deviceGroupDAO.getDevicesNotInGroup(cache.getRoom().getDeviceGroupId(), currentUser);
        deviceComboBox.setItems(FXCollections.observableArrayList(devices));

        deviceComboBox.setCellFactory((comboBox) -> {
            return new ListCell<Device>() {
                @Override
                protected void updateItem(Device item, boolean empty) {
                    super.updateItem(item, empty);
                    if (item == null || empty) {
                        setText(null);
                    } else {
                        setText(item.getName());
                    }
                }
            };
        });

        deviceComboBox.setConverter(new StringConverter<Device>() {
            @Override
            public String toString(Device device) {
                if (device == null) {
                    return null;
                } else {
                    return device.getName();
                }
            }
            @Override
            public Device fromString(String s) {
                return null;
            }
        });
    }

    public void showDevices() {
        devicesVBox.getChildren().clear();
        List<Device> devices = deviceGroupDAO.getDevicesByRoom(cache.getRoom());
        for (Device device : devices) {
            devicesVBox.getChildren().add(createDeviceRow(device));
        }
    }

    private VBox createDeviceRow(Device device) {
        Label deviceLabel = new Label(device.getName());
        deviceLabel.setTextFill(Color.web("#070707"));
        deviceLabel.setFont(new Font(30));

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

        buttonsRow.getChildren().addAll(deleteButton, spacer, toggleButton);
        buttonsRow.setAlignment(Pos.TOP_LEFT);

        deviceRow.setStyle("-fx-border-width: 2;" +
                "-fx-border-insets: 5;" +
                "-fx-border-radius: 5;" +
                "-fx-border-color: #353535;");
        deviceRow.getChildren().addAll(deviceLabel, buttonsRow);

        return deviceRow;
    }
}
