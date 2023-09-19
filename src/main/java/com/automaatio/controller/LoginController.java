package com.automaatio.controller;

import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Controller for the login form
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class LoginController {
    @FXML
    private Text loginErrorText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private String loggedInUsername;

    /**
     * Handle the login button click event.
     *
     * @param event The button click event that triggers the login action.
     * @throws IOException If an error occurs during navigation to the main page.
     */
    @FXML
    protected void onLoginClick(ActionEvent event) throws IOException {
        System.out.println("login clicked");

        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println(username);
        System.out.println(password);

        if (true) { // tähän voi vaihtaa ehdon onko kirjautuminen ok
            System.out.println("open main page");

            // kirjaa käyttäjä sisään
            loggedInUsername = username;

            NavigationUtil nav = new NavigationUtil();
            nav.openMainPage(event, loggedInUsername);
        } else {
            // näytä virhe
            showError("virheilmoitus");
        }
    }

    /**
     * Handle the login with Google button click event.
     */
    //Halutaanko edes?
    @FXML
    protected void onLoginWithGoogleClick() {
        System.out.println("login with google");
    }

    /**
     * Handle the create account button click event.
     *
     * @param event The button click event that triggers the navigation to the create account view.
     * @throws IOException If an error occurs during navigation to the create account view.
     */
    @FXML
    protected void onCreateAccountClick(ActionEvent event) throws IOException {
        System.out.println("create account");
        Parent root = FXMLLoader.load(getClass().getResource("/view/create-account.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Display an error message on the login view.
     *
     * @param errorMessage The error message to be displayed.
     */
    private void showError(String errorMessage) {
        loginErrorText.setText(errorMessage);
    }
}