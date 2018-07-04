package app.weather.com.weatherapp.data.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import static app.weather.com.weatherapp.db.AppDatabase.WEATHER_TABLE_NAME;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

@Entity(tableName = WEATHER_TABLE_NAME)
public class WeatherItem implements Parcelable{

    @PrimaryKey
    private int    id;
    private String name;
    private long   timeInMillis;
    private String description;
    private String iconUrl;
    private int    humidity;
    private int    pressure;
    private double windSpeed;
    private int    rain;
    private int    clouds; //in %
    private double temperature;

    public WeatherItem() {
        //Required by Room
    }

    @Ignore
    public WeatherItem(int id, String name, long timeInMillis, String description, String iconUrl,
                       int humidity, int pressure, double windSpeed, int rain, int clouds, double temperature) {
        this.id = id;
        this.name = name;
        this.timeInMillis = timeInMillis;
        this.description = description;
        this.iconUrl = iconUrl;
        this.humidity = humidity;
        this.pressure = pressure;
        this.windSpeed = windSpeed;
        this.rain = rain;
        this.clouds = clouds;
        this.temperature = temperature;
    }

    @Ignore
    protected WeatherItem(Parcel in) {
        id = in.readInt();
        name = in.readString();
        timeInMillis = in.readLong();
        description = in.readString();
        iconUrl = in.readString();
        humidity = in.readInt();
        pressure = in.readInt();
        windSpeed = in.readDouble();
        rain = in.readInt();
        clouds = in.readInt();
        temperature = in.readDouble();
    }

    public static final Creator<WeatherItem> CREATOR = new Creator<WeatherItem>() {
        @Override
        public WeatherItem createFromParcel(Parcel in) {
            return new WeatherItem(in);
        }

        @Override
        public WeatherItem[] newArray(int size) {
            return new WeatherItem[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public void setPressure(int pressure) {
        this.pressure = pressure;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public int getRain() {
        return rain;
    }

    public void setRain(int rain) {
        this.rain = rain;
    }

    public int getClouds() {
        return clouds;
    }

    public void setClouds(int clouds) {
        this.clouds = clouds;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(name);
        dest.writeLong(timeInMillis);
        dest.writeString(description);
        dest.writeString(iconUrl);
        dest.writeInt(humidity);
        dest.writeInt(pressure);
        dest.writeDouble(windSpeed);
        dest.writeInt(rain);
        dest.writeInt(clouds);
        dest.writeDouble(temperature);
    }
}
