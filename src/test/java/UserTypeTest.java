import com.automaatio.model.database.UserType;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

/**
 * Test for the UserType class
 * @author Matleena Kankaanpää
 * 15.9.2023
 */

public class UserTypeTest {

    @Test
    public void testUserTypeConstructor() {
        UserType userType = new UserType("Admin");

        assertEquals("Description should be 'Admin'", userType.getDescription(), "Admin");
    }

    @Test
    public void testUserTypeGettersAndSetters() {
        UserType userType = new UserType();
        userType.setDescription("User");
        assertEquals("Description should be 'User'", userType.getDescription(), "User");
    }
}
