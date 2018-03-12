package com.example.android.ibidsera;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.androidnetworking.AndroidNetworking;
import com.facebook.stetho.Stetho;

/**
 * Created by Fahmi Hakim on 06/03/2018.
 * for SERA
 */

public class AppController extends Application {
    public static AppController mInstance;


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        init();
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
