import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        Module module = new Module();
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
                minMass = Double.parseDouble(args[1]); // Test Eingabe Parameter
                maxMass = Double.parseDouble(args[2]);
                String input = new String((Files.readAllBytes(Paths.get(sourcepath))));
                System.out.print(input);
                JSONArray meteorJSON = new JSONArray(input);

                System.out.println(meteorJSON.length()); //Anzahl davor

                JSONArray filteredMeteorJSON = module.filter(meteorJSON,44.83333,95.16667,4500); //Region
                //JSONArray filteredMeteorJSON = module.filter(meteorJSON,300.0,400.0); //Masse
                //JSONArray filteredMeteorJSON = module.filter(meteorJSON,2000,2011); //Jahr Bereich
                //JSONArray filteredMeteorJSON = module.filter(meteorJSON,2000,-1); //Jahr
                //String[] classes = {"H6","L5"}
                //JSONArray filteredMeteorJSON = module.filter(meteorJSON,44.83333,95.16667,4500); //class
                System.out.println(filteredMeteorJSON.length()); // Anzahl danach

                //String filteredMeteorString = filteredMeteorJSON.toString(4);
                //System.out.print(filteredMeteorString);
            } catch (IOException e){
                System.out.print(e.getMessage());
            }
        }
    }
}