package com.automaatio.controller.mainpage;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class CreateDeviceRow {
    private CacheSingleton cache = CacheSingleton.getInstance();
    private DeviceDAO deviceDAO = new DeviceDAO();
    private Pane mainPane = cache.getMainPane();
    private Button deleteBtn, editBtn;
    private ToggleButton onOff;
    private Label label;

    private final int btnWidth = 50, vBoxSpacing = 10, hBoxSpacing = 20;
    private final String editTxt = "Edit", deleteTxt = "Delete", onTxt = "On", offTxt = "Off";

        public VBox create(Device device, VBox devicesVBox) {

        label = createLabel(device);
        editBtn = createEditBtn(device);

        VBox newDeviceVBox = new VBox(vBoxSpacing);

        deleteBtn = createDeleteBtn(devicesVBox, newDeviceVBox, device);
        onOff = createToggleBtn(device);

        Pane spacer = new Pane();
        HBox buttonsRow = new HBox(hBoxSpacing);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        buttonsRow.getChildren().addAll(editBtn, deleteBtn, spacer, onOff);
        buttonsRow.setAlignment(Pos.TOP_LEFT);

        newDeviceVBox.getStyleClass().add("deviceRowVBox");
        newDeviceVBox.getChildren().addAll(label, buttonsRow);

        return newDeviceVBox;
    }

    private ToggleButton createToggleBtn(Device device) {
        ToggleButton toggleButton = new ToggleButton();
        toggleButton.setPrefWidth(btnWidth);
        setOnOff(deviceDAO.getDevice(device.getDeviceID()).isOnOff(), device, toggleButton);
        toggleButton.getStyleClass().add("toggleBtn");

        toggleButton.selectedProperty().addListener((obs, wasSelected, isSelected) -> {
            setOnOff(isSelected,device, toggleButton);
        });
        return toggleButton;
    }

    private Button createDeleteBtn(VBox devicesVBox, VBox newDeviceVBox, Device device) {
        Button delete = new Button("Delete");
        delete.getStyleClass().add("deleteBtn");
        delete.setOnAction(event -> {
            deviceDAO.deleteDevice(device.getDeviceID());
            devicesVBox.getChildren().remove(newDeviceVBox);
        });
        return delete;
    }

    private Label createLabel(Device device) {
        Label deviceLabel = new Label(device.getName());
        deviceLabel.getStyleClass().add("deviceLabel");
        return deviceLabel;
    }

    private Button createEditBtn(Device device){
        Button edit = new Button(editTxt);
        edit.getStyleClass().add("editBtn");
        edit.setOnAction(event -> editDevice(device));
        return edit;
    }

    private void setOnOff(boolean isSelected, Device device, ToggleButton onOff){
        if (isSelected) {
            switchOnOff(device);
            onOff.setText("On");
            onOff.getStyleClass().remove("toggleBtnOff");
            onOff.getStyleClass().add("toggleBtnOn");
        } else {
            switchOnOff(device);
            onOff.setText("Off");
            onOff.getStyleClass().remove("toggleBtnOn");
            onOff.getStyleClass().add("toggleBtnOff");
        }
    }

    public void switchOnOff(Device device) {
        deviceDAO.updateDeviceOnOff(device.getDeviceID());
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
}

