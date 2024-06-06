import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        int numOfParameters = 1;
        String sourcepath = "";
        // weitere Parameter um zu Filtern nötig, numOfParamters muss inkrementiert werden.

        if (args.length < numOfParameters) {
            System.out.println("Nicht genug Parameter übergeben!");
        } else {
            try {
                sourcepath = args[0];
                String input = new String((Files.readAllBytes(Paths.get(sourcepath))));
                System.out.print(input);
                JSONArray meteorJSON = new JSONArray(input);
                System.out.println(meteorJSON.length());
                System.out.println(meteorJSON.getJSONObject(0));
                JSONArray filteredMeteorJSON = filter(meteorJSON,20.0,40.0); // 20 und 40 sind Platzhalter
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
            String meteorMassString = meteor.optString("mass", null); // Das klappt irgendwie nicht
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