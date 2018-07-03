package app.weather.com.weatherapp.api.responses;

import java.util.List;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class WeatherResponse {

    private int cnt;
    private List<WeatherModel> list;

    public int getCnt() {
        return cnt;
    }

    public List<WeatherModel> getList() {
        return list;
    }

}
