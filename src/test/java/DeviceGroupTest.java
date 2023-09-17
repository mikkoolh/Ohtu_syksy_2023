import com.autho_project.model.database.Device;
import com.autho_project.model.database.DeviceGroup;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DeviceGroupTest {

    //Testataan konstruktoria ja gettereita
    @Test
    public void testDeviceGroupConstructor() {
        DeviceGroup dg = new DeviceGroup("OlohuoneTesti");

        Assertions.assertEquals("OlohuoneTesti", dg.getName(), "Name should be 'OlohuoneTesti'");
    }

    @Test
    public void testDevGroupGettersAndSetters() {
        DeviceGroup dg = new DeviceGroup();

        dg.setName("GetteriSetteriRyhmä");
        dg.setDeviceGroupId(1);

        Assertions.assertEquals("GetteriSetteriRyhmä", dg.getName(),"Name should be 'GetteriSetteriRyhmä'");
        Assertions.assertEquals(1, dg.getDeviceGroupId(), "Id should be 1");
    }
}