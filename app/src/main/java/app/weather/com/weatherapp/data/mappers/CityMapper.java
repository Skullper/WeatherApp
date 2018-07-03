package app.weather.com.weatherapp.data.mappers;

import java.util.ArrayList;
import java.util.List;

import app.weather.com.weatherapp.api.responses.WeatherModel;
import app.weather.com.weatherapp.data.models.CityItem;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class CityMapper implements DataMapper<List<WeatherModel>, List<CityItem>> {

    @Override
    public List<CityItem> transformTo(List<WeatherModel> data) {
        List<CityItem> tempList = new ArrayList<>();
        for (WeatherModel model : data) {
            tempList.add(new CityItem(model.getId(), model.getName(), model.getCurrentTemperature()));
        }
        return tempList;
    }
}
