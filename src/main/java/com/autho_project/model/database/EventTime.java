package com.autho_project.model.database;

import com.autho_project.model.database.HistoryEvents;
import com.autho_project.model.database.Weekday;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author Mikko Hänninen
 * @author Nikita Nossenko
 * 03.09.2023
 *
 * Class for EventTime Table
 */

@Entity
@Table(name="event_time")
public class EventTime {

    /**
     * The unique identifier for the event time.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_time_id")
    private Long eventTimeId;

    /**
     * Indicates starting time.
     */
    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    /**
     * Indicates ending time.
     */
    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

    /**
     * Default constructor for creating a new EventTime instance.
     */
    public EventTime() {}

    /**
     * Constructs a new EventTime instance with specified starting and ending times.
     * @param startTime Instantiates starting time.
     * @param endTime   Instantiates ending time.
     */
    public EventTime(LocalDateTime startTime, LocalDateTime endTime) {

    }
    @ManyToOne
    @JoinColumn(name = "weekday_id")
    private Weekday weekday;

    @OneToMany(mappedBy = "eventTime")
    @Column(name = "history_events")
    private List<HistoryEvents> historyEvents;

    public Long getEventTimeId() {
        return eventTimeId;
    }

    public void setEventTimeId(Long eventTimeId) {
        this.eventTimeId = eventTimeId;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public Weekday getWeekday() {
        return weekday;
    }

    public void setWeekday(Weekday weekday) {
        this.weekday = weekday;
    }
}