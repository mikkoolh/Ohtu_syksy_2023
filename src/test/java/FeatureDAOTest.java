import com.automaatio.model.database.Feature;
import com.automaatio.model.database.FeatureDAO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FeatureDAOTest {
    /*

    FeatureDAO featureDAO;

    @BeforeEach
    public void setUp() {
        featureDAO = new FeatureDAO();
        featureDAO.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        featureDAO.deleteAll();
    }

    @Test
    public void testAddFeature() {
        Feature feature = new Feature();
        feature.setAffectsOthers(false);
        feature.setIsActive(true);
        feature.setAdjustable(true);
        feature.setDescription("himmennin");
        feature.setTimesUsed(10);

        featureDAO.addFeature(feature);

        //tarkistetaan, että listan koko on nyt vain yksi ominaisuuden lisäämisen jälkeen
        assertEquals(1, featureDAO.getAll().size(), "There should be one feature in the list");
    }

    @Test
    public void testGetAll() {
        Feature f1 = new Feature(1, false, false, false, "katkaisija", 100);
        Feature f2 = new Feature(2, true, true, true, "säädin", 50);

        featureDAO.addFeature(f1);
        featureDAO.addFeature(f2);

        List<Feature> features = featureDAO.getAll();

        assertNotNull(features, "List of features should not be null");
        assertEquals(2, features.size(), "There should be two devices in the list");
    }

     */
}
