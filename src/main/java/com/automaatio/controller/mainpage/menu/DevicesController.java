package com.automaatio.controller.mainpage.menu;

import com.automaatio.controller.mainpage.CreateDeviceRow;
import com.automaatio.controller.mainpage.DevicesClick;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DevicesController implements Initializable, Menu {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private CreateDeviceRow deviceRow = new CreateDeviceRow();
    DeviceDAO deviceDAO = new DeviceDAO();

    @FXML
    private TextField deviceNameField;
    @FXML
    private VBox devicesVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        show();
    }

    /**
     * Populates the VBox with device rows fetched from the database.
     */
    public void show() {
        devicesVBox.getChildren().clear();
        List<Device> devices = deviceDAO.getDevicesByUserName(cache.getUser().getUsername());
        for (Device device : devices) {
            devicesVBox.getChildren().add(deviceRow.create(device, devicesVBox, new DevicesClick()));
        }
    }

    @FXML
    public void add() {
        String deviceName = deviceNameField.getText();
        if (deviceName == null || deviceName.trim().isEmpty()) {
            System.out.println("virhe nimeä antaessa");
            return;
        }

        Device device = new Device(0, deviceName, "01", null, cache.getUser().getUsername());

        deviceDAO.addDevice(device);
        System.out.println(device);
        deviceNameField.clear();
        System.out.println(device.getName() + ", id: " + device.getDeviceID());
        show();
    }
}
