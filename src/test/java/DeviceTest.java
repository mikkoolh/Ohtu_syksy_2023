package test.java;

import com.automaatio.model.database.Device;
import com.automaatio.model.database.DeviceDAO;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DeviceTest {

    @Test
    public void testDeviceConstructor() {
        Device device = new Device(true, true, 100, "TestiLaite", "ABC1122");

        assertTrue(device.isOnOff());
        assertTrue(device.isAutomation());
        assertEquals(100, device.getUsageData());
        assertEquals("TestiLaite", device.getName());
        assertEquals("ABC1122", device.getModelCode());
    }
}
