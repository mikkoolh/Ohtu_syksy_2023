package test.java;

import com.automaatio.model.database.DeviceGroup;
import com.automaatio.model.database.DeviceGroupDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DeviceGroupDaoTest {

    DeviceGroupDAO dgDao;

    @BeforeEach
    public void setUp() {
        dgDao = new DeviceGroupDAO();
    }

    @AfterEach
    public void tearDown() { dgDao.deleteAll(); };

    @Test
    public void testAddGroup() {
        DeviceGroup dg = new DeviceGroup();
        dg.setName("Testiryhmä");

        dgDao.addDeviceGroup(dg);

        DeviceGroup fetchedDg = dgDao.getDeviceGroup(dg.getDeviceGroupId());

        Assertions.assertEquals(fetchedDg.getName(), dg.getName(), "Name should match");
    }

    @Test
    public void testGetAll() {
        DeviceGroup dg1 = new DeviceGroup("Ryhmä1");
        DeviceGroup dg2 = new DeviceGroup("2Ryhmä");

        dgDao.addDeviceGroup(dg1);
        dgDao.addDeviceGroup(dg2);

        List<DeviceGroup> dgroups = dgDao.getAll();

        Assertions.assertNotNull(dgroups, "List should not be null");
        Assertions.assertEquals(2, dgroups.size(), "There should be 2 groups in the list");
    }
}
