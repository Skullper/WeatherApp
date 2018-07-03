package app.weather.com.weatherapp;

import android.app.Application;
import android.content.Context;

import app.weather.com.weatherapp.api.RestClient;
import app.weather.com.weatherapp.utils.TimberCrashReportingTree;
import timber.log.Timber;

import static app.weather.com.weatherapp.utils.Constantaz.END_POINT;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class App extends Application {

    private static volatile App instance;

    public static Context getContext(){
        return instance.getApplicationContext();
    }

    @Override
    public void onCreate(){
        super.onCreate();
        instance = this;
        //Set up logging
        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        } else {
            Timber.plant(new TimberCrashReportingTree());
        }
        //Retrofit init
        RestClient.init(END_POINT);
        //Create database
//        AppDatabase.create(getApplicationContext());
    }

}
