package com.automaatio.model.database;

import java.util.List;

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

    /**
     * Fetches all features
     * @return A list of Feature objects
     */
    public List<Feature> getAll() {
        EntityManager em = MysqlDBJpaConn.getInstance();
        em.getTransaction().begin();

        try {
            TypedQuery<Feature> query = em.createQuery("SELECT f FROM Feature f", Feature.class);
            List<Feature> features = query.getResultList();
            return features;
        } finally {
            em.getTransaction().commit();
        }
    }
}
