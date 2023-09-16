import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DeviceDaoTest {

    DeviceDAO deviceDAO;
    @BeforeEach
    public void setUp() {

        // yhdistää tietokantaan
        // Luo uuden laitteen
        deviceDAO = new DeviceDAO();
    }

    // Poistaa taulun sisällön testien jälkeen
    // Samalla deleteAll-metodin testi
    @AfterEach
    public void tearDown() {
        deviceDAO.deleteAll();
    }

    @Test
    public void testAddDevice() {
        Device device = new Device();
        device.setName("TestiLaite");
        device.setAutomation(true);
        device.setOnOff(false);
        device.setModelCode("OOO222");
        device.setUsageData(50L);

        deviceDAO.addDevice(device);

        Device fetchedDevice = deviceDAO.getDeviceType(device.getDeviceID());

        assertNotNull(fetchedDevice, "Device should not be null");
        assertEquals(device.getName(), fetchedDevice.getName(), "Name should match");
        assertTrue(device.isAutomation(), "Automation should be true.");
        assertFalse(device.isOnOff(), "OnOff should be false.");
        assertEquals("OOO222", device.getModelCode(), "Model code should be 'OOO222'.");
        assertEquals(50L, device.getUsageData());
    }

    @Test
    public void testGetAll() {
        Device device1 = new Device(false, false, 1, "TestiLaite1", "ABC123");
        Device device2 = new Device(true, true, 2, "TestiLaite2", "CBA321");

        deviceDAO.addDevice(device1);
        deviceDAO.addDevice(device2);

        List<Device> devices = deviceDAO.getAll();

        assertNotNull(devices, "List of devices should not be null.");
        assertEquals(2, devices.size(), "There should be 2 devices in the list.");
    }
}
