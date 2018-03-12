package com.example.android.ibidsera.model.homelist;

import com.example.android.ibidsera.model.Auction;
import com.example.android.ibidsera.model.AuctionDetail;
import com.example.android.ibidsera.model.Expedition;
import com.example.android.ibidsera.model.Komponen;

import java.util.List;

/**
 * Created by Fahmi Hakim on 09/03/2018.
 * for SERA
 */

public class UnitMasukKeluarHomelist {

    private AuctionHomelist auction;
    private AuctionDetailHomelist auctiondetail;
    //private String id_pemeriksaanitem;

    public AuctionHomelist getAuction() {
        return auction;
    }

    public void setAuction(AuctionHomelist auction) {
        if (auction == null) throw new NullPointerException("auction");
        this.auction = auction;
    }


    /*public String getId_pemeriksaanitem() {
        return id_pemeriksaanitem;
    }

    public void setId_pemeriksaanitem(String id_pemeriksaanitem) {
        this.id_pemeriksaanitem = id_pemeriksaanitem;
    }*/

    public AuctionDetailHomelist getAuctiondetail() {
        return auctiondetail;
    }

    public void setAuctiondetail(AuctionDetailHomelist auctiondetail) {
        this.auctiondetail = auctiondetail;
    }

}
