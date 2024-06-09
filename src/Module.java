import org.json.JSONArray;
import org.json.JSONObject;

public  abstract class Module {

    public JSONArray filter(JSONArray meteors, Object... arguments){
        JSONArray filteredMeteors = new JSONArray();

        for (int i = 0; i < meteors.length(); i++) {

            JSONObject meteor = meteors.getJSONObject(i);
            if (this.meteorInParameters(meteor, arguments)){
                filteredMeteors.put(meteor);
            }
        }
        return filteredMeteors;
    }

    public abstract boolean meteorInParameters(JSONObject meteor, Object... arguments);

}
