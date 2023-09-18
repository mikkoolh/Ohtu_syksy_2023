import com.automaatio.model.database.Manufacturer;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class ManufacturerTest {
    
    @Test
    public void testManufacturerConstructor() {
        //Luodaan uusi valmistaja
        Manufacturer manufacturer = new Manufacturer(1, "Philips");

        assertEquals(1, manufacturer.getManufacturerId());
        assertEquals("Manufacturer name should be 'Philips'", manufacturer.getName(), "Philips");
    }

    @Test
    public void testManufacturerGettersAndSetters() {
        //Luodaan uusi valmistaja
        Manufacturer manufacturer = new Manufacturer();

        //Lisätään settereillä arvot
        manufacturer.setManufacturerId(2);
        manufacturer.setName("Ikea");

        //haetaan gettereillä tiedot ja tarkistetaan ne
        assertEquals(2, manufacturer.getManufacturerId());
        assertEquals("Manufacturers name should be 'Ikea'", manufacturer.getName(), "Ikea");
    }
}
