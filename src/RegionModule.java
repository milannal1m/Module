import org.json.JSONObject;

/**
 * Module to filter meteorites based on their region.
 */
public class RegionModule extends Module{

    /**
     * Implementation of abstract method to check if a meteorite location is in a
     * given radius to another location.
     * Checks if mass of meteorite is in the radius to a given location.
     *
     * @param meteor JSONObject of meteorite to check if the meteorite location is in the radius to the .
     * @param arguments Indefinite Amount Arguments to filter the meteorites.
     *                  Should be a latitude, a longitude and a radius.
     * @return Boolean if the meteorite matches the filter.
     */
    public boolean meteorInParameters(JSONObject meteor, String... arguments){
        // 1000 as default Value because no meteor has this coordinates
        double meteorLat = meteor.optDouble("reclat", 1000);
        double meteorLon = meteor.optDouble("reclong", 1000);

        if (meteorLat != 1000 && meteorLon != 1000){
            double lat = Double.parseDouble(arguments[0]);
            double lon = Double.parseDouble(arguments[1]);
            double radius = Double.parseDouble(arguments[2]);

            return calculateDistance(meteorLat, meteorLon, lat, lon) <= radius;
        }
        return false;
    }

    /**
     * Calculates the distance between two locations on earth.
     *
     * @param lat1 Latitude of the first location.
     * @param lon1 Longitude of the first location.
     * @param lat2 Latitude of the second location.
     * @param lon2 Longitude of the second location.
     * @return The Distance between the two locations in kilometers.
     */
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int ERDRADIUS = 6371; // radius of the earth in kilometers

        // Convert degrees to radians
        double lat1Rad = Math.toRadians(lat1);
        double lon1Rad = Math.toRadians(lon1);
        double lat2Rad = Math.toRadians(lat2);
        double lon2Rad = Math.toRadians(lon2);

        // Haversine-Formula
        double deltaLat = lat2Rad - lat1Rad;
        double deltaLon = lon2Rad - lon1Rad;
        double a = Math.sin(deltaLat / 2) * Math.sin(deltaLat / 2) +
                Math.cos(lat1Rad) * Math.cos(lat2Rad) *
                        Math.sin(deltaLon / 2) * Math.sin(deltaLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        // Distance in kilometers
        return ERDRADIUS * c;
    }
}
