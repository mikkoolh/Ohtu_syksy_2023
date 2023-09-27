package com.automaatio.controller.mainpage;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * @author Elmo Erla
 *
 */
public class DeviceController implements Initializable {

    DeviceDAO dao = new DeviceDAO();
    DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();

    private final CacheSingleton cache = CacheSingleton.getInstance();

    @FXML
    private TextField deviceNameField;
    @FXML
    private Button changeNameButton;
    @FXML
    private TextField modelCode;
    @FXML
    private ComboBox<DeviceGroup> deviceGroup;
    @FXML
    private TextField usageData;

    private Device device = CacheSingleton.getInstance().getDevice();

    public DeviceController() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        deviceNameField.setText(device.getName());
        changeNameButton.setOnAction(e -> changeDeviceName());

        modelCode.setText(device.getModelCode());
        usageData.setText(Long.toString(device.getUsageData()));

        DeviceGroup currentDeviceGroup = device.getDeviceGroup();
        if (currentDeviceGroup != null) {
            deviceGroup.setPromptText(currentDeviceGroup.getName());
        }

        populateDeviceGroupComboBox();

        deviceGroup.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                deviceGroup.setPromptText(newValue.getName());

                int deviceId = device.getDeviceID();
                int newDeviceGroupId = newValue.getDeviceGroupId();

                dao.updateDeviceGroup(deviceId, newDeviceGroupId);
            }
        });
    }

    private void populateDeviceGroupComboBox() {
        List<DeviceGroup> rooms = deviceGroupDAO.getRoomsByUser(cache.getUser());
        deviceGroup.getItems().addAll(rooms);
        deviceGroup.setCellFactory((comboBox) -> {
            return new ListCell<DeviceGroup>() {
                @Override
                protected void updateItem(DeviceGroup deviceGroup, boolean empty) {
                    super.updateItem(deviceGroup, empty);
                    if (deviceGroup == null || empty) {
                        setText(null);
                    } else {
                        setText(deviceGroup.getName());
                    }
                }
            };
        });
    }

    private void changeDeviceName() {
        String newName = deviceNameField.getText();
        device.setName(newName);
        dao.updateDeviceName(device.getDeviceID(), newName);
    }
}
