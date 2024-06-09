import org.json.JSONObject;

import java.util.Arrays;

public class ClassModule extends Module{

    public boolean meteorInParameters(JSONObject meteor, String... arguments){
        String meteorClass = meteor.optString("recclass", null);
        String[] classes = arguments[0].toString().split(",");
        if (meteorClass != null) {
            return Arrays.asList(classes).contains(meteorClass);
        }
        return false;
    }
}
