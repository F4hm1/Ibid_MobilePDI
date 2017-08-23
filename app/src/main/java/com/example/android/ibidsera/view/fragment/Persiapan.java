package com.example.android.ibidsera.view.fragment;

import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.method.LinkMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
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
 * Created by Yosefricaro on 19/07/2017.
 */

public class Persiapan extends BaseFragment {
    @BindView(R.id.table_persiapan) TableLayout tl;
    @BindView(R.id.progress_view) CircularProgressView cpv;
    @BindView(R.id.background_progress) RelativeLayout bp;
    @BindView(R.id.refreshContainer) SwipeRefreshLayout refreshLayout;
    @BindView(R.id.et_persiapan) EditText searchPersiapan;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragment = inflater.inflate(R.layout.content_persiapan, container, false);
        ButterKnife.bind(this, myFragment);

        hideKeyboard();

        String psearch = "";

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            psearch = bundle.getString("search");
        }

        cpvStart(cpv, bp);
        getItemList(psearch);
        cpvStop(cpv, bp);
        swipeRefresh(refreshLayout, R.id.nav_persiapan);

        return myFragment;
    }

    public void getItemList(String nopol){
        AuctionService auctionService = RetrofitUtil.getAuctionService();

        if (nopol != null && !nopol.isEmpty()) {
            auctionService.getSearchPersiapan(nopol).enqueue(new Callback<List<Unit>>() {
                @Override
                public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                    List<Unit> lu = response.body();
                    StaticUnit.setLu(lu);
                    getPersiapan(lu);
                }

                @Override
                public void onFailure(Call<List<Unit>> call, Throwable t) {
                    errorRetrofit(call, t);
                }
            });
        }else {
            auctionService.getPersiapan().enqueue(new Callback<List<Unit>>() {
                @Override
                public void onResponse(Call<List<Unit>> call, Response<List<Unit>> response) {
                    List<Unit> lu = response.body();
                    StaticUnit.setLu(lu);
                    getPersiapan(lu);
                }

                @Override
                public void onFailure(Call<List<Unit>> call, Throwable t) {
                    errorRetrofit(call, t);
                }
            });
        }
    }

    public void getPersiapan(List<Unit> lu){
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
            TableRow.LayoutParams paramImg = tableRowLP(0, 25, 1f);

            paramImg.gravity = Gravity.CENTER;

            rowColor(row, i);
            textStyle(no, row, param1, String.valueOf(i + 1));
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
            textStyle(type, row, param2, tipe.concat(" " + lu.get(i).getTransmisi()));
            textStyle(model, row, param1, lu.get(i).getModel());
            textStyle(tahun, row, param1, lu.get(i).getTahun());

            if(lu.get(i).getCount_checklist() == 1){
                imgStyle(unit_in2, row, paramImg);
            }else {
                checklist(unit_in1, row, param1, i);
            }
            tl.addView(row);
        }
    }
    
    public void imgStyle(ImageView imageView, TableRow row, TableRow.LayoutParams imgParam){
        imageView.setLayoutParams(imgParam);
        Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.drawable.checklist);
        Bitmap resizedbitmap=Bitmap.createScaledBitmap(bmp, 25, imgParam.height, true);
        imageView.setImageBitmap(resizedbitmap);
        imageView.setBackgroundDrawable(null);
        row.addView(imageView);
    }

    public void checklist(TextView textView, TableRow row, TableRow.LayoutParams param, int id){

        int[][] states = new int[][] {
                new int[] { android.R.attr.state_pressed}, // pressed
                new int[] { android.R.attr.state_focused}, // focused
                new int[] { android.R.attr.state_enabled} // enabled
        };

        int[] colors = new int[] {
                Color.parseColor("#005599"), // dark blue
                Color.parseColor("#005599"), // dark blue
                Color.parseColor("#2196F3")  // light blue
        };

        ColorStateList list = new ColorStateList(states, colors);
        textView.setLayoutParams(param);
        textView.setClickable(true);
        textView.setTextColor(list);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setText("Checklist");
        textView.setOnClickListener(v -> {
            Fragment fragment = new AddMasuk();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            fragment.setArguments(bundle);
            FragmentTransaction ft = Persiapan.this.getActivity().getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(fragment.getClass().getName());
            if (fragment != null) {
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        row.addView(textView);
    }
}

