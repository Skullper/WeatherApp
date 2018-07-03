package app.weather.com.weatherapp.api.responses;

import com.google.gson.annotations.SerializedName;

class Rain {

    @SerializedName("3h")
    private int rain;

    public int getRain() {
        return rain;
    }

}