import org.json.JSONObject;

import java.util.Arrays;

public class ClassModule extends Module{

    public boolean meteorInParameters(JSONObject meteor, String... arguments){
        String meteorClass = meteor.optString("recclass", null);
        if (meteorClass != null) {
            return Arrays.asList(arguments).contains(meteorClass);
        }
        return false;
    }
}
