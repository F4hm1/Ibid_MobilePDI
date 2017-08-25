package com.example.android.ibidsera.model;

/**
 * Created by harfi on 03/08/2017.
 */

public class PersiapanPost {

    private final String cabangtaksasi;
    private final int idwarnadoc;
    private final String WARNA_DOC;
    private final int idwarnafisik;
    private final String WARNA;
    private final int biayadmin;
    private final int biayaparkir;
    private final int kodepenitip;
    private final String namapenitip;
    private final String nmridpenitip;
    private final int tipeidpenitip;
    private final String nonpwp;
    private final String groupbiodata;
    private final boolean statuspeserta;
    private final String jenisusaha;
    private final boolean sebagaiperusahaan;
    private final String teleponpenitip;
    private final String ponselpenitip;
    private final String alamatpenitip;
    private final String kotapenitip;
    private final String kodepospenitip;
    private final String idbiodata;
    private final String jadwalelang;
    private final String tglelang;
    private final String cabanglelang;
    private final String lokasilelang;
    private final String alamatbrglelang;
    private final boolean ikutseselelang;
    private final String lokasibrglelang;
    private final String kotabrglelang;
    private final String namapicdisplay;
    private final String lokasidisplay;
    private final String kotadisplay;
    private final String alamatdisplay;
    private final String telppicdisplay;
    private final String namapic;
    private final String jabatanpic;
    private final String alamatpic;
    private final String kotapic;
    private final String telppic;
    private final String ponselpic;
    private final String faxpic;
    private final String mailpi;
    private final String catatan;
    private final String STNK_SD;
    private final String TGL_KEUR;
    private final String lotnumb;
    private final String no_polisi;
    private final String no_rangka;
    private final String STNK_AN;
    private final String NO_MESIN;
    private final String ALAMAT;
    private final String NO_BPKB;
    private final String KOTA;
    private final String NO_FAKTUR;
    private final String MERK;
    private final String NO_STNK;
    private final String SERI;
    private final String SILINDER;
    private final String NO_KEUR;
    private final String GRADE;
    private final String SUB_GRADE;
    private final String MODEL;
    private final String PLAT_DASAR;
    private final String PENGGERAK;
    private final String TRANSMISI;
    private final String BAHAN_BAKAR;
    private final String TAHUN;
    private final String KM;
    private final String SUMBER;
    private final String KUNCI_TAMBAHAN;
    private final int id_user;

    PersiapanPost(PersiapanBuilder builder) {
        this.cabangtaksasi = builder.cabangtaksasi;
        this.idwarnadoc = builder.idwarnadoc;
        this.WARNA_DOC = builder.WARNA_DOC;
        this.idwarnafisik = builder.idwarnafisik;
        this.WARNA = builder.WARNA;
        this.biayadmin = builder.biayadmin;
        this.biayaparkir = builder.biayaparkir;
        this.kodepenitip = builder.kodepenitip;
        this.namapenitip = builder.namapenitip;
        this.nmridpenitip = builder.nmridpenitip;
        this.tipeidpenitip = builder.tipeidpenitip;
        this.nonpwp = builder.nonpwp;
        this.groupbiodata = builder.groupbiodata;
        this.statuspeserta = builder.statuspeserta;
        this.jenisusaha = builder.jenisusaha;
        this.sebagaiperusahaan = builder.sebagaiperusahaan;
        this.teleponpenitip = builder.teleponpenitip;
        this.ponselpenitip = builder.ponselpenitip;
        this.alamatpenitip = builder.alamatpenitip;
        this.kotapenitip = builder.kotapenitip;
        this.kodepospenitip = builder.kodepospenitip;
        this.idbiodata = builder.idbiodata;
        this.jadwalelang = builder.jadwalelang;
        this.tglelang = builder.tglelang;
        this.cabanglelang = builder.cabanglelang;
        this.lokasilelang = builder.lokasilelang;
        this.alamatbrglelang = builder.alamatbrglelang;
        this.ikutseselelang = builder.ikutseselelang;
        this.lokasibrglelang = builder.lokasibrglelang;
        this.kotabrglelang = builder.kotabrglelang;
        this.namapicdisplay = builder.namapicdisplay;
        this.lokasidisplay = builder.lokasidisplay;
        this.alamatdisplay = builder.alamatdisplay;
        this.kotadisplay = builder.kotadisplay;
        this.telppicdisplay = builder.telppicdisplay;
        this.namapic = builder.namapic;
        this.jabatanpic = builder.jabatanpic;
        this.alamatpic = builder.alamatpic;
        this.kotapic = builder.kotapic;
        this.telppic = builder.telppic;
        this.ponselpic = builder.ponselpic;
        this.faxpic = builder.faxpic;
        this.mailpi = builder.mailpi;
        this.catatan = builder.catatan;
        this.STNK_SD = builder.STNK_SD;
        this.TGL_KEUR = builder.TGL_KEUR;
        this.lotnumb = builder.lotnumb;
        this.no_polisi = builder.no_polisi;
        this.no_rangka = builder.no_rangka;
        this.STNK_AN = builder.STNK_AN;
        this.NO_MESIN = builder.NO_MESIN;
        this.ALAMAT = builder.ALAMAT;
        this.NO_BPKB = builder.NO_BPKB;
        this.KOTA = builder.KOTA;
        this.NO_FAKTUR = builder.NO_FAKTUR;
        this.MERK = builder.MERK;
        this.NO_STNK = builder.NO_STNK;
        this.SERI = builder.SERI;
        this.SILINDER = builder.SILINDER;
        this.NO_KEUR = builder.NO_KEUR;
        this.GRADE = builder.GRADE;
        this.SUB_GRADE = builder.SUB_GRADE;
        this.MODEL = builder.MODEL;
        this.PLAT_DASAR = builder.PLAT_DASAR;
        this.PENGGERAK = builder.PENGGERAK;
        this.TRANSMISI = builder.TRANSMISI;
        this.BAHAN_BAKAR = builder.BAHAN_BAKAR;
        this.TAHUN = builder.TAHUN;
        this.KM = builder.KM;
        this.SUMBER = builder.SUMBER;
        this.KUNCI_TAMBAHAN = builder.KUNCI_TAMBAHAN;
        this.id_user = builder.id_user;
    }

    public String getNo_rangka() { return no_rangka; }

    public String getSTNK_AN() { return STNK_AN; }

    public String getNO_MESIN() { return NO_MESIN; }

    public String getALAMAT() { return ALAMAT; }

    public String getNO_BPKB() { return NO_BPKB; }

    public String getKOTA() { return KOTA; }

    public String getNO_FAKTUR() { return NO_FAKTUR; }

    public String getMERK() { return MERK; }

    public String getNO_STNK() { return NO_STNK; }

    public String getSERI() { return SERI; }

    public String getSILINDER() { return SILINDER; }

    public String getNO_KEUR() { return NO_KEUR; }

    public String getGRADE() { return GRADE; }

    public String getSUB_GRADE() { return SUB_GRADE; }

    public String getMODEL() { return MODEL; }

    public String getPLAT_DASAR() { return PLAT_DASAR; }

    public String getPENGGERAK() { return PENGGERAK; }

    public String getTRANSMISI() { return TRANSMISI; }

    public String getBAHAN_BAKAR() { return BAHAN_BAKAR; }

    public String getTAHUN() { return TAHUN; }

    public String getKM() { return KM; }

    public String getSUMBER() { return SUMBER; }

    public String getKUNCI_TAMBAHAN() { return KUNCI_TAMBAHAN; }

    public String getCabangtaksasi() {
        return cabangtaksasi;
    }

    public int getIdwarnadoc() {
        return idwarnadoc;
    }

    public String getWARNA_DOC() {
        return WARNA_DOC;
    }

    public int getIdwarnafisik() {
        return idwarnafisik;
    }

    public String getWARNA() {
        return WARNA;
    }

    public int getBiayadmin() {
        return biayadmin;
    }

    public int getBiayaparkir() {
        return biayaparkir;
    }

    public int getKodepenitip() {
        return kodepenitip;
    }

    public String getNamapenitip() {
        return namapenitip;
    }

    public String getNmridpenitip() {
        return nmridpenitip;
    }

    public int getTipeidpenitip() {
        return tipeidpenitip;
    }

    public String getNonpwp() {
        return nonpwp;
    }

    public String getGroupbiodata() {
        return groupbiodata;
    }

    public boolean isStatuspeserta() {
        return statuspeserta;
    }

    public String getJenisusaha() {
        return jenisusaha;
    }

    public boolean getSebagaiperusahaan() { return sebagaiperusahaan; }

    public String getTeleponpenitip() {
        return teleponpenitip;
    }

    public String getPonselpenitip() {
        return ponselpenitip;
    }

    public String getAlamatpenitip() {
        return alamatpenitip;
    }

    public String getKotapenitip() {
        return kotapenitip;
    }

    public String getKodepospenitip() {
        return kodepospenitip;
    }

    public String getIdbiodata() {
        return idbiodata;
    }

    public String getJadwalelang() {
        return jadwalelang;
    }

    public String getTglelang() {
        return tglelang;
    }

    public String getCabanglelang() {
        return cabanglelang;
    }

    public String getLokasilelang() {
        return lokasilelang;
    }

    public String getAlamatbrglelang() { return alamatbrglelang; }

    public boolean isIkutseselelang() {
        return ikutseselelang;
    }

    public String getLokasibrglelang() {
        return lokasibrglelang;
    }

    public String getKotabrglelang() { return kotabrglelang; }

    public String getNamapicdisplay() {
        return namapicdisplay;
    }

    public String getLokasidisplay() {
        return lokasidisplay;
    }

    public String getKotadisplay() {
        return kotadisplay;
    }

    public String getAlamatdisplay() {
        return alamatdisplay;
    }

    public String getTelppicdisplay() {
        return telppicdisplay;
    }

    public String getNamapic() {
        return namapic;
    }

    public String getJabatanpic() {
        return jabatanpic;
    }

    public String getAlamatpic() {
        return alamatpic;
    }

    public String getKotapic() {
        return kotapic;
    }

    public String getTelppic() {
        return telppic;
    }

    public String getPonselpic() {
        return ponselpic;
    }

    public String getFaxpic() {
        return faxpic;
    }

    public String getMailpi() {
        return mailpi;
    }

    public String getCatatan() {
        return catatan;
    }

    public String getSTNK_SD() {
        return STNK_SD;
    }

    public String getTGL_KEUR() {
        return TGL_KEUR;
    }

    public String getLotnumb() {
        return lotnumb;
    }

    public String getNo_polisi() {
        return no_polisi;
    }

    public boolean isSebagaiperusahaan() { return sebagaiperusahaan;}

    public int getId_user() {
        return id_user;
    }

}
