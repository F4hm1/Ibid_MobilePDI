package com.example.android.ibidsera;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.androidnetworking.AndroidNetworking;
import com.crashlytics.android.Crashlytics;
import com.example.android.ibidsera.di.AppComponent;
import com.example.android.ibidsera.di.AppModule;
import com.example.android.ibidsera.di.DaggerAppComponent;
import com.facebook.stetho.Stetho;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Fahmi Hakim on 06/03/2018.
 * for SERA
 */

public class AppController extends Application {
    public static AppController mInstance;
    public AppComponent component;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        mInstance = this;
        init();
        initDagger();


    }

    private void initDagger() {
        component = DaggerAppComponent
                .builder()
                .appModule(new AppModule(mInstance))
                .build();
    }

    private void init() {
        AndroidNetworking.initialize(getApplicationContext());
        Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                        .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                        .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                        .build());


    }

    public static AppController getInstance() {
        return mInstance;
    }
}
