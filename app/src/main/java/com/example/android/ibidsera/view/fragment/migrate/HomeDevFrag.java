package com.example.android.ibidsera.view.fragment.migrate;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.RxLazyFragment;
import com.example.android.ibidsera.model.HomeModel;
import com.example.android.ibidsera.service.RetrofitHelper;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Fahmi Hakim on 06/03/2018.
 * for SERA
 */

public class HomeDevFrag extends RxLazyFragment {


    @BindView(R.id.progress_view) CircularProgressView cpv;
    @BindView(R.id.background_progress) RelativeLayout bp;
    @BindView(R.id.refreshContainer) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.terdaftar) TextView terdaftar;
    @BindView(R.id.cek_unit_masuk) TextView cek_unit_masuk;
    @BindView(R.id.terjual) TextView terjual;
    @BindView(R.id.cek_unit_keluar) TextView cek_unit_keluar;

    public static HomeDevFrag newInstance() {
        return new HomeDevFrag();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.content_home;
    }

    @Override
    public void finishCreateView(Bundle state) {

        cpvStart(cpv, bp);

        getItemList();

        cpvStop(cpv, bp);

        swipeRefresh(refreshLayout, R.id.nav_home);

    }


    public void getItemList(){


        /*AuctionService auctionService = RetrofitUtil.getAuctionService();

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
        });*/


        RetrofitHelper.getAuctionDevService()
                .getHomeAuction()
                .compose(bindToLifecycle())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe( homeModel -> getHome(homeModel) , throwable -> Toast.makeText(getActivity().getBaseContext(), "Err", Toast.LENGTH_SHORT).show());


    }


    private void getHome(HomeModel lh) {
        try{
            terdaftar.setText(intToString(lh.getCount_daftar()));
            terjual.setText(intToString(lh.getCount_jual()));
            cek_unit_masuk.setText(intToString(lh.getCount_masuk()));
            cek_unit_keluar.setText(intToString(lh.getCount_keluar()));
        }catch (Exception e){}
    }

    private String intToString(int item){ return String.valueOf(item); }


}
