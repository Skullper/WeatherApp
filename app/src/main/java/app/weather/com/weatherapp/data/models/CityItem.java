package app.weather.com.weatherapp.data.models;

/*
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class CityItem {

    private final int    id;
    private final String cityName;
    private final String time;
    private final double temp;
    private final String iconUrl;

    public CityItem(int id, String cityName, String time, double temp, String iconUrl) {
        this.id = id;
        this.cityName = cityName;
        this.time = time;
        this.temp = temp;
        this.iconUrl = iconUrl;
    }

    public int getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getTime() {
        return time;
    }

    public double getTemp() {
        return temp;
    }

    public String getIconUrl() {
        return iconUrl;
    }
}
