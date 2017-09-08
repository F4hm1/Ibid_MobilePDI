package com.example.android.ibidsera.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.android.ibidsera.view.activity.LoginActivity;

/**
 * Created by harfi on 03/08/2017.
 */

public class BaseActivity extends AppCompatActivity{

    public static final long DISCONNECT_TIMEOUT = 900000; // 5 min = 5 * 60 * 1000 ms

    protected void openNewActivity(Class activity) {
        startActivity(new Intent(this, activity));
        finish();
    }

    protected void hideKeyboard(){
        View view = getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void errorRetrofit(Throwable t){
        Toast.makeText(this, "Tidak ada internet", Toast.LENGTH_SHORT).show();
        Log.e("error", t.getMessage());
    }

    protected void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    private Handler disconnectHandler = new Handler(){
        public void handleMessage(Message msg) {
        }
    };

    private Runnable disconnectCallback = () -> {
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
        editor.clear().apply();
        openNewActivity(LoginActivity.class);
    };

    public void resetDisconnectTimer(){
        disconnectHandler.removeCallbacks(disconnectCallback);
        disconnectHandler.postDelayed(disconnectCallback, DISCONNECT_TIMEOUT);
    }

    public void stopDisconnectTimer(){
        disconnectHandler.removeCallbacks(disconnectCallback);
    }

    @Override
    public void onUserInteraction(){
        resetDisconnectTimer();
    }

    @Override
    public void onResume() {
        super.onResume();
        resetDisconnectTimer();
    }

    @Override
    public void onStop() {
        super.onStop();
        stopDisconnectTimer();
    }
}
