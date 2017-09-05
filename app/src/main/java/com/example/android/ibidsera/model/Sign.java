package com.example.android.ibidsera.model;

/**
 * Created by Yosefricaro on 04/09/2017.
 */

public class Sign {
    private String id_pemeriksaan;
    private String id_auctionitem;
    private String sign_ibid_msk;
    private String sign_cust_msk;
    private String sign_ibid_klr;
    private String sign_cust_klr;

    public String getId_pemeriksaan() {
        return id_pemeriksaan;
    }

    public void setId_pemeriksaan(String id_pemeriksaan) {
        this.id_pemeriksaan = id_pemeriksaan;
    }

    public String getId_auctionitem() {
        return id_auctionitem;
    }

    public void setId_auctionitem(String id_auctionitem) {
        this.id_auctionitem = id_auctionitem;
    }

    public String getSign_ibid_msk() {
        return sign_ibid_msk;
    }

    public void setSign_ibid_msk(String sign_ibid_msk) {
        this.sign_ibid_msk = sign_ibid_msk;
    }

    public String getSign_cust_msk() {
        return sign_cust_msk;
    }

    public void setSign_cust_msk(String sign_cust_msk) {
        this.sign_cust_msk = sign_cust_msk;
    }

    public String getSign_ibid_klr() {
        return sign_ibid_klr;
    }

    public void setSign_ibid_klr(String sign_ibid_klr) {
        this.sign_ibid_klr = sign_ibid_klr;
    }

    public String getSign_cust_klr() {
        return sign_cust_klr;
    }

    public void setSign_cust_klr(String sign_cust_klr) {
        this.sign_cust_klr = sign_cust_klr;
    }
}
