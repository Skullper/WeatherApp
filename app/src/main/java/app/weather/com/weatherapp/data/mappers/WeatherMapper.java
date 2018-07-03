package app.weather.com.weatherapp.data.mappers;

import java.util.ArrayList;
import java.util.List;

import app.weather.com.weatherapp.api.responses.WeatherModel;
import app.weather.com.weatherapp.data.models.WeatherItem;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class WeatherMapper {

    public List<WeatherItem> transformTo(List<WeatherModel> data) {
        List<WeatherItem> tempList = new ArrayList<>(data.size());
        for (WeatherModel it : data) {
            tempList.add(new WeatherItem(it.getId(), it.getName(), it.getDt(), it.getWeatherDescription(),
                    it.getWeatherIconUrl(), it.getHumidity(), it.getPressure(), it.getWindSpeed(),
                    it.getRain(), it.getClouds(), it.getCurrentTemperature()));
        }
        return tempList;
    }

    public WeatherItem transformTo(WeatherModel it){
        return new WeatherItem(it.getId(), it.getName(), it.getDt(), it.getWeatherDescription(),
                it.getWeatherIconUrl(), it.getHumidity(), it.getPressure(), it.getWindSpeed(),
                it.getRain(), it.getClouds(), it.getCurrentTemperature());
    }
}
