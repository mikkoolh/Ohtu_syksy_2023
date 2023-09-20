package com.automaatio.controller;

import com.automaatio.model.database.DeviceGroup;
import com.automaatio.utils.CacheSingleton;
import com.automaatio.utils.NavigationUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController implements Initializable {
    private final CacheSingleton cache = CacheSingleton.getInstance();
    private MainPageController mainController;

    @FXML
    private TextField newRoomTextField;
    @FXML
    private VBox roomsVBox;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public void setMainController(MainPageController mainController){
        this.mainController = mainController;
    }

    @FXML
    private void onShowRoomClick(ActionEvent event, DeviceGroup room) throws IOException {
        System.out.print("show room\n");

        Pane mainView = mainController.getMainPane();       //get the pane from mainController
        cache.setRoom(room);                                //set the room in singleton

            //Load the new room FXML-file, clear the mainView and set the newView
            try{
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/show-room.fxml"));
                Parent newView = loader.load();
                mainView.getChildren().clear();
                mainView.getChildren().add(newView);
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    /**
     * Adds a new group when a new _room_ is created.
     *
     * @param event
     */
    @FXML
    private void onAddGroupClick(ActionEvent event) {
        DeviceGroup newRoom = new DeviceGroup(newRoomTextField.getText());

        newRoomTextField.setText("");                                   //clear the textfield
        roomsVBox.getChildren().add(makeVBoxForNewRoom(newRoom));               //add the new room to VBox

        // Show the room page
        try {
            onShowRoomClick(event, newRoom);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Added group/room: " + newRoom.getName());
    }



    private HBox makeVBoxForNewRoom(DeviceGroup room){
        HBox box = new HBox();
        box.setSpacing(20);

        Button showRoom = new Button("Open");
        showRoom.setFont(Font.font("Copperplate Gothic Bold", 16.0));
        showRoom.getProperties().put("room", room);
        showRoom.setOnAction(event -> {
            DeviceGroup savedRoom = (DeviceGroup) showRoom.getProperties().get("room");
            try {
                onShowRoomClick(event, savedRoom);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        Text title = new Text(room.getName());
        title.setFont(Font.font("Copperplate Gothic Bold", 22.0));

        box.getChildren().add(title);
        box.getChildren().add(showRoom);
        return box;
    }

    @FXML
    private void onLogoutClick(ActionEvent event) throws IOException {
        System.out.println("log out");

        // Siirry login-sivulle
        NavigationUtil nav = new NavigationUtil();
        nav.openLoginPage(event);
    }
}
