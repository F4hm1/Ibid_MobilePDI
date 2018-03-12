package com.example.android.ibidsera.view.fragment.migrate;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.Gravity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.RxLazyFragment;
import com.example.android.ibidsera.model.Attribute;
import com.example.android.ibidsera.model.StaticUnit;
import com.example.android.ibidsera.model.Unit;
import com.example.android.ibidsera.service.RetrofitHelper;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Fahmi Hakim on 08/03/2018.
 * for SERA
 */

public class PersiapanFrag extends RxLazyFragment {


    @BindView(R.id.table_persiapan)
    TableLayout tl;
    @BindView(R.id.progress_view)
    CircularProgressView cpv;
    @BindView(R.id.background_progress)
    RelativeLayout bp;
    @BindView(R.id.refreshContainer)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.et_persiapan)
    EditText searchPersiapan;

    public static PersiapanFrag newInstance() {
        return new PersiapanFrag();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.content_persiapan;
    }

    @Override
    public void finishCreateView(Bundle state) {

        hideKeyboard();
        setCaps(searchPersiapan);

        String psearch = "";

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            psearch = bundle.getString("search");
        }

        cpvStart(cpv, bp);

        getItemList(psearch);

//        cpvStop(cpv, bp);
        swipeRefresh(refreshLayout, R.id.nav_persiapan);

    }


    public void getItemList(String nopol){

        if (nopol != null && !nopol.isEmpty()) {

            RetrofitHelper.getAuctionAzureStockService()
                    .getSearchPersiapan(nopol)
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(units -> {
                        StaticUnit.setLu(units);
                        try {
                            if (units.size() != 0) {
                                getPersiapan(units);

                            } else showToast("Tidak ada data");
                        } catch (Exception e){}
                        cpvStop(cpv, bp);
                    }, throwable -> {
                        Log.d("FAILURE", "1: " + throwable.getMessage());
                        //errorRetrofit(call, throwable);
                        cpvStop(cpv, bp);
                    });


        } else {

            RetrofitHelper.getAuctionAzureStockService()
                    .getPersiapan()
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(units -> {
                                StaticUnit.setLu(units);
                                try {
                                if (units.size() != 0) {
                                    getPersiapan(units);

                                }  else showToast("Tidak ada data");}
                                catch (Exception e){}
                                cpvStop(cpv, bp);
                    }, throwable -> {
                                Log.d("FAILURE", "1: " + throwable.getMessage());
                                //errorRetrofit(call, throwable);
                                cpvStop(cpv, bp);
                    });

        }


    }


    public void getPersiapan(List<Unit> lu) {
        try {
            for (int i = 0; i < lu.size(); i++) {
                TableRow row = tableRow();
                TextView no = textView();
                TextView no_pol = textView();
                TextView merk = textView();
                TextView type = textView();
                TextView model = textView();
                TextView tahun = textView();
                TextView unit_in1 = textView();
                ImageView unit_in2 = imageView();

                TableRow.LayoutParams param1 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
                TableRow.LayoutParams param2 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 2f);
                TableRow.LayoutParams paramImg = tableRowLP(0, 20, 1f);

                paramImg.gravity = Gravity.CENTER;

                rowColor(row, i);
                textStyle(no, row, param1, String.valueOf(i + 1));
                textStyle(no_pol, row, param1, lu.get(i).getAuction().getNo_polisi());
                textStyle(merk, row, param1, lu.get(i).getNama_merk());
                String tipe = "";
                for (Attribute t : lu.get(i).getTipe()) {
                    if (t.getAttributedetail() != null) {
                        if (tipe.equals("")) {
                            tipe = t.getAttributedetail();
                        } else {
                            tipe = tipe + " " + t.getAttributedetail();
                        }
                    }
                }
                textStyle(type, row, param2, tipe.concat(" " + lu.get(i).getTransmisi()));
                textStyle(model, row, param1, lu.get(i).getModel());
                textStyle(tahun, row, param1, lu.get(i).getTahun());

                if (lu.get(i).getCount_checklist() == 1) {
                    imgStyle(unit_in2, row, paramImg);
                } else {
                    checklist(unit_in1, row, param1, i);
                }
                tl.addView(row);
            }
        } catch (Exception e) {
        }
    }

    private String intToString(int item){ return String.valueOf(item); }


}