package com.automaatio.model.database;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Elmo Erla
 */
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "roomID")
    private int roomID;

    @Column(name = "deviceID")
    int deviceID;

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Device> devices = new ArrayList<>();

    public List<Device> getDevices() {
        return devices;
    }

    public void setDevices(List<Device> devices) {
        this.devices = devices;
    }

    public void setDeviceID(int id) { this.deviceID = id; }

    public int getDeviceID() { return deviceID; }

    public int getRoomID() { return roomID; }

    public void setRoomID(int id) { this.roomID = id; }
}
