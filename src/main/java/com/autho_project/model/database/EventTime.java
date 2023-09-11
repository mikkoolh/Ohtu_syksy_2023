package com.autho_project.model.database;

 /**
 * @author Mikko Hänninen
 * @author Nikita Nossenko
 * 03.09.2023
 *
 * Class for EventTime Table
 */

import jakarta.persistence.*;
import java.util.Date;


@Entity
@Table(name="event_time")
public class EventTime {
    /**
     * The unique identifier for the event time.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "timeID")
    private int timeID;

    /**
     * Indicates starting time.
     */
    @Column(name = "startTime")
    private Date startTime;

    /**
     * Indicates ending time.
     */
    @Column(name = "endTime")
    private Date endTime;

    /**
     * Default constructor for creating a new EventTime instance.
     */
    public EventTime() {}

    /**
     * Constructs a new EventTime instance with specified starting and ending times.
     * @param startTime Instantiates starting time.
     * @param endTime   Instantiates ending time.
     */
    public EventTime(Date startTime, Date endTime) {
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
