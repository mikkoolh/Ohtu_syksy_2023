import com.autho_project.model.database.Device;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DeviceTest {

    @Test
    public void testDeviceConstructor() {
        // Luodaan laite
        Device device = new Device(true, true, 100L, "TestiLaite", "Malli1");

        // Tarkistetaan arvot
        assertTrue(device.isOnOff());
        assertTrue(device.isAutomation());
        assertEquals(100L, device.getUsageData());
        assertEquals(device.getName(), "TestiLaite", "Name should be 'TestiLaite'");
        assertEquals(device.getModelCode(), "Malli1", "Model should be 'Malli1'");
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
        assertEquals(device.getName(), "EriLaite", "Name should be 'EriLaite'");
        assertEquals(device.getModelCode(), "Malli2", "Model should be 'Malli2'");
    }
}