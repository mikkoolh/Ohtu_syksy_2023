package com.automaatio.model.database;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

/**
 * Device type entity
 * @author Matleena Kankaanpää
 * 8.9.2023
 */

@Entity
@Table(name = "device_type")
public class DeviceType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_type_id")
    private int deviceTypeId;

    @Column
    private String description;

    @Column
    private String image;

    @ManyToMany
    @JoinTable(
            name = "device_features",
            joinColumns = @JoinColumn(name = "device_type_id"),
            inverseJoinColumns = @JoinColumn(name = "feature_id")
    )
    private List<Feature> features = new ArrayList<Feature>();

    /**
     * Parameterless constructor
     */
    public DeviceType() {}

    /**
     * Parameterized constructor
     * @param deviceTypeId Device type ID
     * @param description Description of the device type
     * @param image Image url
     */
    public DeviceType(int deviceTypeId, String description, String image) {
        this.deviceTypeId = deviceTypeId;
        this.description = description;
        this.image = image;
    }

    public int getDeviceTypeId() {
        return this.deviceTypeId;
    }

    public void setDeviceTypeId(int deviceTypeId) {
        this.deviceTypeId = deviceTypeId;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return this.image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}