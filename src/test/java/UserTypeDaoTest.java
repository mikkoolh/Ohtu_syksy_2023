import com.autho_project.model.database.UserType;
import com.autho_project.model.database.UserTypeDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Test for the UserType DAO
 * @author Matleena Kankaanpää
 * 15.9.2023
 */

public class UserTypeDaoTest {

    UserTypeDAO userTypeDAO;

    @BeforeEach
    public void setUp() {
        userTypeDAO = new UserTypeDAO();
    }

    @AfterEach
    public void tearDown() { userTypeDAO.deleteAll(); };

    @Test
    public void testAddType() {
        UserType testType = new UserType();
        testType.setDescription("TestUser");
        userTypeDAO.addUserType(testType);

        UserType fetchedType = userTypeDAO.getUserType(testType.getUserTypeID());
        assertEquals(fetchedType.getDescription(), testType.getDescription(), "Description should match");
    }

    @Test
    public void testGetAll() {
        UserType type1 = new UserType("Admin");
        UserType type2 = new UserType("Adult");
        UserType type3 = new UserType("Child");

        userTypeDAO.addUserType(type1);
        userTypeDAO.addUserType(type2);
        userTypeDAO.addUserType(type3);

        List<UserType> fetchedTypes = userTypeDAO.getAll();
        assertNotNull(fetchedTypes, "List should not be null");
        assertEquals(3, fetchedTypes.size(), "There should be 3 items in the list");
    }

    @Test
    public void deleteAll() {
        UserType type1 = new UserType("Admin");
        UserType type2 = new UserType("Adult");

        userTypeDAO.addUserType(type1);
        userTypeDAO.addUserType(type2);
        userTypeDAO.deleteAll();

        List<UserType> fetchedTypes = userTypeDAO.getAll();
        assertEquals(0, fetchedTypes.size(), "The returned list should be empty");
    }
}