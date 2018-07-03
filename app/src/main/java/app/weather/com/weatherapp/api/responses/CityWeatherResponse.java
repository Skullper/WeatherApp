package app.weather.com.weatherapp.api.responses;

import app.weather.com.weatherapp.api.models.TemperatureModel;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class CityWeatherResponse {

    private int              id;
    private String           name;
    private TemperatureModel main;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TemperatureModel getTemperature() {
        return main;
    }


}
