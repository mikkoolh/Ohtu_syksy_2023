package com.automaatio.components;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceGroup;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.*;


public class CreateVBoxColumn {

    private Button deleteBtn, expandBtn;
    private ToggleButton onOff;
    private Label label;
    private final String openText = "Open";
    private final int vBoxSpacing = 10, hBoxSpacing = 20;
    private final String editTxt = "Edit";

        public VBox create(Object object, VBox mainVBox, ClickActions clickActions) {

        label = createLabel(object.toString());

        if(object instanceof Device){
            expandBtn = new EditButtonCreator().create(object, clickActions);
            onOff = new ToggleButtonCreator().create(object, clickActions);
        } else if (object instanceof DeviceGroup){
            expandBtn = createOpenButton(((DeviceGroup) object), clickActions);
        }

        VBox newDeviceVBox = new VBox(vBoxSpacing);
        deleteBtn = new DeleteButtonCreator(mainVBox, newDeviceVBox).create(object, clickActions);

        HBox buttonsRow = new HBox(hBoxSpacing);

        buttonsRow.getChildren().addAll(expandBtn, deleteBtn, paneBuilder(75));
        HBox.setHgrow(buttonsRow.getChildren().get(buttonsRow.getChildren().size()-1), Priority.ALWAYS);

        if (object instanceof Device){
            buttonsRow.getChildren().add(onOff);
        } else if (object instanceof DeviceGroup) {
            buttonsRow.getChildren().add(paneBuilder(50));
        }
            buttonsRow.setAlignment(Pos.TOP_LEFT);

        newDeviceVBox.getStyleClass().add("deviceRowVBox");
        newDeviceVBox.getChildren().addAll(label, buttonsRow);

        return newDeviceVBox;
    }

    private Label createLabel(String name) {
        Label deviceLabel = new Label(name);
        deviceLabel.getStyleClass().add("deviceLabel");
        return deviceLabel;
    }

    private Button createOpenButton(DeviceGroup deviceGroup, ClickActions open) {
        Button openButton = new Button(openText);
        openButton.getStyleClass().add("expandBtn");
        openButton.setOnAction(event -> open.onExpandClick(deviceGroup));
        return openButton;
    }

    private Pane paneBuilder(double width){
            Pane temp = new Pane();
            temp.setPrefWidth(width);
            return temp;
    }
}

