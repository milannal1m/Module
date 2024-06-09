import org.json.JSONArray;
import org.json.JSONObject;

public class MassModule extends Module{

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
