package com.automaatio.controller.mainpage.menu;

import com.automaatio.controller.mainpage.CreateDeviceRow;
import com.automaatio.controller.mainpage.RoutinesClick;
import com.automaatio.controller.mainpage.Updateable;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoutinesController implements Initializable, Updateable {

    private final CacheSingleton cache = CacheSingleton.getInstance();

    private Pane mainPane;
    private CreateDeviceRow deviceRow;


    @FXML
    private VBox routinesVBox;

    @FXML
    private TextField routineNameField;

    public void showRoutines() {
        routinesVBox.getChildren().clear();
        DeviceDAO deviceDAO = new DeviceDAO();
        List<Device> devices = deviceDAO.getAutoDevices();

        // Testejä varten
        //List<Device> devices = deviceDAO.getAll();

        for (Device device : devices) {
            deviceRow.create(device, routinesVBox, new RoutinesClick());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane = cache.getMainPane();
        deviceRow = new CreateDeviceRow();
        showRoutines();
    }

    @Override
    public void update() {
        showRoutines();
    }
}
