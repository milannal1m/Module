import org.json.JSONObject;

public class YearModule extends Module{

    public boolean meteorInParameters(JSONObject meteor, Object... arguments){
        String meteorDateString = meteor.optString("year", null);

        if (meteorDateString != null) {
            String meteorYearString = meteorDateString.substring(0,4);
            int meteorYear = Integer.parseInt(meteorYearString);
            int minYear = (int) arguments[0];

            if(arguments.length > 1){
                int maxYear = (int) arguments[1];

                return meteorYear >= minYear && meteorYear <= maxYear;
            }else{

                return meteorYear == minYear;
            }
        }
        return false;
    }
}
