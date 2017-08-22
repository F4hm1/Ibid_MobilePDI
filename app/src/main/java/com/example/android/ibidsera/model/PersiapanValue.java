package com.example.android.ibidsera.model;

import java.util.List;

/**
 * Created by Yosefricaro on 25/07/2017.
 */

public class PersiapanValue {
    private List<Attribute> merk;
    private List<Attribute> subgr;
    private List<Attribute> plat;
    private List<Attribute> sumber;
    private List<Attribute> taksasi;

    public List<Attribute> getMerk() {
        return merk;
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
