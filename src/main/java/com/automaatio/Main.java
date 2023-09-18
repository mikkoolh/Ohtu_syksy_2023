package com.automaatio;

import com.automaatio.model.database.*;
import jakarta.persistence.EntityManager;

/**
 * Author Mikko Hänninen
 * 02.09.2023
 *
 * Main class for the app
 */

public class Main {
    public static void main(String[] args) {
        System.out.println("Let's cook!");

        EntityManager em = MysqlDBJpaConn.getInstance();

        /*
        // Testataan db-yhteyttä, ei vielä lisätä mitään.
        try {
            if (em != null) {
                System.out.println("Connected to the database.");
            } else {
                System.err.println("Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Failed to connect to the database: " + e.getMessage());
        } finally {
            MysqlDBJpaConn.close();
        }


        DeviceDAO deviceDAO = new DeviceDAO();

        Device device = new Device(true, true, 100, "TestiLaite", "ABC123");

        int deviceId = 1;
        Device fetchedDevice = deviceDAO.getDeviceType(deviceId);

        if (fetchedDevice != null) {
            System.out.println("Fetched device: " + fetchedDevice.getName());
        } else {
            System.out.println("Device not found.");
        }
         */
    }
}