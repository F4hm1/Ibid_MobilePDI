package com.example.android.ibidsera.view.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.InsertUnit;
import com.example.android.ibidsera.model.StaticUnit;
import com.example.android.ibidsera.model.Unit;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddMasuk extends BaseFragment{

    @BindView(R.id.table_addm) TableLayout tl;
    @BindView(R.id.progress_view) CircularProgressView cpv;
    @BindView(R.id.background_progress) RelativeLayout bp;
    @BindView(R.id.nopol) AutoCompleteTextView nopol;
    @BindView(R.id.merk) Spinner merk;
    @BindView(R.id.seri) Spinner seri;
    @BindView(R.id.silinder) Spinner silinder;
    @BindView(R.id.grade) Spinner grade;
    @BindView(R.id.sub_grade) Spinner sub_grade;
    @BindView(R.id.transmisi) EditText transmisi;
    @BindView(R.id.tahun) EditText tahun;
    @BindView(R.id.km) EditText km;
    @BindView(R.id.nama_pemilik) EditText nama_pemilik;
    @BindView(R.id.fuel) Spinner fuel;
    @BindView(R.id.cat) Spinner cat;
    @BindView(R.id.tgl_pemeriksaan) EditText tgl_pemeriksaan;
    @BindView(R.id.jam) Spinner jam;
    @BindView(R.id.menit) Spinner menit;
    @BindView(R.id.nama_pengemudi) EditText nama_pengemudi;
    @BindView(R.id.alamat_pengemudi) EditText alamat_pengemudi;
    @BindView(R.id.kota) EditText kota;
    @BindView(R.id.telepon) EditText telepon;
    @BindView(R.id.catatan) EditText catatan;
    @BindView(R.id.pool) EditText pool;
    @BindView(R.id.cases) EditText cases;
    @BindView(R.id.cancel) Button cancel;
    @BindView(R.id.save) Button save;
    @BindView(R.id.checkboxB) CheckBox checkBoxB;
    @BindView(R.id.checkboxR) CheckBox checkBoxR;
    @BindView(R.id.checkboxT) CheckBox checkBoxT;
    private int size = 0;
    private List<Unit> lUnit = new ArrayList<>();
    private int position = -1;
    HashMap<String, CheckBox> h = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragment = inflater.inflate(R.layout.content_addm, container, false);
        ButterKnife.bind(this, myFragment);

        AuctionService auctionService = RetrofitUtil.getAuctionService();
        List<String> ls = new ArrayList<>();

        hideKeyboard();
        setAllCaps();
        setAllDisabled();

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            position = bundle.getInt("id");
        }

        cpvStart(cpv, bp);
        if(position!=-1){
            getAddm(StaticUnit.getLu(), position);
        }

        datePicker(tgl_pemeriksaan);
        getTimeSpinner();
        getKomponen(auctionService);
        cpvStop(cpv, bp);

        nopol.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                nopol.dismissDropDown();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getDropdownList(auctionService, ls);
            }
        });

        nopol.setOnItemClickListener((parent, view, position, id1) -> {
            cpvStart(cpv, bp);
            getAddm(StaticUnit.getLu(), position);
            cpvStop(cpv, bp);
        });

        save.setOnClickListener(v -> {

            HashMap<String, EditText> h = new HashMap<>();
            h.put("NO POLISI", nopol);
            h.put("Nama Pengemudi", nama_pengemudi);
            h.put("Alamat Pengemudi", alamat_pengemudi);
            h.put("Kota", kota);
            h.put("Telp", telepon);

            List<String> ls2 = required(h);
            if(ls2.size() <= 0) {
                auctionService.insertUnitMasuk(setInsertUnit()).enqueue(new Callback<InsertUnit>() {
                    @Override
                    public void onResponse(Call<InsertUnit> call, Response<InsertUnit> response) {
                        Log.i("info", "post submitted to API." + response.body());
                        alertDialog("Proses Penambahan Pemeriksaan Unit Masuk Berhasil", 1);
                    }

                    @Override
                    public void onFailure(Call<InsertUnit> call, Throwable t) {
                        errorRetrofit(call, t);
                    }
                });
            }else {
                String required = "";
                for (int i = ls2.size()-1; i >= 0; i--) {
                    if (i == 0) {
                        required = required + ls2.get(i);
                    } else
                        required = required + ls2.get(i) + ", ";
                }
                alertDialog("Maaf " + required + " belum anda masukan !!", 2);
            }
        });
        cancelListener(cancel);

        return myFragment;
    }

    public void getAddm(List<Unit> lu, int id) {
        lUnit = lu;
        position = id;
        nopol.setText(lu.get(id).getAuction().getNo_polisi());
        merk.setAdapter(getAdapterList(lu.get(id).getNama_merk()));
        seri.setAdapter(getAdapterList(lu.get(id).getTipe().get(0).getAttributedetail()));
        silinder.setAdapter(getAdapterList(lu.get(id).getTipe().get(1).getAttributedetail()));
        grade.setAdapter(getAdapterList(lu.get(id).getTipe().get(2).getAttributedetail()));
        sub_grade.setAdapter(getAdapterList(lu.get(id).getTipe().get(3).getAttributedetail()));
        transmisi.setText(lu.get(id).getTransmisi());
        tahun.setText(lu.get(id).getTahun());
        km.setText(String.valueOf(lu.get(id).getKm()));
        nama_pemilik.setText(lu.get(id).getPntp().getName_pntp());

        fuel.setAdapter(setDropdown(R.array.fuel));
        cat.setAdapter(setDropdown(R.array.cat));

        nama_pengemudi.setText(lu.get(id).getAuction().getNama_pengemudi_msk());
        alamat_pengemudi.setText(lu.get(id).getAuction().getAlamat_pengemudi_msk());
        kota.setText(lu.get(id).getAuction().getKota_msk());
        telepon.setText(lu.get(id).getAuction().getTelepon_msk());
        catatan.setText(lu.get(id).getAuction().getCatatan());
        pool.setText(lu.get(id).getAuction().getPoolkota());
        cases.setText(lu.get(id).getAuction().getCases());
    }

    public ArrayAdapter<String> getAdapterList(String value){
        List<String> list = new ArrayList<>();
        list.add(value);
        return getAdapter(list);
    }

    public InsertUnit setInsertUnit(){
        InsertUnit insertUnit = new InsertUnit();
        insertUnit.setIdpemeriksaanitem(lUnit.get(position).getAuction().getId_pemeriksaanitem());
        insertUnit.setIdauctionitem(lUnit.get(position).getAuction().getIdauction_item());
        insertUnit.setBataskomponen(size);
        insertUnit.setNopolisi(String.valueOf(nopol.getText()));
        insertUnit.setMERK(lUnit.get(position).getId_merk());
        insertUnit.setSERI(String.valueOf(lUnit.get(position).getTipe().get(0).getId_attrdetail()));
        insertUnit.setSILINDER(String.valueOf(lUnit.get(position).getTipe().get(1).getId_attrdetail()));
        insertUnit.setGRADE(String.valueOf(lUnit.get(position).getTipe().get(2).getId_attrdetail()));
        insertUnit.setSUB_GRADE(String.valueOf(lUnit.get(position).getTipe().get(3).getId_attrdetail()));
        insertUnit.setTRANSMISI(String.valueOf(transmisi.getText()));
        insertUnit.setKM(String.valueOf(km.getText()));
        insertUnit.setFuel(fuel.getSelectedItem().toString());
        insertUnit.setCatbody(cat.getSelectedItem().toString());
        insertUnit.setTglpemeriksaan(String.valueOf(tgl_pemeriksaan.getText()));
        insertUnit.setJampemeriksaan(jam.getSelectedItem().toString());
        insertUnit.setMenitpemeriksaan(menit.getSelectedItem().toString());
        insertUnit.setNamapengemudi(String.valueOf(nama_pengemudi.getText()));
        insertUnit.setAlamatpengemudi(String.valueOf(alamat_pengemudi.getText()));
        insertUnit.setKotapengemudi(String.valueOf(kota.getText()));
        insertUnit.setTeleponpengemudi(String.valueOf(telepon.getText()));
        List<Integer> lb = new ArrayList<>();
        List<Integer> lr = new ArrayList<>();
        List<Integer> lt = new ArrayList<>();
        List<Integer> lidKomponen = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            CheckBox b = h.get("b"+i);
            CheckBox r = h.get("r"+i);
            CheckBox t = h.get("t"+i);
            lb.add(isChecked(b));
            lr.add(isChecked(r));
            lt.add(isChecked(t));
            lidKomponen.add(i+1);
        }
        insertUnit.setCektampilkanbaik(lb);
        insertUnit.setCektampilkanrusak(lr);
        insertUnit.setCektampilkantidakada(lt);
        insertUnit.setIdkomponenpemeriksaan(lidKomponen);
        insertUnit.setCatatan(String.valueOf(catatan.getText()));
        insertUnit.setCases(String.valueOf(cases.getText()));
        insertUnit.setPoolkota(String.valueOf(pool.getText()));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        insertUnit.setWEBID_LOGGED_IN(prefs.getInt("userId", 0));
        return insertUnit;
    }

    private int isChecked(CheckBox checkBox){
        if(checkBox.isChecked()){
            return 1;
        } else {
            return 0;
        }
    }

    private void setAllDisabled(){
        setDisabled(merk);
        setDisabled(seri);
        setDisabled(silinder);
        setDisabled(grade);
        setDisabled(sub_grade);
        setDisabled(transmisi);
        setDisabled(tahun);
        setDisabled(nama_pemilik);
    }

    private void setAllCaps(){
        setCaps(nopol);
        setCaps(alamat_pengemudi);
        setCaps(nama_pengemudi);
        setCaps(kota);
        setCaps(telepon);
        setCaps(catatan);
        setCaps(pool);
        setCaps(cases);
    }

    private void getDropdownList(AuctionService auctionService, List<String> ls){
        if (!nopol.getText().toString().equals("")){
            auctionService.getSearchPersiapan(nopol.getText().toString()).enqueue(new Callback<List<Unit>>() {
                @Override
                public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                    List<Unit> lu = response.body();
                    StaticUnit.setLu(lu);
                    ls.clear();
                    for (int i = 0; i < lu.size(); i++) {
                        ls.add(lu.get(i).getAuction().getNo_polisi());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                            android.R.layout.simple_dropdown_item_1line, ls);
                    nopol.setAdapter(adapter);
                    nopol.setThreshold(1);
                    nopol.showDropDown();
                }

                @Override
                public void onFailure(Call<List<Unit>> call, Throwable t) {
                    errorRetrofit(call, t);
                }
            });
        }
    }

    private void getTimeSpinner(){
        Calendar c = Calendar.getInstance();
        jam.setAdapter(setDropdown(R.array.jam));

        int jam_now = c.get(Calendar.HOUR_OF_DAY);
        jam.setSelection(jam_now);

        menit.setAdapter(setDropdown(R.array.menit));

        int menit_now = c.get(Calendar.MINUTE);
        menit.setSelection(menit_now);
    }

    private void getKomponen(AuctionService auctionService){
        auctionService.getPersiapan().enqueue(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                List<Unit> lu = response.body();
                getKomponenList(lu);
            }

            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });
    }

    private void getKomponenList(List<Unit> lu){
        size = lu.get(0).getKomponen().size();
        for (int i = 0; i < size; i++) {
            TableRow row = tableRow();
            TableLayout tl2 = tableLayout();
            TableRow row2 = tableRow();
            TextView no = textView();
            TextView nama = textView();
            CheckBox b = checkBox();
            CheckBox r = checkBox();
            CheckBox t = checkBox();

            TableRow.LayoutParams param_25 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, .25f);
            TableRow.LayoutParams param7 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 7f);
            TableRow.LayoutParams param3 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 3f);
            TableRow.LayoutParams param1 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);

            rowColor(row, i);
            textStyle(no, row, param_25, String.valueOf(i+1));
            textStyle(nama, row2, param7, lu.get(0).getKomponen().get(i).getNama());
            checkboxStyle(b, row2, param1, i, "b", h);
            checkboxStyle(r, row2, param1, i, "r", h);
            checkboxStyle(t, row2, param1, i, "t", h);
            tl2.addView(row2);
            tl2.setLayoutParams(param3);
            row.addView(tl2);
            tl.addView(row);
        }
        checkAllListener(checkBoxB, "b", size, h);
        checkAllListener(checkBoxR, "r", size, h);
        checkAllListener(checkBoxT, "t", size, h);
    }
}
