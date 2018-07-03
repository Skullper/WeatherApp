package app.weather.com.weatherapp.mvp.presenters;

import java.net.HttpURLConnection;

import app.weather.com.weatherapp.R;
import app.weather.com.weatherapp.base.BasePresenter;
import app.weather.com.weatherapp.mvp.models.MainModel;
import app.weather.com.weatherapp.mvp.views.MainView;
import io.reactivex.disposables.CompositeDisposable;
import retrofit2.HttpException;
import timber.log.Timber;

import static app.weather.com.weatherapp.utils.StringUtils.getString;

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

    @Override
    public void destroy() {
        disposable.clear();
    }

    public void getWeather() {
        disposable.add(model.getCities() //
                .subscribe(view::onWeatherFetched, Timber::e));
    }

    public void addCity(String name) {
        disposable.add(model.getCity(name).subscribe(view::onCityAdded, e -> view.onError(getErrorMessage(e))));
    }

    private String getErrorMessage(Throwable throwable) {
        String errorMessage;
        Timber.e(throwable);
        if (throwable instanceof HttpException) {
            int errorCode = ((HttpException) throwable).code();
            switch (errorCode) {
                case HttpURLConnection.HTTP_NOT_FOUND:
                    errorMessage = getString(R.string.error_city_not_found);
                    break;
                case HttpURLConnection.HTTP_INTERNAL_ERROR:
                    errorMessage = getString(R.string.error_no_connection);
                    break;
                default:
                    errorMessage = getString(R.string.error_magic);
            }
        } else {
            errorMessage = getString(R.string.error_magic);
        }
        return errorMessage;
    }
}
