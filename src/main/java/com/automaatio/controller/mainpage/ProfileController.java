package com.automaatio.controller.mainpage;

import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

/**
 * Controller for the user profile
 * @author Matleena Kankaanpää
 * @author Nikita Nossenko
 * 8.9.2023
 */

public class ProfileController {

    @FXML
    private TextField fnameField;

    @FXML
    private TextField lnameField;

    @FXML
    private TextField bdayField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneField;
    @FXML
    private PasswordField passField;

    private UserDAO userDAO = new UserDAO();

    private User user;

    private String loggedInUsername; // Lisätty käyttäjänimi-muuttuja

    /**
     * Default constructor for the ProfileController class.
     */
    public ProfileController() {};

    /**
     * Set the logged-in username.
     *
     * @param username The username of the logged-in user.
     */
    public void setUsername(String username) {
        this.loggedInUsername = username;
    }

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        System.out.println("return to main page");

        NavigationUtil nav = new NavigationUtil();
        nav.openMainPage(event);
    }

    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        System.out.println("log out");

        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }

    /**
     * Initialize the user profile fields with default values.
     * This method is automatically called when the FXML file is loaded.
     */
    @FXML
    private void initialize() {

        fnameField.setText("Matti");
        lnameField.setText("Meikäläinen");
        bdayField.setText("1.1.2000");
        emailField.setText("joku@esimerkki.com");
        phoneField.setText("040 123456");
        passField.setText("Salainen_;)");
    }

    /**
     * Set the displayed username in the profile view.
     *
     * @param loggedInUsername The username of the logged-in user.
     */
    @FXML
    public void setUsernames(String loggedInUsername) {

    }
}