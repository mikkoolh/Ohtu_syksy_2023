import com.automaatio.model.database.Manufacturer;
import com.automaatio.model.database.ManufacturerDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ManufacturerDAOTest {

    ManufacturerDAO manufacturerDAO;

    @BeforeEach
    public void setUp() {
        manufacturerDAO = new ManufacturerDAO();
        manufacturerDAO.deleteAll();  // Clean up before each test
    }

    @AfterEach
    public void tearDown() {
        manufacturerDAO.deleteAll();  // Clean up after each test
    }

    @Test
    public void testAddManufacturer() {
        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setManufacturerId(1);
        manufacturer.setName("Airam");

        manufacturerDAO.addManufacturer(manufacturer);

        // Assert that manufacturer list size is 1 after adding
        assertEquals(1, manufacturerDAO.getAll().size(), "There should be one manufacturer in the list after adding");

        Manufacturer fetchedManufacturer = manufacturerDAO.getManufacturer(manufacturer.getManufacturerId());

        assertNotNull(fetchedManufacturer, "Manufacturer should not be null");
        assertEquals(manufacturer.getName(), fetchedManufacturer.getName(), "names should match");
    }

    @Test
    public void testGetAll() {
        Manufacturer m1 = new Manufacturer(3, "LIFX");
        Manufacturer m2 = new Manufacturer(4, "Kasa Smart");

        manufacturerDAO.addManufacturer(m1);
        manufacturerDAO.addManufacturer(m2);

        List<Manufacturer> manufacturers = manufacturerDAO.getAll();

        assertNotNull(manufacturers, "List of manufacturers should not be null");
        assertEquals(2, manufacturers.size(), "There should be two devices in the list");
    }
}
