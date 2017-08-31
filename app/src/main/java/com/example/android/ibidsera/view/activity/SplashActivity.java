package com.example.android.ibidsera.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseActivity;

public class SplashActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        if(prefs.getString("email", "").equals("")){
            waitSplash(LoginActivity.class);
        }else {
            waitSplash(MainActivity.class);
        }
    }

    public void waitSplash(Class activity) {
        new Handler().postDelayed(() -> {
            openNewActivity(activity);
        }, 2000);
    }
}
