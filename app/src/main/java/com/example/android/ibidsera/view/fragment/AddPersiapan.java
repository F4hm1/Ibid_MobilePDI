package com.example.android.ibidsera.view.fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.Attribute;
import com.example.android.ibidsera.model.Penitip;
import com.example.android.ibidsera.model.PersiapanPost;
import com.example.android.ibidsera.model.PersiapanValue;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.android.ibidsera.model.PersiapanBuilder.persiapan;

/**
 * Created by Yosefricaro on 24/07/2017.
 */

public class AddPersiapan extends BaseFragment {
    @BindView(R.id.nopol_title) TextView nopol_title;
    @BindView(R.id.merk_title) TextView merk_title;
    @BindView(R.id.seri_title) TextView seri_title;
    @BindView(R.id.silinder_title) TextView silinder_title;
    @BindView(R.id.grade_title) TextView grade_title;
    @BindView(R.id.sub_grade_title) TextView sub_grade_title;
    @BindView(R.id.plat_title) TextView plat_title;
    @BindView(R.id.transmisi_title) TextView transmisi_title;
    @BindView(R.id.tahun_title) TextView tahun_title;
    @BindView(R.id.nama_penitip_title) TextView nama_penitip_title;

    @BindView(R.id.progress_view) CircularProgressView cpv;
    @BindView(R.id.background_progress) RelativeLayout bp;
    @BindView(R.id.no_polisi) protected EditText noPolisi;
    @BindView(R.id.stnk_an) protected EditText stnkAn;
    @BindView(R.id.kota) protected EditText kota;
    @BindView(R.id.merk) protected Spinner merk;
    @BindView(R.id.seri) protected Spinner seri;
    @BindView(R.id.silinder) protected Spinner silinder;
    @BindView(R.id.grade) protected Spinner grade;
    @BindView(R.id.sub_grade) protected Spinner subGrade;
    @BindView(R.id.plat_dasar) protected Spinner platDasar;
    @BindView(R.id.transmisi) protected EditText transmisi;
    @BindView(R.id.tahun) protected EditText tahun;
    @BindView(R.id.sumber) protected Spinner sumber;
    @BindView(R.id.warna_doc) protected AutoCompleteTextView warnaDoc;
    @BindView(R.id.lintas) protected CheckBox lintas;
    @BindView(R.id.taksasi_appraiser) protected Spinner taksasiAppraiser;
    @BindView(R.id.no_rangka) protected EditText noRangka;
    @BindView(R.id.no_mesin) protected EditText noMesin;
    @BindView(R.id.no_bpkb) protected EditText noBpkb;
    @BindView(R.id.no_faktur) protected EditText nofaktur;
    @BindView(R.id.no_stnk) protected EditText noStnk;
    @BindView(R.id.stnk_sd) protected EditText stnkSd;
    @BindView(R.id.no_keur) protected EditText noKeur;
    @BindView(R.id.tgl_keur) protected EditText tglKeur;
    @BindView(R.id.model) protected EditText model;
    @BindView(R.id.penggerak) protected EditText penggerak;
    @BindView(R.id.bahan_bakar) protected EditText bahanBakar;
    @BindView(R.id.km) protected EditText km;
    @BindView(R.id.warna) protected AutoCompleteTextView warna;
    @BindView(R.id.kunci_tambahan) protected EditText kunciTambahan;
    @BindView(R.id.biaya_administrasi) protected EditText biayaAdministrasi;
    @BindView(R.id.biaya_parkir) protected EditText biayaParkir;

    @BindView(R.id.nama) protected AutoCompleteTextView nama;
    @BindView(R.id.kode_anggota) protected TextView kode_anggota;
    @BindView(R.id.kode_anggotahead) protected TextView kode_anggotahead;
    @BindView(R.id.no_npwp) protected TextView no_npwp;
    @BindView(R.id.no_npwphead) protected TextView no_npwphead;
    @BindView(R.id.no_identitashead) protected TextView no_identitashead;
    @BindView(R.id.tipe) protected TextView tipe;
    @BindView(R.id.tipehead) protected TextView tipehead;
    @BindView(R.id.group) protected TextView group;
    @BindView(R.id.grouphead) protected TextView grouphead;
    @BindView(R.id.sebagai) protected TextView sebagai;
    @BindView(R.id.sebagaihead) protected TextView sebagaihead;
    @BindView(R.id.perusahaanhead) protected TextView perusahaanhead;
    @BindView(R.id.telepon) protected TextView telepon;
    @BindView(R.id.teleponhead) protected TextView teleponhead;
    @BindView(R.id.alamat) protected TextView alamat;
    @BindView(R.id.alamathead) protected TextView alamathead;
    @BindView(R.id.kota_penitip) protected TextView kota_penitip;
    @BindView(R.id.kota_penitiphead) protected TextView kota_penitiphead;
    @BindView(R.id.kode_pos) protected TextView kode_pos;
    @BindView(R.id.kode_poshead) protected TextView kode_poshead;
    @BindView(R.id.no_identitas) protected EditText noIdentitas;
    @BindView(R.id.perusahaan) protected CheckBox perusahaan;

    @BindView(R.id.nama_pic) protected EditText namaPic;
    @BindView(R.id.ponsel_pic) protected EditText ponselPic;
    @BindView(R.id.lokasi_asal_unit) protected EditText lokasiAsalUnit;
    @BindView(R.id.nama_pic_display) protected  EditText namaPicDisplay;
    @BindView(R.id.btn_save) protected Button save;
    @BindView(R.id.btn_cancel) protected Button cancel;
    private ProgressDialog pDialog;
    private AuctionService auctionService = RetrofitUtil.getAuctionService();
    private PersiapanPost persiapanPost;
    private HashMap<String, String> map;
    private PersiapanValue pv;
    private List<Penitip> lp = new ArrayList<>();
    private int position;
    private String ponselStr = "";
    private String teleponStr = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragment = inflater.inflate(R.layout.content_addp, container, false);
        ButterKnife.bind(this, myFragment);

        cpvStart(cpv, bp);
        hideKeyboard();
        setAllCaps();
        datePicker(tglKeur, 1);
        datePicker(stnkSd, 1);
        setRequired();

        pDialog = new ProgressDialog(getContext());

        auctionService.getAddPersiapan().enqueue(new Callback<PersiapanValue>() {

            @Override
            public void onResponse(Call<PersiapanValue> call, Response<PersiapanValue> response) {
                pv = response.body();
                addList(pv.getMerk(), merk);
                addList(pv.getPlat(), platDasar);
                addList(pv.getSubgr(), subGrade);
                addList(pv.getSumber(), sumber);
                addList(pv.getTaksasi(), taksasiAppraiser);
            }

            @Override
            public void onFailure(Call<PersiapanValue> call, Throwable t) {
                errorRetrofit(call, t);
            }

        });
        cancelListener(cancel);

        return myFragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        List<String> ls = new ArrayList<>();

        merk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                auctionService.getMasterItem(pv.getMerk().get(i)).enqueue(new Callback<List<Attribute>>() {
                    @Override
                    public void onResponse(Call<List<Attribute>> call, Response<List<Attribute>> response) {
                        List<Attribute> attr = response.body();

                        addList(attr, seri);
                        pv.setSeri(attr);
                    }

                    @Override
                    public void onFailure(Call<List<Attribute>> call, Throwable t) {
                        errorRetrofit(call, t);
                        Log.e("Test", String.valueOf(pv.getMerk().get(i).getId_attrdetail()));
                    }

                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}

        });

        seri.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                auctionService.getMasterItem(pv.getSeri().get(i)).enqueue(new Callback<List<Attribute>>() {
                    @Override
                    public void onResponse(Call<List<Attribute>> call, Response<List<Attribute>> response) {
                        List<Attribute> attr = response.body();

                        addList(attr, silinder);
                        pv.setSilinder(attr);
                    }

                    @Override
                    public void onFailure(Call<List<Attribute>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        silinder.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                auctionService.getMasterItem(pv.getSilinder().get(i)).enqueue(new Callback<List<Attribute>>() {
                    @Override
                    public void onResponse(Call<List<Attribute>> call, Response<List<Attribute>> response) {
                        List<Attribute> attr = response.body();

                        addList(attr, grade);
                        pv.setGrade(attr);
                    }

                    @Override
                    public void onFailure(Call<List<Attribute>> call, Throwable t) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        warna.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warna.dismissDropDown();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getDropdownListWarna(auctionService, ls, warna);
            }
        });

        warna.setOnItemClickListener((parent, view, position1, id1) -> {
            hideKeyboard();
        });

        warnaDoc.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warnaDoc.dismissDropDown();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getDropdownListWarna(auctionService, ls, warnaDoc);
            }
        });

        warnaDoc.setOnItemClickListener((parent, view, position1, id1) -> {
            hideKeyboard();
        });

        nama.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                warna.dismissDropDown();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getDropdownListPenitip(auctionService, ls);
            }
        });

        nama.setOnItemClickListener((parent, view, position1, id1) -> {
            AddPersiapan.this.position = position1;
            AddPersiapan.this.hideKeyboard();
            AddPersiapan.this.cpvStart(cpv, bp);
            AddPersiapan.this.getPenitip();
            AddPersiapan.this.cpvStop(cpv, bp);
        });

        perusahaan.setOnCheckedChangeListener((buttonView, isChecked) -> {
            setSebagai();
        });

//        km.addTextChangedListener(new TextWatcher() {
//            public void afterTextChanged(Editable s) {}
//
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
//
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                convertToRupiah(km);
//            }
//        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pDialog.setMessage("Sending Data..");
                pDialog.show();

                try {
                    if (perusahaan.isChecked()) {
                        lp.get(position).setStatus_biodata(1);
                    } else {
                        lp.get(position).setStatus_biodata(0);
                    }
                    lp.get(position).setNo_identitas(noIdentitas.getText().toString());
                }catch (Exception e){}

                HashMap<String, EditText> h = new HashMap<>();
                h.put("NO POLISI", noPolisi);
                h.put("Transmisi", transmisi);
                h.put("Tahun", tahun);
                h.put("Nama", nama);
                h.put("Ponsel", ponselPic);

                HashMap<String, Spinner> hs = new HashMap<>();
                hs.put("Merk", merk);
                hs.put("Seri", seri);
                hs.put("Silinder", silinder);
                hs.put("Grade", grade);
                hs.put("Sub Grade", subGrade);

                List<String> ls2 = AddPersiapan.this.required(h);
                List<String> lsSpinner = AddPersiapan.this.requiredSpinner(hs);

                if (ls2.size() <= 0 || lsSpinner.size() <= 0) {

                    persiapanPost = AddPersiapan.this.getDataView();
                    final Handler handler = new Handler();
                    handler.postDelayed(() -> {

                        auctionService.insertUnit(persiapanPost).enqueue(new Callback<PersiapanPost>() {

                            @Override
                            public void onResponse(Call<PersiapanPost> call, Response<PersiapanPost> response) {
                                Log.i("info", "post submitted to API." + response.body());
                                pDialog.hide();
                                alertDialog("Proses Penambahan Item Berhasil", 1);
                            }

                            @Override
                            public void onFailure(Call<PersiapanPost> call, Throwable t) {
                                errorRetrofit(call, t);
                                pDialog.hide();
                            }
                        });
                    }, 2000);

                } else {
                    pDialog.hide();
                    AddPersiapan.this.alertLogic(ls2);
                    AddPersiapan.this.alertLogic(lsSpinner);
                }

            }
        });
        cpvStop(cpv, bp);
    }

    private PersiapanPost getDataView() {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        PersiapanPost persiapan = persiapan()
                .setNo_polisi(stringVal(noPolisi))
                .setSTNK_AN(stringVal(stnkAn))
                .setKOTA(stringVal(kota))
                .setMERK(String.valueOf(pv.getMerk().get(merk.getSelectedItemPosition()).getId_attrdetail()))
                .setSERI(String.valueOf(pv.getSeri().get(seri.getSelectedItemPosition()).getId_attrdetail()))
                .setSILINDER(String.valueOf(pv.getSilinder().get(silinder.getSelectedItemPosition()).getId_attrdetail()))
                .setGRADE(String.valueOf(pv.getGrade().get(grade.getSelectedItemPosition()).getId_attrdetail()))
                .setSUB_GRADE(String.valueOf(pv.getSubgr().get(subGrade.getSelectedItemPosition()).getId_attrdetail()))
                .setPLAT_DASAR(spinnerItem(platDasar))
                .setTRANSMISI(stringVal(transmisi))
                .setTAHUN(stringVal(tahun))
                .setSUMBER(spinnerItem(sumber))
                .setWARNA_DOC(stringVal(warnaDoc))
                .setCabangtaksasi(spinnerItem(taksasiAppraiser))

                .setNo_rangka(stringVal(noRangka))
                .setNO_MESIN(stringVal(noMesin))
                .setNO_BPKB(stringVal(noBpkb))
                .setNO_FAKTUR(stringVal(nofaktur))
                .setNO_STNK(stringVal(noStnk))
                .setSTNK_SD(stringVal(stnkSd))
                .setNO_KEUR(stringVal(noKeur))
                .setTGL_KEUR(stringVal(tglKeur))
                .setMODEL(stringVal(model))
                .setPENGGERAK(stringVal(penggerak))
                .setBAHAN_BAKAR(stringVal(bahanBakar))
                .setKM(stringVal(km))
                .setWARNA(stringVal(warna))
                .setKUNCI_TAMBAHAN(stringVal(kunciTambahan))
                .setBiayadmin(stringToInt(biayaAdministrasi))
                .setBiayaparkir(stringToInt(biayaParkir))

                .setKodepenitip(String.valueOf(kode_anggota.getText()))
                .setTipeidpenitip(lp.get(position).getId())
                .setNamapenitip(stringVal(nama))
                .setNmridpenitip(stringVal(noIdentitas))
                .setSebagaiperusahaan(lp.get(position).getStatus_biodata())
                .setNonpwp(String.valueOf(no_npwp.getText()))
                .setGroupbiodata(String.valueOf(group.getText()))
                .setStatuspeserta(lp.get(position).getStatus_peserta())
                .setJenisusaha("")
                .setTeleponpenitip(teleponStr)
                .setPonselpenitip(ponselStr)
                .setAlamatpenitip(String.valueOf(alamat.getText()))
                .setKotapenitip(String.valueOf(kota_penitip.getText()))
                .setKodepospenitip(String.valueOf(kode_pos.getText()))
                .setIdbiodata(String.valueOf(lp.get(position).getId()))

                .setNamapic(stringVal(namaPic))
                .setPonselpic(stringVal(ponselPic))
                .setLokasibrglelang(stringVal(lokasiAsalUnit))
                .setNamapicdisplay(stringVal(namaPicDisplay))

                .setJadwalelang(tglKeur.getText().toString())
                .setTglelang(tglKeur.getText().toString())
                .setCabanglelang("")
                .setLokasilelang("")
                .setIkutseselelang(false)
                .setAlamatbrglelang("")
                .setKotabrglelang("")
                .setNamapicdisplay("")
                .setLokasidisplay("")
                .setAlamatdisplay("")
                .setKotadisplay(stringVal(kota))
                .setTelppicdisplay("")
                .setJabatanpic("")
                .setAlamatpic("")
                .setKotapic("")
                .setTelppic("")
                .setFaxpic("")
                .setMailpi("")
                .setCatatan("")
                .setLotnumb("")
                .setALAMAT("")
                .setId_user(prefs.getInt("userId", 0))
                .build();

        return persiapan;
    }

    private void addList(List<Attribute> pv, Spinner spinner) {
        List<String> list = new ArrayList<String>();
        map = new HashMap<>();

        for (int i = 0 ; i < pv.size(); i++) {
            map.put(String.valueOf(pv.get(i).getId_attrdetail())
                    , pv.get(i).getAttributedetail());
            list.add(pv.get(i).getAttributedetail());
        }

        spinner.setAdapter(getAdapter(list));
    }

    private void setAllCaps(){
        setCaps(noPolisi);
        setCaps(stnkAn);
        setCaps(kota);
        setCaps(transmisi);
        setCaps(tahun);
        setCaps(warnaDoc);
        setCaps(noRangka);
        setCaps(noMesin);
        setCaps(noBpkb);
        setCaps(nofaktur);
        setCaps(noStnk);
        setCaps(noKeur);
        setCaps(model);
        setCaps(penggerak);
        setCaps(bahanBakar);
        setCaps(warna);
        setCaps(kunciTambahan);
        setCaps(nama);
        setCaps(noIdentitas);
        setCaps(namaPic);
        setCaps(lokasiAsalUnit);
        setCaps(namaPicDisplay);
    }

    private void getDropdownListWarna(AuctionService auctionService, List<String> ls, AutoCompleteTextView autoComplete){
        if (!autoComplete.getText().toString().equals("")){
            auctionService.getMasterItemWarna(autoComplete.getText().toString()).enqueue(new Callback<List<Attribute>>() {
                @Override
                public void onResponse(Call<List<Attribute>> call, Response<List<Attribute>> response) {
                    List<Attribute> la = response.body();
                    ls.clear();
                    for (int i = 0; i < la.size(); i++) {
                        ls.add(la.get(i).getAttributedetail());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, ls);
                    autoComplete.setAdapter(adapter);
                    autoComplete.setThreshold(1);
                    autoComplete.showDropDown();
                }

                @Override
                public void onFailure(Call<List<Attribute>> call, Throwable t) {
                    errorRetrofit(call, t);
                }
            });
        }
    }

    private void getDropdownListPenitip(AuctionService auctionService, List<String> ls){
        if (!nama.getText().toString().equals("")){
            auctionService.getMasterItemPenitip(nama.getText().toString()).enqueue(new Callback<List<Penitip>>() {
                @Override
                public void onResponse(Call<List<Penitip>> call, Response<List<Penitip>> response) {
                    try {
                        if (!response.body().toString().equals("[]")) {
                            lp = response.body();
                        }
                        ls.clear();
                        try {
                            for (int i = 0; i < lp.size(); i++) {
                                ls.add(lp.get(i).getNama_penitip());
                            }
                        } catch (Exception e) {
                        }
                        ;
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                                android.R.layout.simple_dropdown_item_1line, ls);
                        nama.setAdapter(adapter);
                        nama.setThreshold(1);
                        nama.showDropDown();
                    }catch (Exception e){}
                }

                @Override
                public void onFailure(Call<List<Penitip>> call, Throwable t) {
                    errorRetrofit(call, t);
                }
            });
        }
    }

    private void getPenitip(){
        setVisible(kode_anggotahead);
        setVisible(kode_anggota);
        setVisible(grouphead);
        setVisible(group);
        setVisible(sebagaihead);
        setVisible(sebagai);
        setVisible(perusahaanhead);
        setVisible(perusahaan);
        setVisible(teleponhead);
        setVisible(telepon);
        setVisible(alamathead);
        setVisible(alamat);
        setVisible(kota_penitiphead);
        setVisible(kota_penitip);
        setVisible(kode_poshead);
        setVisible(kode_pos);
        kode_anggota.setText(lp.get(position).getKode_anggota());
        no_npwp.setText(lp.get(position).getNo_npwp());
        noIdentitas.setText(lp.get(position).getNo_identitas());
        tipe.setText(lp.get(position).getTipe_identitas());
        group.setText(lp.get(position).getGroupBiodata());
        if(lp.get(position).getStatus_peserta() == 1){
            sebagai.setText("PENITIP");
        }else {
            sebagai.setText("PESERTA");
        }
        if(lp.get(position).getStatus_biodata() == 1){
            perusahaan.setChecked(true);
        }else {
            perusahaan.setChecked(false);
        }
        teleponStr = lp.get(position).getTelepon();
        ponselStr = lp.get(position).getPonsel();
        if(teleponStr == null){
            teleponStr = "-";
        }
        if(ponselStr == null){
            ponselStr = "-";
        }
        this.telepon.setText(teleponStr+" / "+ponselStr);
        alamat.setText(lp.get(position).getAlamat());
        kota_penitip.setText(lp.get(position).getKota());
        kode_pos.setText(lp.get(position).getKode_pos());
        setSebagai();
    }

    private void setSebagai(){
        if(perusahaan.isChecked()){
            setVisible(no_npwphead);
            setVisible(no_npwp);
            setGone(no_identitashead);
            setGone(noIdentitas);
            setGone(tipehead);
            setGone(tipe);
        }else {
            setVisible(no_identitashead);
            setVisible(noIdentitas);
            setVisible(tipehead);
            setVisible(tipe);
            setGone(no_npwphead);
            setGone(no_npwp);
        }
    }

    private void setRequired(){
        String required = "<font color=#FF0000> *</font>";
        nopol_title.setText(Html.fromHtml(nopol_title.getText() + required));
        merk_title.setText(Html.fromHtml(merk_title.getText() + required));
        seri_title.setText(Html.fromHtml(seri_title.getText() + required));
        silinder_title.setText(Html.fromHtml(silinder_title.getText() + required));
        grade_title.setText(Html.fromHtml(grade_title.getText() + required));
        sub_grade_title.setText(Html.fromHtml(sub_grade_title.getText() + required));
        plat_title.setText(Html.fromHtml(plat_title.getText() + required));
        transmisi_title.setText(Html.fromHtml(transmisi_title.getText() + required));
        tahun_title.setText(Html.fromHtml(tahun_title.getText() + required));
        nama_penitip_title.setText(Html.fromHtml(nama_penitip_title.getText() + required));
    }

//    private void convertToRupiah(EditText editText){
//        String separator = ".";
//        int j = 0;
//        for (int i = editText.length(); i > 0; i--) {
//            j = j + 1;
//            if (((j % 3) == 1) && (j != 1)) {
//                editText.setText(editText.getText().toString().substring(i-1, 1) + separator + editText.getText());
//            }
//        }
//    }

    private String stringVal(EditText item) { // delete unknown symbol
        return item.getText().toString();
    }

    private int stringToInt(EditText item) {
        return Integer.parseInt(item.getText().toString());
    }

    private String spinnerItem(Spinner item){ return item.getSelectedItem().toString(); }

}
