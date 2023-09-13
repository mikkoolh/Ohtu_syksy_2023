package com.automaatio.model.database;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

/**
 * Author Nikita Nossenko
 *
 * DAO for UserType.
 */

public class UserTypeDAO {

    /**
     * Adds a new User Type
     * @param userType A new User Type
     */
    public void addUserType(UserType userType) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(userType);
        em.getTransaction().commit();
    }

    /**
     * Fetches a User Type by its ID
     * @param id ID of UserType
     * @return UserType object
     */
    public UserType getUserType(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        UserType userType = em.find(UserType.class, id);
        em.getTransaction().commit();
        return userType;
    }

    /**
     * Fetches all User Types
     * @return A list of UserType objects
     */
    public List<UserType> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<UserType> query = em.createQuery("SELECT u FROM UserType u", UserType.class);
            List<UserType> userTypes = query.getResultList();
            return userTypes;
        } finally {
            em.getTransaction().commit();
        }
    }
}
