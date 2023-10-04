package com.automaatio.model.database;
import java.util.List;

import jakarta.persistence.*;

/**
 * DAO for EventTime
 * @author Matleena Kankaanpää
 * 28.9.2023
 */

public class EventTimeDAO implements IDAO {

    /**
     * Adds a new event
     * @param eventTime A new event
     */
    public void addObject(Object eventTime) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            em.merge((EventTime) eventTime);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches an event
     * @param id ID of the event
     * @return EventTime object
     */
    public EventTime getObject(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            EventTime eventTime = em.find(EventTime.class, id);
            em.getTransaction().commit();
            return eventTime;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    @Override
    public Object getObject(String s) {
        return null;
    }

    /**
     * Fetches all events
     * @return A list of EventTime objects
     */
    public List<EventTime> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<EventTime> query = em.createQuery("SELECT e FROM EventTime e", EventTime.class);
            List<EventTime> eventTimes = query.getResultList();
            return eventTimes;
        } finally {
            em.getTransaction().commit();
        }
    }

    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            String sql = "DELETE FROM EventTime";
            Query query = em.createQuery(sql);
            int deletedCount = query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Poistettu " + deletedCount + " tapahtuma-aikaa.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}