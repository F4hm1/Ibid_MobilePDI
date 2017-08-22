package com.example.android.ibidsera.view.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.StaticUnit;
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

public class UnitMasuk extends BaseFragment{
    @BindView(R.id.table_unitm) TableLayout tl;
    @BindView(R.id.progress_view) CircularProgressView cpv;
    @BindView(R.id.background_progress) RelativeLayout bp;
    @BindView(R.id.refreshContainer) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.et_unitm) EditText searchUnitm;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragment = inflater.inflate(R.layout.content_unitm, container, false);
        ButterKnife.bind(this, myFragment);

        hideKeyboard();
        setCaps(searchUnitm);

        String psearch = "";

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            psearch = bundle.getString("search");
        }

        cpvStart(cpv, bp);

        getItemList(psearch);

        cpvStop(cpv, bp);

        swipeRefresh(refreshLayout, R.id.nav_masuk);

        return myFragment;
    }

    public void getItemList(String nopol){
        AuctionService auctionService = RetrofitUtil.getAuctionService();

        if (nopol != null && !nopol.isEmpty()) {
            auctionService.getSearchUnitm(nopol).enqueue(new Callback<List<Unit>>() {
                @Override
                public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                    List<Unit> lu = response.body();
                    StaticUnit.setLu(lu);
                    getUnitm(lu);
                }

                @Override
                public void onFailure(Call<List<Unit>> call, Throwable t) {
                    errorRetrofit(call, t);
                }
            });
        }else {
            auctionService.getUnitM().enqueue(new Callback<List<Unit>>() {
                @Override
                public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                    List<Unit> lu = response.body();
                    StaticUnit.setLu(lu);
                    getUnitm(lu);
                }

                @Override
                public void onFailure(Call<List<Unit>> call, Throwable t) {
                    errorRetrofit(call, t);
                }
            });
        }
    }

    public void getUnitm(List<Unit> lu){
        for (int i = 0; i < lu.size(); i++) {
            TableRow row = tableRow();
            ImageButton id = imageButton();
            TextView no_pol = textView();
            TextView tgl_doc = textView();
            TextView pengemudi = textView();
            TextView merk = textView();
            TextView type = textView();

            TableRow.LayoutParams param1 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams param2 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 2f);
            TableRow.LayoutParams paramImg = tableRowLP(0, 30, .5f);
            paramImg.gravity = Gravity.CENTER;

            rowColor(row, i);
            imgStyle(id, row, paramImg, i);
            textStyle(no_pol, row, param1, lu.get(i).getAuction().getNo_polisi());
            textStyle(tgl_doc, row, param1, lu.get(i).getAuction().getTgl_serah_msk());
            textStyle(pengemudi, row, param1, lu.get(i).getAuction().getNama_pengemudi_msk());
            textStyle(merk, row, param1, lu.get(i).getNama_merk());
            String tipe = "";
            for (String t : lu.get(i).getTipe()) {
                if(tipe == ""){
                    tipe = t;
                }else{
                    tipe = tipe + " " + t;
                }
            }
            textStyle(type, row, param2, tipe.concat(" " + lu.get(i).getModel()).concat(" " + lu.get(i).getTransmisi()).concat(" " + lu.get(i).getTahun()));
            tl.addView(row);
        }
    }

    public void imgStyle(ImageButton imageButton, TableRow row, TableRow.LayoutParams imgParam, int id){
        imageButton.setLayoutParams(imgParam);
        Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.drawable.search);
        Bitmap resizedbitmap=Bitmap.createScaledBitmap(bmp, 30, imgParam.height, true);
        imageButton.setImageBitmap(resizedbitmap);
        imageButton.setBackgroundDrawable(null);
        imageButton.setOnClickListener(v -> {
            Fragment fragment = new DetailMasuk();
            Bundle args = new Bundle();
            args.putInt("id",id);
            fragment.setArguments(args);
            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(fragment.getClass().getName());
            if (fragment != null) {
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        row.addView(imageButton);
    }
}
