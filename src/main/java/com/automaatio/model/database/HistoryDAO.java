package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * DAO for HistoryEvents.class
 */
public class HistoryDAO {

    /**
     * Adds a new history event
     * @param historyEvent A new history event
     */
    public void addHistoryEvent(HistoryEvents historyEvent) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(historyEvent);
        em.getTransaction().commit();
    }

    /**
     * Fetches a history event by its ID
     * @param id ID of the history event
     * @return HistoryEvents object
     */
    public HistoryEvents getHistoryEventById(Long id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        HistoryEvents historyEvent = em.find(HistoryEvents.class, id);
        em.getTransaction().commit();
        return historyEvent;
    }

    /**
     * Fetches all history events
     * @return A list of HistoryEvents objects
     */
    public List<HistoryEvents> getAllHistoryEvents() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<HistoryEvents> query = em.createQuery("SELECT h FROM HistoryEvents h", HistoryEvents.class);
            List<HistoryEvents> historyEvents = query.getResultList();
            return historyEvents;
        } finally {
            em.getTransaction().commit();
        }
    }
}
