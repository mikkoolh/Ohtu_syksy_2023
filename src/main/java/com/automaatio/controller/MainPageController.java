package com.automaatio.controller;

import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Controller for the app dashboard
 * @author Matleena Kankaanpää
 * @author Nikita Nossenko
 * 8.9.2023
 */

public class MainPageController {

    @FXML
    private AnchorPane infoPane;

    @FXML
    private Text usernameTXT;

    private String loggedInUsername;

    @FXML
    private Text etusivuText;

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
        try{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/user-profile.fxml"));
            AnchorPane userProfilePane = fxmlLoader.load();

            infoPane.getChildren().setAll(userProfilePane);
            etusivuText.setText(loggedInUsername);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        usernameTXT.setText(username);
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
