package com.automaatio.controller.mainpage.menu;

import com.automaatio.controller.mainpage.CreateVBoxColumn;
import com.automaatio.controller.mainpage.clickActions.RoutinesClick;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoutinesController implements Initializable {

    private final CacheSingleton cache = CacheSingleton.getInstance();

    private final CreateVBoxColumn deviceRow = new CreateVBoxColumn();

    private Pane mainPane;

    @FXML
    private VBox routinesVBox;

    public void showRoutines() {
        routinesVBox.getChildren().clear();
        DeviceDAO deviceDAO = new DeviceDAO();
        List<Device> devices = deviceDAO.getAutoDevices();

        // Testejä varten
        //List<Device> devices = deviceDAO.getAll();

        for (Device device : devices) {
            routinesVBox.getChildren().add(deviceRow.create(device, routinesVBox, new RoutinesClick()));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane = cache.getMainPane();
        showRoutines();
    }
}