package com.example.android.ibidsera.view.fragment.migrate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.base.RxLazyFragment;
import com.example.android.ibidsera.model.StaticUnit;
import com.example.android.ibidsera.model.UnitMasukKeluar;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.model.homelist.StaticUnitHomelist;
import com.example.android.ibidsera.model.homelist.UnitMK;
import com.example.android.ibidsera.service.RetrofitHelper;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fahmi Hakim on 09/03/2018.
 * for SERA
 */

public class DetailMasukFrag extends RxLazyFragment {
    @BindView(R.id.progress_view)
    CircularProgressView cpv;
    @BindView(R.id.background_progress)
    RelativeLayout bp;

    @BindView(R.id.table_detailm)
    TableLayout tl;
    @BindView(R.id.nopol)
    TextView nopol;
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
    @BindView(R.id.pool) TextView pool;
    @BindView(R.id.cases) TextView cases;
    //    @BindView(R.id.imgLampiranMiniBus) ImageView imgMiniBus;
    @BindView(R.id.imgLampiranSedan)
    ImageView imgSedan;
    @BindView(R.id.imgSignCust) ImageView imgSignCust;
    @BindView(R.id.imgSignIbid) ImageView imgSignIbid;
    @BindView(R.id.close)
    Button close;

    private String curUrlLampiran = "";
    private String curUrlTtdIbid = "";
    private String curUrlTtdCust = "";


    public static DetailMasukFrag newInstance() {
        return new DetailMasukFrag();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.content_detailm;
    }


    @Override
    public void finishCreateView(Bundle state) {
        hideKeyboard();

        int id = -1;

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            id = bundle.getInt("idAuction");
        }



        if(id!=-1) {
            //getDetailm(StaticUnit.getLuMasukKeluar(), id);
            getItemList(id);
            cpvStop(cpv, bp);
        } else {
            cpvStart(cpv, bp);
        }

        imageClick(imgSedan, 1, 1);
//        imageClick(imgMiniBus, 1, 2);
        imageClick(imgSignIbid, 2, 1);
        imageClick(imgSignCust, 2, 2);

        cancelListener(close);
    }

    public void getItemList(int idAuction) {

        RetrofitHelper.getUnitMasukDetailApiTaksasiServiceALPHA()
                .getUnitMDetailForm(idAuction)
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(unitMasukKeluars -> {
                    getDetailm(unitMasukKeluars);
                    cpvStop(cpv, bp);
                }, throwable -> {
                    cpvStop(cpv, bp);
                });

    }


    public void getDetailm(UnitMasukKeluar lu) {
        nopol.setText(lu.getAuction().getNo_polisi());
        merk.setText(lu.getAuctiondetail().getNama_merk());
        seri.setText(lu.getAuctiondetail().getTipe().get(0).getAttributedetail());
        silinder.setText(lu.getAuctiondetail().getTipe().get(1).getAttributedetail());
        grade.setText(lu.getAuctiondetail().getTipe().get(2).getAttributedetail());
        sub_grade.setText(lu.getAuctiondetail().getTipe().get(3).getAttributedetail());
        transmisi.setText(lu.getAuctiondetail().getTransmisi());
        tahun.setText(lu.getAuctiondetail().getTahun());
        km.setText(String.valueOf(lu.getAuctiondetail().getKm()));
        nama_pemilik.setText(lu.getAuction().getNama_pengemudi_msk());
        fuel.setText(lu.getAuction().getFuel());
        cat.setText(lu.getAuction().getCat_body());
        tgl_pemeriksaan.setText(lu.getAuction().getTgl_serah_msk().substring(8,10)
                .concat("/" + lu.getAuction().getTgl_serah_msk().substring(5,7)
                        .concat("/" + lu.getAuction().getTgl_serah_msk().substring(0,4))));
        jam.setText(lu.getAuction().getWaktu_msk().substring(0,2) + " : " +
                lu.getAuction().getWaktu_msk().substring(3,5));
        nama_pengemudi.setText(lu.getAuction().getNama_pengemudi_msk());
        alamat_pengemudi.setText(lu.getAuction().getAlamat_pengemudi_msk());
        kota.setText(lu.getAuction().getKota_msk());
        telepon.setText(lu.getAuction().getTelepon_msk());
        catatan.setText(lu.getAuction().getCatatan());
        pool.setText(lu.getAuction().getPoolkota());
        cases.setText(lu.getAuction().getCases());

        for (int i = 0; i < lu.getKomponen().size(); i++) {
            TableRow row = tableRow();
            TableLayout tl2 = tableLayout();
            TableRow row2 = tableRow();
            TextView no = textView();
            TextView nama = textView();
            ImageView b_in = imageView();
            ImageView r_in = imageView();
            ImageView t_in = imageView();

            TableRow.LayoutParams param_3 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, .3f);
            TableRow.LayoutParams param7 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 7f);
            TableRow.LayoutParams param3 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 3f);

            TableRow.LayoutParams paramCheck = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            paramCheck.gravity = Gravity.CENTER;

            rowColor(row, i);
            textStyle(no, row, param_3, String.valueOf(i+1));
            textStyle(nama, row2, param7, lu.getKomponen().get(i).getNama());
            imgStyle(b_in, row2, paramCheck, lu.getKomponen().get(i).getTampil_b());
            imgStyle(r_in, row2, paramCheck, lu.getKomponen().get(i).getTampil_r());
            imgStyle(t_in, row2, paramCheck, lu.getKomponen().get(i).getTampil_t());

            tl2.addView(row2);
            tl2.setLayoutParams(param3);
            row.addView(tl2);
            tl.addView(row);
        }

        Picasso.with(getContext())
                .load(lu.getAuction().getCatatan_image())
                .error(R.drawable.ibid_logo)
                .into(imgSedan);

        Picasso.with(getContext())
                .load(lu.getAuction().getTtd_customer_msk())
                .error(R.drawable.ibid_logo)
                .into(imgSignCust);

        Picasso.with(getContext())
                .load(lu.getAuction().getTtd_ibid_msk())
                .error(R.drawable.ibid_logo)
                .into(imgSignIbid);


        curUrlLampiran = lu.getAuction().getCatatan_image();
        curUrlTtdIbid = lu.getAuction().getTtd_ibid_msk();
        curUrlTtdCust = lu.getAuction().getTtd_customer_msk();

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
            if(curUrlLampiran.equalsIgnoreCase("")) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.ibid_sedan));
            }else {
                Picasso.with(getContext())
                        .load(curUrlLampiran)
                        .error(R.drawable.ibid_logo)
                        .into(imageView);
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
            if(!curUrlTtdIbid.equalsIgnoreCase("")) {
                Picasso.with(getContext())
                        .load(curUrlTtdIbid)
                        .error(R.drawable.ibid_logo)
                        .into(imageView);
            }
        }else {
            if(!curUrlTtdCust.equalsIgnoreCase("")) {
                Picasso.with(getContext())
                        .load(curUrlTtdCust)
                        .error(R.drawable.ibid_logo)
                        .into(imageView);
            }
        }
        container.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, 450);
        alertDialog.setView(container);

        // Set up the buttons
        alertDialog.setPositiveButton("Back", (dialog, which) -> {});
        alertDialog.create().show();
    }
}
