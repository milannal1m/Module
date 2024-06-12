import org.json.JSONArray;
import org.json.JSONObject;

public  abstract class Module {

    //implements the filter method for the modules as a template method
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

    //checks if a given meteor is in the parameters of the module
    public abstract boolean meteorInParameters(JSONObject meteor, String... arguments);

}
