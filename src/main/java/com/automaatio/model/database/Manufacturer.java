package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * Manufacturer entity
 * @author Matleena Kankaanpää
 * 9.9.2023
 */

@Entity
@Table(name = "manufacturer")
public class Manufacturer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "manufacturer_id")
    private int manufacturerId;

    @Column
    private String name;

    /**
     * Parameterless constructor
     */
    public Manufacturer() {}

    /**
     * Parameterized constructor
     * @param manufacturerId Manufacturer ID
     * @param name Manufacturer name
     */
    public Manufacturer(int manufacturerId, String name) {
        this.manufacturerId = manufacturerId;
        this.name = name;
    }

    public int getManufacturerId() {
        return this.manufacturerId;
    }

    public void setManufacturerId(int manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
