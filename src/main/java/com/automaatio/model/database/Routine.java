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
    private String user;

    @Column(name = "device_id")
    private int deviceID;

    @Column(name = "feature_id")
    private int featureID;

    @Column(name = "weekday_id")
    private int weekdayID;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    @Column(name = "automated")
    private boolean automated;

    public Routine() {}

    public Routine(String user, int deviceID, int featureID, int weekdayID, LocalDateTime startTime, LocalDateTime endTime) {
        this.user = user;
        this.deviceID = deviceID;
        this.featureID = featureID;
        this.weekdayID = weekdayID;
        this.startTime = startTime;
        this.endTime = endTime;
        this.automated = false; // False by default
    }

    public String getUser() {
        return user;
    }

    public int getFeatureID() {
        return featureID;
    }

    public int getDeviceID() {
        return deviceID;
    }

    public int getWeekdayID() {
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
