package com.networkIPS.task.services;

import android.util.Log;

import com.networkIPS.task.helpers.Config;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static okhttp3.logging.HttpLoggingInterceptor.Level.BODY;

public class WebService {

    public static int Retry_Time_Offset = 10000;
    private static WebService instance;
    private static Retrofit retrofit;
    private Api api;

    public WebService() {
        if (retrofit == null) {
            synchronized (Retrofit.class) {
                if (retrofit == null) {
                    Gson gson = new GsonBuilder()
                            .setLenient()
                            .create();
                    retrofit = new Retrofit.Builder().baseUrl(Config.MainAPI)
                            .addConverterFactory(GsonConverterFactory.create(gson))
                            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                            .client(provideOkHttpClient())
                            .build();
                }
            }
        }
        api = retrofit.create(Api.class);
    }


    public static Api getApi() {
        if (instance == null) {
            instance = new WebService();
        }
        return instance.api;
    }


    private static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> Log.e("http", message));
        httpLoggingInterceptor.setLevel(BODY);
        return httpLoggingInterceptor;
    }
    private static OkHttpClient provideOkHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(provideHttpLoggingInterceptor())
                .connectTimeout(Retry_Time_Offset, TimeUnit.MILLISECONDS)
                .writeTimeout(Retry_Time_Offset, TimeUnit.MILLISECONDS)
                .readTimeout(Retry_Time_Offset, TimeUnit.MILLISECONDS)
                .build();
    }
}