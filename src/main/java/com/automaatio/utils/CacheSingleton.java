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
}
