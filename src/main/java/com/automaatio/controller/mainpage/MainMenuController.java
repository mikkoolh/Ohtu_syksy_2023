package com.automaatio.controller.mainpage;


import com.automaatio.utils.CacheSingleton;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    private final CacheSingleton cache = CacheSingleton.getInstance();

    private Pane mainPane, menuPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mainPane = cache.getMainPane();
        menuPane = cache.getMenuPane();
    }
}
