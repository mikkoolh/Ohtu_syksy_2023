package com.automaatio.controller;

import com.automaatio.components.TogglableHideIconCreator;
import com.automaatio.components.SwitchablePasswordField;
import com.automaatio.model.database.*;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.util.List;

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
    private Button loginButton;
    private SwitchablePasswordField switchableField;
    private TextInputControl passwordField;
    @FXML
    private GridPane loginFormGrid;

    private CacheSingleton cache = CacheSingleton.getInstance();

    private final NavigationUtil nav;
    private final UserDAO userDAO;

    public LoginController() {
        this.nav = new NavigationUtil();
        this.userDAO = new UserDAO();
    }

    @FXML
    private void initialize() {
        loginButton.setDisable(true);

        // Eye button
        Button togglePasswordButton = (new TogglableHideIconCreator()).create();
        togglePasswordButton.addEventHandler(ActionEvent.ACTION, (e)-> {
            switchableField.toggle();
            loginFormGrid.getChildren().remove(passwordField);
            passwordField = switchableField.getField();
            loginFormGrid.add(passwordField, 1, 1);
        });
        loginFormGrid.add(togglePasswordButton, 2, 1);

        // Switchable password field
        switchableField = new SwitchablePasswordField();
        passwordField = switchableField.getField();
        loginFormGrid.add(passwordField, 1, 1);

        // Change listeners for input fields
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUI();
        });

        passwordField.textProperty().addListener((observable, oldValue, newValue) -> {
            updateUI();
        });

        // testailua yms. varten, voi poistaa lopuks
        try {
            List<User> users = userDAO.getAll();
            System.out.println(users.size() + " users in db");
            for (User user : users) {
                System.out.println(user.getUsername());
            }
        } catch (Exception e) {
            System.out.println("not connected to db");
        }
    }

    /**
     * @param event
     * @throws IOException
     */
    @FXML
    protected void onLoginClick(ActionEvent event) throws IOException {
        String username = usernameField.getText().trim();
        String password = passwordField.getText();

        try {
            User user = userDAO.getObject(username);

            if (user == null) {
                // User not found
                loginErrorText.setText("Username not found");
                System.out.println("user not found");
            } else {
                // User exists, check password
                System.out.println("user exists");
                System.out.println("user: " + username);
                System.out.println("password: " + password);

                if (BCrypt.checkpw(password, user.getPassword())) {
                    System.out.println("password correct");

                    // Save user in cache
                    cache.setUser(userDAO.getObject(username));
                    User loggedInUser = cache.getUser();
                    String usernamecache = loggedInUser.getUsername();
                    System.out.println(username);
                    System.out.println("cacheusername: " + usernamecache);
                    System.out.println("maxprice in login:" +loggedInUser.getMaxPrice());

                    loginErrorText.setText("");
                    nav.openMainPage(event);
                } else {
                    loginErrorText.setText("Password incorrect");
                    System.out.println("wrong password");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Database connection error
            loginErrorText.setText("Error. Please try again shortly.");
        }
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

    /*
    Disables the login button and clears the error message
    when both fields are empty
     */
    private void updateUI() {
        boolean clickable = (!usernameField.getText().trim().isEmpty())
                && (!passwordField.getText().isEmpty());
        loginButton.setDisable(!clickable);

        if (!clickable) {
            loginErrorText.setText("");
        }
    }
}