package com.example.android.ibidsera.model;

import com.example.android.ibidsera.util.ErrorHandler;

/**
 * Created by Yosefricaro on 31/07/2017.
 */

public class Komponen {
    private String nama;
    private String id_pemeriksaanitemdetail;
    private String id_pemeriksaanitem;
    private String id_komponenpemeriksaan;
    private String id_item;
    private String tampil_b;
    private String tampil_r;
    private String tampil_t;
    private String tampil_b_klr;
    private String tampil_r_klr;
    private String tampil_t_klr;

    public String getId_pemeriksaanitemdetail() {
        return id_pemeriksaanitemdetail;
    }

    public void setId_pemeriksaanitemdetail(String id_pemeriksaanitemdetail) {
        this.id_pemeriksaanitemdetail = id_pemeriksaanitemdetail;
    }

    public String getId_pemeriksaanitem() {
        return id_pemeriksaanitem;
    }

    public void setId_pemeriksaanitem(String id_pemeriksaanitem) {
        this.id_pemeriksaanitem = id_pemeriksaanitem;
    }

    public String getId_komponenpemeriksaan() {
        return id_komponenpemeriksaan;
    }

    public void setId_komponenpemeriksaan(String id_komponenpemeriksaan) {
        this.id_komponenpemeriksaan = id_komponenpemeriksaan;
    }

    public String getId_item() {
        return id_item;
    }

    public void setId_item(String id_item) {
        this.id_item = id_item;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTampil_b() {
        return ErrorHandler.nullString(tampil_b);
    }

    public void setTampil_b(String tampil_b) {
        this.tampil_b = tampil_b;
    }

    public String getTampil_r() {
        return ErrorHandler.nullString(tampil_r);
    }

    public void setTampil_r(String tampil_r) {
        this.tampil_r = tampil_r;
    }

    public String getTampil_t() {
        return ErrorHandler.nullString(tampil_t);
    }

    public void setTampil_t(String tampil_t) {
        this.tampil_t = tampil_t;
    }

    public String getTampil_b_klr() {
        return ErrorHandler.nullString(tampil_b_klr);
    }

    public void setTampil_b_klr(String tampil_b_klr) {
        this.tampil_b_klr = tampil_b_klr;
    }

    public String getTampil_r_klr() {
        return ErrorHandler.nullString(tampil_r_klr);
    }

    public void setTampil_r_klr(String tampil_r_klr) {
        this.tampil_r_klr = tampil_r_klr;
    }

    public String getTampil_t_klr() {
        return ErrorHandler.nullString(tampil_t_klr);
    }

    public void setTampil_t_klr(String tampil_t_klr) {
        this.tampil_t_klr = tampil_t_klr;
    }

    

}
