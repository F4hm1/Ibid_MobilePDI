package com.example.android.ibidsera.model;

import java.util.List;

/**
 * Created by randidwinandra on 19/01/18.
 */

public class AuctionDetail {

    private String id_merk;
    private String nama_merk;
    private List<Attribute> tipe = null;
    private String transmisi;
    private String tahun;
    private String model;
    private Pntp pntp;
    private String km;
    private String penggerak;

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

    public Pntp getPntp() {
        return pntp;
    }

    public void setPntp(Pntp pntp) {
        this.pntp = pntp;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getPenggerak() {
        return penggerak;
    }

    public void setPenggerak(String penggerak) {
        this.penggerak = penggerak;
    }
}
