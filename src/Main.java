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

        String[] arguments = new String[args.length - 1];;

        String sourcepath = "";
        sourcepath = args[0];

        for(int i = 1; i < args.length; i++) {
            arguments[i-1] = args[i];
        }

        JSONArray meteorJSON = jsonHandler.JSONParseFile(sourcepath);

        int initialLength = meteorJSON.length(); // Anzahl vorher

        //JSONArray filteredMeteorJSON = regionModule.filter(meteorJSON,arguments); //Region
        //JSONArray filteredMeteorJSON = massModule.filter(meteorJSON,arguments); //Masse
        //JSONArray filteredMeteorJSON = yearModule.filter(meteorJSON,arguments); //Jahr
        JSONArray filteredMeteorJSON = classModule.filter(meteorJSON,arguments); //Klasse

        int finalLength = filteredMeteorJSON.length(); // Anzahl danach

        System.out.println("Filtered " + (initialLength-finalLength) + " meteors.");

        jsonHandler.JSONWrite(filteredMeteorJSON, sourcepath);
    }
}