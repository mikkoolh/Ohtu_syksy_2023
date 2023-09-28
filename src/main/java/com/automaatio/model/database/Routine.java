package com.automaatio.model.database;

import jakarta.persistence.*;

/**
 * @author Matleena Kankaanpää
 * 26.9.2023
 *
 * Routine entity
 */

@Entity
@Table(name = "routine")
public class Routine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "routine_id")
    private int routineID;

    @ManyToOne
    @JoinColumn(name = "username")
    private User user;

    @ManyToOne
    @JoinColumn(name = "device_id")
    private Device deviceID;

    @ManyToOne
    @JoinColumn(name = "feature_id")
    private Feature featureID;

    @ManyToOne
    @JoinColumn(name = "event_time_id")
    private EventTime eventTime;

    @Column(name = "automated")
    private boolean automated;

    public Routine() {}

    public Routine(User user, Device deviceID, Feature featureID, EventTime eventTime) {
        this.user = user;
        this.deviceID = deviceID;
        this.featureID = featureID;
        this.eventTime = eventTime;
        this.automated = false; // False by default
    }

    public int getRoutineID() {
        return routineID;
    }

    public User getUser() {
        return user;
    }

    public Feature getFeatureID() {
        return featureID;
    }

    public Device getDeviceID() {
        return deviceID;
    }

    public EventTime getEventTime() { return eventTime; }

    public boolean getAutomated() {
        return automated;
    }

    public void setAutomated(boolean automated) {
        this.automated = automated;
    }
}
