package app.weather.com.weatherapp.utils;

/**
 * Stores all in app constants
 */
public final class Constantaz {

    private Constantaz() {
        throw new IllegalStateException("U cannot create instance of this class");
    }

    public static final String END_POINT = "https://api.openweathermap.org/data/2.5/";
    public static final String API_KEY   = "7b8832d70f32ad2704f4f8dd90b8e1f1";
    public static final String CELCIUS   = "metric";
    public static final int    KIEV      = 703448;
}