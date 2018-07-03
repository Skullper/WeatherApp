package app.weather.com.weatherapp.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.support.annotation.NonNull;

import app.weather.com.weatherapp.data.models.WeatherItem;
import app.weather.com.weatherapp.db.dao.WeatherDao;

import static app.weather.com.weatherapp.utils.Constantaz.DB_NAME;

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
                            .fallbackToDestructiveMigration() //allow drop table after version update
                            .build();
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

}