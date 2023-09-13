package com.automaatio.model.database;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;

/**
 * DAO for Manufacturer
 * Author: Matleena Kankaanpää
 * Date: 9.9.2023
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
     * Fetches a manufacturer by its ID
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

    /**
     * Fetches all manufacturers
     * @return A list of Manufacturer objects
     */
    public List<Manufacturer> getAllManufacturers() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<Manufacturer> query = em.createQuery("SELECT m FROM Manufacturer m", Manufacturer.class);
            List<Manufacturer> manufacturers = query.getResultList();
            return manufacturers;
        } finally {
            em.getTransaction().commit();
        }
    }
}
