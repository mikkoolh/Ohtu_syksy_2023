package com.automaatio.components;

public class EyeButton extends TogglableButton {
    public EyeButton() {
        super("images/eye-open-svgrepo-com.png", "images/eye-hidden-svgrepo-com.png", "Show password", "Hide password");
        int icon_size = 22; // Icon dimensions
        defaultView.setFitHeight(icon_size);
        altView.setFitHeight(icon_size);
        button.setStyle("-fx-border-color: transparent; -fx-border-width: 0; -fx-background-radius: 0; -fx-background-color: transparent;");
    }
}
