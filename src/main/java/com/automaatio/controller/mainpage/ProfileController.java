package com.automaatio.controller.mainpage;

import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Controller for the user profile
 * @author Matleena Kankaanpää
 * @author Nikita Nossenko
 * 8.9.2023
 */

public class ProfileController {

    @FXML
    private TextField fnameField, lnameField, bdayField, emailField, phoneField;
    @FXML
    Text etusivuText, usernameTXT, nameTXT;
    @FXML
    private PasswordField passField;

    private UserDAO userDAO = new UserDAO();

    private User user = CacheSingleton.getInstance().getUser();
    private CacheSingleton cache = CacheSingleton.getInstance();

    private String loggedInUsername = user.getUsername();
    private String loggedInName = user.getFirstName();

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
        etusivuText.setText("Käyttäjänimi " + loggedInUsername);
        fnameField.setText(loggedInName);
        lnameField.setText(user.getLastName());
        bdayField.setText("Tätä pitää viel muuttaa");
        emailField.setText(user.getEmail());
        phoneField.setText(user.getPhoneNumber());
        passField.setText(user.getPassword());
    }

}