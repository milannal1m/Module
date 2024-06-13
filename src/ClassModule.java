import org.json.JSONObject;

import java.util.Arrays;

/**
 * Module to filter meteorites based on their class.
 */
public class ClassModule extends Module{

    /**
     * Implementation of abstract method to check if a meteorite matches the given meteor classes.
     * Checks if the class of meteorite is in the given meteor classes.
     *
     * @param meteor JSONObject of meteorite to check if it matches the parameters.
     * @param arguments Indefinite Amount Arguments to filter the meteorites.
     *                  Should one or multiple meteorite classes.
     * @return Boolean if the meteorite matches the filter.
     */
    public boolean meteorInParameters(JSONObject meteor, String... arguments){
        String meteorClass = meteor.optString("recclass", null);
        if (meteorClass != null) {
            return Arrays.asList(arguments).contains(meteorClass);
        }
        return false;
    }
}
