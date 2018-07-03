package app.weather.com.weatherapp.providers.impls;

import android.os.Build;
import android.support.annotation.StringRes;
import android.widget.Toast;

import java.util.Locale;

import app.weather.com.weatherapp.App;
import app.weather.com.weatherapp.providers.Reporter;

import static app.weather.com.weatherapp.utils.StringUtils.getString;


/*
  Created by pugman on 23.01.18.
  Contact the developer - sckalper@gmail.com
  company - A2Lab
 */

/**
 * Class designed to replace the toast's routine creating and displaying mechanism
 */
public final class Toaster implements Reporter {

	private static volatile Toaster instance;

	private Toaster(){
		//Preventing a reflection api
		if(instance != null) {
            throw new IllegalStateException("Use get() method to get the single instance of this class.");
		}
	}

	public static Toaster getInstance(){
		//Lazy initialization
		if(instance == null) {
			//Double locking
			synchronized(Toaster.class){
				if(instance == null) instance = new Toaster();
			}
		}
		return instance;
	}

	@Override
	public void show(@StringRes int resourceId){
		String text = getString(resourceId);
		show(text);
	}

	@Override
	public void show(@StringRes int resourceId, Object... args){
		String formatted = String.format(getCurrentLocale(), getString(resourceId), args);
		show(formatted);
	}

	@Override
	public void show(String text){
		Toast.makeText(App.getContext(), text, Toast.LENGTH_SHORT).show();
	}

	private Locale getCurrentLocale(){
		if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
			return App.getContext().getResources().getConfiguration().getLocales().get(0);
		} else{
			//noinspection deprecation
			return App.getContext().getResources().getConfiguration().locale;
		}
	}
}
