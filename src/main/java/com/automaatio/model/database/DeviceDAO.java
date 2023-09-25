package com.automaatio.model.database;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
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

    public List<Device> getDevicesByUserName(String userName) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            TypedQuery<Device> query = em.createQuery("SELECT d FROM Device d WHERE d.userName = :userName", Device.class);
            query.setParameter("userName", userName);
            List<Device> devices = query.getResultList();
            em.getTransaction().commit();
            return devices;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        String sql = "DELETE FROM Device";
        Query query = em.createQuery(sql);

        int deletedCount = query.executeUpdate();

        em.getTransaction().commit();

        System.out.println("Poistettu " + deletedCount + " laitetta.");

    }

    /**
     * Deletes a device with a specific ID from the database.
     *
     * @param id The ID of the device to be deleted.
     * @throws IllegalArgumentException If a device with the given ID is not found.
     */
    public void deleteDevice(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            Device device = em.find(Device.class, id);
            if (device != null) {
                em.remove(device);
            } else {
                throw new IllegalArgumentException("device with id  " + id + " was not found");
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }

    }
}
