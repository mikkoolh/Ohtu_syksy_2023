package com.automaatio.model.database;
import jakarta.persistence.EntityManager;

/**
 * DAO for Manufacturer
 * @author Matleena Kankaanpää
 * 9.9.2023
 */

public class ManufacturerDAO {

    /**
     * Adds a new manufacturer
     * @param manufacturer A new manufacturer
     */
    public void addManufacturer(Manufacturer manufacturer) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(manufacturer);
        em.getTransaction().commit();
    }

    /**
     * Fetches a manufacturer
     * @param id ID of the manufacturer
     * @return Manufacturer object
     */
    public Manufacturer getManufacturer(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        Manufacturer manufacturer = em.find(Manufacturer.class, id);
        em.getTransaction().commit();
        return manufacturer;
    }
}