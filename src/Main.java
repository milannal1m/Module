import org.json.JSONArray;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {

        MassModule massModule = new MassModule();
        ClassModule classModule = new ClassModule();
        RegionModule regionModule = new RegionModule();
        YearModule yearModule = new YearModule();

        JSONHandler jsonHandler = JSONHandler.getInstance();

        int numOfParameters = 3;
        String sourcepath = "";
        double minMass = 0.0;
        double maxMass = 0.0;
        // weitere Parameter um zu Filtern nötig, numOfParamters muss inkrementiert werden.

        if (args.length < numOfParameters) {
            System.out.println("Nicht genug Parameter übergeben!");
        } else {
            sourcepath = args[0];
            JSONArray meteorJSON = jsonHandler.JSONParseFile(sourcepath);
            //minMass = Double.parseDouble(args[1]); // Test Eingabe Parameter
            //maxMass = Double.parseDouble(args[2]);

            System.out.println(meteorJSON.length()); //Anzahl davor

            JSONArray filteredMeteorJSON = regionModule.filter(meteorJSON,44.83333,95.16667,4500.0); //Region
            //JSONArray filteredMeteorJSON = massModule.filter(meteorJSON,300.0,400.0); //Masse
            //JSONArray filteredMeteorJSON = yearModule.filter(meteorJSON,2000,2011); //Jahr Bereich
            //JSONArray filteredMeteorJSON = yearModule.filter(meteorJSON,2000); //Jahr
            //String[] classes = {"H6","L5"};
            //JSONArray filteredMeteorJSON = classModule.filter(meteorJSON,classes); //class
            System.out.println(filteredMeteorJSON.length()); // Anzahl danach

            jsonHandler.JSONWrite(filteredMeteorJSON, sourcepath);

        }
    }
}