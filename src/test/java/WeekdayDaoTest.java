import com.automaatio.model.database.WeekdayDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * Test for the Weekday DAO
 * @author Matleena Kankaanpää
 */

public class WeekdayDaoTest {

    WeekdayDAO weekdayDAO;
    @BeforeEach
    public void setUp() {
        weekdayDAO = new WeekdayDAO();
    }

    @AfterEach
    public void tearDown() {
        weekdayDAO.deleteAll();
    }

    @Test
    public void testAddWeekday() {
        // TODO
    }

    @Test
    public void testGetAll() {
        // TODO
    }

    /**
     * TODO: updateWeekday ja deleteWeekday
     * Tarvitaanko?
     */
}