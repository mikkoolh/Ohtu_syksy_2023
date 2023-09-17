import com.autho_project.model.database.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the User class
 * @author Matleena Kankaanpää
 * 15.9.2023
 */

public class UserTest {

    @Test
    public void testUserConstructor() {
        User user = new User("muumipeikko", "Muumi", "Peikko", "040-1234567", "muumi.peikko@muumilaakso.fi", "salaisuus", 30, 2);

        assertEquals(user.getUsername(), "muumipeikko", "Username should be 'muumipeikko'");
        assertEquals(user.getFirstName(), "Muumi", "First name should be 'Muumi'");
        assertEquals(user.getLastName(), "Peikko", "Last name should be 'Peikko'");
        assertEquals(user.getAge(), 30, "Age should be 30");
        assertEquals(user.getEmail(), "muumi.peikko@muumilaakso.fi", "Email should be 'muumi.peikko@muumilaakso.fi'");
        assertEquals(user.getPhoneNumber(), "040-1234567", "Phone number should be '040-1234567'");
        assertEquals(user.getPassword(), "salaisuus", "Password should be 'salaisuus'");
        assertEquals(user.getUserType(), 2, "User type should be 2");
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

        assertEquals(user.getUsername(), "pikkumyy", "Username should be 'pikkumyy'");
        assertEquals(user.getFirstName(), "Pikku", "First name should be 'Pikku'");
        assertEquals(user.getLastName(), "Myy", "Last name should be 'Myy'");
        assertEquals(user.getAge(), 20, "Age should be 20");
        assertEquals(user.getEmail(), "pikku.myy@muumilaakso.fi", "Email should be 'pikku.myy@muumilaakso.fi'");
        assertEquals(user.getPhoneNumber(), "040-9876543", "Phone number should be '040-9876543'");
        assertEquals(user.getPassword(), "secret", "Password should be 'secret'");
        assertEquals(user.getUserType(), 1, "User type should be 1");
    }
}