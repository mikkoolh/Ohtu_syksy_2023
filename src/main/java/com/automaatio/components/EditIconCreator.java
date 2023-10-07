package com.automaatio.components;

import javafx.scene.control.Button;

public class EditIconCreator extends TogglableIconCreator {
    public Button create() {
        return super.create("images/edit.png", "images/save.png", "Edit", "Save");
    }
}
