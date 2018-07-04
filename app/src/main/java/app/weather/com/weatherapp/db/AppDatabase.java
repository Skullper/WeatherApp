package app.weather.com.weatherapp.db;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import java.util.concurrent.Executors;

import app.weather.com.weatherapp.data.models.WeatherItem;
import app.weather.com.weatherapp.db.dao.WeatherDao;

import static app.weather.com.weatherapp.utils.Constantaz.DB_NAME;
import static app.weather.com.weatherapp.utils.Constantaz.ICON_PATH;
import static app.weather.com.weatherapp.utils.Constantaz.KIEV;
import static app.weather.com.weatherapp.utils.StringUtils.format;

/**
 * Class responsible for building a database and saving instance for it. This class also provides
 * access to DAO.
 * <p>
 * NOTE: You must call {@link #create(Context)} method before you will start working with database
 */
@Database(entities = {WeatherItem.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public static final String WEATHER_TABLE_NAME = "weather_table";

    private static volatile AppDatabase instance;

    public static void create(@NonNull Context context) {
        //Lazy initialization
        if (instance == null) {
            //Double locking
            synchronized(AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AppDatabase.class, DB_NAME) //
                            .addCallback(new Callback() {
                                @Override
                                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                    super.onCreate(db);
                                    Executors.newSingleThreadExecutor().execute(() -> get().getWeatherDao().insertCity(prePopulateKiev()));
                                }
                            }).fallbackToDestructiveMigration() //allow drop table after version update
                            .build();
                    //Run this to trigger room onCreate() callback
                    Executors.newSingleThreadExecutor().execute(() -> {
                        AppDatabase.get().beginTransaction();
                        AppDatabase.get().endTransaction();
                    });
                }
            }
        }
    }

    public static AppDatabase get() {
        if (instance == null) {
            throw new IllegalStateException("Database had been not created");
        }
        return instance;
    }

    public abstract WeatherDao getWeatherDao();

    private static WeatherItem prePopulateKiev() {
        return new WeatherItem(KIEV, "Kiev", System.currentTimeMillis(), "Clear sky",//
                format(ICON_PATH, "01d"), 59, 1014, 4, 0, //
                0, 18.68);
    }
}