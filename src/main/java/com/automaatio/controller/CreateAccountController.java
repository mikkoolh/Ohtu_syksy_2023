package com.automaatio.controller;

import com.automaatio.utils.FormInputValidator;
import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.IOException;

/**
 * Controller for the registration form
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class CreateAccountController {
    private NavigationUtil nav;
    private FormInputValidator validator;

    /**
     * Constructor
     */
    public CreateAccountController() {
        this.nav = new NavigationUtil();
        this.validator = new FormInputValidator();
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

    /**
     * Navigates back to the login page
     * @param event 'Return' button clicked
     * @throws IOException
     */
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        System.out.println("go back");
        nav.openLoginPage(event);
    }

    /**
     * Event handler for submitting the registration form
     * @param event 'Submit' button clicked
     * @throws IOException
     */
    @FXML
    protected void onSave(ActionEvent event) throws IOException {
        System.out.println("create user");

        String firstName = firstNameField.getText().toString();
        String lastName = lastNameField.getText().toString();
        String email = emailField.getText().toString();
        String phoneNumber = phoneNumberField.getText().toString().replaceAll("\\s", ""); // Poistaa välilyönnit
        String username = usernameField.getText().toString();
        String password = passwordField.getText().toString();

        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(phoneNumber);
        System.out.println(username);
        System.out.println(password);

        Boolean fieldsOk = validator.emailFormatCorrect(email)
            && validator.phoneFormatCorrect(phoneNumber)
            && validator.firstNameLengthCorrect(firstName)            
            && validator.lastNameLengthCorrect(lastName)
            && validator.usernameLengthCorrect(username);

        if (fieldsOk) {
            System.out.println("ok");
                    
            // käyttäjän tallennus

            // siirry etusivulle
            nav.openMainPage(event, username);
        } else {
            // jos virhe
            showError("joku virheilmoitus");
            System.out.println("input error");
        }
    }

    private void showError(String errorMessage) {
        createAccountErrorText.setText(errorMessage);
    }
}
