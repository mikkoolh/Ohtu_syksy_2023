package com.automaatio.controller.mainpage;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
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
    private CreateDeviceRow deviceRow = new CreateDeviceRow();

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
            devicesVBox.getChildren().add(deviceRow.create(device, devicesVBox, new DevicesClick()));
        }
    }

    public void updateOnOff(Device device){

    }
}
