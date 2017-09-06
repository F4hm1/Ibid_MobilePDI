package com.example.android.ibidsera.model;

/**
 * Created by Yosefricaro on 06/09/2017.
 */

public class GetStatus {
    private int status;
    private String message;
    private int id_pemeriksaan_item;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getId_pemeriksaan_item() {
        return id_pemeriksaan_item;
    }

    public void setId_pemeriksaan_item(int id_pemeriksaan_item) {
        this.id_pemeriksaan_item = id_pemeriksaan_item;
    }
}
