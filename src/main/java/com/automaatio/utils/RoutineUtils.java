package com.automaatio.utils;

import com.automaatio.model.database.Routine;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

/**
 * @author Matleena Kankaanpää
 * 30.9.2023
 *
 * Utility class for sorting routine lists
 */

public class RoutineUtils {

    /**
     * Sorts a list of routines by weekday
     * @param routines A list of routines
     * @return A list of routines sorted by weekday
     */
    public List<Routine> sortByWeekday(List<Routine> routines) {
        return routines.stream()
                .sorted(Comparator.comparing(routine -> routine.getEventTime().getWeekday().getWeekdayId()))
                .toList();
    }

    /**
     * Sorts a list of routines by time
     * @param routines A list of routines
     * @return A list of routines sorted by hour and minute
     */
    public List<Routine> sortByTime(List<Routine> routines) {
        return routines.stream()
                .sorted(Comparator.comparing(routine -> routine.getEventTime().getStartTime().toLocalTime()))
                .toList();
    }

    /**
     * Checks if all routines in a list are automated
     * @param routines A list of routines
     * @return True if automation is enabled for each routine
     */
    public boolean allAutomated(List<Routine> routines) {
        for (Routine routine : routines) {
            if (!routine.getAutomated()) {
                return false;
            }
        }
        return true;
    }

    /**
     * Time formatter
     * @param time A LocalDateTime object
     * @return A string in HH:mm format
     */
    public String getFormattedTime(LocalDateTime time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return time.format(formatter);
    }
}
