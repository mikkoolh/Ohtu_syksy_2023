package com.automaatio.controller.mainpage.menu;

import com.automaatio.controller.mainpage.CreateDeviceRow;
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
    private CreateDeviceRow deviceRow = new CreateDeviceRow();
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

        Device device = new Device(0, deviceName, "01", null, cache.getUser().getUsername());

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
        List<Device> devices = dao.getDevicesByUserName(cache.getUser().getUsername());
        for (Device device : devices) {
            devicesVBox.getChildren().add(deviceRow.create(device, devicesVBox));
        }
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
