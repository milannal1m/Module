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

        System.out.println(meteorJSON.length()); //Anzahl davor

        JSONArray filteredMeteorJSON = regionModule.filter(meteorJSON,arguments); //Region
        //JSONArray filteredMeteorJSON = massModule.filter(meteorJSON,300.0,400.0); //Masse
        //JSONArray filteredMeteorJSON = yearModule.filter(meteorJSON,2000,2011); //Jahr Bereich
        //JSONArray filteredMeteorJSON = yearModule.filter(meteorJSON,2000); //Jahr einzeln
        //String[] classes = {"H6","L5"};
        //JSONArray filteredMeteorJSON = classModule.filter(meteorJSON,classes); //class
        System.out.println(filteredMeteorJSON.length()); // Anzahl danach

        jsonHandler.JSONWrite(filteredMeteorJSON, sourcepath);
    }
}