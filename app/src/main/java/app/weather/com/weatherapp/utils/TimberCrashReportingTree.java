package app.weather.com.weatherapp.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import timber.log.Timber;

/**
 * Timber tree which will be work in release build
 */
public class TimberCrashReportingTree extends Timber.Tree{

	@Override
	protected void log(int priority, String tag, @NonNull String message, Throwable t){
		if(priority == Log.VERBOSE || priority == Log.DEBUG || priority == Log.INFO) {
			return;
		}
		if(t != null && isExceptionPriority(priority)) {
            // TODO: 03.07.18 Add bug tracker
		}
	}

	private boolean isExceptionPriority(int priority){
		return priority == Log.ERROR || priority == Log.WARN;
	}
}