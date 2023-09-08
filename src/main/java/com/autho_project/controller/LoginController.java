package com.autho_project.controller;

import java.io.IOException;

import com.autho_project.utils.NavigationUtil;

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

public class LoginController {
    @FXML
    private Text errorText;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    protected void onLoginClick(ActionEvent event) throws IOException {
        System.out.println("login clicked");

        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println(username);
        System.out.println(password);

        System.out.println("open main page");
        NavigationUtil nav = new NavigationUtil();
        nav.openMainPage(event);
    }

    @FXML
    protected void onLoginWithGoogleClick() {
        System.out.println("login with google");
    }

    @FXML
    protected void onCreateAccountClick(ActionEvent event) throws IOException {
        System.out.println("create account");
        Parent root = FXMLLoader.load(getClass().getResource("/com/autho_project/view/create-account.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}