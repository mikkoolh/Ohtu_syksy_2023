package com.automaatio.components;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import javafx.scene.control.Button;

public class EditButtonCreator implements IButton{
    private String editTxt = "Edit";
    @Override
    public Button create(Object objectToDelete, ClickActions clickActions) {
        Button editBtn = new Button(editTxt);
        editBtn.getStyleClass().add("editBtn");
        editBtn.setOnAction(event -> clickActions.onExpandClick(objectToDelete));
        return editBtn;
    }
}
