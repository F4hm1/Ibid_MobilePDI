package com.example.android.ibidsera.model;

/**
 * Created by harfi on 04/09/2017.
 */

public class SignPost {

    private int id_pemeriksaanitem;
    private int id_auctionitem;

    public int getId_pemeriksaanitem() {
        return id_pemeriksaanitem;
    }

    public SignPost setId_pemeriksaanitem(int id_pemeriksaanitem) {
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
