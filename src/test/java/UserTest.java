import com.automaatio.model.database.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test for the User class
 * @author Matleena Kankaanpää
 * 15.9.2023
 */

public class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User("muumipeikko", "Muumi", "Peikko", "040-1234567", "muumi.peikko@muumilaakso.fi", "salaisuus", 30, 2);

        assertEquals("Username should be 'muumipeikko'", user.getUsername(), "muumipeikko");
        assertEquals("First name should be 'Muumi'", user.getFirstName(), "Muumi");
        assertEquals("Last name should be 'Peikko'", user.getLastName(), "Peikko");
        assertEquals("Age should be 30", user.getAge(), 30);
        assertEquals("Email should be 'muumi.peikko@muumilaakso.fi'", user.getEmail(), "muumi.peikko@muumilaakso.fi");
        assertEquals("Phone number should be '040-1234567'", user.getPhoneNumber(), "040-1234567");
        assertEquals("Password should be 'salaisuus'", user.getPassword(), "salaisuus");
        assertEquals("User type should be 2", user.getUserType(), 2);
    }

    @Test
    public void testUserGettersAndSetters() {
        User user = new User();

        user.setUsername("pikkumyy");
        user.setFirstName("Pikku");
        user.setLastName("Myy");
        user.setAge(20);
        user.setEmail("pikku.myy@muumilaakso.fi");
        user.setPhoneNumber("040-9876543");
        user.setPassword("secret");
        user.setUserType(1);

        assertEquals("Username should be 'pikkumyy'", user.getUsername(), "pikkumyy");
        assertEquals("First name should be 'Pikku'", user.getFirstName(), "Pikku");
        assertEquals("Last name should be 'Myy'", user.getLastName(), "Myy");
        assertEquals("Age should be 20", user.getAge(), 20);
        assertEquals("Email should be 'pikku.myy@muumilaakso.fi'", user.getEmail(), "pikku.myy@muumilaakso.fi");
        assertEquals("Phone number should be '040-9876543'", user.getPhoneNumber(), "040-9876543");
        assertEquals("Password should be 'secret'", user.getPassword(), "secret");
        assertEquals("User type should be 1", user.getUserType(), 1);
    }
}