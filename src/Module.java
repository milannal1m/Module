import org.json.JSONArray;
import org.json.JSONObject;


/**
 * Abstract class Module to filter meteorites.
 */
public  abstract class Module {

    /**
     * Filters meteorites from a given JSONArray based on the arguments.
     * Is implemented as a template method.
     *
     * @param meteors JSONArray of meteorites to filter.
     * @param arguments Indefinite Amount Arguments to filter the meteorites.
     * @return JSONArray of meteorites that match the filter.
     */
    public JSONArray filter(JSONArray meteors, String... arguments){
        JSONArray filteredMeteors = new JSONArray();

        for (int i = 0; i < meteors.length(); i++) {

            JSONObject meteor = meteors.getJSONObject(i);
            if (this.meteorInParameters(meteor, arguments)){
                filteredMeteors.put(meteor);
            }
        }
        return filteredMeteors;
    }

    /**
     * Abstract method to check if a meteorite matches the given parameters.
     *
     * @param meteor JSONObject of meteorite to check if it matches the parameters.
     * @param arguments Indefinite Amount Arguments to filter the meteorites.
     * @return Boolean if the meteorite matches the filter.
     */
    public abstract boolean meteorInParameters(JSONObject meteor, String... arguments);

}