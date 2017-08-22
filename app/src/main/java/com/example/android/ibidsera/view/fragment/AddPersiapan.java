package com.example.android.ibidsera.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.Attribute;
import com.example.android.ibidsera.model.PersiapanPost;
import com.example.android.ibidsera.model.PersiapanValue;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.util.RetrofitUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
    @BindView(R.id.warna_doc) protected EditText warnaDoc;
    @BindView(R.id.lintas) protected CheckBox lintas;
    @BindView(R.id.taksasi_appraiser) protected Spinner taksasiAppraiser;
    @BindView(R.id.no_rangka) protected EditText noRangka;
    @BindView(R.id.no_mesin) protected EditText noMesin;
    @BindView(R.id.no_bpkb) protected EditText noBpkb;
    @BindView(R.id.no_faktur) protected EditText nofaktur;
    @BindView(R.id.no_stnk) protected EditText noStnk;
    @BindView(R.id.stnk_sd) protected EditText stnkSd;
    @BindView(R.id.tgl_keur) protected DatePicker tglKeur;
    @BindView(R.id.model) protected EditText model;
    @BindView(R.id.penggerak) protected EditText penggerak;
    @BindView(R.id.bahan_bakar) protected EditText bahanBakar;
    @BindView(R.id.km) protected EditText km;
    @BindView(R.id.warna) protected EditText warna;
    @BindView(R.id.kunci_tambahan) protected EditText kunciTambahan;
    @BindView(R.id.biaya_administrasi) protected EditText biayaAdministrasi;
    @BindView(R.id.biaya_parkir) protected EditText biayaParkir;
    @BindView(R.id.nama) protected EditText nama;
    @BindView(R.id.no_identitas) protected EditText noIdentitas;
    @BindView(R.id.perusahaan) protected CheckBox perusahaan;
    @BindView(R.id.nama_pic) protected EditText namaPic;
    @BindView(R.id.ponsel) protected EditText ponsel;
    @BindView(R.id.lokasi_asal_unit) protected EditText lokasiAsalUnit;
    @BindView(R.id.btn_save) protected Button save;
    @BindView(R.id.btn_cancel) protected Button cancel;
    private ProgressDialog pDialog;
    private AuctionService auctionService;
    private PersiapanPost persiapanPost;
    private HashMap<String, String> map;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragment = inflater.inflate(R.layout.content_addp, container, false);
        ButterKnife.bind(this, myFragment);
        pDialog = new ProgressDialog(getContext());

        auctionService = RetrofitUtil.getAuctionService();
        auctionService.getAddPersiapan().enqueue(new Callback<PersiapanValue>() {

            @Override
            public void onResponse(Call<PersiapanValue> call, Response<PersiapanValue> response) {
                PersiapanValue pv = response.body();
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

        save.setOnClickListener(view -> {
            pDialog.setMessage("Sending Data..");
            pDialog.show();

            HashMap<String, EditText> h = new HashMap<>();
            h.put("NO POLISI", noPolisi);
            h.put("Transmisi", transmisi);
            h.put("Tahun", tahun);
            h.put("Nama", nama);
            h.put("Ponsel", ponsel);

            HashMap<String, Spinner> hs = new HashMap<>();
            hs.put("Merk", merk);
            hs.put("Seri", seri);
            hs.put("Silinder", silinder);
            hs.put("Grade", grade);
            hs.put("Sub Grade", subGrade);

            List<String> ls2 = required(h);
            List<String> lsSpinner = requiredSpinner(hs);

            if(ls2.size() <= 0 || lsSpinner.size() <= 0) {

                persiapanPost = getDataView();
                auctionService.insertUnit(persiapanPost).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        String log = response.body();
                        Log.d("POST", log);

                        final Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            pDialog.hide();
                            Toast.makeText(getContext(), "Successfull Send Data", Toast.LENGTH_SHORT).show();
                            getActivity().getSupportFragmentManager().popBackStack();
                        }, 5000);

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Log.e("GAGAL POST", t.getMessage());
                        pDialog.hide();
                    }
                });

            }else{
                pDialog.hide();
                alertLogic(ls2);
                alertLogic(lsSpinner);
            }

        });
        cancelListener(cancel);

        return myFragment;
    }

    private PersiapanPost getDataView() {

        PersiapanPost persiapan = persiapan()
                .setCabangtaksasi(spinnerItem(taksasiAppraiser))
                .setWARNA_DOC(stringVal(warnaDoc))
                .setWARNA(stringVal(warna))
                .setBiayadmin(stringToInt(biayaAdministrasi))
                .setBiayaparkir(stringToInt(biayaParkir))
                .setNamapenitip(stringVal(nama))
                .setNmridpenitip("")
                .setNonpwp("")
                .setGroupbiodata("")
                .setStatuspeserta(false)
                .setJenisusaha("")
                .setSebagaiperusahaan(perusahaan.isChecked())
                .setTeleponpenitip("")
                .setPonselpenitip("")
                .setAlamatpenitip("")
                .setKotapenitip("")
                .setKodepospenitip("")
                .setIdbiodata("")
                .setJadwalelang(intToDate(tglKeur))
                .setTglelang(intToDate(tglKeur))
                .setCabanglelang("")
                .setLokasilelang("")
                .setIkutseselelang(false)
                .setLokasibrglelang("")
                .setAlamatbrglelang("")
                .setKotabrglelang("")
                .setNamapicdisplay("")
                .setLokasidisplay("")
                .setAlamatdisplay("")
                .setKotadisplay("")
                .setTelppicdisplay("")
                .setNamapic("")
                .setJabatanpic("")
                .setAlamatpic("")
                .setKotapic("")
                .setTelppic("")
                .setPonselpic("0918326")
                .setFaxpic("")
                .setMailpi("")
                .setCatatan("")
                .setSTNK_SD("")
                .setTGL_KEUR(intToDate(tglKeur))
                .setLotnumb("")
                .setNo_polisi(stringVal(noPolisi))
                .setNo_rangka(stringVal(noRangka))
                .setSTNK_AN(stringVal(stnkAn))
                .setNO_MESIN(stringVal(noMesin))
                .setALAMAT("")
                .setNO_BPKB(stringVal(noBpkb))
                .setKOTA(stringVal(kota))
                .setNO_FAKTUR(stringVal(nofaktur))
                .setMERK(spinnerItem(merk))
                .setNO_STNK(stringVal(noStnk))
                .setSERI("")
                .setSILINDER("")
                .setNO_KEUR("")
                .setGRADE("")
                .setSUB_GRADE(spinnerItem(subGrade))
                .setMODEL(stringVal(model))
                .setPLAT_DASAR(spinnerItem(platDasar))
                .setPENGGERAK(stringVal(penggerak))
                .setTRANSMISI("manual")
                .setBAHAN_BAKAR(stringVal(bahanBakar))
                .setTAHUN(stringVal(tahun))
                .setKM(stringVal(km))
                .setSUMBER(spinnerItem(sumber))
                .setKUNCI_TAMBAHAN(stringVal(kunciTambahan))
                .setId_user(6)
                .build();

        return persiapan;
    }

    private void addList(List<Attribute> pv, Spinner spinner) {
        List<String> list = new ArrayList<String>();
        map = new HashMap<String, String>();

        for (int i = 0 ; i < pv.size(); i++) {
            map.put(String.valueOf(pv.get(i).getId_attrdetail())
                    , pv.get(i).getAttributedetail());
            list.add(pv.get(i).getAttributedetail());
        }

        spinner.setAdapter(getAdapter(list));
    }

    private Date intToDate(DatePicker value) {

        int day = value.getDayOfMonth();
        int month = value.getMonth();
        int year =  value.getYear();

        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        return calendar.getTime();
    }

    private String stringVal(EditText item) { // delete unknown symbol
        return item.getText().toString();
    }

    private int stringToInt(EditText item) {
        return Integer.parseInt(item.getText().toString());
    }

    private String spinnerItem(Spinner item){ return item.getSelectedItem().toString(); }

}
