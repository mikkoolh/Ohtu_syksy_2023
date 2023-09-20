package com.automaatio.controller.mainpage;

import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class RoomController implements Initializable {
    @FXML
    private Text roomName;

    public RoomController(){

    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        CacheSingleton cache = CacheSingleton.getInstance();
        roomName.setText(cache.getRoom().getName());
    }
}
