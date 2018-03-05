package com.example.android.ibidsera.model;

import java.util.List;

/**
 * Created by Yosefricaro on 03/08/2017.
 */

public class InsertUnit {
    private String idpemeriksaanitem;
    private int idauctionitem;
    private int bataskomponen;
    private String fuel;
    private String catbody;
    private String catatan;
    private String cases;
    private String poolkota;
    private int WEBID_LOGGED_IN;
    private String tglpemeriksaan;
    private String jampemeriksaan;
    private String menitpemeriksaan;
    private String nopolisi;
    private String MERK;
    private String SERI;
    private String SILINDER;
    private String GRADE;
    private String SUB_GRADE;
    private String TRANSMISI;
    private String KM;
    private String namapengemudi;
    private String alamatpengemudi;
    private String kotapengemudi;
    private String teleponpengemudi;
    private String expedition_amount;
    private int iduser;
    private List<String> cektampilkanbaik;
    private List<String> cektampilkanrusak;
    private List<String> cektampilkantidakada;
    private List<Integer> idkomponenpemeriksaan;
    private String gambarchecklist;
    private String ttdibid;
    private String ttdcustomer;
    private String reasonunchecklist;

    public String getIdpemeriksaanitem() {
        return idpemeriksaanitem;
    }

    public void setIdpemeriksaanitem(String idpemeriksaanitem) {
        this.idpemeriksaanitem = idpemeriksaanitem == null ? "" : idpemeriksaanitem;
    }

    public int getIdauctionitem() {
        return idauctionitem;
    }

    public void setIdauctionitem(int idauctionitem) {
        this.idauctionitem = idauctionitem;
    }

    public int getBataskomponen() {
        return bataskomponen;
    }

    public void setBataskomponen(int bataskomponen) {
        this.bataskomponen = bataskomponen;
    }

    public String getFuel() {
        return fuel;
    }

    public void setFuel(String fuel) {
        this.fuel = fuel == null ? "" : fuel;
    }

    public String getCatbody() {
        return catbody;
    }

    public void setCatbody(String catbody) {
        this.catbody = catbody == null ? "" : catbody;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan == null ? "" : catatan;
    }

    public String getTglpemeriksaan() {
        return tglpemeriksaan;
    }

    public void setTglpemeriksaan(String tglpemeriksaan) {
        this.tglpemeriksaan = tglpemeriksaan == null ? "" : tglpemeriksaan;
    }

    public String getJampemeriksaan() {
        return jampemeriksaan;
    }

    public void setJampemeriksaan(String jampemeriksaan) {
        this.jampemeriksaan = jampemeriksaan == null ? "" : jampemeriksaan;
    }

    public String getMenitpemeriksaan() {
        return menitpemeriksaan;
    }

    public void setMenitpemeriksaan(String menitpemeriksaan) {
        this.menitpemeriksaan = menitpemeriksaan == null ? "" : menitpemeriksaan;
    }

    public String getNamapengemudi() {
        return namapengemudi;
    }

    public void setNamapengemudi(String namapengemudi) {
        this.namapengemudi = namapengemudi == null ? "" : namapengemudi;
    }

    public String getAlamatpengemudi() {
        return alamatpengemudi;
    }

    public void setAlamatpengemudi(String alamatpengemudi) {
        this.alamatpengemudi = alamatpengemudi == null ? "" : alamatpengemudi;
    }

    public String getKotapengemudi() {
        return kotapengemudi;
    }

    public void setKotapengemudi(String kotapengemudi) {
        this.kotapengemudi = kotapengemudi == null ? "" : kotapengemudi;
    }

    public String getTeleponpengemudi() {
        return teleponpengemudi;
    }

    public void setTeleponpengemudi(String teleponpengemudi) {
        this.teleponpengemudi = teleponpengemudi == null ? "" : teleponpengemudi;
    }

    public int getIduser() {
        return iduser;
    }

    public void setIduser(int iduser) {
        this.iduser = iduser;
    }

    public List<Integer> getIdkomponenpemeriksaan() {
        return idkomponenpemeriksaan;
    }

    public void setIdkomponenpemeriksaan(List<Integer> idkomponenpemeriksaan) {
        this.idkomponenpemeriksaan = idkomponenpemeriksaan;
    }

    public int getWEBID_LOGGED_IN() {
        return WEBID_LOGGED_IN;
    }

    public void setWEBID_LOGGED_IN(int WEBID_LOGGED_IN) {
        this.WEBID_LOGGED_IN = WEBID_LOGGED_IN;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases == null ? "" : cases;
    }

    public String getPoolkota() {
        return poolkota;
    }

    public void setPoolkota(String poolkota) {
        this.poolkota = poolkota == null ? "" : poolkota;
    }

    public String getMERK() {
        return MERK;
    }

    public void setMERK(String MERK) {
        this.MERK = MERK == null ? "" : MERK;
    }

    public String getSERI() {
        return SERI;
    }

    public void setSERI(String SERI) {
        this.SERI = SERI == null ? "" : SERI;
    }

    public String getSILINDER() {
        return SILINDER;
    }

    public void setSILINDER(String SILINDER) {
        this.SILINDER = SILINDER == null ? "" : SILINDER;
    }

    public String getGRADE() {
        return GRADE;
    }

    public void setGRADE(String GRADE) {
        this.GRADE = GRADE == null ? "" : GRADE;
    }

    public String getSUB_GRADE() {
        return SUB_GRADE;
    }

    public void setSUB_GRADE(String SUB_GRADE) {
        this.SUB_GRADE = SUB_GRADE == null ? "" : SUB_GRADE;
    }

    public String getTRANSMISI() {
        return TRANSMISI;
    }

    public void setTRANSMISI(String TRANSMISI) {
        this.TRANSMISI = TRANSMISI == null ? "" : TRANSMISI;
    }

    public String getKM() {
        return KM;
    }

    public void setKM(String KM) {
        this.KM = KM == null ? "" : KM;
    }

    public String getNopolisi() {
        return nopolisi;
    }

    public void setNopolisi(String nopolisi) {
        this.nopolisi = nopolisi == null ? "" : nopolisi;
    }

    public List<String> getCektampilkanbaik() {
        return cektampilkanbaik;
    }

    public void setCektampilkanbaik(List<String> cektampilkanbaik) {
        this.cektampilkanbaik = cektampilkanbaik;
    }

    public List<String> getCektampilkanrusak() {
        return cektampilkanrusak;
    }

    public void setCektampilkanrusak(List<String> cektampilkanrusak) {
        this.cektampilkanrusak = cektampilkanrusak;
    }

    public List<String> getCektampilkantidakada() {
        return cektampilkantidakada;
    }

    public void setCektampilkantidakada(List<String> cektampilkantidakada) {
        this.cektampilkantidakada = cektampilkantidakada;
    }

    public String getExpedition_amount() {
        return expedition_amount;
    }

    public void setExpedition_amount(String expedition_amount) {
        this.expedition_amount = expedition_amount == null ? "" : expedition_amount;
    }

    public String getGambarchecklist() {
        return gambarchecklist;
    }

    public void setGambarchecklist(String gambarchecklist) {
        this.gambarchecklist = gambarchecklist == null ? "" : gambarchecklist;
    }

    public String getTtdibid() {
        return ttdibid;
    }

    public void setTtdibid(String ttdibid) {
        this.ttdibid = ttdibid == null ? "" : ttdibid;
    }

    public String getTtdcustomer() {
        return ttdcustomer;
    }

    public void setTtdcustomer(String ttdcustomer) {
        this.ttdcustomer = ttdcustomer == null ? "" : ttdcustomer;
    }

    public String getReasonunchecklist() {
        return reasonunchecklist;
    }

    public void setReasonunchecklist(String reasonunchecklist) {
        this.reasonunchecklist = reasonunchecklist == null ? "" : reasonunchecklist;
    }
}
