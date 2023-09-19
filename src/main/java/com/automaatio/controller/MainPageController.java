package com.automaatio.controller;

import java.awt.event.MouseEvent;
import java.io.IOException;

import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Controller for the app dashboard
 * @author Matleena Kankaanpää
 * @Author Elmo Erla
 * 8.9.2023
 */

public class MainPageController {

    @FXML
    AnchorPane infoPage;

    /**
     * Navigates to the user profile view.
     *
     * @param event the action event that triggers the method.
     * @throws IOException if there's an error loading the user-profile.fxml resource.
     */
    @FXML
    private void openProfile(ActionEvent event) throws IOException {
        System.out.println("open profile");
        Parent root = FXMLLoader.load(getClass().getResource("/view/user-profile.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Handles the logout click event.
     * Logs the user out and navigates to the login page.
     *
     * @param event the action event that triggers the method.
     * @throws IOException if there's an error during navigation.
     */
    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        System.out.println("log out");

        // logout

        // Siirry login-sivulle
        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }
    /**
 * Handles the click event of the "Devices" titled pane.
     * Switches the scene to the devices view.
     *
     * @param mouseEvent the mouse event that triggers the method.
     * @throws IOException if there's an error loading the devices.fxml resource.
     */
    @FXML
    private void handleDeviceTitledPaneClick(javafx.scene.input.MouseEvent mouseEvent) throws IOException {
        try {
            AnchorPane loader = FXMLLoader.load(getClass().getResource("/view/devices.fxml"));
            infoPage.getChildren().setAll(loader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
