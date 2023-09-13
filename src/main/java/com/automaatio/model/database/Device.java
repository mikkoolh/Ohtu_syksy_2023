package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * Author: Nikita Nossenko
 * 
 * This class represents a Device entity that is stored in the database.
 */
@Entity
@Table(name = "device")
public class Device {
    /**
     * The unique identifier for the device.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceID")
    private int deviceID;

    /**
     * Indicates whether the device is currently on or off.
     */
    @Column(name = "onOff")
    private boolean onOff;

    /**
     * Indicates whether the device is set for automation.
     */
    @Column(name = "automation")
    private boolean automation;

    /**
     * The usage data associated with the device.
     */
    @Column(name = "usageData")
    private long usageData;

    /**
     * The name of the device.
     */
    @Column(name = "name")
    private String name;

    /**
     * The model code of the device.
     */
    @Column(name = "modelCode")
    private String modelCode;

    /**
     * Default constructor for creating a new Device instance.
     */
    public Device() {}

    /**
     * Constructs a new Device instance with the specified parameters.
     *
     * @param onOff     Whether the device is on or off.
     * @param automation    Whether the device is set for automation.
     * @param usageData     The usage data associated with the device.
     * @param name      The name of the device.
     * @param modelCode The model code of the device.
     */
    public Device(boolean onOff, boolean automation, long usageData, String name, String modelCode) {
        this.onOff = onOff;
        this.automation = automation;
        this.usageData = usageData;
        this.name = name;
        this.modelCode = modelCode;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(int deviceID) {
        this.deviceID = deviceID;
    }

    public boolean isOnOff() {
        return onOff;
    }

    public void setOnOff(boolean onOff) {
        this.onOff = onOff;
    }

    public boolean isAutomation() {
        return automation;
    }

    public void setAutomation(boolean automation) {
        this.automation = automation;
    }

    public long getUsageData() {
        return usageData;
    }

    public void setUsageData(long usageData) {
        this.usageData = usageData;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }
}
