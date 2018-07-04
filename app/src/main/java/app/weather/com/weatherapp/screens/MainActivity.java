package app.weather.com.weatherapp.screens;

import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

import app.weather.com.weatherapp.R;
import app.weather.com.weatherapp.adapter.CitiesAdapter;
import app.weather.com.weatherapp.adapter.decorations.CityItemDecoration;
import app.weather.com.weatherapp.base.activity.BaseActivity;
import app.weather.com.weatherapp.data.models.CityItem;
import app.weather.com.weatherapp.mvp.presenters.MainPresenter;
import app.weather.com.weatherapp.mvp.views.MainView;
import app.weather.com.weatherapp.providers.impls.Toaster;
import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

public class MainActivity extends BaseActivity<MainPresenter> implements MainView {

    private CitiesAdapter adapter;
    private AlertDialog   dialog; //city adding dialog

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
        presenter.getWeather();
    }

    @Override
    public void onWeatherFetched(List<CityItem> items) {
        adapter.setItems(items);
    }

    @Override
    public void onCityAdded(CityItem item) {
        dialog.dismiss();
        adapter.addItem(item);
    }

    @Override
    public void onError(String message) {
        Toaster.getInstance().show(message);
    }

    private void initRecycler() {
        rvCities.setLayoutManager(new LinearLayoutManager(this));
        rvCities.setItemAnimator(new DefaultItemAnimator());
        rvCities.setHasFixedSize(true);
        rvCities.addItemDecoration(new CityItemDecoration());
        adapter = new CitiesAdapter(Picasso.with(this), item -> Timber.d("Name: %s", item.getCityName()));
        rvCities.setAdapter(adapter);
    }

    @OnClick(R.id.fab_main_add)
    void showAddCityDialog() {
        View dialogView = View.inflate(this, R.layout.dialog_add_city, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this) //
                .setView(dialogView) //
                .setCancelable(true);
        dialog = builder.create();
        dialog.show();
        EditText etCityName = dialogView.findViewById(R.id.et_dialog_add_city);
        dialogView.findViewById(R.id.btn_dialog_add_city).setOnClickListener(view -> {
            String cityName = etCityName.getText().toString();
            if (!cityName.isEmpty()) {
                presenter.addCity(cityName);
            } else {
                Toaster.getInstance().show(R.string.dialog_add_city_no_name_error);
            }
        });
    }
}
