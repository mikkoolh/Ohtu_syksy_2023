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
        try {
            em.persist(device);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Rethrow the exception for the caller to handle
        } finally {
            em.close();
        }
    }

    /**
     * Fetches a device by its ID
     * @param id ID of the device
     * @return Device object
     */
    public Device getDeviceType(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            Device device = em.find(Device.class, id);
            em.getTransaction().commit();
            return device;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Rethrow the exception for the caller to handle
        } finally {
            em.close();
        }
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
            em.getTransaction().commit();
            return devices;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Rethrow the exception for the caller to handle
        } finally {
            em.close();
        }
    }
}
