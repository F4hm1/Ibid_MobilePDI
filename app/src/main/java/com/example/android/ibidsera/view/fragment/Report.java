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
import com.example.android.ibidsera.model.Attribute;
import com.example.android.ibidsera.model.StockManagement;
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

        auctionService.getReport().enqueue(new Callback<List<StockManagement>>() {
            @Override
            public void onResponse(Call<List<StockManagement>> call, Response<List<StockManagement>> response) {
                List<StockManagement> lu = response.body();
                getReport(lu);
            }

            @Override
            public void onFailure(Call<List<StockManagement>> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });
    }

    public void getReport(List<StockManagement> lu){
        for (int i = 0; i < lu.size(); i++) {
            TableRow row = tableRow();
            TextView unit = textView();
            TextView cabang = textView();
            TextView no_pol = textView();
            TextView merk = textView();
            TextView type = textView();
            TextView tahun = textView();
            TextView penggerak = textView();
            TextView pemilik = textView();
            TextView expedisi = textView();
            TextView tgl_in = textView();
            TextView lama_in = textView();
            TextView tgl_sold = textView();
            TextView tgl_out = textView();
            TextView ikut_lelang = textView();
            TextView cases = textView();

            TableRow.LayoutParams param1 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams param2 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 2f);

            rowColor(row, i);

            textStyle(unit, row, param1, "1");
            textStyle(cabang, row, param1, lu.get(i).getCabang());
            textStyle(no_pol, row, param1, lu.get(i).getNopol());
            textStyle(merk, row, param1, lu.get(i).getNama_merk());
            String tipe = lu.get(i).getNama_merk()+" "+lu.get(i).getCode_b()+" "+
                    lu.get(i).getModel()+" "+lu.get(i).getTransmisi();
            textStyle(type, row, param2, tipe);
            textStyle(tahun, row, param1, lu.get(i).getTahun());
            textStyle(penggerak, row, param1, lu.get(i).getPenggerak());
            textStyle(pemilik, row, param2, lu.get(i).getPemilik());
            textStyle(tgl_in, row, param1, lu.get(i).getTgl_serah_msk());
            textStyle(tgl_sold, row, param1, lu.get(i).gettgl_sold());
            tl.addView(row);
        }
    }
}
