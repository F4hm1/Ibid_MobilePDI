package com.example.android.ibidsera.model;

/**
 * Created by harfi on 04/09/2017.
 */

public class SignValue {

    private String sign_ibid_msk;
    private String sign_cust_msk;
    private String sign_ibid_klr;
    private String sign_cust_klr;

    public String getSign_ibid_msk() {
        return sign_ibid_msk;
    }

    public SignValue setSign_ibid_msk(String sign_ibid_msk) {
        this.sign_ibid_msk = sign_ibid_msk;
        return this;
    }

    public String getSign_cust_msk() {
        return sign_cust_msk;
    }

    public SignValue setSign_cust_msk(String sign_cust_msk) {
        this.sign_cust_msk = sign_cust_msk;
        return this;
    }

    public String getSign_ibid_klr() {
        return sign_ibid_klr;
    }

    public SignValue setSign_ibid_klr(String sign_ibid_klr) {
        this.sign_ibid_klr = sign_ibid_klr;
        return this;
    }

    public String getSign_cust_klr() {
        return sign_cust_klr;
    }

    public SignValue setSign_cust_klr(String sign_cust_klr) {
        this.sign_cust_klr = sign_cust_klr;
        return this;
    }

}
