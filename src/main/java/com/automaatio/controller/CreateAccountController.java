package com.automaatio.controller;

import java.io.IOException;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.FormInputValidator;
import com.automaatio.utils.NavigationUtil;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    private final int ICON_SIZE;
    private String firstName, lastName, email, phoneNumber, username, password;
    private final Image show, hide;
    @FXML
    private final ImageView sauron;
    @FXML
    private TextField firstNameField, lastNameField, emailField, phoneNumberField, usernameField, passwordField;
    @FXML
    private Text createAccountErrorText, usernameError, firstNameError, lastNameError, emailError, phoneError, passwordError;
    @FXML
    private Button saveButton, togglePassword;
    @FXML
    private GridPane formGrid;
    private boolean hidePassword;

    /*
     To keep track of what's typed in the password field
     while switching the field type
     */
    private String currentPassword;

    /**
     * Constructor
     */
    public CreateAccountController() {
        nav = new NavigationUtil();
        validator = new FormInputValidator();
        userDAO = new UserDAO();
        hidePassword = true;
        currentPassword = "";
        ICON_SIZE = 22; // Eye icon dimensions
        sauron = new ImageView();

        // Voi vaihtaa tai tehdä kokonaan erilaisen buttonin
        show = new Image("images/eye-open-svgrepo-com.png");
        hide = new Image("images/eye-hidden-svgrepo-com.png");
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

        // Käyttäjän luonti
        User user = new User(username, firstName, lastName, phoneNumber, email, BCrypt.hashpw(password, BCrypt.gensalt()), 0, 1);
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
            } else if (userDAO.getUser(username) != null) {
                usernameError.setText("Username already taken");
                return false;
            }
        } catch (Exception e) {
            // Can't connect to the database to check if the username is available
            System.out.println("not connected to db");
            createAccountErrorText.setText("Error. Please try again shortly");
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

    /*
    Creates a new user into the database
    when the save button is clicked
     */
    private void saveUser(User user) {
        try {
            userDAO.addUser(user);
            System.out.println("created user");
            // Save user in cache
        } catch (Exception e) {
            System.out.println(e);
            createAccountErrorText.setText("Error creating account");
        }
    }

    @FXML
    private void initialize() {
        saveButton.setDisable(true);
        Platform.runLater(() -> usernameField.requestFocus()); // Autofocus

        getFieldValues();

        // Set the input guidelines on screen
        validateUsername(username);
        validateFirstName(firstName);
        validateLastName(lastName);
        validateEmail(email);
        validatePhoneNumber(phoneNumber);
        validatePassword(password);

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
            currentPassword = newValue;
            toggleButton();
        });

        // Image view of the open/closed eye
        sauron.setImage(show);
        sauron.setPreserveRatio(true);
        sauron.setFitHeight(ICON_SIZE);

        // Button containing the image
        togglePassword.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
        togglePassword.setGraphic(sauron);
    }

    /*
    Enables/disables the save button depending on
    whether all fields are ok
     */
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

    /*
     Change the eye icon and switch the text field into
     a password field (or vice versa) and pass whatever is typed
     */
    @FXML
    private void togglePassword(ActionEvent event) {
        if (hidePassword) {
            // Create a text field
            TextField textPassword = new TextField();
            textPassword.setText(currentPassword);

            //
            textPassword.textProperty().addListener((observable, oldValue, newValue) -> {
                currentPassword = newValue;
                validatePassword(newValue);
                toggleButton();
            });

            formGrid.add(textPassword, 1, 10);
            sauron.setImage(hide);
        } else {
            // Create a password field
            PasswordField hiddenPassword = new PasswordField();
            hiddenPassword.setText(currentPassword);

            //
            hiddenPassword.textProperty().addListener((observable, oldValue, newValue) -> {
                currentPassword = newValue;
                validatePassword(newValue.trim());
                toggleButton();
            });

            formGrid.add(hiddenPassword, 1, 10);
            sauron.setImage(show);
        }

        hidePassword = !hidePassword;
    }

    private void getFieldValues(){
        firstName = firstNameField.getText().trim();
        lastName = lastNameField.getText().trim();
        email = emailField.getText().trim();
        phoneNumber = phoneNumberField.getText().trim().replaceAll("\\s", ""); // Delete spaces
        username = usernameField.getText().trim();
        password = currentPassword;
    }
}