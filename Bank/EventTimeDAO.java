package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EventTimeDAO {

    public void addEventTime(EventTime eventTime) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(eventTime);
        em.getTransaction().commit();
    }

    public EventTime getEventTimeById(Long id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        EventTime eventTime = em.find(EventTime.class, id);
        em.getTransaction().commit();
        return eventTime;
    }

    public List<EventTime> getAllEventTimes() {
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

}
