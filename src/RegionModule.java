import org.json.JSONObject;

public class RegionModule extends Module{


    public boolean meteorInParameters(JSONObject meteor, Object... arguments){
        double meteorLat = meteor.optDouble("reclat", 1000);
        double meteorLon = meteor.optDouble("reclong", 1000);

        if (meteorLat != 1000 && meteorLon != 1000){
            double lat = (double) arguments[0];
            double lon = (double) arguments[1];
            double radius = (double) arguments[2];

            return calculateDistance(meteorLat, meteorLon, lat, lon) <= radius;
        }
        return false;
    }

    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
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
