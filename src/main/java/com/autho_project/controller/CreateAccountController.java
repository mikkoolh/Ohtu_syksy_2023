package com.autho_project.controller;

import java.io.IOException;

import com.autho_project.utils.NavigationUtil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CreateAccountController {
    private NavigationUtil nav;

    public CreateAccountController() {
        this.nav = new NavigationUtil();
    }

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
        nav.openLoginPage(event);
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
