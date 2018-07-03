package app.weather.com.weatherapp.screens;

import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;
import java.util.Objects;

import app.weather.com.weatherapp.R;
import app.weather.com.weatherapp.adapter.CitiesAdapter;
import app.weather.com.weatherapp.adapter.decorations.CityItemDecoration;
import app.weather.com.weatherapp.api.models.CityItem;
import app.weather.com.weatherapp.base.activity.BaseActivity;
import app.weather.com.weatherapp.mvp.presenters.MainPresenter;
import app.weather.com.weatherapp.mvp.views.MainView;
import butterknife.BindView;
import timber.log.Timber;

import static app.weather.com.weatherapp.utils.Constantaz.KIEV;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private int[] cityIds = new int[]{KIEV};
    private CitiesAdapter adapter;

    @BindView(R.id.rv_main_cities)
    RecyclerView rvCities;
    @BindView(R.id.pb_main)
    ProgressBar  pbContentLoading;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected void initViews() {
        pbContentLoading.setVisibility(View.GONE);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.main_toolbar_title);
        initRecycler();
        presenter.getWeather(cityIds);
    }

    @Override
    public void onWeatherFetched(List<CityItem> items) {
        adapter.addItems(items);
    }

    private void initRecycler() {
        rvCities.setLayoutManager(new LinearLayoutManager(this));
        rvCities.setItemAnimator(new DefaultItemAnimator());
        rvCities.setHasFixedSize(true);
        rvCities.addItemDecoration(new CityItemDecoration());
        adapter = new CitiesAdapter(item -> Timber.d("Name: %s", item.getCityName()));
        rvCities.setAdapter(adapter);
    }
}
