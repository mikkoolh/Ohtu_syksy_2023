package com.automaatio.components;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;

public class SwitchablePasswordField {
    private TextField textField;
    private PasswordField passwordField;
    private TextInputControl current;
    private boolean showPassword;

    public SwitchablePasswordField() {
        showPassword = false;
        textField = new TextField();
        passwordField = new PasswordField();
        passwordField.textProperty().bindBidirectional(textField.textProperty());
        current = passwordField;
    }

    public void toggle() {
        if (current == textField) {
            current = passwordField;
        } else {
            current = textField;
        }
    }

    public TextInputControl getField() {
        return current;
    }
}
