package com.automaatio.model.database;


import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * Author: Elmo Erla
 *
 * DAO for Room
 */
public class RoomDAO {

    /**
     * Add a new room to the database.
     *
     * @param room Room entity to add
     */
    public void addRoom(Room room) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            em.persist(room);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetch a room from the database using its ID.
     *
     * @param roomId ID of the room to fetch
     * @return The room with the specified ID or null if not found
     */
    public Room getRoomById(int roomId) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        return em.find(Room.class, roomId);
    }

    /**
     * Fetch all rooms from the database.
     *
     * @return List of all rooms
     */
    public List<Room> getAllRooms() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        TypedQuery<Room> query = em.createQuery("SELECT r FROM Room r", Room.class);
        return query.getResultList();
    }

    /**
     * Update the details of a room in the database.
     *
     * @param room Room entity to update
     */
    public void updateRoom(Room room) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            em.merge(room);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Delete a room from the database using its ID.
     *
     * @param roomId ID of the room to delete
     */
    public void deleteRoom(int roomId) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        Room room = em.find(Room.class, roomId);

        if (room != null) {
            em.getTransaction().begin();
            try {
                em.remove(room);
                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
                throw e;
            } finally {
                em.close();
            }
        }
    }
}
