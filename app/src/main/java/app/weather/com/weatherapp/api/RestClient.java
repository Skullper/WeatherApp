package app.weather.com.weatherapp.api;

import java.util.concurrent.TimeUnit;

import app.weather.com.weatherapp.BuildConfig;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static app.weather.com.weatherapp.utils.Constantaz.API_KEY;
import static app.weather.com.weatherapp.utils.Constantaz.CELCIUS;

/**
 * Singleton class designed for work with retrofit 2 library.
 * Use {@link #getApi()} method to start sending requests
 * declared in {@link Api} interface to your server
 * <p>
 * NOTE: You must call {@link #init(String)} method before start using this class
 */
public final class RestClient {

    private static volatile RestClient instance;
    private                 Api        api;

    private RestClient(String endPoint) {
        //Preventing a reflection api
        if (instance != null) {
            throw new IllegalStateException("Use get() method to get the single instance of this class.");
        }
        api = provideRetrofit(endPoint).create(Api.class);
    }

    public static RestClient get() {
        if (instance == null) {
            throw new IllegalStateException("Rest client had been not initialized");
        }
        return instance;
    }

    /**
     * Must be invoked before any other methods of this class
     */
    public static void init(String endPoint) {
        //Lazy initialization
        if (instance == null) {
            //Double locking
            synchronized(RestClient.class) {
                if (instance == null) instance = new RestClient(endPoint);
            }
        }
    }

    public Api getApi() {
        return api;
    }

    private OkHttpClient getClient() {
        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
        clientBuilder.addInterceptor(getAcceptInterceptor());
        clientBuilder.addInterceptor(getWeatherInterceptor());
        clientBuilder.addInterceptor(getLoggingInterceptor());
        clientBuilder.connectTimeout(30, TimeUnit.SECONDS)//
                .readTimeout(30, TimeUnit.SECONDS)//
                .writeTimeout(30, TimeUnit.SECONDS);
        return clientBuilder.build();
    }

    private Interceptor getAcceptInterceptor() {
        return chain -> {
            Request original = chain.request();
            Request.Builder requestBuilder = original.newBuilder()//
                    .header("Accept", "application/json");
            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    private Interceptor getWeatherInterceptor(){
        return chain -> {
            Request original = chain.request();
            HttpUrl originalHttpUrl = original.url();

            HttpUrl url = originalHttpUrl.newBuilder()
                    .addQueryParameter("units", CELCIUS)
                    .addQueryParameter("appid", API_KEY)
                    .build();

            // Request customization: add request headers
            Request.Builder requestBuilder = original.newBuilder()
                    .url(url);

            Request request = requestBuilder.build();
            return chain.proceed(request);
        };
    }

    private Interceptor getLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return logging;
    }

    private Retrofit provideRetrofit(String endPoint) {
        return new Retrofit.Builder().baseUrl(endPoint).client(getClient())//
                .addConverterFactory(GsonConverterFactory.create())//
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()) //
                .build();
    }

}