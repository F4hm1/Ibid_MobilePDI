package com.example.android.ibidsera.model;

/**
 * Created by Fahmi Hakim on 27/03/2018.
 * for SERA
 */

public class PhotoTtdCustomer {

    private String idauctionitem;
    private String photottdcust;


    public PhotoTtdCustomer(String idauctionitem, String photottdcust) {
        this.idauctionitem = idauctionitem;
        this.photottdcust = photottdcust;
    }


    public String getIdauctionitem() {
        return idauctionitem;
    }

    public void setIdauctionitem(String idauctionitem) {
        this.idauctionitem = idauctionitem;
    }

    public String getPhotottdcust() {
        return photottdcust;
    }

    public void setPhotottdcust(String photottdcust) {
        this.photottdcust = photottdcust;
    }
}
