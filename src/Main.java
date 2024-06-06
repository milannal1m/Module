import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        int numOfParameters = 3;
        String sourcepath = "";
        double minMass = 0.0;
        double maxMass = 0.0;
        // weitere Parameter um zu Filtern nötig, numOfParamters muss inkrementiert werden.

        if (args.length < numOfParameters) {
            System.out.println("Nicht genug Parameter übergeben!");
        } else {
            try {
                sourcepath = args[0];
                minMass = Double.parseDouble(args[1]);
                maxMass = Double.parseDouble(args[2]);
                String input = new String((Files.readAllBytes(Paths.get(sourcepath))));
                System.out.print(input);
                JSONArray meteorJSON = new JSONArray(input);
                System.out.println(meteorJSON.length());
                JSONArray filteredMeteorJSON = filter(meteorJSON,minMass,maxMass);
                System.out.println(filteredMeteorJSON.length());
                //String filteredMeteorString = filteredMeteorJSON.toString(4);
                //System.out.print(filteredMeteorString);
            } catch (IOException e){
                System.out.print(e.getMessage());
            }
        }
    }
    public static JSONArray filter(JSONArray meteors, double minMass, double maxMass){

        JSONArray filteredMeteors = new JSONArray();

        for (int i = 0; i < meteors.length(); i++) {

            JSONObject meteor = meteors.getJSONObject(i);
            String meteorMassString = meteor.optString("mass", null);
            if (meteorMassString != null) {
                double meteorMass = Double.parseDouble(meteorMassString);

                if (meteorMass >= minMass && meteorMass <= maxMass) {
                    filteredMeteors.put(meteor);
                }
            }
        }

        return filteredMeteors;
    }
}