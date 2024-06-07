import org.json.JSONArray;
import org.json.JSONObject;
import java.util.Arrays;

public class Module {

    public static JSONArray filter(JSONArray meteors, double minMass, double maxMass) {
        JSONArray filteredMeteors = new JSONArray();

        for (int i = 0; i < meteors.length(); i++) {

            JSONObject meteor = meteors.getJSONObject(i);
            String meteorMassString = meteor.optString("mass", null);
            if (meteorMassString != null) {
                double meteorMass = Double.parseDouble(meteorMassString);

                if (meteorMass >= minMass && meteorMass <= maxMass) {
                    filteredMeteors.put(meteor);
                }
            }
        }

        return filteredMeteors;
    }

    public static JSONArray filter(JSONArray meteors, String[] classes) {
        JSONArray filteredMeteors = new JSONArray();

        for (int i = 0; i < meteors.length(); i++) {

            JSONObject meteor = meteors.getJSONObject(i);
            String meteorClassString = meteor.optString("recclass", null);
            if (meteorClassString != null) {
                if (Arrays.asList(classes).contains(meteorClassString)) {
                    filteredMeteors.put(meteor);
                }
            }
        }

        return filteredMeteors;
    }


    public static JSONArray filter(JSONArray meteors, int minYear, int maxYear) {
        JSONArray filteredMeteors = new JSONArray();

        for (int i = 0; i < meteors.length(); i++) {

            JSONObject meteor = meteors.getJSONObject(i);
            String meteorDateString = meteor.optString("year", null);

            if (meteorDateString != null) {
                String yearString = meteorDateString.substring(0,4);
                int year = Integer.parseInt(yearString);
                if (maxYear == -1) {
                    if (year == minYear) {
                        filteredMeteors.put(meteor);
                    }
                }else{
                    if (year >= minYear && year <= maxYear) {
                        filteredMeteors.put(meteor);
                    }
                }
            }
        }

        return filteredMeteors;
    }
    public static JSONArray filter(JSONArray meteors, double latitude, double longitude, double radius) {
        JSONArray filteredMeteors = new JSONArray();

        for (int i = 0; i < meteors.length(); i++) {

            JSONObject meteor = meteors.getJSONObject(i);
            String meteorLatString = meteor.optString("reclat", null);
            String meteorLongString = meteor.optString("reclong", null);

            if (meteorLatString != null && meteorLongString != null) {
                double meteorLat = Double.parseDouble(meteorLatString);
                double meteorLong = Double.parseDouble(meteorLongString);

                if (calculateDistance(latitude, longitude, meteorLat, meteorLong) <= radius) {
                    filteredMeteors.put(meteor);
                }
            }
        }
        return filteredMeteors;
    }

    public static double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int ERDRADIUS = 6371; // Radius der Erde in Kilometern

        // Umrechnung von Grad in Radiant
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Haversine-Formel
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Entfernung in Kilometern
        return ERDRADIUS * c;
    }
}
