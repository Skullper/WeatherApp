package app.weather.com.weatherapp.data.mappers;

import java.util.ArrayList;
import java.util.List;

import app.weather.com.weatherapp.data.models.CityItem;
import app.weather.com.weatherapp.data.models.WeatherItem;

import static app.weather.com.weatherapp.utils.StringUtils.toTime;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class CityMapper {

    public List<CityItem> transformTo(List<WeatherItem> data) {
        List<CityItem> tempList = new ArrayList<>(data.size());
        for (WeatherItem it : data) {
            tempList.add(transformTo(it));
        }
        return tempList;
    }

    public CityItem transformTo(WeatherItem it) {
        return new CityItem(it.getId(), it.getName(), toTime(it.getTimeInMillis()), it.getTemperature(), it.getIconUrl());
    }
}
