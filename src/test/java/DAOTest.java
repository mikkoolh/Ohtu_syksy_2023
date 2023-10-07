import com.automaatio.model.database.*;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

public class DAOTest {

    private IDAO dao;
    private Device device = new Device(100,"TestiLaite","313", null, "testi");
    private DeviceGroup deviceGroup = new DeviceGroup("Olohuone", null);
    private DeviceType deviceType = new DeviceType("Lamppu", "http://www.jalka.fi/lamppu");
    private EventTime eventTime = new EventTime(LocalDateTime.now(), LocalDateTime.now(),new Weekday("testiPäivä"));
    private Feature feature = new Feature(false, true, true, "himmennys", 10);
    private Manufacturer manufacturer = new Manufacturer("Kaalimato");

    private UserType userType = new UserType("vanhus");


    private Weekday weekday = new Weekday("Torjantai");
    private User user = new User("testi", "Maija", "Mehiläinen", "0501234", "maija@mehilainen.fi", "asdf1234", 10, 1, 15);
    private Routine routine = new Routine(user, device, feature, eventTime, false);
    private int id;


    public DAOTest(){
    }

    @ParameterizedTest
    @MethodSource("data")
    public void testGetObject(IDAO testDao) {
        dao = testDao;

        if(dao instanceof DeviceDAO){
            id = device.getDeviceID();
            dao.addObject(device);
            System.out.println(id +" ja "+ device);

        } else if (dao instanceof DeviceGroupDAO) {
            id = deviceGroup.getDeviceGroupId();
            dao.addObject(deviceGroup);

        } else if (dao instanceof DeviceTypeDAO) {
            id = deviceType.getDeviceTypeId();
            dao.addObject(deviceType);

        } else if (dao instanceof EventTimeDAO) {
            id = eventTime.getEventTimeId();
            dao.addObject(eventTime);

        } else if (dao instanceof FeatureDAO) {
            id = feature.getFeatureId();
            dao.addObject(feature);

        } else if (dao instanceof ManufacturerDAO) {
            id = manufacturer.getManufacturerId();
            dao.addObject(manufacturer);


        } else if (dao instanceof RoutineDAO) {
            id = routine.getRoutineID();
            dao.addObject(routine);

        } else if (dao instanceof UserTypeDAO) {
            id = userType.getUserTypeID();
            dao.addObject(new UserType());

        } else if (dao instanceof WeekdayDAO) {
            id = weekday.getWeekdayId();
            dao.addObject(weekday);

        } else if(dao instanceof UserDAO){
            id = user.getUserID();
            dao.addObject(user);

        }
        assertNotNull(dao.getObject(id));
    }

    public static Collection<IDAO[]> data() {
        return Arrays.asList(new IDAO[][] {
                { new DeviceDAO() },
                { new DeviceGroupDAO() },
                { new DeviceTypeDAO() },
                { new EventTimeDAO() },
                { new FeatureDAO() },
                { new ManufacturerDAO() },
                { new RoutineDAO() },
                { new UserTypeDAO() },
                { new WeekdayDAO() }
        });
    }






    @ParameterizedTest
    @MethodSource("data")
    public void testGetAll(IDAO testDao) {
        dao = testDao;
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
