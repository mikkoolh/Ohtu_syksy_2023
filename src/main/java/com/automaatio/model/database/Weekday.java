package com.automaatio.model.database;

/**
 * @author Mikko Hänninen
 * @author Elmo Erla
 * 11.09.2023
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

    @Column
    private String name;

    public Weekday() {}

    /**
     * Parametrized constructor
     * @param id Weekdays id
     * @param name name of the day
     */
    public Weekday(int id, String name) {
        this.weekdayId = id;
        this.name = name;
    }

    /*
    @OneToMany(mappedBy = "weekdays")
    private List<EventTime> eventTimes;
     */

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

    /*
    hajotti testit atm

    public List<EventTime> getEventTimes() {
        return eventTimes;
    }

    public void setEventTimes(List<EventTime> eventTimes) {
        this.eventTimes = eventTimes;
    }

     */
}