package com.automaatio.model.database;

import java.util.List;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * DAO for Device Group
 * @author Matleena Kankaanpää
 * 10.9.2023
 */

public class DeviceGroupDAO {

    /**
     * Adds a new device group
     * @param deviceGroup A new device group
     */
    public void addDeviceGroup(DeviceGroup deviceGroup) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(deviceGroup);
        em.getTransaction().commit();
    }

    /**
     * Fetches a device group
     * @param id ID of the device group
     * @return Device Group object
     */
    public DeviceGroup getDeviceGroup(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        DeviceGroup deviceGroup = em.find(DeviceGroup.class, id);
        em.getTransaction().commit();
        return deviceGroup;
    }

    /**
     * Fetches all device groups
     * @return A list of Device Group objects
     */
    public List<DeviceGroup> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<DeviceGroup> query = em.createQuery("SELECT d FROM DeviceGroup d", DeviceGroup.class);
            List<DeviceGroup> deviceGroups = query.getResultList();
            return deviceGroups;
        } finally {
            em.getTransaction().commit();
        }
    }
}
