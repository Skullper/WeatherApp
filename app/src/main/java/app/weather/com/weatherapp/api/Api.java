package app.weather.com.weatherapp.api;

import app.weather.com.weatherapp.api.responses.WeatherResponse;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public interface Api {

    @GET("group")
    Observable<WeatherResponse> getTempByCity(@Query("id") String arrayOfIds,
                                              @Query("units") String unit);

}
