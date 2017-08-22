package com.example.android.ibidsera.model;

import java.util.List;

/**
 * Created by Yosefricaro on 01/08/2017.
 */

public class StaticUnit {

    private static List<Unit> lu;

    public static List<Unit> getLu() {
        return lu;
    }

    public static void setLu(List<Unit> lu) {
        StaticUnit.lu = lu;
    }
}
