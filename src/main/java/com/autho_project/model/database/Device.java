package com.autho_project.model.database;

/**
* Author Nikita Nossenko
* 
* Device data entity
*/

import jakarta.persistence.*;

@Entity
@Table(name="device")
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "deviceID")
    private int deviceID;

    @Column(name = "on/off")
    private boolean onOff;

    @Column(name = "automation")
    private boolean automation;

    @Column(name = "usage")
    private Long usage;

    @Column(name = "name")
    private String name;

    @Column(name = "model_code")
    private String modelCode;

    /*@JoinColumn(name = "groupID")
    private...

    @JoinColumn(name = "typeID")
    private...*/

    public Device() {}

    public Device(boolean onOff, boolean automation, Long usage, String name, String modelCode) {
        this.onOff = onOff;
        this.automation = automation;
        this.usage = usage;
        this.name = name;
        this.modelCode = modelCode;
    }
}
