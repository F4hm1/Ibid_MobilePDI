package com.example.android.ibidsera.model.migrate;

import com.example.android.ibidsera.model.Auction;
import com.example.android.ibidsera.model.AuctionDetail;
import com.example.android.ibidsera.model.Expedition;
import com.example.android.ibidsera.model.Komponen;

import java.util.List;

/**
 * Created by Fahmi Hakim on 08/03/2018.
 * for SERA
 */

public class UnitKendaraan {

    private Auction auction;
    private AuctionDetail auctiondetail;
    private int no;

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        if (auction == null) throw new NullPointerException("auction");
        this.auction = auction;
    }

    public AuctionDetail getAuctiondetail() {
        return auctiondetail;
    }

    public void setAuctiondetail(AuctionDetail auctiondetail) {
        this.auctiondetail = auctiondetail;
    }

}
