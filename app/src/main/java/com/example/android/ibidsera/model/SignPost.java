package com.example.android.ibidsera.model;

/**
 * Created by harfi on 04/09/2017.
 */

public class SignPost {

    private String id_pemeriksaanitem;
    private int id_auctionitem;

    public String getId_pemeriksaanitem() {
        return id_pemeriksaanitem;
    }

    public SignPost setId_pemeriksaanitem(String id_pemeriksaanitem) {
        this.id_pemeriksaanitem = id_pemeriksaanitem;
        return this;
    }

    public int getId_auctionitem() {
        return id_auctionitem;
    }

    public SignPost setId_auctionitem(int id_auctionitem) {
        this.id_auctionitem = id_auctionitem;
        return this;
    }
}
