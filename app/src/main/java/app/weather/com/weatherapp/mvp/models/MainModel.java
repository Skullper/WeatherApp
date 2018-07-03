package app.weather.com.weatherapp.mvp.models;

import java.util.List;

import app.weather.com.weatherapp.api.RestClient;
import app.weather.com.weatherapp.data.mappers.CityMapper;
import app.weather.com.weatherapp.data.models.CityItem;
import app.weather.com.weatherapp.db.AppDatabase;
import app.weather.com.weatherapp.db.dao.WeatherDao;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static app.weather.com.weatherapp.utils.StringUtils.convertToString;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class MainModel {

    /**
     * Retrieve current temperature for group of cities
     *
     * @param ids of selected cities
     *
     * @return list of {@link CityItem}
     */
    public Observable<List<CityItem>> getCities(int[] ids) {
        WeatherDao dao = AppDatabase.get().getWeatherDao();
        return RestClient.get().getApi() //
                .getWeatherForGroupOfCities(convertToString(ids)) //
                .subscribeOn(Schedulers.io()) //
                .observeOn(AndroidSchedulers.mainThread()) //
//                .map(response -> new WeatherMapper().transformTo(response.getList())) //
//                .concatMap(response -> dao.setTasks(new WeatherMapper().transformTo(response.getList())));
                .map(response -> new CityMapper().transformTo(response.getList()));
    }

    public Observable<CityItem> getCity(String cityName) {
        return RestClient.get().getApi().getWeatherByCityName(cityName) //
                .subscribeOn(Schedulers.io()) //
                .observeOn(AndroidSchedulers.mainThread()) //
                .map(response -> new CityItem(response.getId(), response.getName(), //
                        response.getTemperature().getTemp()));
    }
}
