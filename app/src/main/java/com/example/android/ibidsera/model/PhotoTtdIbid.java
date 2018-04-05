package com.example.android.ibidsera.model;

/**
 * Created by Fahmi Hakim on 27/03/2018.
 * for SERA
 */

public class PhotoTtdIbid {

    private String idauctionitem;
    private String photottdibid;


    public PhotoTtdIbid(String idauctionitem, String photottdibid) {
        this.idauctionitem = idauctionitem;
        this.photottdibid = photottdibid;
    }

    public String getIdauctionitem() {
        return idauctionitem;
    }

    public void setIdauctionitem(String idauctionitem) {
        this.idauctionitem = idauctionitem;
    }

    public String getPhotottdibid() {
        return photottdibid;
    }

    public void setPhotottdibid(String photottdibid) {
        this.photottdibid = photottdibid;
    }
}
