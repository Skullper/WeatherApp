package app.weather.com.weatherapp.db.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import app.weather.com.weatherapp.data.models.WeatherItem;
import io.reactivex.Single;

import static app.weather.com.weatherapp.db.AppDatabase.WEATHER_TABLE_NAME;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

@Dao
public interface WeatherDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCities(List<WeatherItem> items);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCity(WeatherItem city);

    @Query("SELECT * FROM " + WEATHER_TABLE_NAME)
    Single<List<WeatherItem>> getCities();

    @Query("SELECT id FROM " + WEATHER_TABLE_NAME)
    Single<List<Integer>> getIds();

    @Query("SELECT * FROM " + WEATHER_TABLE_NAME + " WHERE id=:cityId")
    Single<WeatherItem> getCityById(int cityId);

}
