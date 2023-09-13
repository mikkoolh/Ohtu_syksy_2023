package com.automaatio.model.database;

import jakarta.persistence.EntityManager;

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
}
