package com.example.android.ibidsera.view.fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.GetStatus;
import com.example.android.ibidsera.model.InsertUnit;
import com.example.android.ibidsera.model.Sign;
import com.example.android.ibidsera.model.SignValue;
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

/**
 * Created by Yosefricaro on 24/07/2017.
 */

public class AddKeluar extends BaseFragment{
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
    @BindView(R.id.cancel) Button cancel;
    @BindView(R.id.save) Button save;
    @BindView(R.id.checkboxB) CheckBox checkBoxB;
    @BindView(R.id.checkboxR) CheckBox checkBoxR;
    @BindView(R.id.checkboxT) CheckBox checkBoxT;
    @BindView(R.id.signature1) ImageView signature1;
    @BindView(R.id.signature2) ImageView signature2;
    private int size = 0;
    private int position = -1;
    HashMap<String, CheckBox> h = new HashMap<>();
    HashMap<String, ImageView> hi = new HashMap<>();
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragment = inflater.inflate(R.layout.content_addk, container, false);
        ButterKnife.bind(this, myFragment);

        cpvStart(cpv, bp);

        AuctionService auctionService = RetrofitUtil.getAuctionService();
        List<String> ls = new ArrayList<>();
        ProgressDialog pDialog = new ProgressDialog(getContext());

        hideKeyboard();
        setAllCaps();
        setAllDisabled();

        datePicker(tgl_pemeriksaan, 0);
        getTimeSpinner();
        getKomponen(auctionService, -1);
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
            hideKeyboard();
            cpvStart(cpv, bp);
            getAddk(StaticUnit.getLu().get(position), position);
            cpvStop(cpv, bp);
        });

        signatureClick(signature1, 1);
        signatureClick(signature2, 2);

        save.setOnClickListener(v -> {
            pDialog.setMessage("Sending Data..");
            pDialog.show();
            HashMap<String, EditText> h = new HashMap<>();
            h.put("NO POLISI", nopol);
            h.put("Nama Pengemudi", nama_pengemudi);
            h.put("Alamat Pengemudi", alamat_pengemudi);
            h.put("Kota", kota);
            h.put("Telp", telepon);

            List<String> ls2 = required(h);
            if(ls2.size() <= 0) {
                final Handler handler = new Handler();
                handler.postDelayed(() -> {
                    auctionService.insertUnitKeluar(setInsertUnit(StaticUnit.getUnit())).enqueue(new Callback<GetStatus>() {
                        @Override
                        public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                            GetStatus getStatus = response.body();
                            Log.i("info", "post submitted to API." + response.body());
                            try {
                                if (getStatus.getStatus() == 200 && getStatus.getId_pemeriksaan_item() != 0) {
                                    postSignature(getStatus, auctionService, pDialog);
                                }else {
                                    pDialog.hide();
                                    alertDialog(getStatus.getMessage(), 1);
                                }
                            }catch (Exception e){}
                        }

                        @Override
                        public void onFailure(Call<GetStatus> call, Throwable t) {
                            pDialog.hide();
                            errorRetrofit(call, t);
                        }
                    });
                }, 2000);
            }else {
                pDialog.hide();
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

    public void getAddk(Unit lu, int id) {
        position = id;
        StaticUnit.setUnit(lu);
        if (lu.getAuction().getValue() != null) {
            nopol.setText(lu.getAuction().getValue());
        }else{
            nopol.setText(lu.getAuction().getNo_polisi());
        }
        merk.setAdapter(getAdapterList(lu.getNama_merk()));
        seri.setAdapter(getAdapterList(lu.getTipe().get(0).getAttributedetail()));
        silinder.setAdapter(getAdapterList(lu.getTipe().get(1).getAttributedetail()));
        grade.setAdapter(getAdapterList(lu.getTipe().get(2).getAttributedetail()));
        sub_grade.setAdapter(getAdapterList(lu.getTipe().get(3).getAttributedetail()));
        transmisi.setText(lu.getTransmisi());
        tahun.setText(lu.getTahun());
        km.setText(String.valueOf(lu.getKm()));
        nama_pemilik.setText(lu.getPntp().getName_pntp());

        fuel.setAdapter(setDropdown(R.array.fuel));
        cat.setAdapter(setDropdown(R.array.cat));

        nama_pengemudi.setText(lu.getAuction().getNama_pengemudi_klr());
        alamat_pengemudi.setText(lu.getAuction().getAlamat_pengemudi_klr());
        kota.setText(lu.getAuction().getKota_klr());
        telepon.setText(lu.getAuction().getTelepon_klr());
        catatan.setText(lu.getAuction().getCatatan_klr());
        if(bitmap1 != null){
            signature1.setImageBitmap(bitmap1);
        } else if(bitmap2 != null){
            signature2.setImageBitmap(bitmap2);
        }
        for (int i = 0; i < size; i++) {
            imgSet(hi.get("b"+i), lu.getKomponen().get(i).getTampil_b());
            imgSet(hi.get("r"+i), lu.getKomponen().get(i).getTampil_r());
            imgSet(hi.get("t"+i), lu.getKomponen().get(i).getTampil_t());
        }
    }

    public ArrayAdapter<String> getAdapterList(String value){
        List<String> list = new ArrayList<>();
        list.add(value);
        return getAdapter(list);
    }

    public InsertUnit setInsertUnit(Unit lUnit){
        InsertUnit insertUnit = new InsertUnit();
        position = 0;
        insertUnit.setIdpemeriksaanitem(lUnit.getId_pemeriksaanitem());
        if(lUnit.getAuction().getId_auctionitem() != 0){
            insertUnit.setIdauctionitem(lUnit.getAuction().getId_auctionitem());
        }else{
            insertUnit.setIdauctionitem(lUnit.getAuction().getIdauction_item());
        }
        insertUnit.setBataskomponen(size);
        insertUnit.setNopolisi(String.valueOf(nopol.getText()));
        insertUnit.setMERK(lUnit.getId_merk());
        insertUnit.setSERI(String.valueOf(lUnit.getTipe().get(0).getId_attrdetail()));
        insertUnit.setSILINDER(String.valueOf(lUnit.getTipe().get(1).getId_attrdetail()));
        insertUnit.setGRADE(String.valueOf(lUnit.getTipe().get(2).getId_attrdetail()));
        insertUnit.setSUB_GRADE(String.valueOf(lUnit.getTipe().get(3).getId_attrdetail()));
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
        List<String> lb = new ArrayList<>();
        List<String> lr = new ArrayList<>();
        List<String> lt = new ArrayList<>();
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
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        insertUnit.setWEBID_LOGGED_IN(prefs.getInt("userId", 0));

        return insertUnit;
    }

    private String isChecked(CheckBox checkBox){
        if(checkBox.isChecked()) return "true";
        else return "false";
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
        setCaps(catatan);
    }

    private void getDropdownList(AuctionService auctionService, List<String> ls){
        if (!nopol.getText().toString().equals("")){
            auctionService.getAutoUnitk(nopol.getText().toString()).enqueue(new Callback<List<Unit>>() {
                @Override
                public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                    List<Unit> lu = response.body();
                    StaticUnit.setLu(lu);
                    ls.clear();
                    for (int i = 0; i < lu.size(); i++) {
                        ls.add(lu.get(i).getAuction().getValue());
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

    private void getKomponen(AuctionService auctionService, int position){
        auctionService.getUnitK().enqueue(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                List<Unit> lu = response.body();
                getKomponenList(lu, 0);
            }

            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });
    }

    private void getKomponenList(List<Unit> lu, int position){
        size = lu.get(position).getKomponen().size();
        for (int i = 0; i < size; i++) {
            TableRow row = tableRow();
            TableLayout tl2 = tableLayout();
            TableRow row2 = tableRow();
            TextView no = textView();
            TextView nama = textView();
            CheckBox b = checkBox();
            CheckBox r = checkBox();
            CheckBox t = checkBox();
            ImageView b_in = imageView();
            ImageView r_in = imageView();
            ImageView t_in = imageView();
            hi.put("b"+i, b_in);
            hi.put("r"+i, r_in);
            hi.put("t"+i, t_in);

            TableRow.LayoutParams param_25 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, .25f);
            TableRow.LayoutParams param5 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 5f);
            TableRow.LayoutParams param3 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 3f);
            TableRow.LayoutParams param1 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams paramCheck = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            paramCheck.gravity = Gravity.CENTER;

            rowColor(row, i);
            textStyle(no, row, param_25, String.valueOf(i+1));
            textStyle(nama, row2, param5, lu.get(position).getKomponen().get(i).getNama());
            checkboxStyle(b, row2, param1, i, "b", h);
            checkboxStyle(r, row2, param1, i, "r", h);
            checkboxStyle(t, row2, param1, i, "t", h);
            imgStyle(b_in, row2, paramCheck);
            imgStyle(r_in, row2, paramCheck);
            imgStyle(t_in, row2, paramCheck);
            tl2.addView(row2);
            tl2.setLayoutParams(param3);
            row.addView(tl2);
            tl.addView(row);
        }
        checkAllListener(checkBoxB, "b", size, h);
        checkAllListener(checkBoxR, "r", size, h);
        checkAllListener(checkBoxT, "t", size, h);
    }

    private void signatureClick(ImageView imageView, int id){
        imageView.setOnClickListener(v -> signatureDialog(id));
    }

    private void signatureDialog(int id){
        AlertDialog.Builder alertDialog  = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Signature Here");
        alertDialog.setCancelable(true);

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout container = new FrameLayout(getContext());
        container.setBackgroundDrawable(getResources().getDrawable(R.drawable.canvas_style));
        Signature mSignature = new Signature(getContext(), null, container);
        container.addView(mSignature, ViewGroup.LayoutParams.MATCH_PARENT, 300);
        linearLayout.addView(container);
        Button reset = new Button(getContext());
        reset.setText("Clear Canvas");
        reset.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_style));
        reset.setTextColor(getResources().getColor(R.color.colorPrimary));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(16,16,16,16);
        reset.setLayoutParams(params);
        reset.setOnClickListener(v -> {
            mSignature.clear();
        });
        linearLayout.addView(reset);
        alertDialog.setView(linearLayout);
        // Set up the buttons
        alertDialog.setPositiveButton("Save Signature", (dialog, which) -> {
            container.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(container.getWidth(), container.getHeight(), Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            container.draw(canvas);
            if(id == 1){
                signature1.setImageBitmap(bitmap);
                bitmap1 = bitmap;
            }else{
                signature2.setImageBitmap(bitmap);
                bitmap2 = bitmap;
            }
        });
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> mSignature.clear());
        alertDialog.create().show();
    }

    private void postSignature(GetStatus gs, AuctionService auctionService, ProgressDialog pDialog){
        auctionService.postSignKeluar(setSignature(StaticUnit.getUnit(), gs)).enqueue(new Callback<SignValue>() {
            @Override
            public void onResponse(Call<SignValue> call, Response<SignValue> response) {
                Log.i("info", "post submitted to API." + response.body());
                pDialog.hide();
                alertDialog("Proses Penambahan Pemeriksaan Unit Keluar Berhasil", 1);
            }

            @Override
            public void onFailure(Call<SignValue> call, Throwable t) {
                pDialog.hide();
                errorRetrofit(call, t);
            }
        });
    }

    private Sign setSignature(Unit lu, GetStatus gs){
        signature1.buildDrawingCache();
        bitmap1 = signature1.getDrawingCache();
        signature2.buildDrawingCache();
        bitmap2 = signature2.getDrawingCache();

        Sign sign = new Sign();

        sign.setSign_ibid_klr(base64Encode(bitmap1));
        sign.setSign_cust_klr(base64Encode(bitmap2));
        sign.setId_pemeriksaanitem(gs.getId_pemeriksaan_item());

        if(lu.getAuction().getId_auctionitem() != 0){
            sign.setId_auctionitem(lu.getAuction().getId_auctionitem());
        }else{
            sign.setId_auctionitem(lu.getAuction().getIdauction_item());
        }
        return sign;
    }

    public void imgStyle(ImageView imageView, TableRow row, TableRow.LayoutParams imgParam) {
        imageView.setLayoutParams(imgParam);
        imageView.setBackgroundDrawable(null);
        row.addView(imageView);
    }

    public void imgSet(ImageView imageView, String check){
        Bitmap bmp;
        if(check.equals("1")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.checklist);
        }else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
        }
        Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 20, 20, true);
        imageView.setImageBitmap(resizedbitmap);
    }
}
