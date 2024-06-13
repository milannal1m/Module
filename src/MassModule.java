import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Module to filter meteorites based on their mass.
 */
public class MassModule extends Module{

    /**
     * Implementation of abstract method to check if a meteorite matches the given mass span.
     * Checks if mass of meteorite is in the given mass span.
     *
     * @param meteor JSONObject of meteorite to check if it matches the parameters.
     * @param arguments Indefinite Amount Arguments to filter the meteorites.
     *                  Should two masses, that build a mass span.
     * @return Boolean if the meteorite matches the filter.
     */
    public boolean meteorInParameters(JSONObject meteor, String... arguments){
        String meteorMassString = meteor.optString("mass", null);
        if (meteorMassString != null) {
            double meteorMass = Double.parseDouble(meteorMassString);
            double minMass = Double.parseDouble(arguments[0]);
            double maxMass = Double.parseDouble(arguments[1]);

            return meteorMass >= minMass && meteorMass <= maxMass;
        }
        return false;
    }
}
