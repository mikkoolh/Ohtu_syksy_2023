package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * @author Mikko Hänninen
 * @author Nikita Nossenko
 * @author Matleena Kankaanpää
 * 15.9.2023
 */

public class UserDAO {

    /**
     * Adds a new user
     * @param user A new user
     */
    public void addUser(User user) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }

    /**
     * Fetches a user by username
     * @param username Username
     * @return User object
     */
    public User getUser(String username) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            User user = em.find(User.class, username);
            em.getTransaction().commit();
            return user;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches all users
     * @return A list of users
     */
    public List<User> getAll() {
        try (EntityManager em = MysqlDBJpaConn.getInstance()) {
            em.getTransaction().begin();
            TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
            List<User> users = query.getResultList();
            em.getTransaction().commit();
            return users;
        }
    }

    /**
     * Deletes all users
     */
    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        String sql = "DELETE FROM User ";
        Query query = em.createQuery(sql);

        int deletedCount = query.executeUpdate();

        em.getTransaction().commit();

        System.out.println("Poistettu " + deletedCount + " käyttäjää.");
    }
}
