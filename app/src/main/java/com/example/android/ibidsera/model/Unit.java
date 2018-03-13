package com.example.android.ibidsera.model;

import com.example.android.ibidsera.util.ErrorHandler;

import java.util.List;

import io.realm.RealmObject;

/**
 * Created by Yosefricaro on 26/07/2017.
 */

public class Unit{

    private Auction auction;
    private String id_merk;
    private String nama_merk;
    private List<Attribute> tipe;
    private String model;
    private String transmisi;
    private String tahun;
    private String km;
    private String penggerak;
    private String id_pemeriksaanitem;
    private Pntp pntp;
    private List<Komponen> komponen;
    private int count_checklist;
    private Expedition expedition;

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        if (auction == null) throw new NullPointerException("auction");
        this.auction = auction;
    }

    public List<Attribute> getTipe() {
        return tipe;
    }

    public void setTipe(List<Attribute> tipe) {
        if (tipe == null) throw new NullPointerException("tipe");
        this.tipe = tipe;
    }

    public String getId_pemeriksaanitem() {
        return id_pemeriksaanitem;
    }

    public void setId_pemeriksaanitem(String id_pemeriksaanitem) {
        this.id_pemeriksaanitem = id_pemeriksaanitem;
    }

    public Pntp getPntp() {
        return pntp;
    }

    public void setPntp(Pntp pntp) {
        if (pntp == null) throw new NullPointerException("pntp");
        this.pntp = pntp;
    }

    public List<Komponen> getKomponen() {
        return komponen;
    }

    public void setKomponen(List<Komponen> komponen) {
        if (komponen == null) throw new NullPointerException("komponen");
        this.komponen = komponen;
    }

    public int getCount_checklist() {
        return count_checklist;
    }

    public void setCount_checklist(int count_checklist) {
        this.count_checklist = count_checklist;
    }

    public String getId_merk() {
        return ErrorHandler.nullString(id_merk);
    }

    public void setId_merk(String id_merk) {
        if (id_merk == null) throw new NullPointerException("id_merk");
        this.id_merk = id_merk;
    }

    public String getNama_merk() {
        return ErrorHandler.nullString(nama_merk);
    }

    public void setNama_merk(String nama_merk) {
        if (nama_merk == null) throw new NullPointerException("nama_merk");
        this.nama_merk = nama_merk;
    }

    public String getModel() {
        return ErrorHandler.nullString(model);
    }

    public void setModel(String model) {
        if (model == null) throw new NullPointerException("model");
        this.model = model;
    }

    public String getTransmisi() {
        return ErrorHandler.nullString(transmisi);
    }

    public void setTransmisi(String transmisi) {
        if (transmisi == null) throw new NullPointerException("transmisi");
        this.transmisi = transmisi;
    }

    public String getTahun() {
        return ErrorHandler.nullString(tahun);
    }

    public void setTahun(String tahun) {
        if (tahun == null) throw new NullPointerException("tahun");
        this.tahun = tahun;
    }

    public String getKm() {
        return ErrorHandler.nullString(km);
    }

    public void setKm(String km) {
        if (km == null) throw new NullPointerException("km");
        this.km = km;
    }

    public String getPenggerak() {
        return ErrorHandler.nullString(penggerak);
    }

    public void setPenggerak(String penggerak) {
        if (penggerak == null) throw new NullPointerException("penggerak");
        this.penggerak = penggerak;
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        if (expedition == null) throw new NullPointerException("expedition");
        this.expedition = expedition;
    }
}
