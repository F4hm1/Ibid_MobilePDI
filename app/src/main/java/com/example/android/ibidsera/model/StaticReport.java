package com.example.android.ibidsera.model;

import java.util.List;

/**
 * Created by Yosefricaro on 05/09/2017.
 */

public class StaticReport {
    private static List<ReportModel> lr;

    public static List<ReportModel> getLr() {
        return lr;
    }

    public static void setLr(List<ReportModel> lr) {
        StaticReport.lr = lr;
    }
}
