package com.autho_project.controller;

import java.io.IOException;
import com.autho_project.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class ProfileController {

    @FXML
    private Text firstName;
    
    @FXML
    private Text lastName;
    
    @FXML
    private Text role;

    @FXML
    private Text birthday;

    @FXML
    private Text email;

    @FXML
    private Text phoneNumber;

    @FXML
    private Text username;

    @FXML
    private void onBackClick(ActionEvent event) throws IOException {
        System.out.println("return to main page");

        NavigationUtil nav = new NavigationUtil();
        nav.openMainPage(event);
    }

    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        System.out.println("log out");

        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }

    // Kenttien täyttö
    @FXML
    private void initialize() {
        firstName.setText("matti");
        lastName.setText("meikäläinen");
        role.setText("Admin"); // vois toteuttaa ehkä enumina tms?
        birthday.setText("1.3.1800");
        email.setText("joku@esimerkki.com");
        phoneNumber.setText("040 123456");
    }
}