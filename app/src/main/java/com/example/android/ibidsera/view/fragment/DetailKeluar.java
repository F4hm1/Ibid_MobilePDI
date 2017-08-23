package com.example.android.ibidsera.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.StaticUnit;
import com.example.android.ibidsera.model.Unit;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
    @BindView(R.id.close) Button close;

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

        cancelListener(close);

        return myFragment;
    }

    public void getDetailk(List<Unit> lu, int id) {
        nopol.setText(lu.get(id).getAuction().getNo_polisi());
        merk.setText(lu.get(id).getNama_merk());
        seri.setText(lu.get(id).getTipe().get(0));
        silinder.setText(lu.get(id).getTipe().get(1));
        grade.setText(lu.get(id).getTipe().get(2));
        sub_grade.setText(lu.get(id).getTipe().get(3));
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
            imgStyle(b_out, row2, paramCheck, lu.get(id).getKomponen().get(i).isTampil_b_klr());
            imgStyle(r_out, row2, paramCheck, lu.get(id).getKomponen().get(i).isTampil_r_klr());
            imgStyle(t_out, row2, paramCheck, lu.get(id).getKomponen().get(i).isTampil_t_klr());
            imgStyle(b_in, row2, paramCheck, lu.get(id).getKomponen().get(i).isTampil_b());
            imgStyle(r_in, row2, paramCheck, lu.get(id).getKomponen().get(i).isTampil_r());
            imgStyle(t_in, row2, paramCheck, lu.get(id).getKomponen().get(i).isTampil_t());

            tl2.addView(row2);
            tl2.setLayoutParams(param6);
            row.addView(tl2);
            tl.addView(row);
        }
    }

    public void imgStyle(ImageView imageView, TableRow row, TableRow.LayoutParams imgParam, boolean bool) {
        imageView.setLayoutParams(imgParam);
        Bitmap bmp;
        if(bool){
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.checklist);
        }else {
            bmp = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
        }
        Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 25, 25, true);
        imageView.setImageBitmap(resizedbitmap);
        imageView.setBackgroundDrawable(null);
        row.addView(imageView);
    }
}