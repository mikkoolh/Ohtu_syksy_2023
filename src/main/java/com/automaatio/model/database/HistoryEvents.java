package com.automaatio.model.database;

 /**
 * Author Mikko Hänninen
 * 02.09.2023
 *
 * Class for history table
 */

import jakarta.persistence.*;

@Entity
@Table(name = "history_events")
public class HistoryEvents {

    @Id
    @Column(name = "event_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long eventId;

    @ManyToOne
    @Column(name = "event_time")
    @JoinColumn(name = "event_time_id")
    private EventTime eventTime;

    @ManyToOne
    @JoinColumn(name = "username")
    private User username;

    //TODO private Device device
    //TODO private Feature feature


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
}
