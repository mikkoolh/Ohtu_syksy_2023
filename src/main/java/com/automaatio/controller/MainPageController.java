package com.automaatio.controller;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import com.automaatio.utils.CacheSingleton;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;


/**
 * Controller for the app dashboard
 * @author Mikko Hänninen 19.9.2023
 */

public class MainPageController implements Initializable {
    @FXML
    private Pane mainPane, menuPane;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setMainPane();
        setMenuPane();
    }

    public Pane getMainPane(){
        return mainPane;
    }

    public Pane getMenuPane(){
        return menuPane;
    }

    public void setMenuPane(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-menu.fxml"));
            Parent firstView = loader.load();
            MainMenuController menuController = loader.getController();
            menuController.setMainController(this);
            menuPane.getChildren().add(firstView);
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    public void setMainPane(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main-welcome.fxml"));
            Parent firstView = loader.load();
            mainPane.getChildren().add(firstView);
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
