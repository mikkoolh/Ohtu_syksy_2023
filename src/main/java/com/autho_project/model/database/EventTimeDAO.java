package com.autho_project.model.database;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * @author Nikita Nossenko
 * 11.09.2023
 *
 * DAO for EventTime.class
 */

public class EventTimeDAO {
    
    /**
     * Adds a new event time.
     * @param eventTime A new event time.
     */
    public void addEventTime(EventTime eventTime) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(eventTime);
        em.getTransaction().commit();
    }

    /**
     * Fetches event time by its ID
     * @param id    ID of the event time
     * @return  Event time object
     */
    public EventTime getEventTime(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        EventTime et = em.find(EventTime.class, id);
        em.getTransaction().commit();
        return et;
    }

    /**
     * Fetches all event times.
     * @return  A list of all event time objects.
     */
    public List<EventTime> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<EventTime> query = em.createQuery("SELECT e FROM EventTime e", EventTime.class);
            List<EventTime> eTimes = query.getResultList();
            return eTimes;
        } finally {
            em.getTransaction().commit();
        }
    }
}
