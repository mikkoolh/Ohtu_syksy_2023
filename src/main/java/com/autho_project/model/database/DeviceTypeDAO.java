package com.autho_project.model.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.EntityManager;

/**
 * DAO for Device Type
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class DeviceTypeDAO {

    /**
     * Adds a new device type
     * @param deviceType A new device type
     */
    public void addDeviceType(DeviceType deviceType) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(deviceType);
        em.getTransaction().commit();
    }

    /**
     * Fetches a device type
     * @param id ID of the device type
     * @return Device type
     */
    public DeviceType getDeviceType(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        DeviceType deviceType = em.find(DeviceType.class, id);
        em.getTransaction().commit();
        return deviceType;
    }

    /**
     * Fetches all device types
     * @return A list of device types
     */
    public List<DeviceType> getAll() {
        // TODO
        return new ArrayList<DeviceType>();
    }
}
