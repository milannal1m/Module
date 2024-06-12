import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.*;

class RegionCalculateDistanceTest {

    double latMunich = 48.1372;
    double longMunich = 11.5755;

    double latStuttgart = 48.7758;
    double lonStuttgart = 9.1829;

    //Distance according to https://www.gpskoordinaten.de/entfernung
    double distanceStuttgartMunich = 190.37;

    // Tests if the distance between Munich and Stuttgart is calculated correctly
    @Test
    void testDistanceMunichStuttgart() throws Exception {
        RegionModule regionModule = new RegionModule();
        Method method = RegionModule.class.getDeclaredMethod("calculateDistance", double.class, double.class, double.class, double.class);
        method.setAccessible(true);

        double result = (double) method.invoke(regionModule,latMunich,longMunich,latStuttgart,lonStuttgart);

        //Compares the result with the distance from Munich to Stuttgart rounded to the nearest whole number
        assertEquals(distanceStuttgartMunich, result, 1.0);
    }

    //Distance according to https://www.gpskoordinaten.de/entfernung
    double distance5Away = 556.59;

    // Tests the distance between two points on the earth  if moved by 5 radians in every cardinal direction
    @Test
    void test5Away() throws Exception {
        RegionModule regionModule = new RegionModule();
        Method method = RegionModule.class.getDeclaredMethod("calculateDistance", double.class, double.class, double.class, double.class);
        method.setAccessible(true);

        double result5North = (double) method.invoke(regionModule,0,0,5,0);
        double result5South = (double) method.invoke(regionModule,0,0,-5,0);
        double result5East = (double) method.invoke(regionModule,0,0,0,5);
        double result5West = (double) method.invoke(regionModule,0,0,0,-5);

        //Compares the result rounded to the nearest whole number
        assertEquals(distance5Away, result5North, 1.0);
        assertEquals(distance5Away, result5South, 1.0);
        assertEquals(distance5Away, result5East, 1.0);
        assertEquals(distance5Away, result5West, 1.0);
    }


    double earthRadius = 6371.0;
    double earthCircumference = 2*earthRadius*Math.PI;
    double halfEarthCircumference = earthCircumference/2;


    // Tests the maximum distance between two points on the earth in every cardinal direction
    @Test
    void testMaxDistance() throws Exception {
        RegionModule regionModule = new RegionModule();
        Method method = RegionModule.class.getDeclaredMethod("calculateDistance", double.class, double.class, double.class, double.class);
        method.setAccessible(true);

        double resultMaxNorth = (double) method.invoke(regionModule,0,0,180,0);
        double resultMaxSouth = (double) method.invoke(regionModule,0,0,-180,0);
        double resultMaxEast = (double) method.invoke(regionModule,0,0,0,180);
        double resultMaxWest = (double) method.invoke(regionModule,0,0,0,-180);

        //Compares the result rounded to the second decimal place
        assertEquals(halfEarthCircumference, resultMaxNorth, 0.01);
        assertEquals(halfEarthCircumference, resultMaxSouth, 0.01);
        assertEquals(halfEarthCircumference, resultMaxEast, 0.01);
        assertEquals(halfEarthCircumference, resultMaxWest, 0.01);
    }
}