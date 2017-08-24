package com.example.android.ibidsera.view.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    @BindView(R.id.no_keur) protected EditText noKeur;
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
    @BindView(R.id.ponsel_pic) protected EditText ponselPic;
    @BindView(R.id.lokasi_asal_unit) protected EditText lokasiAsalUnit;
    @BindView(R.id.nama_pic_display) protected  EditText namaPicDisplay;
    @BindView(R.id.btn_save) protected Button save;
    @BindView(R.id.btn_cancel) protected Button cancel;
    private ProgressDialog pDialog;
    private AuctionService auctionService;
    private PersiapanPost persiapanPost;
    private HashMap<String, String> map;
    private PersiapanValue pv;

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

        save.setOnClickListener(view -> {
            pDialog.setMessage("Sending Data..");
            pDialog.show();

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

            List<String> ls2 = required(h);
            List<String> lsSpinner = requiredSpinner(hs);

            if(ls2.size() <= 0 || lsSpinner.size() <= 0) {

                persiapanPost = getDataView();
                final Handler handler = new Handler();
                handler.postDelayed(() -> {

                    auctionService.insertUnit(persiapanPost).enqueue(new Callback<String>() {

                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String log = response.body();

                            pDialog.hide();
                            Toast.makeText(getContext(), log, Toast.LENGTH_LONG).show();
                            getActivity().getSupportFragmentManager().popBackStack();
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            errorRetrofit(call,t);
                            pDialog.hide();
                        }
                    });
                }, 3000);

            }else{
                pDialog.hide();
                alertLogic(ls2);
                alertLogic(lsSpinner);
            }

        });

    }

    private PersiapanPost getDataView() {

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
                .setTGL_KEUR(intToDate(tglKeur))
                .setMODEL(stringVal(model))
                .setPENGGERAK(stringVal(penggerak))
                .setBAHAN_BAKAR(stringVal(bahanBakar))
                .setKM(stringVal(km))
                .setWARNA(stringVal(warna))
                .setKUNCI_TAMBAHAN(stringVal(kunciTambahan))
                .setBiayadmin(stringToInt(biayaAdministrasi))
                .setBiayaparkir(stringToInt(biayaParkir))

                .setNamapenitip(stringVal(nama))
                .setNmridpenitip(stringVal(noIdentitas))
                .setSebagaiperusahaan(perusahaan.isChecked())

                .setNamapic(stringVal(namaPic))
                .setPonselpic(stringVal(ponselPic))
                .setLokasibrglelang(stringVal(lokasiAsalUnit))
                .setNamapicdisplay(stringVal(namaPicDisplay))

                .setNonpwp("")
                .setGroupbiodata("")
                .setStatuspeserta(false)
                .setJenisusaha("")
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
