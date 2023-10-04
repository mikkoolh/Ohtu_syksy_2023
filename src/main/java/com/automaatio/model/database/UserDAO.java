package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import org.mindrot.jbcrypt.BCrypt;

import java.util.List;

/**
 * @author Mikko Hänninen
 * @author Nikita Nossenko
 * @author Matleena Kankaanpää
 * 15.9.2023
 */

public class UserDAO implements IDAO {

    /**
     * Adds a new user
     * @param user A new user
     */
    public void addObject(Object user) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist((User) user);
        em.getTransaction().commit();
    }

    @Override
    public Object getObject(int id) {
        return null;
    }

    /**
     * Fetches a user by username
     * @param username Username
     * @return User object
     */
    public User getObject(String username) {
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

    public void updatePassword(String username, String oldPassword, String newPassword) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            User user = em.find(User.class, username);

            if (user != null) {
                if (BCrypt.checkpw(oldPassword, user.getPassword())) {
                    String newHashedPassword = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                    user.setPassword(newHashedPassword);
                    em.merge(user);
                    em.getTransaction().commit();
                } else {
                    System.out.println("Väärä salasana");
                }
            } else {
                System.out.println("Käyttäjää ei löytynyt");
            }
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

    public void updateMaxPrice(double price, String username){
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        try {
            User user = em.find(User.class, username);
            user.setMaxPrice(price);
            em.merge(user);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("Käyttäjää ei löytynyt");
            throw e;
        } finally {
            em.close();
        }
    }

    public double getMaxPrice(String username){
        double maxPrice = 0;
        EntityManager em = MysqlDBJpaConn.getInstance();
        try (em) {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            if (user != null) {
                maxPrice = user.getMaxPrice();
                em.getTransaction().commit();
            } else {
                maxPrice = 0.0;
            }
        } catch (Exception e) {
            System.out.println("Ongelma tietojen hakemisessa.");
        }
        return maxPrice;
    }
}