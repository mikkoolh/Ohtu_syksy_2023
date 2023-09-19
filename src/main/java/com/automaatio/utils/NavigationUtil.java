package com.automaatio.utils;

import com.automaatio.controller.MainPageController;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Functions for navigating the app
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class NavigationUtil {

    /**
     * Opens the main dashboard
     * @param event Button click
     * @param loggedInUsername The logged-in user's username
     * @throws IOException
     */
    public void openMainPage(ActionEvent event, String loggedInUsername) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/main.fxml"));
        Parent root = loader.load();
        MainPageController controller = loader.getController();
        controller.setLoggedInUsername(loggedInUsername);
        System.out.println("Logged in username: " + loggedInUsername);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Opens the login page
     * @param event Button click
     * @throws IOException
     */
    public void openLoginPage(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
