package com.automaatio.model.database;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

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

        try {
            em.persist(deviceType);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Rethrow the exception for the caller to handle
        } finally {
            em.close();
        }
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
     * @return A list of Device Type objects
     */
    public List<DeviceType> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<DeviceType> query = em.createQuery("SELECT d FROM DeviceType d", DeviceType.class);
            List<DeviceType> deviceTypes = query.getResultList();
            em.getTransaction().commit();
            return deviceTypes;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Rethrow the exception for the caller to handle
        } finally {
            em.close();
        }
    }

    /**
     * Deletes all device types
     */
    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        String sql = "DELETE FROM DeviceType ";
        Query query = em.createQuery(sql);

        int deletedCount = query.executeUpdate();

        em.getTransaction().commit();

        System.out.println("Poistettu " + deletedCount + " laitetyyppiä.");
    }
}
