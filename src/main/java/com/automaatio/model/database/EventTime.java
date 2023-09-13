package com.automaatio.model.database;

 /**
 * Author Mikko Hänninen
 * 03.09.2023
 *
 * Class for EventTime Table
 */

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="event_time")
public class EventTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_time_id")
    private Long eventTimeId;

    @Column(name = "start_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime startTime;

    @Column(name = "end_time", columnDefinition = "TIMESTAMP")
    private LocalDateTime endTime;

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
