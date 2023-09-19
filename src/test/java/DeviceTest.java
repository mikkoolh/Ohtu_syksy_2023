import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author Nikita Nossenko
 * JUNIT test for Device Entity
 */
public class DeviceTest {

    @Test
    public void testDeviceConstructor() {
        // Luodaan laite
        Device device = new Device(true, true, 100L, "TestiLaite", "Malli1");

        // Tarkistetaan arvot
        assertTrue(device.isOnOff());
        assertTrue(device.isAutomation());
        assertEquals(100L, device.getUsageData());
        assertEquals("Name should be 'TestiLaite'", device.getName(), "TestiLaite");
        assertEquals("Model should be 'Malli1'", device.getModelCode(), "Malli1");
    }

    @Test
    public void testDeviceGettersAndSetters() {
        // Luodaan laite
        Device device = new Device();

        // Use setters to set the values
        device.setOnOff(true);
        device.setAutomation(false);
        device.setUsageData(200L);
        device.setName("EriLaite");
        device.setModelCode("Malli2");

        // Use getters to retrieve the values and check them
        assertTrue(device.isOnOff());
        assertFalse(device.isAutomation());
        assertEquals(200L, device.getUsageData());
        assertEquals("Name should be 'EriLaite'", device.getName(), "EriLaite");
        assertEquals("Model should be 'Malli2'", device.getModelCode(), "Malli2");
    }
}
