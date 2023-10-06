package com.automaatio.components;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TogglableButton {
    protected Button button;
    protected ImageView defaultView, altView;
    private final Tooltip defaultTooltip, altTooltip;

    public TogglableButton(String defaultImage, String altImage, String defaultTooltipText, String altTooltipText) {
        button = new Button();
        defaultView = new ImageView(new Image(defaultImage));
        altView = new ImageView(new Image(altImage));
        defaultTooltip = new Tooltip(defaultTooltipText);
        altTooltip = new Tooltip(altTooltipText);
        defaultView.setPreserveRatio(true);
        altView.setPreserveRatio(true);
        int icon_size = 18; // Icon dimensions
        defaultView.setFitHeight(icon_size);
        altView.setFitHeight(icon_size);
        button.setGraphic(defaultView);
        button.setTooltip(defaultTooltip);
        button.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent event) {
                if (button.getGraphic() == defaultView) {
                    button.setGraphic(altView);
                    button.setTooltip(altTooltip);
                } else {
                    button.setGraphic(defaultView);
                    button.setTooltip(defaultTooltip);
                }
            }
        });
    }

    public Button getButton() {
        return button;
    }
}
