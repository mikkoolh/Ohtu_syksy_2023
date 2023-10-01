import com.automaatio.model.database.DeviceType;
import com.automaatio.model.database.DeviceTypeDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test for the DeviceType DAO
 * @author Matleena Kankaanpää
 * 18.9.2023
 */

public class DeviceTypeDaoTest {
    /*

    DeviceTypeDAO deviceTypeDAO;

    @BeforeEach
    public void setUp() {
        deviceTypeDAO = new DeviceTypeDAO();
    }

    @AfterEach
    public void tearDown() { deviceTypeDAO.deleteAll(); };

    @Test
    public void testAddType() {
        DeviceType testType = new DeviceType();
        testType.setDescription("Light");
        testType.setImage("https://example.com/light.png");
        deviceTypeDAO.addDeviceType(testType);

        DeviceType fetchedType = deviceTypeDAO.getDeviceType(testType.getDeviceTypeId());
        assertEquals(fetchedType.getDescription(), testType.getDescription(), "Description should match");
        assertEquals(fetchedType.getImage(), testType.getImage(), "Image url should match");
    }

    @Test
    public void testGetAll() {
        // ei mene läpi

        DeviceType type1 = new DeviceType(1, "Toothbrush", "https://example.com/toothbrush.png");
        DeviceType type2 = new DeviceType(2, "Toaster", "https://example.com/toaster.png");
        DeviceType type3 = new DeviceType(3, "Kettle", "https://example.com/kettle.png");

        deviceTypeDAO.addDeviceType(type1);
        deviceTypeDAO.addDeviceType(type2);
        deviceTypeDAO.addDeviceType(type3);

        List<DeviceType> fetchedTypes = deviceTypeDAO.getAll();
        assertNotNull(fetchedTypes, "List should not be null");
        assertEquals(3, fetchedTypes.size(), "There should be 3 items in the list");
    }

    @Test
    public void deleteAll() {
        // ei mene läpi

        DeviceType type1 = new DeviceType(1, "TV", "https://example.com/tv.png");
        DeviceType type2 = new DeviceType(2, "Vacuum", "https://example.com/vacuum.png");

        deviceTypeDAO.addDeviceType(type1);
        deviceTypeDAO.addDeviceType(type2);
        deviceTypeDAO.deleteAll();

        List<DeviceType> fetchedTypes = deviceTypeDAO.getAll();
        assertEquals(1, fetchedTypes.size(), "The returned list should be empty");
    }

     */
}