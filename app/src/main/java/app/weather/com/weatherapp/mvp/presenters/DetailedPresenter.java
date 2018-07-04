package app.weather.com.weatherapp.mvp.presenters;

import app.weather.com.weatherapp.R;
import app.weather.com.weatherapp.base.BasePresenter;
import app.weather.com.weatherapp.mvp.models.DetailedModel;
import app.weather.com.weatherapp.mvp.views.DetailedView;
import io.reactivex.disposables.CompositeDisposable;

import static app.weather.com.weatherapp.utils.StringUtils.getString;

/**
 * Created by pugman on 04.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class DetailedPresenter extends BasePresenter<DetailedView, DetailedModel> {

    private final CompositeDisposable disposable;

    public DetailedPresenter(DetailedView view) {
        super(view, new DetailedModel());
        disposable = new CompositeDisposable();
    }

    @Override
    public void destroy() {
        disposable.clear();
    }

    public void getCityWeather(int id) {
        disposable.add(model.getWeatherItem(id).subscribe(view::onCityFetched, //
                e -> view.onError(getString(R.string.error_city_not_found))));
    }
}
