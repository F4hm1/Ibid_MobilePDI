package com.example.android.ibidsera.view.fragment;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
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
    private int id_pemeriksaan = 0;
    private int id_auction = 0;
    HashMap<String, CheckBox> h = new HashMap<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragment = inflater.inflate(R.layout.content_addm, container, false);
        ButterKnife.bind(this, myFragment);

        hideKeyboard();
        setAllCaps();
        setAllDisabled();

        int id = -1;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }

        cpvStart(cpv, bp);
        if(id!=-1){
            getAddm(StaticUnit.getLu(), id);
        }
        cpvStop(cpv, bp);

        checkAllListener(checkBoxB, "b", size, h);
        checkAllListener(checkBoxR, "r", size, h);
        checkAllListener(checkBoxT, "t", size, h);

        List<String> ls = new ArrayList<>();
        AuctionService auctionService = RetrofitUtil.getAuctionService();

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
            h.put("nama pengemudi", nama_pengemudi);

            List<String> ls2 = required(h);
            if(ls2.size() <= 0) {

                String post = RetrofitUtil.toJson(setInsertUnit());
                Log.i("isi", post);
                auctionService.createInsertUnit(post).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {
                            Log.i("info", "post submitted to API." + response.body());
                            alertDialog("Proses Penambahan Pemeriksaan Item Masuk Berhasil", 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        errorRetrofit(call, t);
                    }
                });

            }else {
                String required = "";
                for (int i = 0; i <= ls2.size() - 1; i++) {
                    if (i == ls2.size() - 1) {
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
        Calendar c = Calendar.getInstance();

        id_pemeriksaan = lu.get(id).getAuction().getId_pemeriksaanitem();
        id_auction = lu.get(id).getAuction().getId_auctionitem();
        size = lu.get(id).getKomponen().size();
        nopol.setText(lu.get(id).getAuction().getNo_polisi());
        merk.setAdapter(getAdapterList(lu.get(id).getNama_merk()));
        seri.setAdapter(getAdapterList(lu.get(id).getTipe().get(0)));
        silinder.setAdapter(getAdapterList(lu.get(id).getTipe().get(1)));
        grade.setAdapter(getAdapterList(lu.get(id).getTipe().get(2)));
        sub_grade.setAdapter(getAdapterList(lu.get(id).getTipe().get(3)));
        transmisi.setText(lu.get(id).getTransmisi());
        tahun.setText(lu.get(id).getTahun());
//        km.setText(String.valueOf(lu.get(id).getKm()));
        nama_pemilik.setText(lu.get(id).getPntp().getName_pntp());

        fuel.setAdapter(setDropdown(R.array.fuel));
        cat.setAdapter(setDropdown(R.array.cat));

        datePicker(tgl_pemeriksaan);

        jam.setAdapter(setDropdown(R.array.jam));

        int jam_now = c.get(Calendar.HOUR_OF_DAY);
        jam.setSelection(jam_now);

        menit.setAdapter(setDropdown(R.array.menit));

        int menit_now = c.get(Calendar.MINUTE);
        menit.setSelection(menit_now);

        nama_pengemudi.setText(lu.get(id).getAuction().getNama_pengemudi_msk());
        alamat_pengemudi.setText(lu.get(id).getAuction().getAlamat_pengemudi_msk());
        kota.setText(lu.get(id).getAuction().getKota_msk());
        telepon.setText(lu.get(id).getAuction().getTelepon_msk());
        catatan.setText(lu.get(id).getAuction().getCatatan());
        pool.setText(lu.get(id).getAuction().getPoolkota());
        cases.setText(lu.get(id).getAuction().getCases());

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
            textStyle(nama, row2, param7, lu.get(id).getKomponen().get(i).getNama());
            checkboxStyle(b, row2, param1, i, "b", h);
            checkboxStyle(r, row2, param1, i, "r", h);
            checkboxStyle(t, row2, param1, i, "t", h);
            tl2.addView(row2);
            tl2.setLayoutParams(param3);
            row.addView(tl2);
            tl.addView(row);
        }
    }

    public ArrayAdapter<String> getAdapterList(String value){
        List<String> list = new ArrayList<>();
        list.add(value);
        return getAdapter(list);
    }

    public InsertUnit setInsertUnit(){
        InsertUnit insertUnit = new InsertUnit();
        insertUnit.setIdpemeriksaanitem(id_pemeriksaan);
        insertUnit.setIdauctionitem(id_auction);
        insertUnit.setBataskomponen(size);
        insertUnit.setNopolisi(String.valueOf(nopol.getText()));
        insertUnit.setFuel(fuel.getSelectedItem().toString());
        insertUnit.setCatbody(cat.getSelectedItem().toString());
        insertUnit.setTglpemeriksaan(String.valueOf(tgl_pemeriksaan.getText()));
        insertUnit.setJampemeriksaan(jam.getSelectedItem().toString());
        insertUnit.setMenitpemeriksaan(menit.getSelectedItem().toString());
        insertUnit.setNamapengemudi(String.valueOf(nama_pengemudi.getText()));
        insertUnit.setAlamatpengemudi(String.valueOf(alamat_pengemudi.getText()));
        insertUnit.setKotapengemudi(String.valueOf(kota.getText()));
        insertUnit.setTeleponpengemudi(String.valueOf(telepon.getText()));
        List<Boolean> lb = new ArrayList<>();
        List<Boolean> lr = new ArrayList<>();
        List<Boolean> lt = new ArrayList<>();
        List<Integer> lidKomponen = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            CheckBox b = h.get("b"+i);
            CheckBox r = h.get("r"+i);
            CheckBox t = h.get("t"+i);
            lb.add(b.isChecked());
            lr.add(r.isChecked());
            lt.add(t.isChecked());
            lidKomponen.add(i+1);
        }
        insertUnit.setCektampilkanbaik(lb);
        insertUnit.setCektampilkanrusak(lr);
        insertUnit.setCektampilkantidakada(lt);
        insertUnit.setIdkomponenpemeriksaan(lidKomponen);
        insertUnit.setCatatan(String.valueOf(catatan.getText()));
        return insertUnit;
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
}
