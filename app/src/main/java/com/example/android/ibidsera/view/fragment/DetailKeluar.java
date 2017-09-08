package com.example.android.ibidsera.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.Lampiran;
import com.example.android.ibidsera.model.SignPost;
import com.example.android.ibidsera.model.SignValue;
import com.example.android.ibidsera.model.StaticUnit;
import com.example.android.ibidsera.model.Unit;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.util.RetrofitUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yosefricaro on 24/07/2017.
 */

public class DetailKeluar extends BaseFragment {
    @BindView(R.id.table_detailk) TableLayout tl;
    @BindView(R.id.nopol) TextView nopol;
    @BindView(R.id.merk) TextView merk;
    @BindView(R.id.seri) TextView seri;
    @BindView(R.id.silinder) TextView silinder;
    @BindView(R.id.grade) TextView grade;
    @BindView(R.id.sub_grade) TextView sub_grade;
    @BindView(R.id.transmisi) TextView transmisi;
    @BindView(R.id.tahun) TextView tahun;
    @BindView(R.id.km) TextView km;
    @BindView(R.id.nama_pemilik) TextView nama_pemilik;
    @BindView(R.id.fuel) TextView fuel;
    @BindView(R.id.cat) TextView cat;
    @BindView(R.id.tgl_pemeriksaan) TextView tgl_pemeriksaan;
    @BindView(R.id.jam) TextView jam;
    @BindView(R.id.nama_pengemudi) TextView nama_pengemudi;
    @BindView(R.id.alamat_pengemudi) TextView alamat_pengemudi;
    @BindView(R.id.kota) TextView kota;
    @BindView(R.id.telepon) TextView telepon;
    @BindView(R.id.catatan) TextView catatan;
    @BindView(R.id.imgLampiranMiniBus) ImageView imgMiniBus;
    @BindView(R.id.imgLampiranSedan) ImageView imgSedan;
    @BindView(R.id.imgSignCust) ImageView imgSignCust;
    @BindView(R.id.imgSignIbid) ImageView imgSignIbid;
    @BindView(R.id.close) Button close;
    private Bitmap bitmap1;
    private Bitmap bitmap2;
    private Bitmap bitmap3;
    private Bitmap bitmap4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View myFragment = inflater.inflate(R.layout.content_detailk, container, false);
        ButterKnife.bind(this, myFragment);

        hideKeyboard();

        int id = -1;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("id");
        }

        if(id!=-1) {
            getDetailk(StaticUnit.getLu(), id);
        }

        imageClick(imgSedan, 1, 1);
        imageClick(imgMiniBus, 1, 2);
        imageClick(imgSignIbid, 2, 1);
        imageClick(imgSignCust, 2, 2);

        cancelListener(close);

        return myFragment;
    }

    public void getDetailk(List<Unit> lu, int id) {
        nopol.setText(lu.get(id).getAuction().getNo_polisi());
        merk.setText(lu.get(id).getNama_merk());
        seri.setText(lu.get(id).getTipe().get(0).getAttributedetail());
        silinder.setText(lu.get(id).getTipe().get(1).getAttributedetail());
        grade.setText(lu.get(id).getTipe().get(2).getAttributedetail());
        sub_grade.setText(lu.get(id).getTipe().get(3).getAttributedetail());
        transmisi.setText(lu.get(id).getTransmisi());
        tahun.setText(lu.get(id).getTahun());
        km.setText(String.valueOf(lu.get(id).getKm()));
        nama_pemilik.setText(lu.get(id).getPntp().getName_pntp());
        fuel.setText(lu.get(id).getAuction().getFuel());
        cat.setText(lu.get(id).getAuction().getCat_body());
        tgl_pemeriksaan.setText(lu.get(id).getAuction().getTgl_serah_klr().substring(8,10)
                .concat("/" + lu.get(id).getAuction().getTgl_serah_klr().substring(5,7)
                        .concat("/" + lu.get(id).getAuction().getTgl_serah_klr().substring(0,4))));
        jam.setText(lu.get(id).getAuction().getWaktu_klr().substring(0,2) + " : " +
                lu.get(id).getAuction().getWaktu_klr().substring(3,5));
        nama_pengemudi.setText(lu.get(id).getAuction().getNama_pengemudi_klr());
        alamat_pengemudi.setText(lu.get(id).getAuction().getAlamat_pengemudi_klr());
        kota.setText(lu.get(id).getAuction().getKota_klr());
        telepon.setText(lu.get(id).getAuction().getTelepon_klr());
        catatan.setText(lu.get(id).getAuction().getCatatan_klr());

        for (int i = 0; i < lu.get(id).getKomponen().size(); i++) {
            TableRow row = tableRow();
            TableLayout tl2 = tableLayout();
            TableRow row2 = tableRow();
            TextView no = textView();
            TextView nama = textView();
            ImageView b_out = imageView();
            ImageView r_out = imageView();
            ImageView t_out = imageView();
            ImageView b_in = imageView();
            ImageView r_in = imageView();
            ImageView t_in = imageView();

            TableRow.LayoutParams param_5 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, .5f);
            TableRow.LayoutParams param6 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 6f);
            TableRow.LayoutParams paramCheck = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            paramCheck.gravity = Gravity.CENTER;

            rowColor(row, i);
            textStyle(no, row, param_5, String.valueOf(i+1));
            textStyle(nama, row2, param6, lu.get(id).getKomponen().get(i).getNama());
            imgStyle(b_out, row2, paramCheck, lu.get(id).getKomponen().get(i).getTampil_b_klr());
            imgStyle(r_out, row2, paramCheck, lu.get(id).getKomponen().get(i).getTampil_r_klr());
            imgStyle(t_out, row2, paramCheck, lu.get(id).getKomponen().get(i).getTampil_t_klr());
            imgStyle(b_in, row2, paramCheck, lu.get(id).getKomponen().get(i).getTampil_b());
            imgStyle(r_in, row2, paramCheck, lu.get(id).getKomponen().get(i).getTampil_r());
            imgStyle(t_in, row2, paramCheck, lu.get(id).getKomponen().get(i).getTampil_t());

            tl2.addView(row2);
            tl2.setLayoutParams(param6);
            row.addView(tl2);
            tl.addView(row);
        }
        AuctionService auctionService = RetrofitUtil.getAuctionService();
        auctionService.getLampiran(lu.get(id).getAuction().getId_pemeriksaanitem()).enqueue(new Callback<List<Lampiran>>() {
            @Override
            public void onResponse(Call<List<Lampiran>> call, Response<List<Lampiran>> response) {
                List<Lampiran> ls = response.body();

                if (!ls.isEmpty()) {
                    for (int i = 0; i < ls.size() && i < 2; i++) {

                        if (ls.get(i).getNama_lampiran().equals("SEDAN")) {
                            bitmap3 = decodeImg(ls.get(i).getBase64img());
                            imgSedan.setImageBitmap(bitmap3);
                        } else if (ls.get(i).getNama_lampiran().equals("MINIBUS")) {
                            bitmap4 = decodeImg(ls.get(i).getBase64img());
                            imgMiniBus.setImageBitmap(bitmap4);
                        }
                    }
                }
            }

            @Override
            public void onFailure(Call<List<Lampiran>> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });

        SignPost sp = new SignPost();
        sp.setId_auctionitem(lu.get(id).getAuction().getId_auctionitem());
        sp.setId_pemeriksaanitem(lu.get(id).getAuction().getId_pemeriksaanitem());

        auctionService.getSignKeluar(sp).enqueue(new Callback<SignValue>() {
            @Override
            public void onResponse(Call<SignValue> call, Response<SignValue> response) {
                SignValue sv = response.body();

                if (sv != null) {
                    bitmap1 = decodeImg(sv.getSign_ibid_klr());
                    bitmap2 = decodeImg(sv.getSign_cust_klr());
                    imgSignCust.setImageBitmap(decodeImg(sv.getSign_cust_klr()));
                    imgSignIbid.setImageBitmap(decodeImg(sv.getSign_ibid_klr()));
                }

            }

            @Override
            public void onFailure(Call<SignValue> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });

    }

    public void imgStyle(ImageView imageView, TableRow row, TableRow.LayoutParams imgParam, String check) {
        imageView.setLayoutParams(imgParam);
        Bitmap bmp;
        if(check.equals("true")){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.checklist);
        }else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
        }
        Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 20, 20, true);
        imageView.setImageBitmap(resizedbitmap);
        imageView.setBackgroundDrawable(null);
        row.addView(imageView);
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

        FrameLayout container = new FrameLayout(getContext());
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
        alertDialog.setView(container);

        // Set up the buttons
        alertDialog.setPositiveButton("Back", (dialog, which) -> {});
        alertDialog.create().show();
    }

    private void signatureDialog(int id){
        AlertDialog.Builder alertDialog  = new AlertDialog.Builder(getContext());
        alertDialog.setTitle("Signature Here");
        alertDialog.setCancelable(true);

        FrameLayout container = new FrameLayout(getContext());
        ImageView imageView = new ImageView(getContext());
        if(id == 1){
            if(bitmap1 == null) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_sedan));
            }else {
                imageView.setImageBitmap(bitmap1);
            }
        }else {
            if(bitmap2 == null) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_niaga));
            }else {
                imageView.setImageBitmap(bitmap2);
            }
        }
        container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, 450);
        alertDialog.setView(container);

        // Set up the buttons
        alertDialog.setPositiveButton("Back", (dialog, which) -> {});
        alertDialog.create().show();
    }
}