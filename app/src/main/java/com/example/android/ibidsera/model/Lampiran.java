package com.example.android.ibidsera.model;

/**
 * Created by Yosefricaro on 04/09/2017.
 */

public class Lampiran {
    private int idpemeriksaan_item;
    private String nama_lampiran;
    private String base64img;

    public String getNama_lampiran() {
        return nama_lampiran;
    }

    public void setNama_lampiran(String nama_lampiran) {
        this.nama_lampiran = nama_lampiran;
    }

    public String getBase64img() {
        return base64img;
    }

    public void setBase64img(String base64img) {
        this.base64img = base64img;
    }

    public int getIdpemeriksaan_item() {
        return idpemeriksaan_item;
    }

    public void setIdpemeriksaan_item(int idpemeriksaan_item) {
        this.idpemeriksaan_item = idpemeriksaan_item;
    }
}
