package com.example.android.ibidsera.model;

import com.example.android.ibidsera.util.ErrorHandler;

/**
 * Created by Fahmi Hakim on 14/03/2018.
 * for SERA
 */

public class ExpeditionVarian {

    private String VarianId;
    private String Harga;
    private String Varian;

    public String getVarianId() {
        return ErrorHandler.nullString(VarianId);
    }

    public void setVarianId(String varianId) {
        if (varianId == null) throw new NullPointerException("varianId");
        VarianId = varianId;
    }

    public String getHarga() {
        return ErrorHandler.nullString(Harga);
    }

    public void setHarga(String harga) {
        if (harga == null) throw new NullPointerException("harga");
        Harga = harga;
    }

    public String getVarian() {
        return ErrorHandler.nullString(Varian);
    }

    public void setVarian(String varian) {
        if (varian == null) throw new NullPointerException("varian");
        Varian = varian;
    }
}
