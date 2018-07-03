package app.weather.com.weatherapp.data.mappers;

import java.util.ArrayList;
import java.util.List;

import app.weather.com.weatherapp.data.models.CityItem;
import app.weather.com.weatherapp.data.models.WeatherItem;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class CityMapper {

    public List<CityItem> transformTo(List<WeatherItem> data) {
        List<CityItem> tempList = new ArrayList<>(data.size());
        for (WeatherItem it : data) {
            tempList.add(new CityItem(it.getId(), it.getName(), it.getTemperature()));
        }
        return tempList;
    }
}
