package com.example.android.ibidsera.model;

/**
 * Created by randidwinandra on 15/01/18.
 */

public class NoPolUnit {
    private int AuctionItemId;
    private String nopolisi;
    private String IsPDI;

    public int getAuctionItemId() {
        return AuctionItemId;
    }

    public void setAuctionItemId(int auctionItemId) {
        AuctionItemId = auctionItemId;
    }

    public String getNopolisi() {
        return nopolisi;
    }

    public void setNopolisi(String nopolisi) {
        this.nopolisi = nopolisi;
    }

    public String getIsPDI() {
        return IsPDI;
    }

    public void setIsPDI(String isPDI) {
        IsPDI = isPDI;
    }

    @Override
    public String toString() {
        return nopolisi;
    }
}
