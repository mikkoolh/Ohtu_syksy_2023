package com.automaatio.controller;

import java.io.IOException;

import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.Node;

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

    
    /** 
     * @param event
     * @throws IOException
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

            NavigationUtil nav = new NavigationUtil();
            nav.openMainPage(event);
        } else {
            // näytä virhe
            showError("virheilmoitus");
        }
    }

    @FXML
    protected void onLoginWithGoogleClick() {
        System.out.println("login with google");
    }

    @FXML
    protected void onCreateAccountClick(ActionEvent event) throws IOException {
        System.out.println("create account");
        Parent root = FXMLLoader.load(getClass().getResource("/view/create-account.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    private void showError(String errorMessage) {
        loginErrorText.setText(errorMessage);
    }
}