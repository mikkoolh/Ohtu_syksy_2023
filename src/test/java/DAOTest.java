import com.automaatio.model.database.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

/**
 * KORJATTAVA TESTIEN SAMAAN AIKAAN AJAMINEN!!!!
 * KAIKKIA DAOJA EI VOI TESTATA, KOSKA EM EI PYSY MUKANA
 */

public class DAOTest {

    private Device device = new Device(100, "TestiLaite", "313", null, "testi");
    private DeviceGroup deviceGroup = new DeviceGroup("Olohuone", null);
    private DeviceType deviceType = new DeviceType("Lamppu", "http://www.jalka.fi/lamppu");
    private EventTime eventTime = new EventTime(LocalDateTime.now(), LocalDateTime.now(), new Weekday("testiPäivä"));
    private Feature feature = new Feature(false, true, true, "himmennys", 10);
    private Manufacturer manufacturer = new Manufacturer("Kaalimato");
    private UserType userType = new UserType("vanhus");
    private Weekday weekday = new Weekday("Torjantai");
    private User user = new User("testi", "Maija", "Mehiläinen", "0501234", "maija@mehilainen.fi", "asdf1234", 10, 1);
    private Routine routine = new Routine(user, device, feature, eventTime, false);

    private IDAO dao;

    public DAOTest() {
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testGetObject(IDAO testDao) {

        dao = testDao;

        int id = -5;


        if (dao instanceof DeviceDAO) {
            dao.addObject(device);
            id = device.getDeviceID();

        } else if (dao instanceof DeviceGroupDAO) {
            dao.addObject(deviceGroup);
            id = deviceGroup.getDeviceGroupId();

        } else if (dao instanceof DeviceTypeDAO) {
            dao.addObject(deviceType);
            id = deviceType.getDeviceTypeId();

        } else if (dao instanceof EventTimeDAO) {
            dao.addObject(eventTime);
            id = eventTime.getEventTimeId();

        } else if (dao instanceof FeatureDAO) {
            dao.addObject(feature);
            id = feature.getFeatureId();

        } else if (dao instanceof ManufacturerDAO) {
            dao.addObject(manufacturer);
            id = manufacturer.getManufacturerId();

        } else if (dao instanceof RoutineDAO) {
            dao.addObject(routine);
            id = routine.getRoutineID();

        } else if (dao instanceof UserTypeDAO) {
            dao.addObject(new UserType());
            id = userType.getUserTypeID();

        } else if (dao instanceof WeekdayDAO) {
            dao.addObject(weekday);
            id = weekday.getWeekdayId();

        } else if (dao instanceof UserDAO) {
            dao.addObject(user);
            id = user.getUserID();

        }
        assertNotNull(dao.getObject(id));
    }

    public static Collection<IDAO[]> data() {
        return Arrays.asList(new IDAO[][]{
            {new DeviceDAO()},
            {new DeviceGroupDAO()},
            {new DeviceTypeDAO()},
        /*   {new EventTimeDAO() },
            {new FeatureDAO() },
            {new ManufacturerDAO() },
            {new RoutineDAO() },
            {new UserTypeDAO() },*/
            {new WeekdayDAO()}
        });
    }


    @ParameterizedTest
    @MethodSource("data")
    public void testGetAll(IDAO testDao) {
        IDAO dao = testDao;
        assertNotNull(dao.getAll());
    }
/*
    @Test
    public void testAddObject(){
        int temp = dao.getAll().size();
        if(dao instanceof UserDAO){
            dao.addObject(newUser());
        }else {
            dao.addObject(new Object());
        }
        assertEquals(temp+1,dao.getAll().size(), "Dao ei lisännyt objectia");

    }*/


}
