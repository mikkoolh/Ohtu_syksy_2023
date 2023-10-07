package com.automaatio.components;

import com.automaatio.controller.mainpage.clickActions.ClickActions;
import javafx.scene.Node;

public interface IButton {
    Node create(Object object, ClickActions clickActions);
}
