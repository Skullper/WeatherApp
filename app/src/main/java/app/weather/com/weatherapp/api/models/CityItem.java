package app.weather.com.weatherapp.api.models;

import android.arch.persistence.room.PrimaryKey;

/*
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class CityItem {

    @PrimaryKey
    private int id;
    private String cityName;
    private double temp;

    public CityItem() {
        //Required by Room
    }

    public CityItem(int id, String cityName, double temp) {
        this.id = id;
        this.cityName = cityName;
        this.temp = temp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }
}
