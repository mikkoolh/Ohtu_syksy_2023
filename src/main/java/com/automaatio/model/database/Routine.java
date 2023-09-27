package com.automaatio.model.database;

import jakarta.persistence.*;
import java.time.LocalDateTime;

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

    @Column(name = "username")
    private User user;

    @Column(name = "device_id")
    private Device deviceID;

    @Column(name = "feature_id")
    private Feature featureID;

    @Column(name = "weekday_id")
    private Weekday weekdayID;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    @Column(name = "automated")
    private boolean automated;

    public Routine() {}

    public Routine(User user, Device deviceID, Feature featureID, Weekday weekdayID, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.deviceID = deviceID;
        this.featureID = featureID;
        this.weekdayID = weekdayID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.automated = false; // False by default
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

    public Weekday getWeekdayID() {
        return weekdayID;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public boolean getAutomated() {
        return automated;
    }
}
