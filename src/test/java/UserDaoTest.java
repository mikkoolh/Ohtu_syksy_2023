package test.java;

import com.automaatio.model.database.User;
import com.automaatio.model.database.UserDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test for the User DAO
 * @author Matleena Kankaanpää
 * 15.9.2023
 */

public class UserDaoTest {

    private User testUser1, testUser2;
    UserDAO userDAO;

    @BeforeEach
    public void setUp() {
        userDAO = new UserDAO();
        testUser1 = new User("nuuskamuikkunen", "Nuuska", "Muikkunen", "040-8743486", "nuuskis@muumilaakso.fi", "topsecret", 56, 1);
        testUser2 = new User("hemuli", "Hemuli", "Hemulen", "040-1112233", "hemuli@muumilaakso.fi", "badpassword", 70, 2);
    }

    @AfterEach
    public void tearDown() {
        userDAO.deleteAll();
    };

    @Test
    public void testAddUser() {
        userDAO.addUser(testUser1);
        User fetchedUser = userDAO.getUser(testUser1.getUsername());

        assertEquals(fetchedUser.getUsername(), testUser1.getUsername(), "Username should match");
        assertEquals(fetchedUser.getFirstName(), testUser1.getFirstName(), "First name should match");
        assertEquals(fetchedUser.getLastName(), testUser1.getLastName(), "Last name should match");
        assertEquals(fetchedUser.getAge(), testUser1.getAge(), "Age should match");
        assertEquals(fetchedUser.getEmail(), testUser1.getEmail(), "Email should match");
        assertEquals(fetchedUser.getPhoneNumber(), testUser1.getPhoneNumber(), "Phone number should match");
        assertEquals(fetchedUser.getPassword(), testUser1.getPassword(), "Password should match");
        assertEquals(fetchedUser.getUserType(), testUser1.getUserType(), "User type should match");
    }

    @Test
    public void testGetUser() {
        userDAO.addUser(testUser1);
        User fetchedUser = userDAO.getUser(testUser1.getUsername());
        assertNotNull(fetchedUser, "Returned user should not be null");
    }

    @Test
    public void testGetAll() {
        userDAO.addUser(testUser1);
        userDAO.addUser(testUser2);
        List<User> fetchedUsers = userDAO.getAll();
        assertEquals(2, fetchedUsers.size(), "There should be 2 users in the list");
    }

    @Test
    public void testDeleteAll() {
        userDAO.addUser(testUser1);
        userDAO.addUser(testUser2);
        userDAO.deleteAll();
        List<User> fetchedUsers = userDAO.getAll();
        assertEquals(0, fetchedUsers.size(), "The returned list should be empty");
    }
}