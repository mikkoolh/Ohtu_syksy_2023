import com.automaatio.model.database.Weekday;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for the Weekday class
 * @author Matleena Kankaanpää
 * 14.9.2023
 */

public class WeekdayTest {

    @Test
    public void testWeekdayConstructor() {
        Weekday weekday = new Weekday(1, "Monday");

        assertEquals(1, weekday.getWeekdayId());
        assertEquals("Name should be 'Monday'", weekday.getName(), "Monday");
    }

    @Test
    public void testWeekdayGettersAndSetters() {
        Weekday weekday = new Weekday();
        weekday.setWeekdayId(5);
        weekday.setName("Friday");

        assertEquals(5, weekday.getWeekdayId());
        assertEquals("Name should be 'Friday'", weekday.getName(), "Friday");
    }
}