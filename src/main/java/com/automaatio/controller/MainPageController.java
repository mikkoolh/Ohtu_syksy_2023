package com.automaatio.controller;

import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the app dashboard
 * @author Matleena Kankaanpää
 * @author Nikita Nossenko
 * 8.9.2023
 */

public class MainPageController {

    @FXML
    private Label usernameLabel;

    private String loggedInUsername;

    /**
     * Default constructor for the MainPageController class.
     */
    public MainPageController(){};

    /**
     * Open the user profile page.
     *
     * @param event Button click event that triggers the navigation.
     * @throws IOException If an error occurs while loading the user profile view.
     */
    @FXML
    private void openProfile(ActionEvent event) throws IOException {
        System.out.println("open profile");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/user-profile.fxml")); //
        Parent root = loader.load();
        ProfileController editProfileController = loader.getController();
        editProfileController.setUsernames(loggedInUsername);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * Handle the logout action.
     *
     * @param event Button click event that triggers the logout.
     * @throws IOException If an error occurs during navigation to the login page.
     */
    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        System.out.println("log out");

        // Kirjaudu ulos

        // Siirry kirjautumissivulle
        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }

    /**
     * Set the logged-in username and display it on the dashboard.
     *
     * @param username The username of the logged-in user.
     */
    public void setLoggedInUsername(String username) {
        this.loggedInUsername = username;
        usernameLabel.setText(username);
    }

    /**
     * Get the logged-in username.
     *
     * @return The username of the logged-in user.
     */
    public String getLoggedInUsername() {
        return this.loggedInUsername;
    }

}
