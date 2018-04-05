package com.example.android.ibidsera.model.homelist;

import com.example.android.ibidsera.model.Attribute;
import com.example.android.ibidsera.model.Pntp;

import java.util.List;

/**
 * Created by Fahmi Hakim on 09/03/2018.
 * for SERA
 */

public class AuctionDetailHomelist {

    private String id_merk;
    private String nama_merk;
    private List<Attribute> tipe = null;
    private String transmisi;
    private String tahun;
    private String model;


    public String getId_merk() {
        return id_merk;
    }

    public void setId_merk(String id_merk) {
        this.id_merk = id_merk;
    }

    public String getNama_merk() {
        return nama_merk;
    }

    public void setNama_merk(String nama_merk) {
        this.nama_merk = nama_merk;
    }

    public List<Attribute> getTipe() {
        return tipe;
    }

    public void setTipe(List<Attribute> tipe) {
        this.tipe = tipe;
    }

    public String getTransmisi() {
        return transmisi;
    }

    public void setTransmisi(String transmisi) {
        this.transmisi = transmisi;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
