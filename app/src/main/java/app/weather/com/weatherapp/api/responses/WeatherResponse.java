package app.weather.com.weatherapp.api.responses;

import java.util.List;

import app.weather.com.weatherapp.api.models.TemperatureModel;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class WeatherResponse {

    private int             cnt;

    private List<CityModel> list;

    public int getCnt() {
        return cnt;
    }

    public List<CityModel> getList() {
        return list;
    }

    public class CityModel {

        private TemperatureModel main;
        private int              id;
        private String           name;

        public TemperatureModel getMain() {
            return main;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }
    }
}
