package com.example.android.ibidsera.util;

import com.example.android.ibidsera.BuildConfig;
import com.example.android.ibidsera.model.api.AuctionService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Yosefricaro on 03/08/2017.
 */

public class RetrofitUtil {
    private static Retrofit getRetrofit(){



        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);

        return new Retrofit.Builder()
                .baseUrl(ApiConstants.ALPHA_STOK_URL) //BuildConfig.URI
                .addConverterFactory(GsonConverterFactory.create(RetrofitUtil.getGson()))
                .client(httpClient.build())
                .build();
    }

    public static AuctionService getAuctionService(){
        return RetrofitUtil.getRetrofit().create(AuctionService.class);
    }

    public static Gson getGson(){
        return new GsonBuilder()
                .setLenient()
                .create();
    }

    public static String toJson(Object object) {
        return RetrofitUtil.getGson().toJson(object);
    }
}
