package com.automaatio.model.database;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
* Author Nikita Nossenko
* 
* DAO for Device
*/


public class DeviceDAO {
    
    /**
     * Adds a new device
     * @param device A new device
     */
    public void addDevice(Device device) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(device);
        em.getTransaction().commit();
    }

    /**
     * Fetches a device by its ID
     * @param id ID of the device
     * @return Device object
     */
    public Device getDeviceType(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        Device device = em.find(Device.class, id);
        em.getTransaction().commit();
        return device;
    }

    /**
     * Fetches all devices
     * @return A list of Device objects
     */
    public List<Device> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<Device> query = em.createQuery("SELECT d FROM Device d", Device.class);
            List<Device> devices = query.getResultList();
            return devices;
        } finally {
            em.getTransaction().commit();
        }
    }
}
