package com.autho_project.model.database;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * @Author Elmo Erla
 * 11.09.2023
 *
 * DAO for Weekday.class
 */

public class WeekdayDAO {

    /**
     * Add new weekday
     * @param day New weekday
     */
    public void addWeekday(Weekday day) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(day);
        em.getTransaction().commit();
    }

    /**
     * Fetches all weekdays
     * @return A list of weekdays
     */
    public List<Weekday> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<Weekday> query = em.createQuery("SELECT w FROM Weekday w", Weekday.class);
            List<Weekday> weekdays = query.getResultList();
                return weekdays;
        } finally {
            em.getTransaction().commit();
        }
    }
}
