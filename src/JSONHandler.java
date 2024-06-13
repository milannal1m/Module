import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
/**
 * Handles the reading and writing of JSON files.
 */
public class JSONHandler {

    private static JSONHandler instance;

    private JSONHandler() {}

    // Singleton pattern
    public static JSONHandler getInstance() {
        if (instance == null) {
            instance = new JSONHandler();
        }
        return instance;
    }

    /**
     * Parses a JSON file and returns it as a JSONArray.
     *
     * @param sourcepath Path to the JSON file to parse.
     * @return JSONArray of the parsed JSON file.
     */
    public JSONArray JSONParseFile(String sourcepath){
        JSONArray parsedFile = null;
        try {
            String input = new String((Files.readAllBytes(Paths.get(sourcepath))));
            parsedFile = new JSONArray(input);
        } catch (IOException e){
            System.out.print(e.getMessage());
        }
        return parsedFile;
    }

    /**
     * Writes a JSONArray to a JSON file.
     *
     * @param meteorList JSONArray of meteorites to write to a JSON file.
     * @param sourcepath Path to the JSON file to write to.
     */

    public void JSONWrite(JSONArray meteorList, String sourcepath){
        try{
            FileWriter file = new FileWriter(sourcepath);
            file.write(meteorList.toString(4));
            file.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
