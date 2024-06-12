import org.junit.jupiter.api.Test;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.junit.jupiter.api.Assertions.*;

class RegionFilterTest {

    RegionModule regionModule = new RegionModule();

    double latMunich = 48.1372;
    double longMunich = 11.5755;

    double latStuttgart = 48.7758;
    double lonStuttgart = 9.1829;

    double latBerlin = 52.5200;
    double lonBerlin = 13.4050;

    double latDortmund = 51.5136;
    double lonDortmund = 7.4653;

    // Distances according to https://www.gpskoordinaten.de/entfernung
    // distanceStuttgartMunich = 190.37;
    // distanceStuttgartBerlin = 512.2;
    // distanceStuttgartDortmund = 328.46;


    // Creates a mock JSONArray with three locations Munich Berlin and Dortmund
    public JSONArray createMockMeteors(){
        // Create the first JSON object
        JSONObject jsonObject1 = new JSONObject();
        jsonObject1.put("reclat", latMunich);
        jsonObject1.put("reclong", longMunich);

        // Create the second JSON object
        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("reclat", latBerlin);
        jsonObject2.put("reclong", lonBerlin);

        JSONObject jsonObject3 = new JSONObject();
        jsonObject3.put("reclat", latDortmund);
        jsonObject3.put("reclong", lonDortmund);

        // Create a JSON array and add the JSON objects to it
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject1);
        jsonArray.put(jsonObject2);
        jsonArray.put(jsonObject3);

        return jsonArray;
    }

    //Tests if the JSONArray is empty if no meteor is in the criteria by comparing every location
    //with Stuttgart and setting the radius very small
    @Test
    void testNoneInCriteria() {
        JSONArray mockJSOnArray = createMockMeteors();

        JSONArray result = regionModule.filter(mockJSOnArray, Double.toString(latStuttgart), Double.toString(lonStuttgart), "10");
        assertEquals(0, result.length());
    }

    //Tests if the function only filters Berlin if the radius is set to the distance between Stuttgart and Dortmund
    @Test
    void testSomeInCriteria() {
        JSONArray mockJSOnArray = createMockMeteors();

        JSONArray result = regionModule.filter(mockJSOnArray, Double.toString(latStuttgart), Double.toString(lonStuttgart), "328.46");
        assertEquals(2, result.length());
    }

    //Tests if the function filters no locations if the radius is set to the distance between Stuttgart and Berlin
    @Test
    void testAllInCriteria() {
        JSONArray mockJSOnArray = createMockMeteors();

        JSONArray result = regionModule.filter(mockJSOnArray, Double.toString(latStuttgart), Double.toString(lonStuttgart), "512.2");
        assertEquals(3, result.length());
    }

    //Tests if the function still works if the JSONObject is missing Location data
    @Test
    void testMissingJSONData() {
        JSONObject jsonObject1 = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        jsonArray.put(jsonObject1);

        JSONArray result = regionModule.filter(jsonArray, Double.toString(latStuttgart), Double.toString(lonStuttgart), "512.2");
        assertEquals(0, result.length());
    }

    //Tests if the function still works if the JSONArray is empty
    @Test
    void testEmptyJSONArray() {
        JSONArray jsonArray = new JSONArray();

        JSONArray result = regionModule.filter(jsonArray, Double.toString(latStuttgart), Double.toString(lonStuttgart), "512.2");
        assertEquals(0, result.length());
    }

}