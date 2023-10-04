
import com.automaatio.model.database.Weekday;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the Weekday class
 * @author Matleena Kankaanpää
 * 14.9.2023
 */

public class WeekdayTest {

    @Test
    public void testWeekdayConstructor() {
        Weekday weekday = new Weekday("Monday");

        assertEquals(1, weekday.getWeekdayId());
        assertEquals(weekday.getName(), "Monday", "Name should be 'Monday'");
    }

    @Test
    public void testWeekdayGettersAndSetters() {
        Weekday weekday = new Weekday();
        weekday.setName("Friday");

        assertEquals(5, weekday.getWeekdayId());
        assertEquals(weekday.getName(), "Friday", "Name should be 'Friday'");
    }
}