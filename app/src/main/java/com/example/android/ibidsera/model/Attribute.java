package com.example.android.ibidsera.model;

import com.example.android.ibidsera.util.ErrorHandler;

/**
 * Created by harfi on 07/08/2017.
 */

public class Attribute {
    private int id_attrdetail;
    private String attributedetail;

    public int getId_attrdetail() {
        return id_attrdetail;
    }

    public void setId_attrdetail(int id_attrdetail) {
        this.id_attrdetail = id_attrdetail;
    }

    public String getAttributedetail() {
        return ErrorHandler.nullString(attributedetail);
    }

    public void setAttributedetail(String attributedetail) {
        if (attributedetail == null) throw new NullPointerException("attributedetail");
        this.attributedetail = attributedetail;
    }
}
