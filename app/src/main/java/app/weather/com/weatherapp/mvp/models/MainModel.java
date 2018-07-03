package app.weather.com.weatherapp.mvp.models;

import java.util.List;

import app.weather.com.weatherapp.api.RestClient;
import app.weather.com.weatherapp.api.mappers.CityMapper;
import app.weather.com.weatherapp.api.models.CityItem;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static app.weather.com.weatherapp.utils.Constantaz.CELCIUS;
import static app.weather.com.weatherapp.utils.StringUtils.convertToString;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class MainModel {

    /**
     * Retrieve current temperature for group of cities
     * @param ids of selected cities
     * @return list of {@link CityItem}
     */
    public Observable<List<CityItem>> getTemperature(int[] ids) {
        return RestClient.get().getApi() //
                .getTempByCity(convertToString(ids), CELCIUS) //
                .subscribeOn(Schedulers.io()) //
                .observeOn(AndroidSchedulers.mainThread()) //
                .map(response -> new CityMapper().transformTo(response.getList()));
    }
}
