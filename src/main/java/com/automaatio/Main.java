package com.automaatio;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;

/**
 * Author Mikko Hänninen
 * 02.09.2023
 *
 * Main class for the app
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        Device device = new Device(true, true, 100, "TestiLaite", "ABC1122");
        DeviceDAO deviceDao = new DeviceDAO();
        deviceDao.addDevice(device);
    }
}