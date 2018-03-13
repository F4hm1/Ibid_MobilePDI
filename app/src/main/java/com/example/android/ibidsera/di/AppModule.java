package com.example.android.ibidsera.di;

import android.app.Application;

import com.example.android.ibidsera.service.eventbus.EventListener;
import com.google.common.eventbus.EventBus;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Fahmi Hakim on 13/03/2018.
 * for SERA
 */

@Module
public class AppModule {


    private Application app;

    public AppModule(Application app) {
        this.app = app;
    }

    @Provides
    @Singleton
    public String provideString() {
        return "App has been Injected";
    }


    @Provides
    @Singleton
    public EventBus provideEventbus() {
        return new EventBus();
    }


}
