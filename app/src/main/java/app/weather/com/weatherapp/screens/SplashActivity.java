package app.weather.com.weatherapp.screens;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import java.util.concurrent.Executors;

import app.weather.com.weatherapp.data.models.WeatherItem;
import app.weather.com.weatherapp.db.AppDatabase;

import static app.weather.com.weatherapp.utils.Constantaz.ICON_PATH;
import static app.weather.com.weatherapp.utils.Constantaz.KIEV;
import static app.weather.com.weatherapp.utils.StringUtils.format;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        prePopulateKiev();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    //Call it here because RoomCallback's onCreate() called only after performing concrete operation
    // with DAO. Because I need data from start I pre-populate it here.
    private void prePopulateKiev() {
        Executors.newSingleThreadExecutor().execute(() -> //
                AppDatabase.get().getWeatherDao().insertCity(new WeatherItem(KIEV, "Kiev", 1530639126, "Clear sky",//
                        format(ICON_PATH, "01d"), 59, 1014, 4, 0, //
                        0, 18.68)));
    }

}
