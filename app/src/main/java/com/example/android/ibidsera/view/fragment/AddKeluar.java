package com.example.android.ibidsera.view.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;

/**
 * Created by Yosefricaro on 24/07/2017.
 */

public class AddKeluar extends BaseFragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.content_addk, container, false);
    }
}
