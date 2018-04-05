package com.example.android.ibidsera.model;

import com.example.android.ibidsera.model.homelist.UnitMasukKeluarHomelist;

import java.util.List;

/**
 * Created by Yosefricaro on 01/08/2017.
 */

public class StaticUnit {

    private static List<Unit> lu;
    private static Unit unit;

    private static List<UnitMasukKeluar> luMasukKeluar;
    private static UnitMasukKeluar unitMasukKeluar;


    public static List<UnitMasukKeluar> getLuMasukKeluar() {
        return luMasukKeluar;
    }



    public static void setLuMasukKeluar(List<UnitMasukKeluar> luMasukKeluar) {
        StaticUnit.luMasukKeluar = luMasukKeluar;
    }

    public static List<Unit> getLu() {
        return lu;
    }

    public static void setLu(List<Unit> lu) {
        StaticUnit.lu = lu;
    }

    public static Unit getUnit() {
        return unit;
    }

    public static void setUnit(Unit unit) {
        if (unit == null) throw new NullPointerException("unit");
        StaticUnit.unit = unit;
    }


    public static UnitMasukKeluar getUnitMasukKeluar() {
        return unitMasukKeluar;
    }

    public static void setUnitMasukKeluar(UnitMasukKeluar unitMasukKeluar) {
        if (unitMasukKeluar == null) throw new NullPointerException("unit masuk keluar");
        StaticUnit.unitMasukKeluar = unitMasukKeluar;
    }
}
