package com.automaatio.model.database;

import java.util.List;

import com.automaatio.utils.CacheSingleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

/**
 * DAO for Device Group
 * @author Matleena Kankaanpää
 * @author Elmo Erla
 * 10.9.2023
 */

public class DeviceGroupDAO implements IDAO {

    /**
     * Adds a new device group
     * @param deviceGroup A new device group
     */
    public void addObject(Object deviceGroup) {
        try (EntityManager em = MysqlDBJpaConn.getInstance()) {
            em.getTransaction().begin();
            em.persist((DeviceGroup) deviceGroup);
            em.getTransaction().commit();
        }
    }

    /**
     * Fetches a device group
     * @param id ID of the device group
     * @return Device Group object
     */
    public DeviceGroup getObject(int id) {
        try (EntityManager em = MysqlDBJpaConn.getInstance()) {
            em.getTransaction().begin();
            DeviceGroup deviceGroup = em.find(DeviceGroup.class, id);
            em.getTransaction().commit();
            return deviceGroup;
        }
    }

    @Override
    public Object getObject(String s) {
        return null;
    }

    /**
     * Fetches all device groups
     * @return A list of Device Group objects
     */
    public List<DeviceGroup> getAll() {
        try (EntityManager em = MysqlDBJpaConn.getInstance()) {
            em.getTransaction().begin();
            TypedQuery<DeviceGroup> query = em.createQuery("SELECT d FROM DeviceGroup d", DeviceGroup.class);
            List<DeviceGroup> deviceGroups = query.getResultList();
            em.getTransaction().commit();
            return deviceGroups;
        }
    }

    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        String sql = "DELETE FROM DeviceGroup ";
        Query query = em.createQuery(sql);

        int deletedCount = query.executeUpdate();

        em.getTransaction().commit();

        System.out.println("Poistettu " + deletedCount + " ryhmää.");
    }

    public List<DeviceGroup> getRoomsByUser(User user) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        TypedQuery<DeviceGroup> query = em.createQuery(
                "SELECT dg FROM DeviceGroup dg WHERE dg.user = :userObj", DeviceGroup.class);
        query.setParameter("userObj", user);
        List<DeviceGroup> deviceGroups = query.getResultList();
        em.getTransaction().commit();
        return deviceGroups;
    }

    public List<Device> getDevicesByRoom(DeviceGroup deviceGroup) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        TypedQuery<Device> query = em.createQuery(
                "SELECT d FROM Device d WHERE d.deviceGroup = :deviceGroupObj", Device.class);
        query.setParameter("deviceGroupObj", deviceGroup);
        List<Device> devices = query.getResultList();
        em.getTransaction().commit();
        return devices;
    }

    /**
     * Removes a device from a specific device group
     * @param deviceGroup The device group from which the device should be removed
     * @param device The device to be removed
     */
    public void removeDeviceFromGroup(DeviceGroup deviceGroup, Device device) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        device.setDeviceGroup(null);
        em.merge(device);
        em.getTransaction().commit();
    }

    public List<Device> getDevicesNotInGroup(int groupId, String user) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        TypedQuery<Device> query = em.createQuery(
                "SELECT d FROM Device d WHERE (d.deviceGroup.id != :groupId OR d.deviceGroup IS NULL) AND d.userName = :user", Device.class);
        query.setParameter("groupId", groupId);
        query.setParameter("user", user);

        List<Device> devicesNotInGroup = query.getResultList();

        em.getTransaction().commit();
        return devicesNotInGroup;
    }

    /**
     * Deletes a specific device group
     * @param deviceGroupId ID of the device group to be deleted
     */
    public void deleteGroup(int deviceGroupId) {
        try (EntityManager em = MysqlDBJpaConn.getInstance()) {
            em.getTransaction().begin();
            deleteGroupWithoutTransaction(em, deviceGroupId);
            em.getTransaction().commit();
        }
    }

    public List<Device> getDevicesByRoomWithoutTransaction(EntityManager em, DeviceGroup deviceGroup) {
        TypedQuery<Device> query = em.createQuery(
                "SELECT d FROM Device d WHERE d.deviceGroup = :deviceGroupObj", Device.class);
        query.setParameter("deviceGroupObj", deviceGroup);
        return query.getResultList();
    }

    public void deleteGroupWithoutTransaction(EntityManager em, int deviceGroupId) {
        DeviceGroup deviceGroup = em.find(DeviceGroup.class, deviceGroupId);
        if (deviceGroup != null) {
            for (Device device : getDevicesByRoomWithoutTransaction(em, deviceGroup)) {
                device.setDeviceGroup(null);
                em.merge(device);
            }
            em.remove(deviceGroup);
        }
    }
}
