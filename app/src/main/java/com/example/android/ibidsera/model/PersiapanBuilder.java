package com.example.android.ibidsera.model;

import java.util.Date;

/**
 * Created by harfi on 04/08/2017.
 */

public class PersiapanBuilder {

    String cabangtaksasi;
    int idwarnadoc;
    String WARNA_DOC;
    int idwarnafisik;
    String WARNA;
    int biayadmin;
    int biayaparkir;
    int kodepenitip;
    String namapenitip;
    String nmridpenitip;
    int tipeidpenitip;
    String nonpwp;
    String groupbiodata;
    boolean statuspeserta;
    String jenisusaha;
    boolean sebagaiperusahaan;
    String teleponpenitip;
    String ponselpenitip;
    String alamatpenitip;
    String kotapenitip;
    String kodepospenitip;
    String idbiodata;
    Date jadwalelang;
    Date tglelang;
    String cabanglelang;
    String lokasilelang;
    String alamatbrglelang;
    boolean ikutseselelang;
    String lokasibrglelang;
    String kotabrglelang;
    String namapicdisplay;
    String lokasidisplay;
    String alamatdisplay;
    String kotadisplay;
    String telppicdisplay;
    String namapic;
    String jabatanpic;
    String alamatpic;
    String kotapic;
    String telppic;
    String ponselpic;
    String faxpic;
    String mailpi;
    String catatan;
    String STNK_SD;
    Date TGL_KEUR;
    String lotnumb;
    String no_polisi;
    String no_rangka;
    String STNK_AN;
    String NO_MESIN;
    String ALAMAT;
    String NO_BPKB;
    String KOTA;
    String NO_FAKTUR;
    String MERK;
    String NO_STNK;
    String SERI;
    String SILINDER;
    String NO_KEUR;
    String GRADE;
    String SUB_GRADE;
    String MODEL;
    String PLAT_DASAR;
    String PENGGERAK;
    String TRANSMISI;
    String BAHAN_BAKAR;
    String TAHUN;
    String KM;
    String SUMBER;
    String KUNCI_TAMBAHAN;

    public PersiapanBuilder setNo_rangka(String no_rangka) {
        this.no_rangka = no_rangka;
        return this;
    }

    public PersiapanBuilder setSTNK_AN(String STNK_AN) {
        this.STNK_AN = STNK_AN;
        return this;
    }

    public PersiapanBuilder setNO_MESIN(String NO_MESIN) {
        this.NO_MESIN = NO_MESIN;
        return this;
    }

    public PersiapanBuilder setALAMAT(String ALAMAT) {
        this.ALAMAT = ALAMAT;
        return this;
    }

    public PersiapanBuilder setNO_BPKB(String NO_BPKB) {
        this.NO_BPKB = NO_BPKB;
        return this;
    }

    public PersiapanBuilder setKOTA(String KOTA) {
        this.KOTA = KOTA;
        return this;
    }

    public PersiapanBuilder setNO_FAKTUR(String NO_FAKTUR) {
        this.NO_FAKTUR = NO_FAKTUR;
        return this;
    }

    public PersiapanBuilder setMERK(String MERK) {
        this.MERK = MERK;
        return this;
    }

    public PersiapanBuilder setNO_STNK(String NO_STNK) {
        this.NO_STNK = NO_STNK;
        return this;
    }

    public PersiapanBuilder setSERI(String SERI) {
        this.SERI = SERI;
        return this;
    }

    public PersiapanBuilder setSILINDER(String SILINDER) {
        this.SILINDER = SILINDER;
        return this;
    }

    public PersiapanBuilder setNO_KEUR(String NO_KEUR) {
        this.NO_KEUR = NO_KEUR;
        return this;
    }

    public PersiapanBuilder setGRADE(String GRADE) {
        this.GRADE = GRADE;
        return this;
    }

    public PersiapanBuilder setSUB_GRADE(String SUB_GRADE) {
        this.SUB_GRADE = SUB_GRADE;
        return this;
    }

    public PersiapanBuilder setMODEL(String MODEL) {
        this.MODEL = MODEL;
        return this;
    }

    public PersiapanBuilder setPLAT_DASAR(String PLAT_DASAR) {
        this.PLAT_DASAR = PLAT_DASAR;
        return this;
    }

    public PersiapanBuilder setPENGGERAK(String PENGGERAK) {
        this.PENGGERAK = PENGGERAK;
        return this;
    }

    public PersiapanBuilder setTRANSMISI(String TRANSMISI) {
        this.TRANSMISI = TRANSMISI;
        return this;
    }

    public PersiapanBuilder setBAHAN_BAKAR(String BAHAN_BAKAR) {
        this.BAHAN_BAKAR = BAHAN_BAKAR;
        return this;
    }

    public PersiapanBuilder setTAHUN(String TAHUN) {
        this.TAHUN = TAHUN;
        return this;
    }

    public PersiapanBuilder setKM(String KM) {
        this.KM = KM;
        return this;
    }

    public PersiapanBuilder setSUMBER(String SUMBER) {
        this.SUMBER = SUMBER;
        return this;
    }

    public PersiapanBuilder setKUNCI_TAMBAHAN(String KUNCI_TAMBAHAN) {
        this.KUNCI_TAMBAHAN = KUNCI_TAMBAHAN;
        return this;
    }

    int id_user;

    public static PersiapanBuilder persiapan() {
        return new PersiapanBuilder();
    }

    public PersiapanBuilder setCabangtaksasi(String cabangtaksasi) {
        this.cabangtaksasi = cabangtaksasi;
        return this;
    }

    public PersiapanBuilder setIdwarnadoc(int idwarnadoc) {
        this.idwarnadoc = idwarnadoc;
        return this;
    }

    public PersiapanBuilder setWARNA_DOC(String WARNA_DOC) {
        this.WARNA_DOC = WARNA_DOC;
        return this;
    }

    public PersiapanBuilder setIdwarnafisik(int idwarnafisik) {
        this.idwarnafisik = idwarnafisik;
        return this;
    }

    public PersiapanBuilder setWARNA(String WARNA) {
        this.WARNA = WARNA;
        return this;
    }

    public PersiapanBuilder setBiayadmin(int biayadmin) {
        this.biayadmin = biayadmin;
        return this;
    }

    public PersiapanBuilder setBiayaparkir(int biayaparkir) {
        this.biayaparkir = biayaparkir;
        return this;
    }

    public PersiapanBuilder setKodepenitip(int kodepenitip) {
        this.kodepenitip = kodepenitip;
        return this;
    }

    public PersiapanBuilder setNamapenitip(String namapenitip) {
        this.namapenitip = namapenitip;
        return this;
    }

    public PersiapanBuilder setNmridpenitip(String nmridpenitip) {
        this.nmridpenitip = nmridpenitip;
        return this;
    }

    public PersiapanBuilder setTipeidpenitip(int tipeidpenitip) {
        this.tipeidpenitip = tipeidpenitip;
        return this;
    }

    public PersiapanBuilder setNonpwp(String nonpwp) {
        this.nonpwp = nonpwp;
        return this;
    }

    public PersiapanBuilder setGroupbiodata(String groupbiodata) {
        this.groupbiodata = groupbiodata;
        return this;
    }

    public PersiapanBuilder setStatuspeserta(boolean statuspeserta) {
        this.statuspeserta = statuspeserta;
        return this;
    }

    public PersiapanBuilder setJenisusaha(String jenisusaha) {
        this.jenisusaha = jenisusaha;
        return this;
    }

    public PersiapanBuilder setSebagaiperusahaan(boolean sebagaiperusahaan) {
        this.sebagaiperusahaan = sebagaiperusahaan;
        return this;
    }

    public PersiapanBuilder setTeleponpenitip(String teleponpenitip) {
        this.teleponpenitip = teleponpenitip;
        return this;
    }

    public PersiapanBuilder setPonselpenitip(String ponselpenitip) {
        this.ponselpenitip = ponselpenitip;
        return this;
    }

    public PersiapanBuilder setAlamatpenitip(String alamatpenitip) {
        this.alamatpenitip = alamatpenitip;
        return this;
    }

    public PersiapanBuilder setKotapenitip(String kotapenitip) {
        this.kotapenitip = kotapenitip;
        return this;
    }

    public PersiapanBuilder setKodepospenitip(String kodepospenitip) {
        this.kodepospenitip = kodepospenitip;
        return this;
    }

    public PersiapanBuilder setIdbiodata(String idbiodata) {
        this.idbiodata = idbiodata;
        return this;
    }

    public PersiapanBuilder setJadwalelang(Date jadwalelang) {
        this.jadwalelang = jadwalelang;
        return this;
    }

    public PersiapanBuilder setTglelang(Date tglelang) {
        this.tglelang = tglelang;
        return this;
    }

    public PersiapanBuilder setCabanglelang(String cabanglelang) {
        this.cabanglelang = cabanglelang;
        return this;
    }

    public PersiapanBuilder setLokasilelang(String lokasilelang) {
        this.lokasilelang = lokasilelang;
        return this;
    }

    public PersiapanBuilder setIkutseselelang(boolean ikutseselelang) {
        this.ikutseselelang = ikutseselelang;
        return this;
    }

    public PersiapanBuilder setLokasibrglelang(String lokasibrglelang) {
        this.lokasibrglelang = lokasibrglelang;
        return this;
    }

    public PersiapanBuilder setAlamatbrglelang(String alamatbrglelang) {
        this.alamatbrglelang = alamatbrglelang;
        return this;
    }

    public PersiapanBuilder setKotabrglelang(String kotabrglelang) {
        this.kotabrglelang = kotabrglelang;
        return this;
    }

    public PersiapanBuilder setNamapicdisplay(String namapicdisplay) {
        this.namapicdisplay = namapicdisplay;
        return this;
    }

    public PersiapanBuilder setLokasidisplay(String lokasidisplay) {
        this.lokasidisplay = lokasidisplay;
        return this;
    }

    public PersiapanBuilder setAlamatdisplay(String alamatdisplay) {
        this.alamatdisplay = alamatdisplay;
        return this;
    }

    public PersiapanBuilder setKotadisplay(String kotadisplay) {
        this.kotadisplay = kotadisplay;
        return this;
    }

    public PersiapanBuilder setTelppicdisplay(String telppicdisplay) {
        this.telppicdisplay = telppicdisplay;
        return this;
    }

    public PersiapanBuilder setNamapic(String namapic) {
        this.namapic = namapic;
        return this;
    }

    public PersiapanBuilder setJabatanpic(String jabatanpic) {
        this.jabatanpic = jabatanpic;
        return this;
    }

    public PersiapanBuilder setAlamatpic(String alamatpic) {
        this.alamatpic = alamatpic;
        return this;
    }

    public PersiapanBuilder setKotapic(String kotapic) {
        this.kotapic = kotapic;
        return this;
    }

    public PersiapanBuilder setTelppic(String telppic) {
        this.telppic = telppic;
        return this;
    }

    public PersiapanBuilder setPonselpic(String ponselpic) {
        this.ponselpic = ponselpic;
        return this;
    }

    public PersiapanBuilder setFaxpic(String faxpic) {
        this.faxpic = faxpic;
        return this;
    }

    public PersiapanBuilder setMailpi(String mailpi) {
        this.mailpi = mailpi;
        return this;
    }

    public PersiapanBuilder setCatatan(String catatan) {
        this.catatan = catatan;
        return this;
    }

    public PersiapanBuilder setSTNK_SD(String STNK_SD) {
        this.STNK_SD = STNK_SD;
        return this;
    }

    public PersiapanBuilder setTGL_KEUR(Date TGL_KEUR) {
        this.TGL_KEUR = TGL_KEUR;
        return this;
    }

    public PersiapanBuilder setLotnumb(String lotnumb) {
        this.lotnumb = lotnumb;
        return this;
    }

    public PersiapanBuilder setNo_polisi(String no_polisi) {
        this.no_polisi = no_polisi;
        return this;
    }

    public PersiapanBuilder setId_user(int id_user) {
        this.id_user = id_user;
        return this;
    }

    public PersiapanPost build() {
        return new PersiapanPost(this);
    }
}
