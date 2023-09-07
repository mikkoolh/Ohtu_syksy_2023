package com.autho_project.model.database;

 /**
 * Author Mikko Hänninen
 * 02.09.2023
 *
 * DAO for User.class
 */

import jakarta.persistence.EntityManager;

public class UserDAO {

    /**
     *
     * Used to add new user
     */
    public void addUser(User user) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();

    }
}
