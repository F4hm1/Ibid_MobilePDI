package com.example.android.ibidsera.base;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by harfi on 03/08/2017.
 */

public class BaseActivity extends AppCompatActivity{
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
}
