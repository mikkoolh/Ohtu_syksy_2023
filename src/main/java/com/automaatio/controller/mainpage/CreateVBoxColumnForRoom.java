package com.automaatio.controller.mainpage;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import com.automaatio.model.database.DeviceGroupDAO;
import com.automaatio.utils.CacheSingleton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;


public class CreateVBoxColumnForRoom {
    private DeviceDAO deviceDAO = new DeviceDAO();
    private DeviceGroupDAO deviceGroupDAO = new DeviceGroupDAO();
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private Button deleteBtn, editBtn;
    private ToggleButton onOff;
    private Label label;

    private final int btnWidth = 50, vBoxSpacing = 10, hBoxSpacing = 20;
    private final String editTxt = "Edit", deleteTxt = "Delete", onTxt = "On", offTxt = "Off";

    public VBox create(Device device, VBox devicesVBox, ClickActions editAction) {

        label = createLabel(device);
        editBtn = createEditBtn(device, editAction);

        VBox newDeviceVBox = new VBox(vBoxSpacing);

        deleteBtn = createDeleteBtn(devicesVBox, newDeviceVBox, device);
        onOff = createToggleBtn(device);

        Pane spacer = new Pane();

        //muuttaa yksittäisen HBoxin leveyden scrollpanen kokoiseksi laiskalla tavalla
        spacer.setPrefWidth(75);

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
        setOnOff(deviceDAO.getObject(device.getDeviceID()).isOnOff(), device, toggleButton);
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
            deviceGroupDAO.removeDeviceFromGroup(cache.getRoom(), device);
            devicesVBox.getChildren().remove(newDeviceVBox);
        });
        return delete;
    }

    private Label createLabel(Device device) {
        Label deviceLabel = new Label(device.getName());
        deviceLabel.getStyleClass().add("deviceLabel");
        return deviceLabel;
    }

    private Button createEditBtn(Device device, ClickActions edit){
        Button editBtn = new Button(editTxt);
        editBtn.getStyleClass().add("editBtn");
        editBtn.setOnAction(event -> edit.onEditClick(device));
        return editBtn;
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
}

