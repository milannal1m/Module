import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }

    }
    public JSONArray filter(JSONArray meteors, int minMass, int maxMass){

        JSONArray filteredMeteors = null;

        for (int i = 0; i < meteors.length(); i++) {

            JSONObject meteor = meteors.getJSONObject(i);

            int meteorMass = meteor.getInt("mass");

            if(meteorMass >= minMass && meteorMass <= maxMass){
                filteredMeteors.put(meteor);
            }
        }

        return filteredMeteors;
    }
}