package com.automaatio.model.database;

 /**
 * Author Mikko Hänninen
 * 03.09.2023
 *
 * Weekday-table for EventTime
 */

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "weekday")
public class Weekday {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekday_id")
    private int weekdayId;

    @Column(name = "name", unique = true)
    private String name;

    @OneToMany(mappedBy = "weekday")
    @Column(name = "event_times")
    private List<EventTime> eventTimes;

    public Weekday() {}

    public Weekday(String name) {
        this.name = name;
    }

    public int getWeekdayId() {
        return weekdayId;
    }

    public void setWeekdayId(int weekdayId) {
        this.weekdayId = weekdayId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EventTime> getEventTimes() {
        return eventTimes;
    }

    public void setEventTimes(List<EventTime> eventTimes) {
        this.eventTimes = eventTimes;
    }
}
