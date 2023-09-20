package com.automaatio.controller.mainpage.menu;

public class RoomsController {
/*
    @FXML
    private void onShowRoomClick(ActionEvent event, DeviceGroup room) throws IOException {
        System.out.print("show room\n");

        Pane mainView = mainController.getMainPane();       //get the pane from mainController
        cache.setRoom(room);                                //set the room in singleton

        //Load the new room FXML-file, clear the mainView and set the newView
        try{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/room.fxml"));
            Parent newView = loader.load();
            mainView.getChildren().clear();
            mainView.getChildren().add(newView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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
    }*/
}
