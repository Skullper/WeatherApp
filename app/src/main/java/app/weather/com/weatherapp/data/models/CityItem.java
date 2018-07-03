package app.weather.com.weatherapp.data.models;

/*
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class CityItem {

    private final int    id;
    private final String cityName;
    private final double temp;

    public CityItem(int id, String cityName, double temp) {
        this.id = id;
        this.cityName = cityName;
        this.temp = temp;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }


    public double getTemp() {
        return temp;
    }

}
