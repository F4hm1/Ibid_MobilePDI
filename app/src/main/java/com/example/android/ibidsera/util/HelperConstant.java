package com.example.android.ibidsera.util;

import android.graphics.Bitmap;
import android.os.Environment;

import java.util.ArrayList;

/**
 * Created by Randi Dwi Nandra on 23/11/2017.
 * randi.dwinandra@gmail.com
 */

public class HelperConstant {
    public static final int LAMPIRAN_NIAGA = 1;
    public static final int LAMPIRAN_SEDAN = 2;
    public static final int LAMPIRAN_PICKUP = 3;
    public static final String LAMPIRAN_KEY = "lampiran_key";

    public static Bitmap mTempBitmapSedan = null;
    public static Bitmap mTempBitmapNiaga = null;
    public static Bitmap mTempBitmapPickup = null;

    public static ArrayList<PathColored> sPathSavedSedan = null;
    public static ArrayList<PathColored> sPathSavedNiaga = null;
    public static ArrayList<PathColored> sPathSavedPickup = null;

}
