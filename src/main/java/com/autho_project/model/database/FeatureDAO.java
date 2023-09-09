package com.autho_project.model.database;

import jakarta.persistence.*;

/**
 * DAO for Device Feature
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

public class FeatureDAO {

    /**
     * Adds a new feature
     * @param feature A new feature
     */
    public void addFeature(Feature feature) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        em.persist(feature);
        em.getTransaction().commit();
    }

    /**
     * Fetches a feature
     * @param id ID of the feature
     * @return Feature object
     */
    public Feature getFeature(int id) {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();
        Feature feature = em.find(Feature.class, id);
        em.getTransaction().commit();
        return feature;
    }
}
