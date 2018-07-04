package app.weather.com.weatherapp.mvp.models;

import java.util.List;

import app.weather.com.weatherapp.api.RestClient;
import app.weather.com.weatherapp.data.mappers.CityMapper;
import app.weather.com.weatherapp.data.mappers.WeatherMapper;
import app.weather.com.weatherapp.data.models.CityItem;
import app.weather.com.weatherapp.data.models.WeatherItem;
import app.weather.com.weatherapp.db.AppDatabase;
import app.weather.com.weatherapp.db.dao.WeatherDao;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class MainModel {

    private final WeatherDao dao;

    public MainModel() {
        this.dao = AppDatabase.get().getWeatherDao();
    }

    /**
     * Retrieve current temperature for group of cities
     *
     * @return list of {@link CityItem}
     */
    public Observable<List<CityItem>> getCities() {
        return Observable.concatArray(getWeatherLocally(), getWeatherRemote())
                .subscribeOn(Schedulers.io())
                .materialize()
                .observeOn(AndroidSchedulers.mainThread())
                .filter(it -> !it.isOnError())
                .dematerialize();
    }

    public Observable<CityItem> getCity(String cityName) {
        return RestClient.get().getApi().getWeatherByCityName(cityName) //
                .subscribeOn(Schedulers.io())
                .map(new WeatherMapper()::transformTo)
                .doOnNext(dao::insertCity)
                .map(new CityMapper()::transformTo)
                .observeOn(AndroidSchedulers.mainThread());
    }

    private Single<List<Integer>> getExistingIds(){
        return dao.getIds()
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    private Observable<List<CityItem>> getWeatherRemote() {
        return getExistingIds().map(ids -> RestClient.get().getApi() //
                .getWeatherForGroupOfCities(convertToString(ids)))
                .blockingGet()
                .map(response -> new WeatherMapper().transformTo(response.getList()))
                .doOnNext(this::updateWeatherInDb)
                .map(new CityMapper()::transformTo);
    }

    private Observable<List<CityItem>> getWeatherLocally(){
        return dao.getCities().toObservable().map(new CityMapper()::transformTo);
    }

    private void updateWeatherInDb(List<WeatherItem> items) {
        Completable.fromAction(() -> dao.insertCities(items))
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe();
    }

    private String convertToString(List<Integer> array) {
        StringBuilder result = new StringBuilder(String.valueOf(array.get(0)));
        for (int i = 1; i < array.size(); i++) {
            result.append(",").append(array.get(i));
        }
        return result.toString();
    }

}
