import com.autho_project.model.database.UserType;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for the UserType class
 * @author Matleena Kankaanpää
 * 15.9.2023
 */

public class UserTypeTest {

    @Test
    public void testUserTypeConstructor() {
        UserType userType = new UserType("Admin");

        assertEquals(userType.getDescription(), "Admin", "Description should be 'Admin'");
    }

    @Test
    public void testUserTypeGettersAndSetters() {
        UserType userType = new UserType();
        userType.setDescription("User");
        assertEquals(userType.getDescription(), "User", "Description should be 'User'");
    }
}