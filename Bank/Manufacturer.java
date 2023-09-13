package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * Manufacturer entity
 * Author: Matleena Kankaanpää
 * Date: 9.9.2023
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
     * @param name Manufacturer name
     */
    public Manufacturer(String name) {
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
