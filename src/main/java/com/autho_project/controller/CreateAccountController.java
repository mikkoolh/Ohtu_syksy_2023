package com.autho_project.controller;

import java.io.IOException;

import com.autho_project.utils.NavigationUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CreateAccountController {
    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;
    
    @FXML
    private TextField emailField;

    @FXML
    private TextField phoneNumberField;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField passwordField;

    @FXML
    private Text createAccountErrorText;

    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        System.out.println("go back");
        Parent root = FXMLLoader.load(getClass().getResource("/com/autho_project/view/login.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    protected void onSave(ActionEvent event) throws IOException {
        System.out.println("create user");

        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String phoneNumber = phoneNumberField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(phoneNumber);
        System.out.println(username);
        System.out.println(password);

        if (true) { // tähän voi vaihtaa ehdon jolla tarkistetaan onko tiedot ok
            // käyttäjän tallennus

            // siirry etusivulle
            NavigationUtil nav = new NavigationUtil();
            nav.openMainPage(event);
        } else {
            // jos virhe
            showError("joku virheilmoitus");
        }
    }

    private void showError(String errorMessage) {
        createAccountErrorText.setText(errorMessage);
    }
}
