package com.example.android.ibidsera.view.fragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.HomeModel;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Yosefricaro on 24/07/2017.
 */

public class Home extends BaseFragment{

    @BindView(R.id.progress_view) CircularProgressView cpv;
    @BindView(R.id.background_progress) RelativeLayout bp;
    @BindView(R.id.refreshContainer) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.terdaftar) TextView terdaftar;
    @BindView(R.id.cek_unit_masuk) TextView cek_unit_masuk;
    @BindView(R.id.terjual) TextView terjual;
    @BindView(R.id.cek_unit_keluar) TextView cek_unit_keluar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragment = inflater.inflate(R.layout.content_home, container, false);
        ButterKnife.bind(this, myFragment);

        cpvStart(cpv, bp);

        getItemList();

        cpvStop(cpv, bp);

        swipeRefresh(refreshLayout, R.id.nav_home);

        return myFragment;
    }

    public void getItemList(){
        AuctionService auctionService = RetrofitUtil.getAuctionService();

        auctionService.getHome().enqueue(new Callback<HomeModel>() {
            @Override
            public void onResponse(Call<HomeModel> call, Response<HomeModel> response) {
                HomeModel lh = response.body();
                getHome(lh);
            }

            @Override
            public void onFailure(Call<HomeModel> call, Throwable t) {
                errorRetrofit(call, t);
            }
        });
    }

    private void getHome(HomeModel lh) {
        terdaftar.setText(intToString(lh.getCount_daftar()));
        terjual.setText(intToString(lh.getCount_jual()));
        cek_unit_masuk.setText(intToString(lh.getCount_masuk()));
        cek_unit_keluar.setText(intToString(lh.getCount_keluar()));
    }

    private String intToString(int item){ return String.valueOf(item); }

}
