import org.junit.jupiter.api.Test;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class RegionMeteorInParametersTest {

    RegionModule regionModule = new RegionModule();

    double latMunich = 48.1372;
    double longMunich = 11.5755;

    double latStuttgart = 48.7758;
    double lonStuttgart = 9.1829;

    //Distance according to https://www.gpskoordinaten.de/entfernung
    double distanceStuttgartMunich = 190.37;

    //Tests if the function returns true if the location is in the radius
    @Test
    void testMeteorInParameters_InRadius() {

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reclat", latMunich);
        jsonObject.put("reclong", longMunich);

        String testLat = Double.toString(latStuttgart);
        String testLon = Double.toString(lonStuttgart);
        String testRadius = Double.toString(distanceStuttgartMunich + 20);

        boolean result = regionModule.meteorInParameters(jsonObject, testLat, testLon, testRadius);

        assertTrue(result);
    }

    //Tests if the function returns false if the location is out of the radius
    @Test
    void testMeteorInParameters_OutOfRadius() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reclat", latMunich);
        jsonObject.put("reclong", longMunich);

        String testLat = Double.toString(latStuttgart);
        String testLon = Double.toString(lonStuttgart);
        String testRadius = Double.toString(distanceStuttgartMunich - 20);

        boolean result = regionModule.meteorInParameters(jsonObject, testLat, testLon, testRadius);

        assertFalse(result);
    }

    //Tests if the function returns true if the location is on the edge of the radius
    @Test
    void testMeteorInParameters_OnRadius() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("reclat", latMunich);
        jsonObject.put("reclong", longMunich);

        String testLat = Double.toString(latStuttgart);
        String testLon = Double.toString(lonStuttgart);
        String testRadius = Double.toString(distanceStuttgartMunich);

        boolean result = regionModule.meteorInParameters(jsonObject, testLat, testLon, testRadius);

        assertTrue(result);
    }

    //Tests if the function returns false if the JSON object is missing the necessary data
    @Test
    void testMeteorInParameters_MissingJSONData() {
        JSONObject jsonObject = new JSONObject();

        String testLat = Double.toString(latStuttgart);
        String testLon = Double.toString(lonStuttgart);
        String testRadius = Double.toString(distanceStuttgartMunich);

        boolean result = regionModule.meteorInParameters(jsonObject, testLat, testLon, testRadius);

        assertFalse(result);
    }
}