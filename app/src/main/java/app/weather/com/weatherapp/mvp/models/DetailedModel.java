package app.weather.com.weatherapp.mvp.models;

import app.weather.com.weatherapp.data.models.WeatherItem;
import app.weather.com.weatherapp.db.AppDatabase;
import app.weather.com.weatherapp.db.dao.WeatherDao;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pugman on 04.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class DetailedModel {

    private final WeatherDao dao;

    public DetailedModel() {
        this.dao = AppDatabase.get().getWeatherDao();
    }

    public Observable<WeatherItem> getWeatherItem(int id){
        return dao.getCityById(id)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
