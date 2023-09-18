import com.automaatio.model.database.DeviceType;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for the DeviceType class
 * @author Matleena Kankaanpää
 * 18.9.2023
 */

public class DeviceTypeTest {

    @Test
    public void testDeviceTypeConstructor() {
        DeviceType deviceType = new DeviceType(1, "Lamp", "https://example.com/lamp.png");
        assertEquals("Description should be 'Lamp'", deviceType.getDescription(), "Lamp");
        assertEquals("Description should be 'https://example.com/lamp.png'", deviceType.getImage(), "https://example.com/lamp.png");
    }

    @Test
    public void testDeviceTypeGettersAndSetters() {
        DeviceType deviceType = new DeviceType();

        deviceType.setDescription("Fridge");
        deviceType.setImage("https://example.com/fridge.png");
        assertEquals("Description should be 'Fridge'", deviceType.getDescription(), "Fridge");
        assertEquals("url should be 'https://example.com/fridge.png'", deviceType.getImage(), "https://example.com/fridge.png");
    }
}
