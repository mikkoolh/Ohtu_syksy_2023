import com.automaatio.model.database.Feature;
import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;

public class FeatureTest {

    @Test
    public void testFeatureConstructor() {
        //luodaan feature
        Feature feature = new Feature(1, false, true, true, "himmennys", 10);

        //tarkkistetaan arvot
        assertEquals(1, feature.getFeatureId());
        assertFalse(feature.getAffectsOthers());
        assertTrue(feature.getIsActive());
        assertTrue(feature.getAdjustable());
        assertEquals("Description of feature should be 'himmennys'", feature.getDescription(), "himmennys");
        assertEquals(10, feature.getTimesUsed());
    }

    @Test
    public void testFeatureGettersAndSetters() {
        Feature feature = new Feature();

        //syötetään arvot settereillä
        feature.setFeatureId(1);
        feature.setAffectsOthers(true);
        feature.setIsActive(false);
        feature.setAdjustable(false);
        feature.setDescription("äänen voimakkuus");
        feature.setTimesUsed(20);

        assertEquals(1, feature.getFeatureId());
        assertTrue(feature.getAffectsOthers());
        assertFalse(feature.getIsActive());
        assertFalse(feature.getAdjustable());
        assertEquals("Description should be 'äänen voimakkuus", feature.getDescription(), "äänen voimakkuus");
    }
}
