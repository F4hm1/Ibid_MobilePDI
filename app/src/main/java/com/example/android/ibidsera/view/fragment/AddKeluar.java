package com.example.android.ibidsera.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Html;
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
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.GetStatus;
import com.example.android.ibidsera.model.InsertUnit;
import com.example.android.ibidsera.model.NoPolUnit;
import com.example.android.ibidsera.model.Sign;
import com.example.android.ibidsera.model.SignValue;
import com.example.android.ibidsera.model.StaticUnit;
import com.example.android.ibidsera.model.Unit;
import com.example.android.ibidsera.model.UnitMasukKeluar;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.util.HelperConstant;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.example.android.ibidsera.view.activity.PemeriksaanActivity;
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

import static android.app.Activity.RESULT_OK;

/**
 * Created by Yosefricaro on 24/07/2017.
 */

public class AddKeluar extends BaseFragment {
    @BindView(R.id.nopol_title)
    TextView nopol_title;
    @BindView(R.id.nama_title)
    TextView nama_title;
    @BindView(R.id.alamat_title)
    TextView alamat_title;
    @BindView(R.id.kota_title)
    TextView kota_title;
    @BindView(R.id.telepon_title)
    TextView telepon_title;
    @BindView(R.id.table_addm)
    TableLayout tl;
    @BindView(R.id.progress_view)
    CircularProgressView cpv;
    @BindView(R.id.background_progress)
    RelativeLayout bp;
    @BindView(R.id.nopol)
    AutoCompleteTextView nopol;
    @BindView(R.id.merk)
    Spinner merk;
    @BindView(R.id.seri)
    Spinner seri;
    @BindView(R.id.silinder)
    Spinner silinder;
    @BindView(R.id.grade)
    Spinner grade;
    @BindView(R.id.sub_grade)
    Spinner sub_grade;
    @BindView(R.id.transmisi)
    EditText transmisi;
    @BindView(R.id.tahun)
    EditText tahun;
    @BindView(R.id.km)
    EditText km;
    @BindView(R.id.nama_pemilik)
    EditText nama_pemilik;
    @BindView(R.id.fuel)
    Spinner fuel;
    @BindView(R.id.cat)
    Spinner cat;
    @BindView(R.id.tgl_pemeriksaan)
    EditText tgl_pemeriksaan;
    @BindView(R.id.jam)
    Spinner jam;
    @BindView(R.id.menit)
    Spinner menit;
    @BindView(R.id.nama_pengemudi)
    EditText nama_pengemudi;
    @BindView(R.id.alamat_pengemudi)
    EditText alamat_pengemudi;
    @BindView(R.id.kota)
    EditText kota;
    @BindView(R.id.telepon)
    EditText telepon;
    @BindView(R.id.catatan)
    EditText catatan;
    @BindView(R.id.cancel)
    Button cancel;
    @BindView(R.id.save)
    Button save;
    @BindView(R.id.checkboxB)
    CheckBox checkBoxB;
    @BindView(R.id.checkboxR)
    CheckBox checkBoxR;
    @BindView(R.id.checkboxT)
    CheckBox checkBoxT;
    @BindView(R.id.signature1)
    ImageView signature1;
    @BindView(R.id.signature2)
    ImageView signature2;
    private int size = 0;
    private int position = -1;
    HashMap<String, CheckBox> h = new HashMap<>();
    HashMap<String, ImageView> hi = new HashMap<>();
    private Bitmap bitmap1;
    private Bitmap bitmap2;

    //Enhancement
    @BindView(R.id.toggle_checklistable)
    Switch mToggleChecklist;

    @BindView(R.id.et_checklist_not)
    EditText mEtChecklistNot;

    @BindView(R.id.radio_addm_minibus)
    RadioButton mRadioMinibus;

    @BindView(R.id.radio_addm_sedan)
    RadioButton mRadioSedan;

    @BindView(R.id.ibid_sedan)
    ImageView ibid_sedan;

    @BindView(R.id.ibid_niaga)
    ImageView ibid_niaga;

    @BindView(R.id.cases)
    EditText cases;

    @BindView(R.id.pool)
    EditText pool;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragment = inflater.inflate(R.layout.content_addk, container, false);
        ButterKnife.bind(this, myFragment);

        mToggleChecklist.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                tl.setVisibility(View.VISIBLE);
                mEtChecklistNot.setVisibility(View.GONE);
            } else {
                tl.setVisibility(View.GONE);
                mEtChecklistNot.setVisibility(View.VISIBLE);
            }
        });

        mRadioSedan.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                ibid_sedan.setEnabled(true);
                ibid_niaga.setEnabled(false);
                ibid_sedan.setAlpha(1f);
                ibid_niaga.setAlpha(0.2f);

                mRadioMinibus.setChecked(false);
            }
        });

        mRadioMinibus.setOnCheckedChangeListener((compoundButton, b) -> {
            if (b) {
                ibid_sedan.setEnabled(false);
                ibid_niaga.setEnabled(true);
                ibid_sedan.setAlpha(0.2f);
                ibid_niaga.setAlpha(1f);
                mRadioSedan.setChecked(false);
            }
        });

        mRadioSedan.setChecked(true);

        cpvStart(cpv, bp);

        AuctionService auctionService = RetrofitUtil.getAuctionService();
        List<String> ls = new ArrayList<>();
        ProgressDialog pDialog = new ProgressDialog(getContext());

        hideKeyboard();
        setAllCaps();
        setAllDisabled();
        setRequired();

        datePicker(tgl_pemeriksaan, 0);
        getTimeSpinner();
        getKomponen(auctionService, -1);
        cpvStop(cpv, bp);

        nopol.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
            }

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
//            getAddk(StaticUnit.getLu().get(position), position);
//            cpvStop(cpv, bp);
            getDetailUnitById(listNoPolUnit.get(position).getAuctionItemId(), position, auctionService);
        });

        signatureClick(signature1, 1);
        signatureClick(signature2, 2);
        ibid_sedan.setOnClickListener(view -> goToPemeriksaanActivity(1));
        ibid_niaga.setOnClickListener(view -> goToPemeriksaanActivity(2));

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
            InsertUnit requestUnit = setInsertUnit(StaticUnit.getUnitMasukKeluar());
            if (ls2.size() <= 0) {
                final Handler handler = new Handler();
                handler.postDelayed(() -> {
                    auctionService.insertUnitKeluar(requestUnit).enqueue(new Callback<GetStatus>() {
                        @Override
                        public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                            GetStatus getStatus = response.body();
                            Log.i("info", "post submitted to API." + response.body());
                            try {
                                if (getStatus.getStatus() == 200 && getStatus.getId_pemeriksaan_item() != 0) {
                                    pDialog.hide();
                                    alertDialog("Proses Penambahan Pemeriksaan Unit Keluar Berhasil", 1);
                                } else {
                                    pDialog.hide();
                                    alertDialog(getStatus.getMessage(), 1);
                                }
                            } catch (Exception e) {
                            }
                        }

                        @Override
                        public void onFailure(Call<GetStatus> call, Throwable t) {
                            pDialog.hide();
//                            errorRetrofit(call, t);
                            alertDialog("Terdapat kesalahan ketika menyimpan data", 1);

                        }
                    });
                }, 2000);
            } else {
                pDialog.hide();
                String required = "";
                for (int i = ls2.size() - 1; i >= 0; i--) {
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

    private void getDetailUnitById(int auctionItemId, int position, AuctionService auctionService) {
        auctionService.getDetailUnitMasuk(auctionItemId + "").enqueue(new Callback<List<UnitMasukKeluar>>() {
            @Override
            public void onResponse(Call<List<UnitMasukKeluar>> call, Response<List<UnitMasukKeluar>> response) {
                List<UnitMasukKeluar> lu = response.body();
                StaticUnit.setLuMasukKeluar(lu);
                getAddk(lu.get(0), position);
                cpvStop(cpv, bp);
            }

            @Override
            public void onFailure(Call<List<UnitMasukKeluar>> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });
    }

    public void getAddk(UnitMasukKeluar lu, int id) {
        position = id;
        StaticUnit.setUnitMasukKeluar(lu);
        if (lu.getAuction().getValue() != null) {
            nopol.setText(lu.getAuction().getValue());
        } else {
            nopol.setText(lu.getAuction().getNo_polisi());
        }
        merk.setAdapter(getAdapterList(lu.getAuctiondetail().getNama_merk()));
        seri.setAdapter(getAdapterList(lu.getAuctiondetail().getTipe().get(0).getAttributedetail()));
        silinder.setAdapter(getAdapterList(lu.getAuctiondetail().getTipe().get(1).getAttributedetail()));
        grade.setAdapter(getAdapterList(lu.getAuctiondetail().getTipe().get(2).getAttributedetail()));
        sub_grade.setAdapter(getAdapterList(lu.getAuctiondetail().getTipe().get(3).getAttributedetail()));
        transmisi.setText(lu.getAuctiondetail().getTransmisi());
        tahun.setText(lu.getAuctiondetail().getTahun());
        km.setText(String.valueOf(lu.getAuctiondetail().getKm()));
        nama_pemilik.setText(lu.getAuctiondetail().getPntp().getName_pntp());

        fuel.setAdapter(setDropdown(R.array.fuel));
        cat.setAdapter(setDropdown(R.array.cat));

        nama_pengemudi.setText(lu.getAuction().getNama_pengemudi_klr());
        alamat_pengemudi.setText(lu.getAuction().getAlamat_pengemudi_klr());
        kota.setText(lu.getAuction().getKota_klr());
        telepon.setText(lu.getAuction().getTelepon_klr());
        catatan.setText(lu.getAuction().getCatatan_klr());
        if (bitmap1 != null) {
            signature1.setImageBitmap(bitmap1);
        } else if (bitmap2 != null) {
            signature2.setImageBitmap(bitmap2);
        }
        for (int i = 0; i < size; i++) {
            imgSet(hi.get("b" + i), lu.getKomponen().get(i).getTampil_b());
            imgSet(hi.get("r" + i), lu.getKomponen().get(i).getTampil_r());
            imgSet(hi.get("t" + i), lu.getKomponen().get(i).getTampil_t());
        }
    }

    public ArrayAdapter<String> getAdapterList(String value) {
        List<String> list = new ArrayList<>();
        list.add(value);
        return getAdapter(list);
    }

    public InsertUnit setInsertUnit(UnitMasukKeluar lUnit) {
        InsertUnit insertUnit = new InsertUnit();
        insertUnit.setIdpemeriksaanitem(lUnit.getAuction().getId_pemeriksaanitem());
        if (lUnit.getAuction().getId_auctionitem() != 0) {
            insertUnit.setIdauctionitem(lUnit.getAuction().getId_auctionitem());
        } else {
            insertUnit.setIdauctionitem(lUnit.getAuction().getIdauction_item());
        }
        insertUnit.setBataskomponen(size);
        insertUnit.setNopolisi(String.valueOf(nopol.getText()));
        insertUnit.setMERK(lUnit.getAuctiondetail().getId_merk());
        insertUnit.setSERI(String.valueOf(lUnit.getAuctiondetail().getTipe().get(0).getId_attrdetail()));
        insertUnit.setSILINDER(String.valueOf(lUnit.getAuctiondetail().getTipe().get(1).getId_attrdetail()));
        insertUnit.setGRADE(String.valueOf(lUnit.getAuctiondetail().getTipe().get(2).getId_attrdetail()));
        insertUnit.setSUB_GRADE(String.valueOf(lUnit.getAuctiondetail().getTipe().get(3).getId_attrdetail()));
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
        insertUnit.setExpedition_amount("");
        insertUnit.setCases(String.valueOf(cases.getText()));
        insertUnit.setPoolkota(String.valueOf(pool.getText()));
        List<String> lb = new ArrayList<>();
        List<String> lr = new ArrayList<>();
        List<String> lt = new ArrayList<>();
        List<Integer> lidKomponen = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            CheckBox b = h.get("b" + i);
            CheckBox r = h.get("r" + i);
            CheckBox t = h.get("t" + i);
            lb.add(isChecked(b));
            lr.add(isChecked(r));
            lt.add(isChecked(t));
            lidKomponen.add(i + 1);
        }
        insertUnit.setCektampilkanbaik(lb);
        insertUnit.setCektampilkanrusak(lr);
        insertUnit.setCektampilkantidakada(lt);
        insertUnit.setIdkomponenpemeriksaan(lidKomponen);
        insertUnit.setCatatan(String.valueOf(catatan.getText()));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        insertUnit.setWEBID_LOGGED_IN(prefs.getInt("userId", 0));

        signature1.buildDrawingCache();
        bitmap1 = signature1.getDrawingCache();
        signature2.buildDrawingCache();
        bitmap2 = signature2.getDrawingCache();
        insertUnit.setTtdibid(base64Encode(bitmap1));
        insertUnit.setTtdcustomer(base64Encode(bitmap2));

        if (mRadioSedan.isChecked()) {
            if (HelperConstant.mTempBitmapSedan != null) {
                insertUnit.setGambarchecklist(base64Encode(HelperConstant.mTempBitmapSedan));
            } else {
                showToast("Harap melakukan checklist gambar tipe mobil sedan");
                return null;
            }
        } else {
            if (HelperConstant.mTempBitmapNiaga != null) {
                insertUnit.setGambarchecklist(base64Encode(HelperConstant.mTempBitmapNiaga));
            } else {
                showToast("Harap melakukan checklist gambar tipe mobil minibus");
                return null;
            }
        }

        if (!mToggleChecklist.isChecked()) {
            insertUnit.setReasonunchecklist(mEtChecklistNot.getText().toString());
        } else {
            insertUnit.setReasonunchecklist("");
        }

        return insertUnit;
    }

    private String isChecked(CheckBox checkBox) {
        if (checkBox.isChecked()) return "true";
        else return "false";
    }

    private void setAllDisabled() {
        setDisabled(merk);
        setDisabled(seri);
        setDisabled(silinder);
        setDisabled(grade);
        setDisabled(sub_grade);
        setDisabled(transmisi);
        setDisabled(tahun);
        setDisabled(nama_pemilik);
    }

    private void setAllCaps() {
        setCaps(nopol);
        setCaps(alamat_pengemudi);
        setCaps(nama_pengemudi);
        setCaps(kota);
        setCaps(catatan);
    }

    List<NoPolUnit> listNoPolUnit = new ArrayList<>();

    private void getDropdownList(AuctionService auctionService, List<String> ls) {
        if (!nopol.getText().toString().equals("")) {
            auctionService.getNoPolUnitK(nopol.getText().toString()).enqueue(new Callback<List<NoPolUnit>>() {
                @Override
                public void onResponse(Call<List<NoPolUnit>> call, Response<List<NoPolUnit>> response) {
                    List<NoPolUnit> lu = response.body();

                    listNoPolUnit.clear();
                    try {
                        for (int i = 0; i < lu.size(); i++) {
                            listNoPolUnit.add(lu.get(i));
                        }
                    } catch (Exception e) {
                    }
                    ArrayAdapter<NoPolUnit> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, listNoPolUnit);
                    nopol.setAdapter(adapter);
                    nopol.setThreshold(1);
                    nopol.showDropDown();
                }

                @Override
                public void onFailure(Call<List<NoPolUnit>> call, Throwable t) {
                    errorRetrofit(call, t);
                }
            });
        }
    }

    private void getTimeSpinner() {
        Calendar c = Calendar.getInstance();
        jam.setAdapter(setDropdown(R.array.jam));

        int jam_now = c.get(Calendar.HOUR_OF_DAY);
        jam.setSelection(jam_now);

        menit.setAdapter(setDropdown(R.array.menit));

        int menit_now = c.get(Calendar.MINUTE);
        menit.setSelection(menit_now);
    }

    private void getKomponen(AuctionService auctionService, int position) {
        auctionService.getUnitK().enqueue(new Callback<List<UnitMasukKeluar>>() {
            @Override
            public void onResponse(Call<List<UnitMasukKeluar>> call, Response<List<UnitMasukKeluar>> response) {
                List<UnitMasukKeluar> lu = response.body();
                getKomponenList(lu, 0);
            }

            @Override
            public void onFailure(Call<List<UnitMasukKeluar>> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });
    }

    private void getKomponenList(List<UnitMasukKeluar> lu, int position) {
        try {
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
                hi.put("b" + i, b_in);
                hi.put("r" + i, r_in);
                hi.put("t" + i, t_in);

                TableRow.LayoutParams param_25 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, .25f);
                TableRow.LayoutParams param5 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 5f);
                TableRow.LayoutParams param3 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 3f);
                TableRow.LayoutParams param1 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                TableRow.LayoutParams paramCheck = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                paramCheck.gravity = Gravity.CENTER;

                rowColor(row, i);
                textStyle(no, row, param_25, String.valueOf(i + 1));
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
        } catch (Exception e) {
        }
    }

    private void signatureClick(ImageView imageView, int id) {
        imageView.setOnClickListener(v -> signatureDialog(id));
    }

    private void goToPemeriksaanActivity(int curPosition) {
        Intent intent = new Intent(getActivity(), PemeriksaanActivity.class);
        if (curPosition == 1) {
            intent.putExtra(HelperConstant.LAMPIRAN_KEY, HelperConstant.LAMPIRAN_SEDAN);
            startActivityForResult(intent, HelperConstant.LAMPIRAN_SEDAN);
        } else {
            intent.putExtra(HelperConstant.LAMPIRAN_KEY, HelperConstant.LAMPIRAN_NIAGA);
            startActivityForResult(intent, HelperConstant.LAMPIRAN_NIAGA);
        }
    }

    private void signatureDialog(int id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
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
        params.setMargins(16, 16, 16, 16);
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
            if (id == 1) {
                signature1.setImageBitmap(bitmap);
                bitmap1 = bitmap;
            } else {
                signature2.setImageBitmap(bitmap);
                bitmap2 = bitmap;
            }
        });
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> mSignature.clear());
        alertDialog.create().show();
    }

    private void postSignature(GetStatus gs, AuctionService auctionService, ProgressDialog pDialog) {
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

    private Sign setSignature(Unit lu, GetStatus gs) {
        signature1.buildDrawingCache();
        bitmap1 = signature1.getDrawingCache();
        signature2.buildDrawingCache();
        bitmap2 = signature2.getDrawingCache();

        Sign sign = new Sign();

        sign.setSign_ibid_klr(base64Encode(bitmap1));
        sign.setSign_cust_klr(base64Encode(bitmap2));
        sign.setId_pemeriksaanitem(gs.getId_pemeriksaan_item());

        if (lu.getAuction().getId_auctionitem() != 0) {
            sign.setId_auctionitem(lu.getAuction().getId_auctionitem());
        } else {
            sign.setId_auctionitem(lu.getAuction().getIdauction_item());
        }
        return sign;
    }

    public void imgStyle(ImageView imageView, TableRow row, TableRow.LayoutParams imgParam) {
        imageView.setLayoutParams(imgParam);
        imageView.setBackgroundDrawable(null);
        row.addView(imageView);
    }

    public void imgSet(ImageView imageView, String check) {
        Bitmap bmp;
        if (check.equals("true")) {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.checklist);
        } else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
        }
        Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 20, 20, true);
        imageView.setImageBitmap(resizedbitmap);
    }

    private void setRequired() {
        String required = "<font color=#FF0000> *</font>";
        nopol_title.setText(Html.fromHtml(nopol_title.getText() + required));
        nama_title.setText(Html.fromHtml(nama_title.getText() + required));
//        alamat_title.setText(Html.fromHtml(alamat_title.getText() + required));
//        kota_title.setText(Html.fromHtml(kota_title.getText() + required));
        telepon_title.setText(Html.fromHtml(telepon_title.getText() + required));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case HelperConstant.LAMPIRAN_SEDAN: {
                if (resultCode == RESULT_OK) {
                    if (HelperConstant.mTempBitmapSedan != null) {
                        ibid_sedan.setImageBitmap(HelperConstant.mTempBitmapSedan);
                    }
                }
                break;
            }
            case HelperConstant.LAMPIRAN_NIAGA: {
                if (resultCode == RESULT_OK) {
                    if (HelperConstant.mTempBitmapNiaga != null) {
                        ibid_niaga.setImageBitmap(HelperConstant.mTempBitmapNiaga);
                    }
                }
                break;
            }
        }
    }
}
