package com.automaatio.utils;

import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.User;

public class CacheSingleton {

    private static CacheSingleton instance;
    private DeviceGroup room;
    private User user;

    private CacheSingleton() {
    }

    public static CacheSingleton getInstance() {
        if (instance == null) {
            instance = new CacheSingleton();
        }
        return instance;
    }

    public void setUser(User user){
        this.user = user;
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
}
