package com.example.android.ibidsera.view.fragment.migrate;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.RxLazyFragment;
import com.example.android.ibidsera.model.Attribute;
import com.example.android.ibidsera.model.StaticUnit;
import com.example.android.ibidsera.model.UnitMasukKeluar;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.model.homelist.StaticUnitHomelist;
import com.example.android.ibidsera.model.homelist.UnitMasukKeluarHomelist;
import com.example.android.ibidsera.service.RetrofitHelper;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.example.android.ibidsera.view.fragment.DetailKeluar;
import com.example.android.ibidsera.view.fragment.UnitKeluar;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Fahmi Hakim on 08/03/2018.
 * for SERA
 */

public class UnitKeluarFrag extends RxLazyFragment {
    @BindView(R.id.table_unitk)
    TableLayout tl;
    @BindView(R.id.progress_view)
    CircularProgressView cpv;
    @BindView(R.id.background_progress)
    RelativeLayout bp;
    @BindView(R.id.refreshContainer)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.et_unitk)
    EditText searchUnitk;

    public static UnitKeluarFrag newInstance() {
        return new UnitKeluarFrag();
    }


    @Override
    public int getLayoutResId() {
        return R.layout.content_unitk;
    }


    @Override
    public void finishCreateView(Bundle state) {
        hideKeyboard();

        setCaps(searchUnitk);

        String psearch = "";

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            psearch = bundle.getString("search");
        }

        cpvStart(cpv, bp);

        getItemList(psearch);

        swipeRefresh(refreshLayout, R.id.nav_keluar);
    }

    public void getItemList(String nopol){


        if (nopol != null && !nopol.isEmpty()) {

            RetrofitHelper.getUnitKeluarApiTaksasiServiceALPHA()
                    .getSearchUnitk(nopol)
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(unitMasukKeluars -> {
                        StaticUnitHomelist.setLuMasukKeluar(unitMasukKeluars);
                        try {
                            if (unitMasukKeluars.size() != 0 ) {
                                getUnitk(unitMasukKeluars);

                            } else {
                                showToast("Tidak ada data");
                            }
                        }catch (Exception e){}
                        cpvStop(cpv, bp);
                    }, throwable -> cpvStop(cpv, bp)
                    );

        }else {

            RetrofitHelper.getUnitKeluarApiTaksasiServiceALPHA()
                    .getUnitK()
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(unitMasukKeluars -> {
                                StaticUnitHomelist.setLuMasukKeluar(unitMasukKeluars);
                                try {
                                    if (unitMasukKeluars.size() != 0 ) {
                                        getUnitk(unitMasukKeluars);

                                    } else {
                                        showToast("Tidak ada data");
                                    }
                                }catch (Exception e){}
                                cpvStop(cpv, bp);
                            }, throwable -> cpvStop(cpv, bp)
                    );
        }
    }

    public void getUnitk(List<UnitMasukKeluarHomelist> lu){
        try{
            for (int i = 0; i < lu.size(); i++) {
                TableRow row = tableRow();
                ImageButton id = imageButton();
                ImageButton printBtn = imageButton();
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
                imgStyle(id, row, paramImg, lu.get(i).getAuction().getIdauction_item());
                textStyle(no_pol, row, param1, lu.get(i).getAuction().getNo_polisi());
                //textStyle(tgl_doc, row, param1, lu.get(i).getAuction().getTgl_serah_klr());
                //textStyle(pengemudi, row, param1, lu.get(i).getAuction().getNama_pengemudi_klr());
                textStyle(merk, row, param1, lu.get(i).getAuctiondetail().getNama_merk());
                String tipe = "";
                for (Attribute t : lu.get(i).getAuctiondetail().getTipe()) {
                    if(t.getAttributedetail() != null){
                        if(tipe.equals("")){
                            tipe = t.getAttributedetail();
                        }else {
                            tipe = tipe + " " + t.getAttributedetail();
                        }
                    }
                }
                textStyle(type, row, param2, tipe.concat(" " + lu.get(i).getAuctiondetail().getModel()).concat(" " + lu.get(i).getAuctiondetail().getTransmisi()).concat(" " + lu.get(i).getAuctiondetail().getTahun()));
                imgPrint(printBtn, row, paramImg, lu.get(i).getAuction().getIdauction_item());
                tl.addView(row);
            }
        }catch (Exception e){}
    }

    public void imgStyle(ImageButton imageButton, TableRow row, TableRow.LayoutParams imgParam, int idUnit){
        imageButton.setLayoutParams(imgParam);
        Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.drawable.search);
        Bitmap resizedbitmap=Bitmap.createScaledBitmap(bmp, 30, imgParam.height, true);
        imageButton.setImageBitmap(resizedbitmap);
        imageButton.setBackgroundDrawable(null);
        imageButton.setOnClickListener(v -> {
            Fragment fragment = DetailKeluarFrag.newInstance();
            Bundle bundle = new Bundle();
            bundle.putInt("idUnitKeluar", idUnit);
            fragment.setArguments(bundle);
            FragmentTransaction ft = this.getActivity().getSupportFragmentManager().beginTransaction();
            ft.addToBackStack("1");

            if (fragment != null) {
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        row.addView(imageButton);
    }

    public void imgPrint(ImageButton imageButton, TableRow row, TableRow.LayoutParams imgParam, int idUnitKeluar){
        imageButton.setLayoutParams(imgParam);
        Bitmap bmp= BitmapFactory.decodeResource(getResources(), R.drawable.ic_print);
        Bitmap resizedbitmap=Bitmap.createScaledBitmap(bmp, 30, imgParam.height, true);
        imageButton.setImageBitmap(resizedbitmap);
        imageButton.setBackgroundDrawable(null);
        imageButton.setOnClickListener(v -> {
            AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
            WebView wv = new WebView(getActivity());
            wv.loadUrl("http:\\alpha.ibid.astra.co.id/backend/adms/pdi/printout/index/6/" + idUnitKeluar );
            wv.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);

                    return true;
                }
            });

            alert.setView(wv);
            alert.setCancelable(false);
            alert.setNegativeButton("Tutup", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {
                    dialog.dismiss();
                }
            });
            alert.show();
        });
        row.addView(imageButton);
    }

}