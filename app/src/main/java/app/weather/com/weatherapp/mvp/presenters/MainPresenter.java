package app.weather.com.weatherapp.mvp.presenters;

import app.weather.com.weatherapp.base.BasePresenter;
import app.weather.com.weatherapp.mvp.models.MainModel;
import app.weather.com.weatherapp.mvp.views.MainView;
import io.reactivex.disposables.CompositeDisposable;
import timber.log.Timber;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public class MainPresenter extends BasePresenter<MainView, MainModel> {

    private final CompositeDisposable disposable;

    public MainPresenter(MainView view) {
        super(view, new MainModel());
        disposable = new CompositeDisposable();
    }

    public void getWeather(int[] ids) {
        disposable.add(model.getTemperature(ids) //
                .subscribe(view::onWeatherFetched, Timber::e));
    }

    @Override
    public void destroy() {
        disposable.clear();
    }
}
