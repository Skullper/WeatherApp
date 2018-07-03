package app.weather.com.weatherapp.base;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public abstract class BasePresenter<T extends BaseView, M> {

    protected final T view;
    protected final M model;

    public BasePresenter(T view, M model) {
        this.view = view;
        this.model = model;
    }

    public abstract void destroy();

}