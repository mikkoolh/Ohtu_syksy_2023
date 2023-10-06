package com.automaatio.components;

import com.dlsc.gemsfx.TimePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import org.controlsfx.control.spreadsheet.Grid;

public class TimeSelectorGrid {
    private final GridPane grid;
    public TimeSelectorGrid(TimePicker startTime, TimePicker endTime) {
        grid = new GridPane();
        grid.add(new Label("From"), 0, 0);
        grid.add(new Label("To"), 0, 1);
        grid.add(startTime, 1, 0);
        grid.add(endTime, 1, 1);
        //grid.setStyle("-fx-border-color: blue;"); // testailua varten
        grid.setHgap(40);
        grid.setVgap(10);
    }

    public GridPane getGrid() {
        return grid;
    }
}
