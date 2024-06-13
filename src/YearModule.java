import org.json.JSONObject;


/**
 * Module to filter meteorites based on their finding Year.
 */
public class YearModule extends Module{

    /**
     * Implementation of abstract method to check if a meteorite matches the given finding Year.
     * Checks if meteorite was found in the given year or in the given year span.
     *
     * @param meteor JSONObject of meteorite to check if it matches the parameters.
     * @param arguments Indefinite Amount Arguments to filter the meteorites.
     *                  Should one year or two years that build a year span.
     * @return Boolean if the meteorite matches the filter.
     */
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
