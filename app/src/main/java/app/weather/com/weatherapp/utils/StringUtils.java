package app.weather.com.weatherapp.utils;

import android.support.annotation.StringRes;

import java.util.List;
import java.util.Locale;

import app.weather.com.weatherapp.App;

/**
 * Helper class designed to simplify string manipulations in project
 */
public final class StringUtils {

    private StringUtils() {
        throw new IllegalStateException("U cannot create instance of Utils class");
    }

    public static String getString(@StringRes int resourceId) {
        return App.getContext().getString(resourceId);
    }

    public static String format(String text, Object... args) {
        return String.format(Locale.US, text, args);
    }

    public static String format(@StringRes int resourceId, Object... args) {
        return format(getString(resourceId), args);
    }

    public static String convertToString(List<Integer> array) {
        StringBuilder result = new StringBuilder(String.valueOf(array.get(0)));
        for (int i = 1; i < array.size(); i++) {
            result.append(",").append(array.get(i));
        }
        return result.toString();
    }
}