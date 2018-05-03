package com.example.android.ibidsera.view.fragment;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
import android.widget.Toast;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.base.RxLazyFragment;
import com.example.android.ibidsera.model.ExpeditionVarian;
import com.example.android.ibidsera.model.GetStatus;
import com.example.android.ibidsera.model.InsertUnit;
import com.example.android.ibidsera.model.Lampiran;
import com.example.android.ibidsera.model.NoPolUnit;
import com.example.android.ibidsera.model.PhotoChecklist;
import com.example.android.ibidsera.model.PhotoTtdCustomer;
import com.example.android.ibidsera.model.PhotoTtdIbid;
import com.example.android.ibidsera.model.Sign;
import com.example.android.ibidsera.model.SignValue;
import com.example.android.ibidsera.model.StaticUnit;
import com.example.android.ibidsera.model.Unit;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.service.APICall;
import com.example.android.ibidsera.service.RetrofitHelper;
import com.example.android.ibidsera.util.HelperBridge;
import com.example.android.ibidsera.util.HelperConstant;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.example.android.ibidsera.view.activity.PemeriksaanActivity;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.trello.rxlifecycle2.RxLifecycle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_OK;

public class AddMasuk extends RxLazyFragment implements AdapterView.OnItemSelectedListener {


    @BindView(R.id.spinnerEkspedisi)
    Spinner spinnerEkspedisi;

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
    @BindView(R.id.pool)
    EditText pool;
    @BindView(R.id.cases)
    EditText cases;
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
    @BindView(R.id.ibid_sedan)
    ImageView ibid_sedan;
    @BindView(R.id.ibid_niaga)
    ImageView ibid_niaga;

    /*@BindView(R.id.ibid_pickup)
    ImageView ibid_pickup;*/
    private int size = 0;
    private int position = -1;
    private HashMap<String, CheckBox> h = new HashMap<>();
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;
    private boolean onClickSpinner = false;

    /*//Start-Enhancement
    @BindView(R.id.expedition_id)
    EditText expeditionId;
    @BindView(R.id.expedition_notes)
    EditText expeditionNotes;
    @BindView(R.id.expedition_amount)
    EditText expeditionAmount;
    @BindView(R.id.expedition_amount_title)
    TextView expeditionAmountTitle;*/

    @BindView(R.id.toggle_checklistable)
    Switch mToggleChecklist;

    @BindView(R.id.et_checklist_not)
    EditText mEtChecklistNot;

    /*@BindView(R.id.radio_addm_pickup)
    RadioButton mRadioPickup;*/

    @BindView(R.id.radio_addm_minibus)
    RadioButton mRadioMinibus;

    @BindView(R.id.radio_addm_sedan)
    RadioButton mRadioSedan;

    @BindView(R.id.tv_expedition_title)
    TextView tvExpeditionTitle;

    @BindView(R.id.v_expedition_separator)
    View vExpeditionSeparator;

    @BindView(R.id.ll_expedition_container)
    LinearLayout llExpeditionContainer;

    //End-Enhancement

    private static final int KEY_PEMERIKSAAN_ACTIVITY = 1009;

    private InsertUnit requestUnit;
    private String selectedString = "";
    private String tempString = "";

    @Override
    public int getLayoutResId() {
        return R.layout.content_addm;
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        *//*View myFragment = inflater.inflate(R.layout.content_addm, container, false);
        ButterKnife.bind(this, myFragment);*//*


        return myFragment;
    }*/

    @Override
    public void finishCreateView(Bundle state) {
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



        cpvStop(cpv, bp);

        AuctionService auctionService = RetrofitUtil.getAuctionService();
        AuctionService postGambarService = RetrofitUtil.postGambarAuctionService();
        APICall apiCall = RetrofitHelper.getUnitMasukSearchByNopolStokServiceALPHA();
        APICall apiServicePostGbr = RetrofitHelper.postGambarAddMasukTaksasiServiceALPHA();




        List<String> ls = new ArrayList<>();
        ProgressDialog pDialog = new ProgressDialog(getContext());

        hideKeyboard();
        setAllCaps();
        setAllDisabled();
        setRequired();


        Bundle bundle = this.getArguments();
        if (bundle != null) {
            position = bundle.getInt("id");
        }


        if (position != -1) {
            cpvStart(cpv, bp);
            getAddm(StaticUnit.getLu().get(position), position);
            cpvStop(cpv, bp);
        }

        datePicker(tgl_pemeriksaan, 0);
        getTimeSpinner();
        //getKomponen(apiCall);
        //cpvStop(cpv, bp);


        nopol.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                if (onClickSpinner) {
                    nopol.dismissDropDown();
                    onClickSpinner = false;
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                nopol.dismissDropDown();
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!onClickSpinner) {
                    if (!selectedString.equals(s)) getDropdownList(apiCall, ls);
                }
            }
        });

        nopol.setOnItemClickListener((parent, view, position, id1) -> {
            hideKeyboard();

            selectedString = listNoPolUnit.get(position).getNopolisi();
//            getAddm(StaticUnit.getLu().get(position), position);
            getDetailUnitById(listNoPolUnit.get(position).getAuctionItemId(), position, apiCall);
        });



        toolTip(cases, "BUY BACK / WANPRES");
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
            requestUnit = setInsertUnit(StaticUnit.getUnit());

            if (requestUnit != null) {
                if (ls2.size() <= 0) {
                    final Handler handler = new Handler();
                    handler.postDelayed(() -> {

                        apiCall.insertUnitMasuk(requestUnit)
                                .compose(bindToLifecycle())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(getStatus -> {

                                    Observable<GetStatus> uploadChecklist =  apiServicePostGbr.postObsRawJsonGbrMasukChecklist(new PhotoChecklist(String.valueOf(requestUnit.getIdauctionitem()), requestUnit.getGambarchecklist()));
                                    Observable<GetStatus> uploadTtdIbid =  apiServicePostGbr.postObsRawJsonGbrMasukTtdIbid(new PhotoTtdIbid(String.valueOf(requestUnit.getIdauctionitem()), requestUnit.getTtdibid()));
                                    Observable<GetStatus> uploadTtdCustomer =  apiServicePostGbr.postObsRawJsonGbrMasukTtdCust(new PhotoTtdCustomer(String.valueOf(requestUnit.getIdauctionitem()), requestUnit.getTtdcustomer()));

                                    Observable.concat(uploadChecklist, uploadTtdIbid, uploadTtdCustomer)
                                            .subscribeOn(Schedulers.io())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new Observer<GetStatus>() {
                                                @Override
                                                public void onSubscribe(@NonNull Disposable d) {

                                                }

                                                @Override
                                                public void onNext(@NonNull GetStatus getStatus) {

                                                }

                                                @Override
                                                public void onError(@NonNull Throwable e) {

                                                }

                                                @Override
                                                public void onComplete() {
                                                    HelperConstant.mTempBitmapNiaga = null;
                                                    HelperConstant.mTempBitmapSedan = null;
                                                    pDialog.hide();
                                                    alertDialog("Proses Penambahan Pemeriksaan Unit Masuk Berhasil", 1);
                                                }
                                            });



                                    /*try{
                                        apiServicePostGbr.postRawJsonGbrMasukChecklist(new PhotoChecklist(String.valueOf(requestUnit.getIdauctionitem()), requestUnit.getGambarchecklist())).enqueue(new Callback<GetStatus>() {
                                            @Override
                                            public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                                                try {
                                                    Toast.makeText(getActivity(), response.body().getMessage() + "Ceklist", Toast.LENGTH_SHORT).show();
                                                } catch (Exception e) {

                                                }

                                            }

                                            @Override
                                            public void onFailure(Call<GetStatus> call, Throwable t) {
                                                Toast.makeText(getActivity(), String.valueOf(t), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        apiServicePostGbr.postRawJsonGbrMasukTtdIbid(new PhotoTtdIbid(String.valueOf(requestUnit.getIdauctionitem()), requestUnit.getTtdibid())).enqueue(new Callback<GetStatus>() {
                                            @Override
                                            public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                                                try {
                                                    Toast.makeText(getActivity(), response.body().getMessage() + "Ibid", Toast.LENGTH_SHORT).show();
                                                } catch (Exception e) {

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<GetStatus> call, Throwable t) {
                                                Toast.makeText(getActivity(), String.valueOf(t), Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                        apiServicePostGbr.postRawJsonGbrMasukTtdCust(new PhotoTtdCustomer(String.valueOf(requestUnit.getIdauctionitem()), requestUnit.getTtdcustomer())).enqueue(new Callback<GetStatus>() {
                                            @Override
                                            public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                                                try {
                                                    Toast.makeText(getActivity(), response.body().getMessage() + "Customer", Toast.LENGTH_SHORT).show();
                                                } catch (Exception e) {

                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<GetStatus> call, Throwable t) {
                                                Toast.makeText(getActivity(), String.valueOf(t), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    } catch (Exception e){

                                    }*/




                                    /*Log.i("info", "post submitted to API." + getStatus);
                                    try {
                                            pDialog.hide();
                                            alertDialog("Proses Penambahan Pemeriksaan Unit Masuk Berhasil", 1);
                                            HelperConstant.mTempBitmapNiaga = null;
                                            HelperConstant.mTempBitmapSedan = null;
                                    } catch (Exception e) {
                                    }*/
                                }, throwable -> {
                                    pDialog.hide();
//                            errorRetrofit(call, t);
                                    alertDialog("Terdapat kesalahan ketika menyimpan data", 2);
                                });


                        /*auctionService.insertUnitMasuk(requestUnit).enqueue(new Callback<GetStatus>() {
                            @Override
                            public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                                GetStatus getStatus = response.body();






                                Log.i("info", "post submitted to API." + response.body());
                                try {
                                    pDialog.hide();
                                    alertDialog("Proses Penambahan Pemeriksaan Unit Masuk Berhasil", 1);
                                    HelperConstant.mTempBitmapNiaga = null;
                                    HelperConstant.mTempBitmapSedan = null;

                                } catch (Exception e) {
                                }


                            }

                            @Override
                            public void onFailure(Call<GetStatus> call, Throwable t) {
                                pDialog.hide();
//                            errorRetrofit(call, t);
                                alertDialog("Terdapat kesalahan ketika menyimpan data", 1);
                            }
                        });*/


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
            }
        });

        cancelListener(cancel);

    }

    /*private void uploadGbr(AuctionService postGbrService, InsertUnit iu) {
        postGbrService.postRawJsonChecklist(new PhotoChecklist(String.valueOf(iu.getIdauctionitem()), iu.getGambarchecklist())).enqueue(new Callback<GetStatus>() {
            @Override
            public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                try {
                    if (response.body().getStatus() == 200 && response.body().getId_pemeriksaan_item() != 0) {
                        Toast.makeText(getActivity(), response.body().getMessage() + "Ceklist", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage() + "Gagal upload Ceklist", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), String.valueOf(e), Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<GetStatus> call, Throwable t) {
                Toast.makeText(getActivity(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });

        postGbrService.postRawJsonTtdIbid(new PhotoTtdIbid(String.valueOf(iu.getIdauctionitem()), iu.getTtdibid())).enqueue(new Callback<GetStatus>() {
            @Override
            public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                try {
                    if (response.body().getStatus() == 200 && response.body().getId_pemeriksaan_item() != 0) {
                        Toast.makeText(getActivity(), response.body().getMessage() + "Ibid", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage() + "Gagal upload Ibid", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetStatus> call, Throwable t) {
                Toast.makeText(getActivity(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });

        postGbrService.postRawJsonTtdCust(new PhotoTtdCustomer(String.valueOf(iu.getIdauctionitem()), iu.getTtdcustomer())).enqueue(new Callback<GetStatus>() {
            @Override
            public void onResponse(Call<GetStatus> call, Response<GetStatus> response) {
                try {
                    if (response.body().getStatus() == 200 && response.body().getId_pemeriksaan_item() != 0) {
                        Toast.makeText(getActivity(), response.body().getMessage() + "Customer", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getActivity(), response.body().getMessage() + "Gagal upload Customer", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getActivity(), String.valueOf(e), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetStatus> call, Throwable t) {
                Toast.makeText(getActivity(), String.valueOf(t), Toast.LENGTH_SHORT).show();
            }
        });
    }*/



    private void getDetailUnitById(int auctionItemId, int position, APICall auctionService) throws NumberFormatException {
        cpvStart(cpv, bp);
        auctionService.getDetailUnitPersiapan(auctionItemId + "")
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(unit -> {
                    //StaticUnit.setLu(units);
                    getKomponenList(unit);
                    getAddm(unit, position);
                    try {
                        if (unit.getExpedition().getExpeditionType() != null) {
                            toggleExpedition(true);
                            List<ExpeditionVarian> listVarian = unit.getExpedition().getExpeditionType().getExpeditionVarien();
                            List<String> arrayDataSpinner =  new ArrayList<String>();
                            for (int i = 0; i < listVarian.size() ; i++) {
                                arrayDataSpinner.add(listVarian.get(i).getVarian() + " - Harga " +  (listVarian.get(i).getHarga()).split("\\.")[0]);
                            }
                            ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, arrayDataSpinner);
                            spinnerEkspedisi.setAdapter(adp);
                        } else {
                            toggleExpedition(false);
                        }
                        cpvStop(cpv, bp);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                }, throwable -> {
                    Log.e("Error", throwable.getMessage());
                    cpvStop(cpv, bp);
                    errorRetrofit(null, throwable);
                });

        /*RetrofitUtil.getAuctionService().getDetailUnitPersiapan(auctionItemId + "").enqueue(new Callback<Unit>() {
            @Override
            public void onResponse(Call<Unit> call, Response<Unit> response) {
                //List<Unit> lu = response.body();
                //StaticUnit.setLu(lu);
                getAddm(response.body(), position);
                cpvStop(cpv, bp);
                try {
                    if (response.body().getExpedition().getExpeditionType() != null) {
                        toggleExpedition(true);
                        List<ExpeditionVarian> listVarian = response.body().getExpedition().getExpeditionType().getExpeditionVarien();
                        List<String> arrayDataSpinner =  new ArrayList<String>();
                        for (int i = 0; i < listVarian.size() ; i++) {
                            arrayDataSpinner.add(listVarian.get(i).getVarian() + " - Harga " +  (listVarian.get(i).getHarga()).split("\\.")[0]);
                        }
                        ArrayAdapter<String> adp = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_spinner_item, arrayDataSpinner);
                        spinnerEkspedisi.setAdapter(adp);
                    } else {
                        toggleExpedition(false);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<Unit> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });*/

    }

    public void getAddm(Unit lu, int pos) {
        position = pos;
        StaticUnit.setUnit(lu);
        if (lu.getAuction().getValue() != null) {
            nopol.setText(lu.getAuction().getValue());
        } else {
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

        nama_pengemudi.setText(lu.getAuction().getNama_pengemudi_msk());
        alamat_pengemudi.setText(lu.getAuction().getAlamat_pengemudi_msk());
        kota.setText(lu.getAuction().getKota_msk());
        telepon.setText(lu.getAuction().getTelepon_msk());
        catatan.setText(lu.getAuction().getCatatan());
        pool.setText(lu.getAuction().getPoolkota());
        cases.setText(lu.getAuction().getCases());
        if (bitmap3 != null) {
            ibid_sedan.setImageBitmap(bitmap3);
        } else if (bitmap4 != null) {
            ibid_niaga.setImageBitmap(bitmap4);
        }
        if (bitmap1 != null) {
            signature1.setImageBitmap(bitmap1);
        } else if (bitmap2 != null) {
            signature2.setImageBitmap(bitmap2);
        }

        Log.d("POLO", "lu: " + RetrofitUtil.toJson(lu));

        //Start-Enhancement
        /*try {
            if (lu.getExpedition() != null) {
                toggleExpedition(true);
                *//*expeditionId.setText(lu.getExpedition().getExpeditionOrderId());
                expeditionNotes.setText(
                                "Origin City: " + lu.getExpedition().getExpeditionType().getOriginCity() + "\n"
                                + "Auction City: " + lu.getExpedition().getExpeditionType().getAuctionCity() + "\n");*//*
            } else {
    //            expeditionId.setText("-");
    //            expeditionNotes.setText("-");
                toggleExpedition(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }*/
        //End-Enhancement


    }

    public ArrayAdapter<String> getAdapterList(String value) {
        List<String> list = new ArrayList<>();
        list.add(value);
        return getAdapter(list);
    }

    public InsertUnit setInsertUnit(Unit lu) {
        InsertUnit insertUnit = new InsertUnit();
        insertUnit.setIdpemeriksaanitem(lu.getKomponen().get(0).getId_pemeriksaanitem());
        if (lu.getAuction().getId_auctionitem() != 0) {
            insertUnit.setIdauctionitem(lu.getAuction().getId_auctionitem());
        } else {
            insertUnit.setIdauctionitem(lu.getAuction().getIdauction_item());
        }
        insertUnit.setBataskomponen(size);
        insertUnit.setNopolisi(String.valueOf(nopol.getText()));
        insertUnit.setMERK(lu.getId_merk());
        insertUnit.setSERI(String.valueOf(lu.getTipe().get(0).getId_attrdetail()));
        insertUnit.setSILINDER(String.valueOf(lu.getTipe().get(1).getId_attrdetail()));
        insertUnit.setGRADE(String.valueOf(lu.getTipe().get(2).getId_attrdetail()));
        insertUnit.setSUB_GRADE(String.valueOf(lu.getTipe().get(3).getId_attrdetail()));
        insertUnit.setTRANSMISI(String.valueOf(transmisi.getText()));
        insertUnit.setKM(String.valueOf(km.getText()));
        insertUnit.setFuel(fuel.getSelectedItem().toString());
        insertUnit.setCatbody(cat.getSelectedItem().toString());
        insertUnit.setTglpemeriksaan(String.valueOf(tgl_pemeriksaan.getText()));
        insertUnit.setJampemeriksaan(jam.getSelectedItem().toString());
        insertUnit.setMenitpemeriksaan(menit.getSelectedItem().toString());
        insertUnit.setNamapengemudi(String.valueOf(nama_pengemudi.getText()));
        insertUnit.setAlamatpengemudi(String.valueOf(alamat_pengemudi.getText()));
        //Enhancement
        //insertUnit.setExpedition_amount(String.valueOf(expeditionAmount.getText()));
        insertUnit.setKotapengemudi(String.valueOf(kota.getText()));
        insertUnit.setTeleponpengemudi(String.valueOf(telepon.getText()));
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
        insertUnit.setCases(String.valueOf(cases.getText()));
        insertUnit.setPoolkota(String.valueOf(pool.getText()));
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getContext());
        insertUnit.setWEBID_LOGGED_IN(prefs.getInt("userId", 0));
        signature1.buildDrawingCache();
        bitmap1 = signature1.getDrawingCache();
        signature2.buildDrawingCache();
        bitmap2 = signature2.getDrawingCache();
        insertUnit.setTtdibid(base64Encode(bitmap1));
        insertUnit.setTtdcustomer(base64Encode(bitmap2));

        ibid_sedan.buildDrawingCache();
        bitmap3 = ibid_sedan.getDrawingCache();
        ibid_niaga.buildDrawingCache();
        bitmap4 = ibid_niaga.getDrawingCache();
        if (mRadioSedan.isChecked()) {
            if (HelperConstant.mTempBitmapSedan != null) {
                insertUnit.setGambarchecklist(base64Encode(HelperConstant.mTempBitmapSedan));
            } else {
                showToast("Harap melakukan checklist gambar tipe mobil sedan");
                return null;
            }
        }

        if (mRadioMinibus.isChecked()) {
            if (HelperConstant.mTempBitmapNiaga != null) {
                insertUnit.setGambarchecklist(base64Encode(HelperConstant.mTempBitmapNiaga));
            } else {
                showToast("Harap melakukan checklist gambar tipe mobil minibus");
                return null;
            }
        }


        /*if (mRadioPickup.isChecked()) {
            if (HelperConstant.mTempBitmapPickup != null) {
                insertUnit.setGambarchecklist(base64Encode(HelperConstant.mTempBitmapPickup));
            } else {
                showToast("Harap melakukan checklist gambar tipe mobil pickup");
                return null;
            }
        }*/

        /*else {
            if (HelperConstant.mTempBitmapNiaga != null) {
                insertUnit.setGambarchecklist(base64Encode(HelperConstant.mTempBitmapNiaga));
            } else {
                showToast("Harap melakukan checklist gambar tipe mobil minibus");
                return null;
            }
        }*/

        if (!mToggleChecklist.isChecked()) {
            insertUnit.setReasonunchecklist(mEtChecklistNot.getText().toString());
        } else {
            insertUnit.setReasonunchecklist("");
        }

        try {
            if (lu.getExpedition().getExpeditionType() != null) {
                int pos = spinnerEkspedisi.getSelectedItemPosition();
                insertUnit.setExpeditionvarianname(lu.getExpedition().getExpeditionType().getExpeditionVarien().get(pos).getVarian());
                insertUnit.setExpeditionprice(lu.getExpedition().getExpeditionType().getExpeditionVarien().get(pos).getHarga());
                insertUnit.setExpeditiontypeid(lu.getExpedition().getExpeditionTypeId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        return insertUnit;
    }

    private Sign setSignature(Unit lu, GetStatus gs) {
        signature1.buildDrawingCache();
        bitmap1 = signature1.getDrawingCache();
        signature2.buildDrawingCache();
        bitmap2 = signature2.getDrawingCache();

        Sign sign = new Sign();

        sign.setSign_ibid_msk(base64Encode(bitmap1));
        sign.setSign_cust_msk(base64Encode(bitmap2));
        sign.setId_pemeriksaanitem(gs.getId_pemeriksaan_item());

        if (lu.getAuction().getId_auctionitem() != 0) {
            sign.setId_auctionitem(lu.getAuction().getId_auctionitem());
        } else {
            sign.setId_auctionitem(lu.getAuction().getIdauction_item());
        }
        return sign;
    }

    public void toggleExpedition(boolean toggle) {
        if (toggle) {
            tvExpeditionTitle.setVisibility(View.VISIBLE);
            vExpeditionSeparator.setVisibility(View.VISIBLE);
            llExpeditionContainer.setVisibility(View.VISIBLE);
        } else {
            //expeditionAmount.setText("");
            tvExpeditionTitle.setVisibility(View.GONE);
            vExpeditionSeparator.setVisibility(View.GONE);
            llExpeditionContainer.setVisibility(View.GONE);
        }
    }

    private List<Lampiran> setLampiran(GetStatus gs) {
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

        //Start-Enhancement
        //setDisabled(expeditionId);
        //setDisabled(expeditionNotes);
        //End-Enhancement
    }

    private void setAllCaps() {
        setCaps(nopol);
        setCaps(alamat_pengemudi);
        setCaps(nama_pengemudi);
        setCaps(kota);
        setCaps(catatan);
        setCaps(pool);
        setCaps(cases);
        //Start-Enhancement
       // setCaps(expeditionId);
        //End-Enhancement
    }

    List<NoPolUnit> listNoPolUnit = new ArrayList<>();

    private void getDropdownList(APICall auctionService, List<String> ls) {
        if (!nopol.getText().toString().equals("") && nopol.getText().toString().length() > 1 ) {

            cpvStart(cpv, bp);
           auctionService.getNoPolUnitM(nopol.getText().toString())
                   .compose(bindToLifecycle())
                   .subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(noPolUnits -> {
                       cpvStop(cpv, bp);
                       listNoPolUnit.clear();
                       try {
                           for (int i = 0; i < noPolUnits.size(); i++) {
                               listNoPolUnit.add(noPolUnits.get(i));
                           }
                       } catch (Exception e) {

                       }
                       ArrayAdapter<NoPolUnit> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_dropdown_item_1line, listNoPolUnit);
                       nopol.setAdapter(adapter);
                       nopol.setThreshold(3);
                       if(listNoPolUnit.size() != 1) nopol.showDropDown();
                   }, throwable -> {
                       cpvStop(cpv, bp);
                       alertDialog(String.valueOf(throwable), 2);
                       Log.e("Error", throwable.getMessage());
                   });

           /* auctionService.getNoPolUnitM(nopol.getText().toString()).enqueue(new Callback<List<NoPolUnit>>() {
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
            });*/
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

    private void getKomponen(APICall auctionService) {
//        auctionService.getPersiapan().enqueue(new Callback<List<Unit>>() {
//            @Override
//            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
//                List<Unit> lu = response.body();
//                getKomponenList(lu);
//            }
//
//            @Override
//            public void onFailure(Call<List<Unit>> call, Throwable t) {
//                errorRetrofit(call, t);
//            }
//        });
        //getKomponenList(StaticUnit.getLu());
    }

    private void getKomponenList(Unit lu) {
        try {
            if (tl.getChildCount() > 1) tl.removeViews(1, tl.getChildCount() - 1);
            size = lu.getKomponen().size();
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
                textStyle(no, row, param_25, String.valueOf(i + 1));
                textStyle(nama, row2, param7, lu.getKomponen().get(i).getNama());
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
        } catch (Exception e) {
        }
    }

    private void imageClick(ImageView imageView, int id, int position) {
        if (id == 1) {
//            imageView.setOnClickListener(v -> lampiranDialog(position));
            imageView.setOnClickListener(v -> goToPemeriksaanActivity(position));

        } else {
            imageView.setOnClickListener(v -> signatureDialog(position));
        }
    }

    private void goToPemeriksaanActivity(int curPosition) {
        Intent intent = new Intent(getActivity(), PemeriksaanActivity.class);
        switch (curPosition){
            case 1:
                intent.putExtra(HelperConstant.LAMPIRAN_KEY, HelperConstant.LAMPIRAN_SEDAN);
                startActivityForResult(intent, HelperConstant.LAMPIRAN_SEDAN);
                break;
            case 2:
                intent.putExtra(HelperConstant.LAMPIRAN_KEY, HelperConstant.LAMPIRAN_NIAGA);
                startActivityForResult(intent, HelperConstant.LAMPIRAN_NIAGA);
                break;
            case 3:
                intent.putExtra(HelperConstant.LAMPIRAN_KEY, HelperConstant.LAMPIRAN_PICKUP);
                startActivityForResult(intent, HelperConstant.LAMPIRAN_PICKUP);
                break;
        }
        /*if (curPosition == 1) {
            intent.putExtra(HelperConstant.LAMPIRAN_KEY, HelperConstant.LAMPIRAN_SEDAN);
            startActivityForResult(intent, HelperConstant.LAMPIRAN_SEDAN);
        } else {
            intent.putExtra(HelperConstant.LAMPIRAN_KEY, HelperConstant.LAMPIRAN_NIAGA);
            startActivityForResult(intent, HelperConstant.LAMPIRAN_NIAGA);
        }*/
    }

    private void lampiranDialog(int id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Lampiran");
        alertDialog.setCancelable(false);

        LinearLayout linearLayout = new LinearLayout(getContext());
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        FrameLayout container = new FrameLayout(getContext());
        onTouchLampiran(container);
        ImageView imageView = new ImageView(getContext());
        if (id == 1) {
            if (bitmap3 == null) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_sedan));
            } else {
                imageView.setImageBitmap(bitmap3);
            }
        } else {
            if (bitmap4 == null) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_niaga));
            } else {
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
        params.setMargins(16, 16, 16, 16);
        reset.setLayoutParams(params);
        reset.setOnClickListener(v -> {
            container.destroyDrawingCache();
            container.removeAllViews();
            if (id == 1) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_sedan));
            } else {
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
            if (id == 1) {
                ibid_sedan.setImageBitmap(bitmap);
                bitmap3 = bitmap;
            } else {
                ibid_niaga.setImageBitmap(bitmap);
                bitmap4 = bitmap;
            }
        });
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> {
        });
        alertDialog.create().show();
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

    private void onTouchLampiran(FrameLayout frameLayout) {
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


    private void postSignature(GetStatus gs, AuctionService auctionService, ProgressDialog pDialog) {
        auctionService.postSignMasuk(setSignature(StaticUnit.getUnit(), gs)).enqueue(new Callback<SignValue>() {
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

    private void postLampiran(GetStatus gs, AuctionService auctionService, ProgressDialog pDialog) {
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

    private void setRequired() {
        String required = "<font color=#FF0000> *</font>";
        nopol_title.setText(Html.fromHtml(nopol_title.getText() + required));
        nama_title.setText(Html.fromHtml(nama_title.getText() + required));
//        alamat_title.setText(Html.fromHtml(alamat_title.getText() + required));
//        kota_title.setText(Html.fromHtml(kota_title.getText() + required));
        telepon_title.setText(Html.fromHtml(telepon_title.getText() + required));
        //Start-Enhancement
      //  expeditionAmountTitle.setText(Html.fromHtml(expeditionAmountTitle.getText() + required));
        //End-Enhancement
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case HelperConstant.LAMPIRAN_SEDAN: {
                if (resultCode == RESULT_OK) {
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(
//                            data.getByteArrayExtra("bitmapArray"), 0,
//                            data.getByteArrayExtra("bitmapArray").length);
                    if (HelperConstant.mTempBitmapSedan != null) {
                        ibid_sedan.setImageBitmap(HelperConstant.mTempBitmapSedan);
                    }
//                    ibid_sedan.setImageBitmap(bitmap);
                }
                break;
            }
            case HelperConstant.LAMPIRAN_NIAGA: {
                if (resultCode == RESULT_OK) {
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(
//                            data.getByteArrayExtra("bitmapArray"), 0,
//                            data.getByteArrayExtra("bitmapArray").length);
                    if (HelperConstant.mTempBitmapNiaga != null) {
                        ibid_niaga.setImageBitmap(HelperConstant.mTempBitmapNiaga);
                    }
//                    ibid_niaga.setImageBitmap(bitmap);
                }
                break;
            }
            /*case HelperConstant.LAMPIRAN_PICKUP: {
                if (resultCode == RESULT_OK) {
//                    Bitmap bitmap = BitmapFactory.decodeByteArray(
//                            data.getByteArrayExtra("bitmapArray"), 0,
//                            data.getByteArrayExtra("bitmapArray").length);
                    if (HelperConstant.mTempBitmapPickup != null) {
                        ibid_pickup.setImageBitmap(HelperConstant.mTempBitmapPickup);
                    }
//                    ibid_sedan.setImageBitmap(bitmap);
                }
                break;
            }*/
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
