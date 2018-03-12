package com.example.android.ibidsera.model.homelist;

/**
 * Created by Fahmi Hakim on 09/03/2018.
 * for SERA
 */

public class AuctionHomelist {

    private int id_pemeriksaan;
    private int id_item;
    private int idauction_item;
    private String no_polisi;
    private String tgl_serah_msk;
    private String waktu_msk;
    private String nama_pengemudi_msk;

    public int getId_pemeriksaan() {
        return id_pemeriksaan;
    }

    public void setId_pemeriksaan(int id_pemeriksaan) {
        this.id_pemeriksaan = id_pemeriksaan;
    }

    public int getId_item() {
        return id_item;
    }

    public void setId_item(int id_item) {
        this.id_item = id_item;
    }

    public int getIdauction_item() {
        return idauction_item;
    }

    public void setIdauction_item(int idauction_item) {
        this.idauction_item = idauction_item;
    }

    public String getNo_polisi() {
        return no_polisi;
    }

    public void setNo_polisi(String no_polisi) {
        this.no_polisi = no_polisi;
    }

    public String getTgl_serah_msk() {
        return tgl_serah_msk;
    }

    public void setTgl_serah_msk(String tgl_serah_msk) {
        this.tgl_serah_msk = tgl_serah_msk;
    }

    public String getWaktu_msk() {
        return waktu_msk;
    }

    public void setWaktu_msk(String waktu_msk) {
        this.waktu_msk = waktu_msk;
    }

    public String getNama_pengemudi_msk() {
        return nama_pengemudi_msk;
    }

    public void setNama_pengemudi_msk(String nama_pengemudi_msk) {
        this.nama_pengemudi_msk = nama_pengemudi_msk;
    }
}
