package app.weather.com.weatherapp.api.mappers;

import java.util.ArrayList;
import java.util.List;

import app.weather.com.weatherapp.api.models.CityItem;
import app.weather.com.weatherapp.api.responses.WeatherResponse;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class CityMapper implements DataMapper<List<WeatherResponse.CityModel>, List<CityItem>> {

    @Override
    public List<CityItem> transformTo(List<WeatherResponse.CityModel> data) {
        List<CityItem> tempList = new ArrayList<>();
        for (WeatherResponse.CityModel model : data) {
            tempList.add(new CityItem(model.getId(), model.getName(), model.getMain().getTemp()));
        }
        return tempList;
    }
}
