package com.automaatio.components;

import com.automaatio.components.buttons.DeleteButtonCreator;
import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.DeviceGroup;
import com.automaatio.utils.CacheSingleton;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class CreateRoomsVBoxColumn {
    private CacheSingleton cache = CacheSingleton.getInstance();

    private Button openButton;

    private Label label;

    private final int vBoxSpacing = 10, hBoxSpacing = 20;

    private final String openText = "Open";

    public VBox create(DeviceGroup deviceGroup, ClickActions openAction) {
        label = createLabel(deviceGroup);
        openButton = createOpenButton(deviceGroup, openAction);
        VBox newDeviceGroupVBox = new VBox(vBoxSpacing);

        Pane spacer = new Pane();

        //muuttaa yksittäisen HBoxin leveyden scrollpanen kokoiseksi laiskalla tavalla
        spacer.setPrefWidth(220);

        HBox buttonsRow = new HBox(hBoxSpacing);
        HBox.setHgrow(spacer, Priority.ALWAYS);

        buttonsRow.getChildren().addAll(openButton, spacer);
        buttonsRow.setAlignment(Pos.TOP_LEFT);

        newDeviceGroupVBox.getStyleClass().add("deviceRowVBox");
        newDeviceGroupVBox.getChildren().addAll(label, buttonsRow);

        return newDeviceGroupVBox;
    }

    private Label createLabel(DeviceGroup deviceGroup) {
        Label deviceGroupLabel = new Label(deviceGroup.getName());
        deviceGroupLabel.getStyleClass().add("deviceLabel");
        return deviceGroupLabel;
    }

    private Button createOpenButton(DeviceGroup deviceGroup, ClickActions open) {
        Button openButton = new Button(openText);
        openButton.getStyleClass().add("editBtn");
        openButton.setOnAction(event -> open.onExpandClick(deviceGroup));

        return openButton;
    }
}

