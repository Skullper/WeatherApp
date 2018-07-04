package app.weather.com.weatherapp.mvp.views;

import app.weather.com.weatherapp.base.BaseView;
import app.weather.com.weatherapp.data.models.WeatherItem;

/**
 * Created by pugman on 04.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public interface DetailedView extends BaseView {

    void onCityFetched(WeatherItem item);

    void onError(String message);
}
