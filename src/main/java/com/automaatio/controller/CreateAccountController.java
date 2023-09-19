package com.automaatio.controller;

import java.io.IOException;
import java.util.List;
import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import com.automaatio.utils.FormInputValidator;
import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Controller for the registration form
 * @author Matleena Kankaanpää
 * 19.9.2023
 */

public class CreateAccountController {
    private NavigationUtil nav;
    private FormInputValidator validator;

    private UserDAO userDAO;

    /**
     * Constructor
     */
    public CreateAccountController() {
        this.nav = new NavigationUtil();
        this.validator = new FormInputValidator();
        this.userDAO = new UserDAO();
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


    // Kenttien virheilmoitukset
    @FXML
    private Text usernameError;

    @FXML
    private Text firstNameError;

    @FXML
    private Text lastNameError;

    @FXML
    private Text emailError;

    @FXML
    private Text phoneError;

    @FXML
    private Text passwordError;

    // Virheilmoitusten tekstit
    private String usernameErrorText, firstNameErrorText, lastNameErrorText, emailErrorText, phoneErrorText, passwordErrorText;

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
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String phoneNumber = phoneNumberField.getText().trim().replaceAll("\\s", ""); // Poistaa välilyönnit
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        System.out.println(username);
        System.out.println(firstName);
        System.out.println(lastName);
        System.out.println(email);
        System.out.println(phoneNumber);
        System.out.println(password);

        boolean inputOk = usernameValid(username)
                && firstNameValid(firstName)
                && lastNameValid(lastName)
                && emailValid(email)
                && phoneValid(phoneNumber)
                && passwordValid(password);

        // username
        if (!usernameValid(username)) {
            usernameError.setText(usernameErrorText);
        } else {
            usernameError.setText("");
        }

        // first name
        if (!firstNameValid(firstName)) {
            firstNameError.setText(firstNameErrorText);
        } else {
            firstNameError.setText("");
        }

        // last name
        if (!lastNameValid(lastName)) {
            lastNameError.setText(lastNameErrorText);
        } else {
            lastNameError.setText("");
        }

        // email
        if (!emailValid(email)) {
            emailError.setText(emailErrorText);
        } else {
            emailError.setText("");
        }

        // phone
        if (!phoneValid(phoneNumber)) {
            phoneError.setText(phoneErrorText);
        } else {
            phoneError.setText("");
        }

        // password
        if (!passwordValid(password)) {
            passwordError.setText(passwordErrorText);
        } else {
            passwordError.setText("");
        }

        if (inputOk) {
            System.out.println("ok");

            User user = new User(username, firstName, lastName, phoneNumber, email, BCrypt.hashpw(password, BCrypt.gensalt()), 0, 1);
            System.out.println(user);
            saveUser(user);

            // käyttäjän tallennus
            
            createAccountErrorText.setText("");
            nav.openMainPage(event);
        } else {
            // jos virhe
            System.out.println("input error");
            //createAccountErrorText.setText(":/");
        }
    }


    /*
    Käyttäjätunnuksen validointi ja tarkistus ettei
    samaa käyttäjätunnusta löydy jo tietokannasta
     */
    private boolean usernameValid(String username) {
        if (!validator.usernameLengthCorrect(username)) {
            usernameErrorText = "Username must be " + validator.getUSERNAME_MIN_LENGTH() + "-" + validator.getUSERNAME_MAX_LENGTH() + " characters";
            return false;
        } else if (userDAO.getUser(username) != null) {
            usernameErrorText = "Username already taken";
            return false;
        }
        return true;
    }

    private boolean firstNameValid(String firstName) {
        if (!validator.firstNameLengthCorrect(firstName)) {
            firstNameErrorText = "First name must be " + validator.getFIRSTNAME_MIN_LENGTH() + "-" + validator.getFIRSTNAME_MAX_LENGTH() + " characters";
            return false;
        }
        return true;
    }

    private boolean lastNameValid(String lastName) {
        if (!validator.lastNameLengthCorrect(lastName)) {
            lastNameErrorText = "Last name must be " + validator.getLASTNAME_MIN_LENGTH() + "-" + validator.getLASTNAME_MAX_LENGTH() + " characters";
            return false;
        }
        return true;
    }

    private boolean emailValid(String email) {
        if (!validator.emailFormatCorrect(email)) {
            emailErrorText = "Invalid email format";
            return false;
        }
        return true;
    }

    private boolean phoneValid(String phone) {
        if (!validator.phoneFormatCorrect(phone)) {
            phoneErrorText = "Invalid phone number format";
            return false;
        }
        return true;
    }

    private boolean passwordValid(String password) {
        if (!validator.passwordLengthCorrect(password)) {
            passwordErrorText = "Password must be " + validator.getPASSWORD_MIN_LENGTH() + "-" + validator.getPASSWORD_MAX_LENGTH() + " characters";
            return false;
        }
        return true;
    }

    private void saveUser(User user) {
        try {
            userDAO.addUser(user);
            System.out.println("created user");

            // testaukseen
            List<User> users = userDAO.getAll();
            System.out.println(users.size() + " users in db");
        } catch (Exception e) {
            System.out.println(e);
            createAccountErrorText.setText("Error creating account");
        }
    }

    @FXML
    private void initialize() {
        /*
        try {
            List<User> users = userDAO.getAll();
            System.out.println(users.size() + " users in db");
        } catch (Exception e) {
            createAccountErrorText.setText("Unable to connect to the database");
        }

         */
    }
}
