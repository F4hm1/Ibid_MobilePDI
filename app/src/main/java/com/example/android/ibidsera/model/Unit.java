package com.example.android.ibidsera.model;

import java.util.List;

/**
 * Created by Yosefricaro on 26/07/2017.
 */

public class Unit {

    private Auction auction;
    private AuctionAuto auction_auto;
    private String id_merk;
    private String nama_merk;
    private List<Attribute> tipe;
    private String model;
    private String transmisi;
    private String tahun;
    private String km;
    private String penggerak;
    private Pntp pntp;
    private List<Komponen> komponen;
    private int count_checklist;

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        this.auction = auction;
    }

    public AuctionAuto getAuction_auto() {
        return auction_auto;
    }

    public Unit setAuction_auto(AuctionAuto auction_auto) {
        this.auction_auto = auction_auto;
        return this;
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

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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

    public Pntp getPntp() {
        return pntp;
    }

    public void setPntp(Pntp pntp) {
        this.pntp = pntp;
    }

    public String getPenggerak() {
        return penggerak;
    }

    public void setPenggerak(String penggerak) {
        this.penggerak = penggerak;
    }

    public List<Komponen> getKomponen() {
        return komponen;
    }

    public void setKomponen(List<Komponen> komponen) {
        this.komponen = komponen;
    }

    public int getCount_checklist() {
        return count_checklist;
    }

    public void setCount_checklist(int count_checklist) {
        this.count_checklist = count_checklist;
    }

    public String getKm() {
        return km;
    }

    public void setKm(String km) {
        this.km = km;
    }

    public String getId_merk() {
        return id_merk;
    }

    public void setId_merk(String id_merk) {
        this.id_merk = id_merk;
    }
}
