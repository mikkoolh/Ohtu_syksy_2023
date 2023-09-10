package com.autho_project.model.database;

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
    @Column(name = "on/off")
    private boolean onOff;

    /**
     * Indicates whether the device is set for automation.
     */
    @Column(name = "automation")
    private boolean automation;

    /**
     * The usage data associated with the device.
     */
    @Column(name = "usage")
    private Long usage;

    /**
     * The name of the device.
     */
    @Column(name = "name")
    private String name;

    /**
     * The model code of the device.
     */
    @Column(name = "model_code")
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
     * @param usage     The usage data associated with the device.
     * @param name      The name of the device.
     * @param modelCode The model code of the device.
     */
    public Device(boolean onOff, boolean automation, Long usage, String name, String modelCode) {
        this.onOff = onOff;
        this.automation = automation;
        this.usage = usage;
        this.name = name;
        this.modelCode = modelCode;
    }
}
