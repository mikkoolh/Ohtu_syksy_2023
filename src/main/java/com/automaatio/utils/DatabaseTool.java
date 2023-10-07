package com.automaatio.utils;

import com.automaatio.model.database.Weekday;
import com.automaatio.model.database.WeekdayDAO;

public class DatabaseTool {
    private static WeekdayDAO weekdayDAO = new WeekdayDAO();

    // ettei tarvi aina käydä lisäämässä tietokantaan uudestaan kun on ajettu testejä :D
    public static void resetWeekdays() {
        if (weekdayDAO.getAll().size() != 7) {
            weekdayDAO.deleteAll();
            weekdayDAO.addWeekday(new Weekday("Monday"));
            weekdayDAO.addWeekday(new Weekday("Tuesday"));
            weekdayDAO.addWeekday(new Weekday("Wednesday"));
            weekdayDAO.addWeekday(new Weekday("Thursday"));
            weekdayDAO.addWeekday(new Weekday("Friday"));
            weekdayDAO.addWeekday(new Weekday("Saturday"));
            weekdayDAO.addWeekday(new Weekday("Sunday"));
        }
    }
}
