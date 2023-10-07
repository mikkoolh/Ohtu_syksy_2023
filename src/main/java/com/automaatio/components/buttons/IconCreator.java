package com.automaatio.components.buttons;

import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * @author Matleena Kankaanpää
 * 7.10.2023
 *
 * Image button
 */

public abstract class IconCreator {
    public Button create(String image, String tooltip) {
        Button button = new Button();
        ImageView imageView = new ImageView(new Image(image));
        imageView.setPreserveRatio(true);
        imageView.setFitHeight(18);
        button.setGraphic(imageView);
        button.setTooltip((new Tooltip(tooltip)));
        return button;
    }
}
