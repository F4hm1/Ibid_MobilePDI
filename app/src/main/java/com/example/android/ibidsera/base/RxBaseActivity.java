package com.example.android.ibidsera.base;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.android.ibidsera.view.activity.LoginActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public abstract class RxBaseActivity extends RxAppCompatActivity {
    private Unbinder bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*setContentView(getLayoutId());

        bind = ButterKnife.bind(this);

        initViews(savedInstanceState);

        initToolBar();*/
    }



    public abstract int getLayoutId();


    public abstract void initViews(Bundle savedInstanceState);


    public abstract void initToolBar();


    public void loadData() {
    }


    public void showProgressBar() {
    }


    public void hideProgressBar() {
    }


    public void initRecyclerView() {
    }


    public void initRefreshLayout() {
    }


    public void finishTask() {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //bind.unbind();
    }





}
