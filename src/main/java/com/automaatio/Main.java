package com.automaatio;

import com.automaatio.model.database.*;
import com.automaatio.view.GraphicalUI;
import jakarta.persistence.EntityManager;
import javafx.application.Application;

/**
 * Author Mikko Hänninen
 * 02.09.2023
 *
 * Main class for the app
 */

public class Main {
    public static void main(String[] args) {
        Application.launch(GraphicalUI.class, args);

    }
}