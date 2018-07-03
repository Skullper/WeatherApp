package app.weather.com.weatherapp.mvp.views;

import java.util.List;

import app.weather.com.weatherapp.data.models.CityItem;
import app.weather.com.weatherapp.base.BaseView;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public interface MainView extends BaseView {

    void onWeatherFetched(List<CityItem> items);

    void onCityAdded(CityItem item);

    void onError(String message);
}
