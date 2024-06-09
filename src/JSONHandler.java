import org.json.JSONArray;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONHandler {

    private static JSONHandler instance;

    private JSONHandler() {}

    public static JSONHandler getInstance() {
        if (instance == null) {
            instance = new JSONHandler();
        }
        return instance;
    }

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
