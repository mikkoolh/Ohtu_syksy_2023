package com.automaatio.controller.mainpage;

import com.automaatio.model.database.Device;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;

import java.net.URL;
import java.util.ResourceBundle;

public class DeviceController implements Initializable {

    @FXML
    private TextArea deviceNameField;

    private Device device = CacheSingleton.getInstance().getDevice();

    public DeviceController() {}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CacheSingleton cache = CacheSingleton.getInstance();
        deviceNameField.setText(device.getName());
    }
}
