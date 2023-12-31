package com.automaatio.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class GraphicalUI extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(GraphicalUI.class.getResource("/view/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("HomeAutomation v.0.01");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}