package com.example.android.ibidsera.model;

import com.example.android.ibidsera.util.ErrorHandler;

import java.util.List;

/**
 * Created by randidwinandra on 19/01/18.
 */

public class UnitMasukKeluar {

    private Auction auction;
    private AuctionDetail auctiondetail;
    private String id_pemeriksaanitem;
    private List<Komponen> komponen;
    private int count_checklist;
    private Expedition expedition;

    public Auction getAuction() {
        return auction;
    }

    public void setAuction(Auction auction) {
        if (auction == null) throw new NullPointerException("auction");
        this.auction = auction;
    }


    public String getId_pemeriksaanitem() {
        return id_pemeriksaanitem;
    }

    public void setId_pemeriksaanitem(String id_pemeriksaanitem) {
        this.id_pemeriksaanitem = id_pemeriksaanitem;
    }

    public List<Komponen> getKomponen() {
        return komponen;
    }

    public void setKomponen(List<Komponen> komponen) {
        if (komponen == null) throw new NullPointerException("komponen");
        this.komponen = komponen;
    }

    public int getCount_checklist() {
        return count_checklist;
    }

    public void setCount_checklist(int count_checklist) {
        this.count_checklist = count_checklist;
    }

    public AuctionDetail getAuctiondetail() {
        return auctiondetail;
    }

    public void setAuctiondetail(AuctionDetail auctiondetail) {
        this.auctiondetail = auctiondetail;
    }

    public Expedition getExpedition() {
        return expedition;
    }

    public void setExpedition(Expedition expedition) {
        if (expedition == null) throw new NullPointerException("expedition");
        this.expedition = expedition;
    }
}
