package com.example.android.ibidsera.util;

import com.example.android.ibidsera.BuildConfig;

/**
 * Created by Yosefricaro on 14/05/2017.
 */

public class URL {
    private static final String BASE_URL = BuildConfig.URI;

    public static String getMainURL(){
        return BASE_URL;
    }
}
