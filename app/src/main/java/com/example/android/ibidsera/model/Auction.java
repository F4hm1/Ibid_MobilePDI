package com.example.android.ibidsera.model;

/**
 * Created by Yosefricaro on 27/07/2017.
 */

public class Auction {

    private int idauction_item;
    private int idauction_detail;
    private int id_attribute;
    private String value;
    private int sts_remove;
    private char sold;
    private String id_pemeriksaanitem;
    private int id_auctionitem;
    private String no_polisi;
    private String tgl_serah_msk;
    private String waktu_msk;
    private String nama_pengemudi_msk;
    private String alamat_pengemudi_msk;
    private String kota_msk;
    private String telepon_msk;
    private String tgl_serah_klr;
    private String waktu_klr;
    private String nama_pengemudi_klr;
    private String alamat_pengemudi_klr;
    private String kota_klr;
    private String telepon_klr;
    private String fuel;
    private String cat_body;
    private String catatan;
    private String cases;
    private String poolkota;
    private String catatan_klr;
    private int ekspedisis;

    private String catatan_image;
    private String ttd_ibid_msk;
    private String ttd_customer_msk;
    private String ttd_ibid_klr;
    private String ttd_customer_klr;
    private String alasan_tdk_check;

    public String getCatatan_image() {
        return catatan_image;
    }

    public void setCatatan_image(String catatan_image) {
        this.catatan_image = catatan_image;
    }

    public String getTtd_ibid_msk() {
        return ttd_ibid_msk;
    }

    public void setTtd_ibid_msk(String ttd_ibid_msk) {
        this.ttd_ibid_msk = ttd_ibid_msk;
    }

    public String getTtd_customer_msk() {
        return ttd_customer_msk;
    }

    public void setTtd_customer_msk(String ttd_customer_msk) {
        this.ttd_customer_msk = ttd_customer_msk;
    }

    public String getTtd_ibid_klr() {
        return ttd_ibid_klr;
    }

    public void setTtd_ibid_klr(String ttd_ibid_klr) {
        this.ttd_ibid_klr = ttd_ibid_klr;
    }

    public String getTtd_customer_klr() {
        return ttd_customer_klr;
    }

    public void setTtd_customer_klr(String ttd_customer_klr) {
        this.ttd_customer_klr = ttd_customer_klr;
    }

    public String getAlasan_tdk_check() {
        return alasan_tdk_check;
    }

    public void setAlasan_tdk_check(String alasan_tdk_check) {
        this.alasan_tdk_check = alasan_tdk_check;
    }

    public int getIdauction_detail() {
        return idauction_detail;
    }

    public Auction setIdauction_detail(int idauction_detail) {
        this.idauction_detail = idauction_detail;
        return this;
    }

    public int getId_attribute() {
        return id_attribute;
    }

    public Auction setId_attribute(int id_attribute) {
        this.id_attribute = id_attribute;
        return this;
    }

    public String getValue() {
        return value;
    }

    public Auction setValue(String value) {
        this.value = value;
        return this;
    }

    public int getSts_remove() {
        return sts_remove;
    }

    public Auction setSts_remove(int sts_remove) {
        this.sts_remove = sts_remove;
        return this;
    }

    public char getSold() {
        return sold;
    }

    public Auction setSold(char sold) {
        this.sold = sold;
        return this;
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

    public String getNama_pengemudi_msk() {
        return nama_pengemudi_msk;
    }

    public void setNama_pengemudi_msk(String nama_pengemudi_msk) {
        this.nama_pengemudi_msk = nama_pengemudi_msk;
    }

    public String getTgl_serah_klr() {
        return tgl_serah_klr;
    }

    public void setTgl_serah_klr(String tgl_serah_klr) {
        this.tgl_serah_klr = tgl_serah_klr;
    }

    public String getNama_pengemudi_klr() {
        return nama_pengemudi_klr;
    }

    public void setNama_pengemudi_klr(String nama_pengemudi_klr) {
        this.nama_pengemudi_klr = nama_pengemudi_klr;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel;
    }

    public String getCat_body() {
        return cat_body;
    }

    public void setCat_body(String cat_body) {
        this.cat_body = cat_body;
    }

    public String getWaktu_msk() {
        return waktu_msk;
    }

    public void setWaktu_msk(String waktu_msk) {
        this.waktu_msk = waktu_msk;
    }

    public String getAlamat_pengemudi_msk() {
        return alamat_pengemudi_msk;
    }

    public void setAlamat_pengemudi_msk(String alamat_pengemudi_msk) {
        this.alamat_pengemudi_msk = alamat_pengemudi_msk;
    }

    public String getKota_msk() {
        return kota_msk;
    }

    public void setKota_msk(String kota_msk) {
        this.kota_msk = kota_msk;
    }

    public String getTelepon_msk() {
        return telepon_msk;
    }

    public void setTelepon_msk(String telepon_msk) {
        this.telepon_msk = telepon_msk;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getPoolkota() {
        return poolkota;
    }

    public void setPoolkota(String poolkota) {
        this.poolkota = poolkota;
    }

    public String getWaktu_klr() {
        return waktu_klr;
    }

    public void setWaktu_klr(String waktu_klr) {
        this.waktu_klr = waktu_klr;
    }

    public String getAlamat_pengemudi_klr() {
        return alamat_pengemudi_klr;
    }

    public void setAlamat_pengemudi_klr(String alamat_pengemudi_klr) {
        this.alamat_pengemudi_klr = alamat_pengemudi_klr;
    }

    public String getKota_klr() {
        return kota_klr;
    }

    public void setKota_klr(String kota_klr) {
        this.kota_klr = kota_klr;
    }

    public String getTelepon_klr() {
        return telepon_klr;
    }

    public void setTelepon_klr(String telepon_klr) {
        this.telepon_klr = telepon_klr;
    }

    public String getCatatan_klr() {
        return catatan_klr;
    }

    public void setCatatan_klr(String catatan_klr) {
        this.catatan_klr = catatan_klr;
    }

    public String getId_pemeriksaanitem() {
        return id_pemeriksaanitem;
    }

    public void setId_pemeriksaanitem(String id_pemeriksaanitem) {
        this.id_pemeriksaanitem = id_pemeriksaanitem;
    }

    public int getId_auctionitem() {
        return id_auctionitem;
    }

    public void setId_auctionitem(int id_auctionitem) {
        this.id_auctionitem = id_auctionitem;
    }

    public int getIdauction_item() {
        return idauction_item;
    }

    public void setIdauction_item(int idauction_item) {
        this.idauction_item = idauction_item;
    }

    public int getEkspedisis() {
        return ekspedisis;
    }

    public void setEkspedisis(int ekspedisis) {
        this.ekspedisis = ekspedisis;
    }
}
