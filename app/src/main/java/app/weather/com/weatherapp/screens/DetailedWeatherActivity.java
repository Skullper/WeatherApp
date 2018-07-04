package app.weather.com.weatherapp.screens;

import android.content.Context;
import android.content.Intent;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import app.weather.com.weatherapp.R;
import app.weather.com.weatherapp.base.activity.BaseActivity;
import app.weather.com.weatherapp.data.models.WeatherItem;
import app.weather.com.weatherapp.mvp.presenters.DetailedPresenter;
import app.weather.com.weatherapp.mvp.views.DetailedView;
import app.weather.com.weatherapp.providers.impls.Toaster;
import butterknife.BindView;

/**
 * Created by pugman on 04.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class DetailedWeatherActivity extends BaseActivity<DetailedPresenter> implements DetailedView{

    private static final String EXTRA_WEATHER = "weather_item";

    @BindView(R.id.iv_detailed)
    ImageView ivIcon;
    @BindView(R.id.tv_detailed_description)
    TextView tvDescription;
    @BindView(R.id.tv_detailed_temperature)
    TextView tvTemp;
    @BindView(R.id.tv_detailed_humidity)
    TextView tvHumidity;
    @BindView(R.id.tv_detailed_pressure)
    TextView tvPressure;
    @BindView(R.id.tv_detailed_wind)
    TextView tvWind;
    @BindView(R.id.tv_detailed_rain)
    TextView tvRain;
    @BindView(R.id.tv_detailed_clouds)
    TextView tvClouds;

    public static Intent provideIntent(Context context, int id){
        Intent intent = new Intent(context, DetailedWeatherActivity.class);
        intent.putExtra(EXTRA_WEATHER, id);
        return intent;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_detailed;
    }

    @Override
    protected DetailedPresenter createPresenter() {
        return new DetailedPresenter(this);
    }

    @Override
    protected void initViews() {
        int id = getIntent().getIntExtra(EXTRA_WEATHER, 0);
        presenter.getCityWeather(id);
    }

    @Override
    public void onCityFetched(WeatherItem item) {
        getSupportActionBar().setTitle(item.getName());
        updateUi(item);
    }

    @Override
    public void onError(String message) {
        Toaster.getInstance().show(message);
    }

    private void updateUi(WeatherItem item){
        Picasso.with(this).load(item.getIconUrl()).into(ivIcon);
        tvDescription.setText(item.getDescription());
        tvTemp.setText(getString(R.string.detailed_temp, item.getTemperature()));
        tvHumidity.setText(getString(R.string.detailed_humidity, item.getHumidity()));
        tvPressure.setText(getString(R.string.detailed_pressure, item.getPressure()));
        tvWind.setText(getString(R.string.detailed_wind, item.getWindSpeed()));
        tvRain.setText(getString(R.string.detailed_rain, item.getRain()));
        tvClouds.setText(getString(R.string.detailed_clouds, item.getClouds()));
    }
}
