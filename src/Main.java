import org.json.JSONArray;
import org.json.JSONObject;

public class Main {
    public static void main(String[] args) {

        int numOfParameters = 1;
        String meteorString = "";
        // weitere Parameter um zu Filtern nötig, numOfParamters muss inkrementiert werden.

        if (args.length < numOfParameters) {
            System.out.println("Nicht genug Parameter übergeben!");
        } else {
            meteorString = args[0];
            System.out.println(meteorString);
            JSONArray meteorJSON = new JSONArray(meteorString);
            JSONArray filteredMeteorJSON = filter(meteorJSON,20,40); // 20 und 40 sind Platzhalter
            String filteredMeteorString = filteredMeteorJSON.toString(4);
            System.out.print(filteredMeteorString);
        }


    }
    public static JSONArray filter(JSONArray meteors, int minMass, int maxMass){

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