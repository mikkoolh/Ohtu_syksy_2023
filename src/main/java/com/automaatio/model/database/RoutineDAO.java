package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * @author Matleena Kankaanpää
 * 26.9.2023
 *
 * DAO for Routine class
 */

public class RoutineDAO {

    /**
     * Adds a new routine
     * @param routine A new routine
     */
    public void addRoutine(Routine routine) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            em.persist(routine);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Rethrow the exception for the caller to handle
        } finally {
            em.close();
        }
    }

    /**
     * Fetches all routines
     * @return A list of Routine objects
     */
    public List<Routine> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            TypedQuery<Routine> query = em.createQuery("SELECT r FROM Routine r", Routine.class);
            List<Routine> routines = query.getResultList();
            em.getTransaction().commit();
            return routines;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // Rethrow the exception for the caller to handle
        } finally {
            em.close();
        }
    }
}
