package com.example.android.ibidsera.view.fragment;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
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
import com.example.android.ibidsera.model.Lampiran;
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
    @BindView(R.id.signature1) ImageView signature1;
    @BindView(R.id.signature2) ImageView signature2;
    @BindView(R.id.ibid_sedan) ImageView ibid_sedan;
    @BindView(R.id.ibid_niaga) ImageView ibid_niaga;
    private int size = 0;
    private int position = -1;
    private HashMap<String, CheckBox> h = new HashMap<>();
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;
    private boolean onClickSpinner = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragment = inflater.inflate(R.layout.content_addm, container, false);
        ButterKnife.bind(this, myFragment);

        AuctionService auctionService = RetrofitUtil.getAuctionService();
        List<String> ls = new ArrayList<>();
        ProgressDialog pDialog = new ProgressDialog(getContext());

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

        datePicker(tgl_pemeriksaan, 0);
        getTimeSpinner();
        getKomponen(auctionService);
        cpvStop(cpv, bp);

        nopol.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if(onClickSpinner) {
                    nopol.dismissDropDown();
                    onClickSpinner = false;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                nopol.dismissDropDown();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!onClickSpinner) {
                    getDropdownList(auctionService, ls);
                }
            }
        });

        nopol.setOnItemClickListener((parent, view, position, id1) -> {
            onClickSpinner = true;
            hideKeyboard();
            cpvStart(cpv, bp);
            getAddm(StaticUnit.getLu(), position);
            cpvStop(cpv, bp);
        });

        imageClick(ibid_sedan, 1, 1);
        imageClick(ibid_niaga, 1, 2);
        imageClick(signature1, 2, 1);
        imageClick(signature2, 2, 2);

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
                    auctionService.insertUnitMasuk(setInsertUnit(StaticUnit.getLu())).enqueue(new Callback<GetStatus>() {
                        @Override
                        public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                            GetStatus getStatus = response.body();
                            Log.i("info", "post submitted to API." + response.body());
                            try {
                                if (getStatus.getStatus() == 200 && getStatus.getId_pemeriksaan_item() != 0) {
                                    postSignature(getStatus, auctionService, pDialog);
                                    postLampiran(getStatus, auctionService, pDialog);
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

    public void getAddm(List<Unit> lu, int id) {
        position = id;
        if (lu.get(id).getAuction().getValue() != null) {
            nopol.setText(lu.get(id).getAuction().getValue());
        }else{
            nopol.setText(lu.get(id).getAuction().getNo_polisi());
        }
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
        if(bitmap3 != null){
            ibid_sedan.setImageBitmap(bitmap3);
        } else if(bitmap4 != null){
            ibid_niaga.setImageBitmap(bitmap4);
        }
        if(bitmap1 != null){
            signature1.setImageBitmap(bitmap1);
        } else if(bitmap2 != null){
            signature2.setImageBitmap(bitmap2);
        }
    }

    public ArrayAdapter<String> getAdapterList(String value){
        List<String> list = new ArrayList<>();
        list.add(value);
        return getAdapter(list);
    }

    public InsertUnit setInsertUnit(List<Unit> lUnit){
        InsertUnit insertUnit = new InsertUnit();
        insertUnit.setIdpemeriksaanitem(lUnit.get(position).getAuction().getId_pemeriksaanitem());
        if(lUnit.get(position).getAuction().getId_auctionitem() != 0){
            insertUnit.setIdauctionitem(lUnit.get(position).getAuction().getId_auctionitem());
        }else{
            insertUnit.setIdauctionitem(lUnit.get(position).getAuction().getIdauction_item());
        }
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

    private Sign setSignature(List<Unit> lu, GetStatus gs){
        signature1.buildDrawingCache();
        bitmap1 = signature1.getDrawingCache();
        signature2.buildDrawingCache();
        bitmap2 = signature2.getDrawingCache();

        Sign sign = new Sign();

        sign.setSign_ibid_msk(base64Encode(bitmap1));
        sign.setSign_cust_msk(base64Encode(bitmap2));
        sign.setId_pemeriksaanitem(gs.getId_pemeriksaan_item());

        if(lu.get(position).getAuction().getId_auctionitem() != 0){
            sign.setId_auctionitem(lu.get(position).getAuction().getId_auctionitem());
        }else{
            sign.setId_auctionitem(lu.get(position).getAuction().getIdauction_item());
        }
        return sign;
    }

    private List<Lampiran> setLampiran(GetStatus gs){
        ibid_sedan.buildDrawingCache();
        bitmap3 = ibid_sedan.getDrawingCache();
        ibid_niaga.buildDrawingCache();
        bitmap4 = ibid_niaga.getDrawingCache();

        List<Lampiran> ll = new ArrayList<>();

        Lampiran lampiran = new Lampiran();
        lampiran.setIdpemeriksaan_item(gs.getId_pemeriksaan_item());
        lampiran.setNama_lampiran("mobil1");
        lampiran.setBase64img(base64Encode(bitmap3));
        ll.add(lampiran);

        Lampiran lampiran2 = new Lampiran();
        lampiran2.setIdpemeriksaan_item(gs.getId_pemeriksaan_item());
        lampiran2.setNama_lampiran("mobil2");
        lampiran2.setBase64img(base64Encode(bitmap4));
        ll.add(lampiran2);

        return ll;
    }

    private int isChecked(CheckBox checkBox){
        if(checkBox.isChecked())return 1;
        else return 0;
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
        setCaps(pool);
        setCaps(cases);
    }

    private void getDropdownList(AuctionService auctionService, List<String> ls){
        if (!nopol.getText().toString().equals("")){
            auctionService.getAutoUnitm(nopol.getText().toString()).enqueue(new Callback<List<Unit>>() {
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

    private void imageClick(ImageView imageView, int id, int position){
        if(id == 1) {
            imageView.setOnClickListener(v -> lampiranDialog(position));
        }else {
            imageView.setOnClickListener(v -> signatureDialog(position));
        }
    }

    private void lampiranDialog(int id){
        AlertDialog.Builder alertDialog  = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Lampiran");
        alertDialog.setCancelable(false);

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout container = new FrameLayout(getContext());
        onTouchLampiran(container);
        ImageView imageView = new ImageView(getContext());
        if(id == 1){
            if(bitmap3 == null) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_sedan));
            }else {
                imageView.setImageBitmap(bitmap3);
            }
        }else {
            if(bitmap4 == null) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_niaga));
            }else {
                imageView.setImageBitmap(bitmap4);
            }
        }
        container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, 450);
        linearLayout.addView(container);
        Button reset = new Button(getContext());
        reset.setText("Clear Notes");
        reset.setBackgroundDrawable(getResources().getDrawable(R.drawable.button_style));
        reset.setTextColor(getResources().getColor(R.color.colorPrimary));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(16,16,16,16);
        reset.setLayoutParams(params);
        reset.setOnClickListener(v -> {
            container.destroyDrawingCache();
            container.removeAllViews();
            if(id == 1) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_sedan));
            }else {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_niaga));
            }
            container.addView(imageView);
        });
        linearLayout.addView(reset);
        alertDialog.setView(linearLayout);

        // Set up the buttons
        alertDialog.setPositiveButton("Save Notes", (dialog, which) -> {
            container.setDrawingCacheEnabled(true);
            Bitmap bitmap = Bitmap.createBitmap(container.getWidth(), 450, Bitmap.Config.RGB_565);
            Canvas canvas = new Canvas(bitmap);
            container.draw(canvas);
            if(id == 1){
                ibid_sedan.setImageBitmap(bitmap);
                bitmap3 = bitmap;
            }else{
                ibid_niaga.setImageBitmap(bitmap);
                bitmap4 = bitmap;
            }
        });
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> {});
        alertDialog.create().show();
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

    private void onTouchLampiran(FrameLayout frameLayout){
        frameLayout.setOnTouchListener((view, event) -> {
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(AddMasuk.this.getContext());
                alertDialog.setTitle("Masukan catatan kerusakan : ");
                alertDialog.setCancelable(true);

                final EditText input = new EditText(AddMasuk.this.getContext());

                FrameLayout container = new FrameLayout(AddMasuk.this.getContext());
                FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.leftMargin = 20; // remember to scale correctly
                params.rightMargin = 20;
                input.setLayoutParams(params);
                container.addView(input);
                alertDialog.setView(container);

                int[] location = new int[2];
                view.getLocationOnScreen(location);
                float screenX = event.getRawX();
                float screenY = event.getRawY();
                final float viewX = screenX - location[0];
                final float viewY = screenY - location[1];

                // Set up the buttons
                alertDialog.setPositiveButton("OK", (dialog, which) -> {

                    TextView dynamicTextView = new TextView(AddMasuk.this.getContext());
                    dynamicTextView.setText(input.getText().toString());
                    dynamicTextView.setX(viewX);
                    dynamicTextView.setY(viewY);
                    dynamicTextView.setPadding(10, 10, 10, 10);
                    dynamicTextView.setTypeface(null, Typeface.BOLD);
                    dynamicTextView.setTextColor(getResources().getColor(R.color.colorAccent));
                    frameLayout.addView(dynamicTextView);
                });
                alertDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
                alertDialog.create().show();
            }
            return true;
        });
    }

    private void postSignature(GetStatus gs, AuctionService auctionService, ProgressDialog pDialog){
        auctionService.postSignMasuk(setSignature(StaticUnit.getLu(), gs)).enqueue(new Callback<SignValue>() {
            @Override
            public void onResponse(Call<SignValue> call, Response<SignValue> response) {
                Log.i("info", "post submitted to API." + response.body());
            }

            @Override
            public void onFailure(Call<SignValue> call, Throwable t) {
                pDialog.hide();
                errorRetrofit(call, t);
            }
        });
    }

    private void postLampiran(GetStatus gs, AuctionService auctionService, ProgressDialog pDialog){
        auctionService.postLampiran(setLampiran(gs)).enqueue(new Callback<GetStatus>() {
            @Override
            public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                Log.i("info", "post submitted to API." + response.body().toString());
                pDialog.hide();
                alertDialog("Proses Penambahan Pemeriksaan Unit Masuk Berhasil", 1);
            }

            @Override
            public void onFailure(Call<GetStatus> call, Throwable t) {
                pDialog.hide();
                errorRetrofit(call, t);
            }
        });
    }
}
