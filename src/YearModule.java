import org.json.JSONObject;

public class YearModule extends Module{

    // checks if the year of the meteor is in the parameters given
    public boolean meteorInParameters(JSONObject meteor, String... arguments){
        String meteorDateString = meteor.optString("year", null);

        if (meteorDateString != null) {
            String meteorYearString = meteorDateString.substring(0,4);
            int meteorYear = Integer.parseInt(meteorYearString);
            int minYear = Integer.parseInt(arguments[0]);

            if(arguments.length > 1){
                int maxYear = Integer.parseInt(arguments[1]);

                return meteorYear >= minYear && meteorYear <= maxYear;
            }else{

                return meteorYear == minYear;
            }
        }
        return false;
    }
}
