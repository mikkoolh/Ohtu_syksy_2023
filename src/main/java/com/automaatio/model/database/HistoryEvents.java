package com.automaatio.model.database;

import jakarta.persistence.*;

@Entity
@Table(name = "history_events")
public class HistoryEvents {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ManyToOne
    @JoinColumn(name = "event_time_id")
    private EventTime eventTime;

    @ManyToOne
    @JoinColumn(name = "username")
    private User username;

    @ManyToOne
    @JoinColumn(name = "device_id") // Add the appropriate column name
    private Device device;

    @ManyToOne
    @JoinColumn(name = "feature_id") // Add the appropriate column name
    private Feature feature;

    public Long getEventId() {
        return eventId;
    }

    public void setEventId(Long eventId) {
        this.eventId = eventId;
    }

    public EventTime getEventTime() {
        return eventTime;
    }

    public void setEventTime(EventTime eventTime) {
        this.eventTime = eventTime;
    }

    public User getUser() {
        return username;
    }

    public void setUser(User user) {
        username = user;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    public Feature getFeature() {
        return feature;
    }

    public void setFeature(Feature feature) {
        this.feature = feature;
    }
}
