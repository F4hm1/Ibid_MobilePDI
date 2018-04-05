package com.example.android.ibidsera.model;

/**
 * Created by Fahmi Hakim on 27/03/2018.
 * for SERA
 */

public class PhotoChecklist {

    private String idauctionitem;
    private String photoceklis;

    public PhotoChecklist(String idauctionitem, String photoceklis) {
        this.idauctionitem = idauctionitem;
        this.photoceklis = photoceklis;
    }

    public String getIdauctionitem() {
        return idauctionitem;
    }

    public void setIdauctionitem(String idauctionitem) {
        this.idauctionitem = idauctionitem;
    }

    public String getPhotoceklis() {
        return photoceklis;
    }

    public void setPhotoceklis(String photoceklis) {
        this.photoceklis = photoceklis;
    }
}
