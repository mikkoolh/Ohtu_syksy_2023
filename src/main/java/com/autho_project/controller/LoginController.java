package com.autho_project.controller;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private Text errorText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onLoginClick() {
        System.out.println("login clicked");
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println(username);
        System.out.println(password);
    }

    @FXML
    protected void onLoginWithGoogleClick() {
        System.out.println("login with google");
    }

    @FXML
    protected void onCreateAccountClick() {
        System.out.println("create account");
    }
}