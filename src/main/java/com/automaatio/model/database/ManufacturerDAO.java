package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

/**
 * DAO for Manufacturer
 * @author Matleena Kankaanpää, Elmo Erla
 * 9.9.2023
 */
public class ManufacturerDAO {

    /**
     * Adds a new manufacturer
     * @param manufacturer A new manufacturer
     */
    public void addManufacturer(Manufacturer manufacturer) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            em.merge(manufacturer);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }

    /**
     * Fetches a manufacturer
     * @param id ID of the manufacturer
     * @return Manufacturer object
     */
    public Manufacturer getManufacturer(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            Manufacturer manufacturer = em.find(Manufacturer.class, id);
            em.getTransaction().commit();
            return manufacturer;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    /**
     * Retrieves a list of all manufacturers from the database.
     *
     * @return a List containing all the Manufacturer entities present in the database.
     * @throws RuntimeException if any error occurs during the retrieval.
     */
    public List<Manufacturer> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            TypedQuery<Manufacturer> query = em.createQuery("SELECT m FROM Manufacturer m", Manufacturer.class);
            List<Manufacturer> manufacturers = query.getResultList();
            em.getTransaction().commit();
            return manufacturers;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
    /**
     * Deletes all manufacturer entries from the database.
     *
     * @throws RuntimeException if any error occurs during the delete operation.
     */
    public void deleteAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        try {
            em.getTransaction().begin();
            String sql = "DELETE FROM Manufacturer";
            Query query = em.createQuery(sql);
            int deletedCount = query.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Poistettu " + deletedCount + " valmistajaa.");
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        } finally {
            em.close();
        }
    }
}
