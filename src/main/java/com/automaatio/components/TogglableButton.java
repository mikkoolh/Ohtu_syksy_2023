package com.automaatio.components;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TogglableButton {
    private Button button;
    private final int ICON_DIMENSIONS = 18;
    private ImageView defaultView, altView;
    private Tooltip defaultTooltip, altTooltip;

    public TogglableButton(String defaultImage, String altImage, String defaultTooltip, String altTooltip) {
        button = new Button();
        defaultView = new ImageView(new Image(defaultImage));
        altView = new ImageView(new Image(altImage));
        this.defaultTooltip = new Tooltip(defaultTooltip);
        this.altTooltip = new Tooltip(altTooltip);
        defaultView.setPreserveRatio(true);
        altView.setPreserveRatio(true);
        defaultView.setFitHeight(ICON_DIMENSIONS);
        altView.setFitHeight(ICON_DIMENSIONS);
        button.setGraphic(defaultView);
    }

    public void toggle() {
        if (button.getGraphic() == defaultView) {
            button.setGraphic(altView);
            button.setTooltip(altTooltip);
        } else {
            button.setGraphic(defaultView);
            button.setTooltip(defaultTooltip);
        }
    }
}
