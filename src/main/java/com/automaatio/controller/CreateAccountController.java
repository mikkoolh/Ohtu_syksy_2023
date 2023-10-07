package com.automaatio.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.automaatio.components.TogglableHideIconCreator;
import com.automaatio.components.SwitchablePasswordField;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.FormInputValidator;
import com.automaatio.utils.NavigationUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Controller for the registration form
 * @author Matleena Kankaanpää
 * 19.9.2023
 */

public class CreateAccountController {
    private final NavigationUtil nav;
    private final FormInputValidator validator;
    private final UserDAO userDAO;
    private String firstName, lastName, email, phoneNumber, username, password;
    private SwitchablePasswordField switchableField;
    private TextInputControl passwordField;
    @FXML
    private TextField firstNameField, lastNameField, emailField, phoneNumberField, usernameField;
    @FXML
    private Text createAccountErrorText, usernameError, firstNameError, lastNameError, emailError, phoneError, passwordError;
    @FXML
    private Button saveButton;
    @FXML
    private GridPane formGrid;

    /**
     * Constructor
     */
    public CreateAccountController() {
        nav = new NavigationUtil();
        validator = new FormInputValidator();
        userDAO = new UserDAO();
    }

    /**
     * Navigates back to the login page
     * @param event 'Return' button clicked
     * @throws IOException
     */
    @FXML
    protected void goBack(ActionEvent event) throws IOException {
        nav.openLoginPage(event);
    }

    /**
     * Event handler for submitting the registration form
     * @param event 'Submit' button clicked
     * @throws IOException
     */
    @FXML
    protected void onSave(ActionEvent event) throws IOException {
        getFieldValues();

        System.out.println(username);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(phoneNumber);
        System.out.println(password);

        // Create a new user
        User user = new User(username, firstName, lastName, phoneNumber, email, BCrypt.hashpw(password, BCrypt.gensalt()), 0, 1, 0);
        System.out.println(user);
        saveUser(user);
        createAccountErrorText.setText("");
        nav.openLoginPage(event);
    }

    /*
    Check that the username doesn't already exist in the
    database and is in a valid format
     */
    private boolean validateUsername(String username) {

        try {
            if (username.isEmpty()) {
                usernameError.setText("Required field");
                return false;
            } else if (!validator.includesNoSpaces(username)) {
                usernameError.setText("Username cannot contain spaces");
                return false;
            } else if (username.length() < validator.getUSERNAME_MIN_LENGTH()) {
                usernameError.setText("Username must be at least " + validator.getUSERNAME_MIN_LENGTH() + " characters");
                return false;
            } else if (username.length() > validator.getUSERNAME_MAX_LENGTH()) {
                usernameError.setText("Username must be " + validator.getUSERNAME_MAX_LENGTH() + " characters or less");
                return false;
            } else if (userDAO.getObject(username) != null) {
                usernameError.setText("Username already taken");
                return false;
            }
        } catch (Exception e) {
            // Can't connect to the database to check if the username is available
            System.out.println("DB connection error: " + e);
            createAccountErrorText.setText("Error. Please try again shortly.");
            return false;
        }

        // Username valid and available
        usernameError.setText("Username available");
        createAccountErrorText.setText("");
        return true;
    }

    private boolean validateFirstName(String firstName) {
        if (firstName.isEmpty()) {
            firstNameError.setText("Required field");
            return false;
        } else if (firstName.length() > validator.getFIRSTNAME_MAX_LENGTH()) {
            firstNameError.setText("First name must be " + validator.getFIRSTNAME_MAX_LENGTH() + " characters or less");
            return false;
        }
        firstNameError.setText("");
        return true;
    }

    private boolean validateLastName(String lastName) {
        if (lastName.isEmpty()) {
            lastNameError.setText("Required field");
            return false;
        } else if (lastName.length() > validator.getLASTNAME_MAX_LENGTH()) {
            lastNameError.setText("Last name must be " + validator.getLASTNAME_MAX_LENGTH() + " characters or less");
            return false;
        }
        lastNameError.setText("");
        return true;
    }

    private boolean validateEmail(String email) {
        if (email.isEmpty()){
            emailError.setText("Required field");
            return false;
        } else if (!validator.emailFormatCorrect(email)) {
            emailError.setText("Invalid email address");
            return false;
        }
        emailError.setText("");
        return true;
    }

    private boolean validatePhoneNumber(String phone) {
        if (phone.isEmpty()){
            phoneError.setText("Required field");
            return false;
        } else if (!validator.phoneFormatCorrect(phone)) {
            phoneError.setText("Invalid phone number");
            return false;
        }
        phoneError.setText("");
        return true;
    }

    private boolean validatePassword(String password) {
        if (password.isEmpty()){
            passwordError.setText("Required field");
            return false;
        } else if (password.length() < validator.getPASSWORD_MIN_LENGTH()) {
            passwordError.setText("Password must be at least " + validator.getPASSWORD_MIN_LENGTH() + " characters");
            return false;
        } else if (password.length() > validator.getPASSWORD_MAX_LENGTH()) {
            passwordError.setText("Password must be " + validator.getPASSWORD_MAX_LENGTH() + " characters or less");
            return false;
        } else if (!validator.includesNoSpaces(password)) {
            passwordError.setText("Password cannot contain spaces");
            return false;
        }
        passwordError.setText("");
        return true;
    }

    // Creates a new user into the database when the save button is clicked
    private void saveUser(User user) {
        try {
            userDAO.addObject(user);
            System.out.println("created user");
        } catch (Exception e) {
            System.out.println(e);
            createAccountErrorText.setText("Error creating account");
        }
    }

    @FXML
    private void initialize() {
        saveButton.setDisable(true);
        Platform.runLater(() -> usernameField.requestFocus()); // Autofocus

        // Eye button
        Button togglePasswordButton = (new TogglableHideIconCreator()).create();
        togglePasswordButton.addEventHandler(ActionEvent.ACTION, (e)-> {
            switchableField.toggle();
            formGrid.getChildren().remove(passwordField);
            passwordField = switchableField.getField();
            formGrid.add(passwordField, 1, 10);
        });
        formGrid.add(togglePasswordButton, 2, 10);

        // Switchable password field
        switchableField = new SwitchablePasswordField();
        passwordField = switchableField.getField();
        formGrid.add(passwordField, 1, 10);

        getFieldValues();

        // Set the input guidelines on screen
        validateUsername(username);
        validateFirstName(firstName);
        validateLastName(lastName);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        validatePassword(password);

        // Change listeners for input fields
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateUsername(newValue.trim());
            toggleButton();
        });

        firstNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateFirstName(newValue.trim());
            toggleButton();
        });

        lastNameField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateLastName(newValue.trim());
            toggleButton();
        });

        emailField.textProperty().addListener((observable, oldValue, newValue) -> {
            validateEmail(newValue.trim());
            toggleButton();
        });

        phoneNumberField.textProperty().addListener((observable, oldValue, newValue) -> {
            validatePhoneNumber(newValue.trim().replaceAll("\\s", ""));
            toggleButton();
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            validatePassword(newValue);
            toggleButton();
        });

        List<Text> tooltips = Arrays.asList(usernameError, firstNameError, lastNameError, emailError, phoneError, passwordError);
        for (Text tooltip : tooltips) {
            // Voi muuttaa fontin mut kaikki ei tue italicia
            tooltip.setStyle("-fx-fill: #5E5E5E; -fx-font-family: Verdana; -fx-font-style: italic; -fx-font-size: 11px;");
            // tooltip.getStyleClass().add("tooltip"); ei toimi en tiä miks :p
        }
    }

    // Enables/disables the save button depending on whether all fields are pass validation
    private void toggleButton() {
        getFieldValues();

        boolean inputOk = validateUsername(username)
                && validateFirstName(firstName)
                && validateLastName(lastName)
                && validateEmail(email)
                && validatePhoneNumber(phoneNumber)
                && validatePassword(password);

        saveButton.setDisable(!inputOk);
    }

    private void getFieldValues(){
        firstName = firstNameField.getText().trim();
        lastName = lastNameField.getText().trim();
        email = emailField.getText().trim();
        phoneNumber = phoneNumberField.getText().trim().replaceAll("\\s", ""); // Delete spaces
        username = usernameField.getText().trim();
        password = passwordField.getText();
    }
}