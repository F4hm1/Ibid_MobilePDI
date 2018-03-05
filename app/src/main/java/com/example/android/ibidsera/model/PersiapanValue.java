package com.example.android.ibidsera.model;

import java.util.List;

/**
 * Created by Yosefricaro on 25/07/2017.
 */

public class PersiapanValue {
    private List<Attribute> merk;
    private List<Attribute> seri;
    private List<Attribute> silinder;
    private List<Attribute> grade;
    private List<Attribute> subgr;
    private List<Attribute> plat;
    private List<Attribute> sumber;
    private List<Attribute> taksasi;

    public List<Attribute> getMerk() {
        return merk;
    }

    public List<Attribute> getSeri() {
        return seri;
    }

    public PersiapanValue setSeri(List<Attribute> seri) {
        this.seri = seri;
        return this;
    }

    public List<Attribute> getSilinder() {
        return silinder;
    }

    public PersiapanValue setSilinder(List<Attribute> silinder) {
        this.silinder = silinder;
        return this;
    }

    public List<Attribute> getGrade() {
        return grade;
    }

    public PersiapanValue setGrade(List<Attribute> grade) {
        this.grade = grade;
        return this;
    }

    public List<Attribute> getSubgr() {
        return subgr;
    }

    public List<Attribute> getPlat() {
        return plat;
    }

    public List<Attribute> getSumber() { return sumber; }

    public List<Attribute> getTaksasi() { return taksasi; }

}
