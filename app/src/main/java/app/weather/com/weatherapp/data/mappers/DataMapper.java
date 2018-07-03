package app.weather.com.weatherapp.data.mappers;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public interface DataMapper<R, E> {

    E transformTo(R data);
}
