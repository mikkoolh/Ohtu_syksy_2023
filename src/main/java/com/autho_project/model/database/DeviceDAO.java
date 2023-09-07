package com.autho_project.model.database;

/**
* Author Nikita Nossenko
* 
* DAO for Device
*/

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class DeviceDAO {
    private final EntityManagerFactory entityManagerFactory;

    public DeviceDAO() {
    }
}
