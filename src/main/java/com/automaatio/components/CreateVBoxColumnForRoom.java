package com.automaatio.components;

import com.automaatio.components.buttons.DeleteButtonCreator;
import com.automaatio.components.buttons.EditButtonCreator;
import com.automaatio.components.buttons.ToggleButtonCreator;
import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;


public class CreateVBoxColumnForRoom {
    private DeviceDAO deviceDAO = new DeviceDAO();
    private Button deleteBtn, editBtn;
    private ToggleButton toggleButton;
    private Label label;

    private final int vBoxSpacing = 10, hBoxSpacing = 20;

    public VBox create(Device device, VBox devicesVBox, ClickActions clickAction) {

        VBox newDeviceVBox = new VBox(vBoxSpacing);
        DeleteButtonCreator deleteButtonCreator = new DeleteButtonCreator(devicesVBox, newDeviceVBox);
        EditButtonCreator editButtonCreator = new EditButtonCreator();
        ToggleButtonCreator toggleButtonCreator= new ToggleButtonCreator();

        label = createLabel(device);
        editBtn = editButtonCreator.create(device, clickAction);

        deleteBtn = deleteButtonCreator.create(device, clickAction);
        toggleButton = toggleButtonCreator.create(device, clickAction);

        Pane spacer = new Pane();

        //muuttaa yksittäisen HBoxin leveyden scrollpanen kokoiseksi laiskalla tavalla
        spacer.setPrefWidth(550);

        HBox buttonsRow = new HBox(hBoxSpacing);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        buttonsRow.getChildren().addAll(editBtn, deleteBtn, spacer, toggleButton);
        buttonsRow.setAlignment(Pos.TOP_LEFT);

        newDeviceVBox.getStyleClass().add("deviceRowVBox");
        newDeviceVBox.getChildren().addAll(label, buttonsRow);

        return newDeviceVBox;
    }

    private Label createLabel(Device device) {
        Label deviceLabel = new Label(device.getName());
        deviceLabel.getStyleClass().add("deviceLabel");
        return deviceLabel;
    }
}

