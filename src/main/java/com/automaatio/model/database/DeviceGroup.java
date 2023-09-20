package com.automaatio.model.database;

import jakarta.persistence.*;

import java.util.List;

/**
 * Device group entity
 * @author Matleena Kankaanpää 10.9.2023
 * @author Mikko Hänninen 20.9.2023
 */

@Entity
@Table(name = "device_group")
public class DeviceGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "device_group_id")
    private int deviceGroupId;

    @Column
    private String name;

    @Column
    @OneToMany(mappedBy = "device", cascade = CascadeType.ALL)
    private List<Device> deviceList;

    /**
     * Parameterless constructor
     */
    public DeviceGroup() {}

    /**
     * Parameterized constructor
     * @param name Device group name
     */
    public DeviceGroup(String name) {
        this.name = name;
    }

    public int getDeviceGroupId() {
        return this.deviceGroupId;
    }

    public void setDeviceGroupId(int deviceGroupId) {
        this.deviceGroupId = deviceGroupId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addDevice(Device device){
        deviceList.add(device);
    }

    public void setDeviceList(List<Device> deviceList){
        this.deviceList = deviceList;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }
}
