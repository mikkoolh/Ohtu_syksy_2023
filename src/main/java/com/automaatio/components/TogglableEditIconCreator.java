package com.automaatio.components;

import javafx.scene.control.Button;

/**
 * @author Matleena Kankaanpää
 * 7.10.2023
 *
 */

public class TogglableEditIconCreator extends TogglableIconCreator {
    public Button create() {
        super.create("images/edit.png", "images/save.png", "Edit", "Save");
        int icon_size = 18; // Icon dimensions
        defaultView.setFitHeight(icon_size);
        altView.setFitHeight(icon_size);
        //button.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
        return button;
    }
}
