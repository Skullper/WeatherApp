package app.weather.com.weatherapp.adapter;

import app.weather.com.weatherapp.api.models.CityItem;

/*
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

/**
 * Used to handle click event on recycler view item in {@link CitiesAdapter}
 */
public interface OnCityClickListener {

    void onCityClick(CityItem item);
}
