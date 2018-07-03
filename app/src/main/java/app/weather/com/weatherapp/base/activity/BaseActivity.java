package app.weather.com.weatherapp.base.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import app.weather.com.weatherapp.base.BasePresenter;
import app.weather.com.weatherapp.base.BaseView;
import butterknife.ButterKnife;

/**
 * Created by pugman on 03.07.18.
 * Contact the developer - sckalper@gmail.com
 * company - A2Lab
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity implements BaseView{

    protected P    presenter;

    /**
     * Place your layout resource as return parameter
     *
     * @return resourceId of layout which designed for current fragment
     */
    protected abstract @LayoutRes
    int getLayoutId();

    /**
     * You should create an instance of your presenter here. After that you can use {@link #presenter} everywhere in created activity
     *
     * @return an instance of presenter for created activity
     */
    protected abstract P createPresenter();

    /**
     * Initialize all views here(e.g. ButterKnife.bind(this)).
     * This method do the same as {@link android.app.Activity#onCreate}
     */
    protected abstract void initViews();

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        presenter = createPresenter();
        initViews();
    }

    @Override
    protected void onStop() {
        presenter.destroy();
        super.onStop();
    }

    protected void startActivityWithFinish(Intent intent){
        startActivity(intent);
        finish();
    }

}