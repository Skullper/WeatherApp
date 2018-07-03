package app.weather.com.weatherapp.providers;

public interface Reporter{

	void show(int resourceId);

	void show(int resourceId, Object... args);

	/**
	 * Shows a toast message
	 * @param text to be shown
	 */
	void show(String text);

}