package app.weather.com.weatherapp.api.responses;

import java.util.List;

import app.weather.com.weatherapp.data.models.TemperatureModel;

import static app.weather.com.weatherapp.utils.Constantaz.ICON_PATH;
import static app.weather.com.weatherapp.utils.StringUtils.format;

public class WeatherModel {

    private int              id;
    private String           name;
    private List<Weather>    weather;
    private TemperatureModel main;
    private Wind             wind;
    private Clouds           clouds;
    private Rain             rain;
    private int              dt; //time stamp

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWeatherDescription() {
        return weather.get(0).getDescription();
    }

    public String getWeatherIconUrl() {
        return format(ICON_PATH, weather.get(0).getIcon());
    }

    public double getCurrentTemperature() {
        return main.getTemp();
    }

    public int getHumidity() {
        return main.getHumidity();
    }

    public int getPressure() {
        return main.getPressure();
    }

    public double getWindSpeed() {
        return wind.getSpeed();
    }

    public int getRain() {
        return rain.getRain();
    }

    public int getClouds() {
        return clouds.getAll();
    }

    public int getDt() {
        return dt;
    }
}