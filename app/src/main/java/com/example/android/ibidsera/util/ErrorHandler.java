package com.example.android.ibidsera.util;

/**
 * Created by harfi on 08/09/2017.
 */

public class ErrorHandler {

    public static Boolean nullBoolean(Boolean item){
        if (item == null || !item) return false;
        else return true;
    }

    public static String nullString(String item){
        if (item == null) return "";
        else return item;
    }

}
