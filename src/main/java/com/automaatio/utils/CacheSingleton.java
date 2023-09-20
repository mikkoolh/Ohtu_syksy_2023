package com.automaatio.utils;

import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.User;
import javafx.scene.layout.Pane;

public class CacheSingleton {

    private static CacheSingleton instance;
    private DeviceGroup room;
    private User user;

    private Pane mainPane, menuPane;

    private CacheSingleton() {
    }

    public static CacheSingleton getInstance() {
        if (instance == null) {
            instance = new CacheSingleton();
        }
        return instance;
    }

    public void setUser(User user){
        this.user = new User(user.getUsername(), user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail(), user.getPassword(), user.getAge(), user.getUserType());
    }

    public void setRoom(DeviceGroup room) {
        System.out.println("room set in singleton: " +room.getName());
        this.room = room;
    }

    public User getUser(){
        return user;
    }

    public DeviceGroup getRoom(){
        System.out.println("getRoom in singleton: " +room.getName());
        return room;
    }

    public Pane getMainPane() {
        return mainPane;
    }

    public Pane getMenuPane() {
        return menuPane;
    }

    public void setMainPane(Pane mainPane) {
        this.mainPane = mainPane;
    }

    public void setMenuPane(Pane menuPane) {
        this.menuPane = menuPane;
    }
}
