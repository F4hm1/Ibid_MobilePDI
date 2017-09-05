package com.example.android.ibidsera.model;

/**
 * Created by harfi on 05/09/2017.
 */

public class AuctionAuto {

    private int id_auctionitem;
    private int idauction_detail;
    private int idauction_item;
    private int id_attribute;
    private String value;
    private int sts_remove;
    private char sold;

    public int getId_auctionitem() {
        return id_auctionitem;
    }

    public AuctionAuto setId_auctionitem(int id_auctionitem) {
        this.id_auctionitem = id_auctionitem;
        return this;
    }

    public int getIdauction_detail() {
        return idauction_detail;
    }

    public AuctionAuto setIdauction_detail(int idauction_detail) {
        this.idauction_detail = idauction_detail;
        return this;
    }

    public int getIdauction_item() {
        return idauction_item;
    }

    public AuctionAuto setIdauction_item(int idauction_item) {
        this.idauction_item = idauction_item;
        return this;
    }

    public int getId_attribute() {
        return id_attribute;
    }

    public AuctionAuto setId_attribute(int id_attribute) {
        this.id_attribute = id_attribute;
        return this;
    }

    public String getValue() {
        return value;
    }

    public AuctionAuto setValue(String value) {
        this.value = value;
        return this;
    }

    public int getSts_remove() {
        return sts_remove;
    }

    public AuctionAuto setSts_remove(int sts_remove) {
        this.sts_remove = sts_remove;
        return this;
    }

    public char getSold() {
        return sold;
    }

    public AuctionAuto setSold(char sold) {
        this.sold = sold;
        return this;
    }
}
