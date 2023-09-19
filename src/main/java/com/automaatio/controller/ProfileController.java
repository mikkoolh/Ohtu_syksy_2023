package com.automaatio.controller;

import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Controller for the user profile
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class ProfileController {


    @FXML
    private Text etusivuText;

    @FXML
    private Text firstName;

    @FXML
    private Text lastName;

    @FXML
    private Text role;

    @FXML
    private Text birthday;

    @FXML
    private Text email;

    @FXML
    private Text phoneNumber;

    @FXML
    private Label usernameLabel;

    private String loggedInUsername; // Lisätty käyttäjänimi-muuttuja

    public ProfileController() {};

    public void setUsername(String username) {
        this.loggedInUsername = username;
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        System.out.println("return to main page");

        NavigationUtil nav = new NavigationUtil();
        nav.openMainPage(event, loggedInUsername);
    }

    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        System.out.println("log out");

        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }

    // Kenttien täyttö
    @FXML
    private void initialize() {
        firstName.setText("Matti");
        lastName.setText("Meikäläinen");
        role.setText("Admin"); // Voisit toteuttaa tämän esimerkiksi enumina tms.
        birthday.setText("1.3.1800");
        email.setText("joku@esimerkki.com");
        phoneNumber.setText("040 123456");
    }

    @FXML
    public void setUsernames(String loggedInUsername) {
        System.out.println("LoggedInUsername: " + loggedInUsername);
        usernameLabel.setText(loggedInUsername);
        etusivuText.setText(loggedInUsername);
    }
}
