package com.example.android.ibidsera.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.Unit;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yosefricaro on 24/07/2017.
 */

public class Report extends BaseFragment{
    @BindView(R.id.table_report) TableLayout tl;
    @BindView(R.id.progress_view) CircularProgressView cpv;
    @BindView(R.id.background_progress) RelativeLayout bp;
    @BindView(R.id.refreshContainer) SwipeRefreshLayout refreshLayout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragment = inflater.inflate(R.layout.content_report, container, false);
        ButterKnife.bind(this, myFragment);

        cpvStart(cpv, bp);

        getItemList();

        cpvStop(cpv, bp);

        swipeRefresh(refreshLayout, R.id.nav_report);

        return myFragment;
    }

    public void getItemList(){
        AuctionService auctionService = RetrofitUtil.getAuctionService();

        auctionService.getReport().enqueue(new Callback<List<Unit>>() {
            @Override
            public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                List<Unit> lu = response.body();
                getReport(lu);
            }

            @Override
            public void onFailure(Call<List<Unit>> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });

    }

    public void getReport(List<Unit> lu){
        for (int i = 0; i < lu.size(); i++) {
            TableRow row = tableRow();
            TextView no_pol = textView();
            TextView merk = textView();
            TextView type = textView();
            TextView tahun = textView();
            TextView penggerak = textView();
            TextView pemilik = textView();
            TextView tgl_in = textView();
            TextView tgl_sold = textView();

            TableRow.LayoutParams param1 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams param2 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 2f);

            rowColor(row, i);
            textStyle(no_pol, row, param1, lu.get(i).getAuction().getNo_polisi());
            textStyle(merk, row, param1, lu.get(i).getNama_merk());
            String tipe = "";
            for (String t : lu.get(i).getTipe()) {
                if(t != null){
                    if(tipe.equals("")){
                        tipe = t;
                    }else {
                        tipe = tipe + " " + t;
                    }
                }
            }
            textStyle(type, row, param2, tipe.concat(" " + lu.get(i).getModel()).concat(" " + lu.get(i).getTransmisi()));
            textStyle(tahun, row, param1, lu.get(i).getTahun());
            textStyle(penggerak, row, param1, lu.get(i).getPenggerak());
            textStyle(pemilik, row, param2, lu.get(i).getPntp().getName_pntp());
            textStyle(tgl_in, row, param1, lu.get(i).getAuction().getTgl_serah_msk());
            textStyle(tgl_sold, row, param1, lu.get(i).getAuction().getTgl_serah_klr());
            tl.addView(row);
        }
    }
}
