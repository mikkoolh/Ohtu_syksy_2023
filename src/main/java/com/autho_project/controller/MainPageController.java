package com.autho_project.controller;

import java.io.IOException;

import com.autho_project.utils.NavigationUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Controller for the app dashboard
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class MainPageController {
    
    @FXML
    private void openProfile(ActionEvent event) throws IOException {
        System.out.println("open profile");
        Parent root = FXMLLoader.load(getClass().getResource("/view/user-profile.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        System.out.println("log out");

        // logout

        // Siirry login-sivulle
        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }
}
