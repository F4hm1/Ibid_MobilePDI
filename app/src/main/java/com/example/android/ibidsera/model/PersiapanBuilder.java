package com.example.android.ibidsera.model;

/**
 * Created by harfi on 04/08/2017.
 */

public class PersiapanBuilder {

    int id_user;
    String cabangtaksasi;
    int idwarnadoc;
    String WARNA_DOC;
    int idwarnafisik;
    String WARNA;
    int biayadmin;
    int biayaparkir;
    String kodepenitip;
    String namapenitip;
    String nmridpenitip;
    int tipeidpenitip;
    String nonpwp;
    String groupbiodata;
    int statuspeserta;
    String jenisusaha;
    int sebagaiperusahaan;
    String teleponpenitip;
    String ponselpenitip;
    String alamatpenitip;
    String kotapenitip;
    String kodepospenitip;
    String idbiodata;
    String jadwalelang;
    String tglelang;
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
    String TGL_KEUR;
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

    public PersiapanBuilder setId_user(int id_user) {
        this.id_user = id_user;
        return this;
    }

    public PersiapanBuilder setCabangtaksasi(String cabangtaksasi) {
        if (cabangtaksasi == null) throw new NullPointerException("cabangtaksasi");
        this.cabangtaksasi = cabangtaksasi;
        return this;
    }

    public PersiapanBuilder setWARNA_DOC(String WARNA_DOC) {
        if (WARNA_DOC == null) throw new NullPointerException("WARNA_DOC");
        this.WARNA_DOC = WARNA_DOC;
        return this;
    }

    public PersiapanBuilder setWARNA(String WARNA) {
        if (WARNA == null) throw new NullPointerException("WARNA");
        this.WARNA = WARNA;
        return this;
    }

    public PersiapanBuilder setKodepenitip(String kodepenitip) {
        if (kodepenitip == null) throw new NullPointerException("kodepenitip");
        this.kodepenitip = kodepenitip;
        return this;
    }

    public PersiapanBuilder setNamapenitip(String namapenitip) {
        if (namapenitip == null) throw new NullPointerException("namapenitip");
        this.namapenitip = namapenitip;
        return this;
    }

    public PersiapanBuilder setNmridpenitip(String nmridpenitip) {
        if (nmridpenitip == null) throw new NullPointerException("nmridpenitip");
        this.nmridpenitip = nmridpenitip;
        return this;
    }

    public PersiapanBuilder setNonpwp(String nonpwp) {
        if (nonpwp == null) throw new NullPointerException("nonpwp");
        this.nonpwp = nonpwp;
        return this;
    }

    public PersiapanBuilder setGroupbiodata(String groupbiodata) {
        if (groupbiodata == null) throw new NullPointerException("groupbiodata");
        this.groupbiodata = groupbiodata;
        return this;
    }

    public PersiapanBuilder setJenisusaha(String jenisusaha) {
        if (jenisusaha == null) throw new NullPointerException("jenisusaha");
        this.jenisusaha = jenisusaha;
        return this;
    }

    public PersiapanBuilder setTeleponpenitip(String teleponpenitip) {
        if (teleponpenitip == null) throw new NullPointerException("teleponpenitip");
        this.teleponpenitip = teleponpenitip;
        return this;
    }

    public PersiapanBuilder setPonselpenitip(String ponselpenitip) {
        if (ponselpenitip == null) throw new NullPointerException("ponselpenitip");
        this.ponselpenitip = ponselpenitip;
        return this;
    }

    public PersiapanBuilder setAlamatpenitip(String alamatpenitip) {
        if (alamatpenitip == null) throw new NullPointerException("alamatpenitip");
        this.alamatpenitip = alamatpenitip;
        return this;
    }

    public PersiapanBuilder setKotapenitip(String kotapenitip) {
        if (kotapenitip == null) throw new NullPointerException("kotapenitip");
        this.kotapenitip = kotapenitip;
        return this;
    }

    public PersiapanBuilder setKodepospenitip(String kodepospenitip) {
        if (kodepospenitip == null) throw new NullPointerException("kodepospenitip");
        this.kodepospenitip = kodepospenitip;
        return this;
    }

    public PersiapanBuilder setIdbiodata(String idbiodata) {
        if (idbiodata == null) throw new NullPointerException("idbiodata");
        this.idbiodata = idbiodata;
        return this;
    }

    public PersiapanBuilder setJadwalelang(String jadwalelang) {
        if (jadwalelang == null) throw new NullPointerException("jadwalelang");
        this.jadwalelang = jadwalelang;
        return this;
    }

    public PersiapanBuilder setTglelang(String tglelang) {
        if (tglelang == null) throw new NullPointerException("tglelang");
        this.tglelang = tglelang;
        return this;
    }

    public PersiapanBuilder setCabanglelang(String cabanglelang) {
        if (cabanglelang == null) throw new NullPointerException("cabanglelang");
        this.cabanglelang = cabanglelang;
        return this;
    }

    public PersiapanBuilder setLokasilelang(String lokasilelang) {
        if (lokasilelang == null) throw new NullPointerException("lokasilelang");
        this.lokasilelang = lokasilelang;
        return this;
    }

    public PersiapanBuilder setAlamatbrglelang(String alamatbrglelang) {
        if (alamatbrglelang == null) throw new NullPointerException("alamatbrglelang");
        this.alamatbrglelang = alamatbrglelang;
        return this;
    }

    public PersiapanBuilder setIkutseselelang(boolean ikutseselelang) {
        this.ikutseselelang = ikutseselelang;
        return this;
    }

    public PersiapanBuilder setLokasibrglelang(String lokasibrglelang) {
        if (lokasibrglelang == null) throw new NullPointerException("lokasibrglelang");
        this.lokasibrglelang = lokasibrglelang;
        return this;
    }

    public PersiapanBuilder setKotabrglelang(String kotabrglelang) {
        if (kotabrglelang == null) throw new NullPointerException("kotabrglelang");
        this.kotabrglelang = kotabrglelang;
        return this;
    }

    public PersiapanBuilder setNamapicdisplay(String namapicdisplay) {
        if (namapicdisplay == null) throw new NullPointerException("namapicdisplay");
        this.namapicdisplay = namapicdisplay;
        return this;
    }

    public PersiapanBuilder setLokasidisplay(String lokasidisplay) {
        if (lokasidisplay == null) throw new NullPointerException("lokasidisplay");
        this.lokasidisplay = lokasidisplay;
        return this;
    }

    public PersiapanBuilder setAlamatdisplay(String alamatdisplay) {
        if (alamatdisplay == null) throw new NullPointerException("alamatdisplay");
        this.alamatdisplay = alamatdisplay;
        return this;
    }

    public PersiapanBuilder setKotadisplay(String kotadisplay) {
        if (kotadisplay == null) throw new NullPointerException("kotadisplay");
        this.kotadisplay = kotadisplay;
        return this;
    }

    public PersiapanBuilder setTelppicdisplay(String telppicdisplay) {
        if (telppicdisplay == null) throw new NullPointerException("telppicdisplay");
        this.telppicdisplay = telppicdisplay;
        return this;
    }

    public PersiapanBuilder setNamapic(String namapic) {
        if (namapic == null) throw new NullPointerException("namapic");
        this.namapic = namapic;
        return this;
    }

    public PersiapanBuilder setJabatanpic(String jabatanpic) {
        if (jabatanpic == null) throw new NullPointerException("jabatanpic");
        this.jabatanpic = jabatanpic;
        return this;
    }

    public PersiapanBuilder setAlamatpic(String alamatpic) {
        if (alamatpic == null) throw new NullPointerException("alamatpic");
        this.alamatpic = alamatpic;
        return this;
    }

    public PersiapanBuilder setKotapic(String kotapic) {
        if (kotapic == null) throw new NullPointerException("kotapic");
        this.kotapic = kotapic;
        return this;
    }

    public PersiapanBuilder setTelppic(String telppic) {
        if (telppic == null) throw new NullPointerException("telppic");
        this.telppic = telppic;
        return this;
    }

    public PersiapanBuilder setPonselpic(String ponselpic) {
        if (ponselpic == null) throw new NullPointerException("ponselpic");
        this.ponselpic = ponselpic;
        return this;
    }

    public PersiapanBuilder setFaxpic(String faxpic) {
        if (faxpic == null) throw new NullPointerException("faxpic");
        this.faxpic = faxpic;
        return this;
    }

    public PersiapanBuilder setMailpi(String mailpi) {
        if (mailpi == null) throw new NullPointerException("mailpi");
        this.mailpi = mailpi;
        return this;
    }

    public PersiapanBuilder setCatatan(String catatan) {
        if (catatan == null) throw new NullPointerException("catatan");
        this.catatan = catatan;
        return this;
    }

    public PersiapanBuilder setSTNK_SD(String STNK_SD) {
        if (STNK_SD == null) throw new NullPointerException("STNK_SD");
        this.STNK_SD = STNK_SD;
        return this;
    }

    public PersiapanBuilder setTGL_KEUR(String TGL_KEUR) {
        if (TGL_KEUR == null) throw new NullPointerException("TGL_KEUR");
        this.TGL_KEUR = TGL_KEUR;
        return this;
    }

    public PersiapanBuilder setLotnumb(String lotnumb) {
        if (lotnumb == null) throw new NullPointerException("lotnumb");
        this.lotnumb = lotnumb;
        return this;
    }

    public PersiapanBuilder setNo_polisi(String no_polisi) {
        if (no_polisi == null) throw new NullPointerException("no_polisi");
        this.no_polisi = no_polisi;
        return this;
    }

    public PersiapanBuilder setNo_rangka(String no_rangka) {
        if (no_rangka == null) throw new NullPointerException("no_rangka");
        this.no_rangka = no_rangka;
        return this;
    }

    public PersiapanBuilder setSTNK_AN(String STNK_AN) {
        if (STNK_AN == null) throw new NullPointerException("STNK_AN");
        this.STNK_AN = STNK_AN;
        return this;
    }

    public PersiapanBuilder setNO_MESIN(String NO_MESIN) {
        if (NO_MESIN == null) throw new NullPointerException("NO_MESIN");
        this.NO_MESIN = NO_MESIN;
        return this;
    }

    public PersiapanBuilder setALAMAT(String ALAMAT) {
        if (ALAMAT == null) throw new NullPointerException("ALAMAT");
        this.ALAMAT = ALAMAT;
        return this;
    }

    public PersiapanBuilder setNO_BPKB(String NO_BPKB) {
        if (NO_BPKB == null) throw new NullPointerException("NO_BPKB");
        this.NO_BPKB = NO_BPKB;
        return this;
    }

    public PersiapanBuilder setKOTA(String KOTA) {
        if (KOTA == null) throw new NullPointerException("KOTA");
        this.KOTA = KOTA;
        return this;
    }

    public PersiapanBuilder setNO_FAKTUR(String NO_FAKTUR) {
        if (NO_FAKTUR == null) throw new NullPointerException("NO_FAKTUR");
        this.NO_FAKTUR = NO_FAKTUR;
        return this;
    }

    public PersiapanBuilder setMERK(String MERK) {
        if (MERK == null) throw new NullPointerException("MERK");
        this.MERK = MERK;
        return this;
    }

    public PersiapanBuilder setNO_STNK(String NO_STNK) {
        if (NO_STNK == null) throw new NullPointerException("NO_STNK");
        this.NO_STNK = NO_STNK;
        return this;
    }

    public PersiapanBuilder setSERI(String SERI) {
        if (SERI == null) throw new NullPointerException("SERI");
        this.SERI = SERI;
        return this;
    }

    public PersiapanBuilder setSILINDER(String SILINDER) {
        if (SILINDER == null) throw new NullPointerException("SILINDER");
        this.SILINDER = SILINDER;
        return this;
    }

    public PersiapanBuilder setNO_KEUR(String NO_KEUR) {
        if (NO_KEUR == null) throw new NullPointerException("NO_KEUR");
        this.NO_KEUR = NO_KEUR;
        return this;
    }

    public PersiapanBuilder setGRADE(String GRADE) {
        if (GRADE == null) throw new NullPointerException("GRADE");
        this.GRADE = GRADE;
        return this;
    }

    public PersiapanBuilder setSUB_GRADE(String SUB_GRADE) {
        if (SUB_GRADE == null) throw new NullPointerException("SUB_GRADE");
        this.SUB_GRADE = SUB_GRADE;
        return this;
    }

    public PersiapanBuilder setMODEL(String MODEL) {
        if (MODEL == null) throw new NullPointerException("MODEL");
        this.MODEL = MODEL;
        return this;
    }

    public PersiapanBuilder setPLAT_DASAR(String PLAT_DASAR) {
        if (PLAT_DASAR == null) throw new NullPointerException("PLAT_DASAR");
        this.PLAT_DASAR = PLAT_DASAR;
        return this;
    }

    public PersiapanBuilder setPENGGERAK(String PENGGERAK) {
        if (PENGGERAK == null) throw new NullPointerException("PENGGERAK");
        this.PENGGERAK = PENGGERAK;
        return this;
    }

    public PersiapanBuilder setTRANSMISI(String TRANSMISI) {
        if (TRANSMISI == null) throw new NullPointerException("TRANSMISI");
        this.TRANSMISI = TRANSMISI;
        return this;
    }

    public PersiapanBuilder setBAHAN_BAKAR(String BAHAN_BAKAR) {
        if (BAHAN_BAKAR == null) throw new NullPointerException("BAHAN_BAKAR");
        this.BAHAN_BAKAR = BAHAN_BAKAR;
        return this;
    }

    public PersiapanBuilder setTAHUN(String TAHUN) {
        if (TAHUN == null) throw new NullPointerException("TAHUN");
        this.TAHUN = TAHUN;
        return this;
    }

    public PersiapanBuilder setKM(String KM) {
        if (KM == null) throw new NullPointerException("KM");
        this.KM = KM;
        return this;
    }

    public PersiapanBuilder setSUMBER(String SUMBER) {
        if (SUMBER == null) throw new NullPointerException("SUMBER");
        this.SUMBER = SUMBER;
        return this;
    }

    public PersiapanBuilder setKUNCI_TAMBAHAN(String KUNCI_TAMBAHAN) {
        if (KUNCI_TAMBAHAN == null) throw new NullPointerException("KUNCI_TAMBAHAN");
        this.KUNCI_TAMBAHAN = KUNCI_TAMBAHAN;
        return this;
    }

    public PersiapanBuilder setIdwarnadoc(int idwarnadoc) {
        this.idwarnadoc = idwarnadoc;
        return this;
    }

    public PersiapanBuilder setIdwarnafisik(int idwarnafisik) {
        this.idwarnafisik = idwarnafisik;
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

    public PersiapanBuilder setTipeidpenitip(int tipeidpenitip) {
        this.tipeidpenitip = tipeidpenitip;
        return this;
    }

    public PersiapanBuilder setStatuspeserta(int statuspeserta) {
        this.statuspeserta = statuspeserta;
        return this;
    }

    public PersiapanBuilder setSebagaiperusahaan(int sebagaiperusahaan) {
        this.sebagaiperusahaan = sebagaiperusahaan;
        return this;
    }

    public static PersiapanBuilder persiapan() {
        return new PersiapanBuilder();
    }

    public PersiapanPost build() {
        return new PersiapanPost(this);
    }
}
