package com.automaatio.controller.mainpage.menu;

import com.automaatio.controller.mainpage.CreateRoomsVBoxColumn;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class RoomsController implements Initializable, Menu {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    @FXML
    private TextField newRoomTextField;
    private final CreateRoomsVBoxColumn deviceGroupRow = new CreateRoomsVBoxColumn();

    @FXML
    private VBox roomsVBox;
    private Pane mainPane;

    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane = cache.getMainPane();
        show();
    }

    public void show() {
        roomsVBox.getChildren().clear();
        List<DeviceGroup> deviceGroups = deviceGroupDAO.getRoomsByUser(cache.getUser());
        for (DeviceGroup deviceGroup : deviceGroups) {
            roomsVBox.getChildren().add(deviceGroupRow.create(deviceGroup));
        }
    }

    @FXML
    private void onAddGroupClick() {
        String deviceGroupName = newRoomTextField.getText();
        if (deviceGroupName == null || deviceGroupName.trim().isEmpty()) {
            System.out.println("virhe nimeä antaessa");
            return;
        }
        DeviceGroup newRoom = new DeviceGroup(newRoomTextField.getText(), cache.getUser());
        deviceGroupDAO.addObject(newRoom);
        newRoomTextField.clear();
        System.out.println(newRoom);
        System.out.println("lisätty " + newRoom.getName() + " tietokantaan");
        show();
    }
}