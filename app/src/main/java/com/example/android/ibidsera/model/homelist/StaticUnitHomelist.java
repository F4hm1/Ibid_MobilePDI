package com.example.android.ibidsera.model.homelist;

import com.example.android.ibidsera.model.StaticUnit;
import com.example.android.ibidsera.model.Unit;
import com.example.android.ibidsera.model.UnitMasukKeluar;

import java.util.List;

/**
 * Created by Fahmi Hakim on 09/03/2018.
 * for SERA
 */

public class StaticUnitHomelist {

    private static List<Unit> lu;
    private static Unit unit;

    private static List<UnitMasukKeluarHomelist> luMasukKeluar;
    private static UnitMasukKeluar unitMasukKeluar;


    public static List<UnitMasukKeluarHomelist> getLuMasukKeluar() {
        return luMasukKeluar;
    }



    public static void setLuMasukKeluar(List<UnitMasukKeluarHomelist> luMasukKeluar) {
        StaticUnitHomelist.luMasukKeluar = luMasukKeluar;
    }

    public static List<Unit> getLu() {
        return lu;
    }

    public static void setLu(List<Unit> lu) {
        StaticUnitHomelist.lu = lu;
    }

    public static Unit getUnit() {
        return unit;
    }

    public static void setUnit(Unit unit) {
        if (unit == null) throw new NullPointerException("unit");
        StaticUnitHomelist.unit = unit;
    }

    public static UnitMasukKeluar getUnitMasukKeluar() {
        return unitMasukKeluar;
    }

    public static void setUnitMasukKeluar(UnitMasukKeluar unitMasukKeluar) {
        if (unitMasukKeluar == null) throw new NullPointerException("unit masuk keluar");
        StaticUnitHomelist.unitMasukKeluar = unitMasukKeluar;
    }

}
