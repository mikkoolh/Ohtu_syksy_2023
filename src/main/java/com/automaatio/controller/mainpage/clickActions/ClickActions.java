package com.automaatio.controller.mainpage.clickActions;

import javafx.scene.layout.VBox;

public interface ClickActions {
    void onEditClick(Object object);
    void onDeleteClick(Object object, VBox mainVBox, VBox boxToDelete);
}
