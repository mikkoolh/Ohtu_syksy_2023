package com.automaatio.components;

import com.dlsc.gemsfx.TimePicker;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class TimeSelector {
    private TimePicker timePicker;

    public TimeSelector() {
        this.timePicker = new TimePicker();
    }

    public TimePicker getTimePicker() {
        CheckBox rollOverBox = new CheckBox("Rollover");
        rollOverBox.selectedProperty().bindBidirectional(timePicker.rolloverProperty());

        CheckBox linkFieldsBox = new CheckBox("Link Fields");
        linkFieldsBox.selectedProperty().bindBidirectional(timePicker.linkingFieldsProperty());

        CheckBox fullWidth = new CheckBox("Full Width");
        fullWidth.selectedProperty().addListener(it -> {
            if (fullWidth.isSelected()) {
                timePicker.setMaxWidth(Double.MAX_VALUE);
            } else {
                timePicker.setMaxWidth(Region.USE_PREF_SIZE);
            }
        });

        CheckBox showPopupButtonBox = new CheckBox("Show Button");
        showPopupButtonBox.selectedProperty().bindBidirectional(timePicker.showPopupTriggerButtonProperty());

        Button showOrHidePopupButton = new Button("Show Popup");
        showOrHidePopupButton.setOnAction(evt -> timePicker.show());

        Label valueLabel = new Label();
        valueLabel.textProperty().bind(Bindings.createStringBinding(() -> {
            LocalTime time = timePicker.getTime();
            if (time != null) {
                return "Time: " + DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT).format(time) + " (adjusted: " + (timePicker.isAdjusted() ? "yes" : "no") + ")";
            }
            return "empty";
        }, timePicker.timeProperty(), timePicker.adjustedProperty()));

        TextField textField = new TextField();
        textField.setPromptText("Text field");

        DatePicker datePicker = new DatePicker();
        datePicker.setMaxWidth(Double.MAX_VALUE);
        datePicker.valueProperty().addListener(it -> System.out.println("date: " + datePicker.getValue()));
        datePicker.getEditor().textProperty().addListener(it -> {
            try {
                DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).parse(datePicker.getEditor().getText());
            } catch (DateTimeParseException ex) {
            }
        });

        VBox box0 = new VBox(20, timePicker, valueLabel);


        VBox box = new VBox(box0);
        box.setFillWidth(true);

        return timePicker;
    }
}
