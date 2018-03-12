package com.example.android.ibidsera.view.fragment.migrate;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Gravity;
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
import com.example.android.ibidsera.model.homelist.StaticUnitHomelist;
import com.example.android.ibidsera.model.homelist.UnitMasukKeluarHomelist;
import com.example.android.ibidsera.service.RetrofitHelper;
import com.example.android.ibidsera.view.fragment.DetailMasuk;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.util.List;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Fahmi Hakim on 08/03/2018.
 * for SERA
 */

public class UnitMasukFrag extends RxLazyFragment {
    @BindView(R.id.table_unitm)
    TableLayout tl;
    @BindView(R.id.progress_view)
    CircularProgressView cpv;
    @BindView(R.id.background_progress)
    RelativeLayout bp;
    @BindView(R.id.refreshContainer)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.et_unitm)
    EditText searchUnitm;



    public static UnitMasukFrag newInstance() {
        return new UnitMasukFrag();
    }


    @Override
    public int getLayoutResId() {
        return R.layout.content_unitm;
    }

    @Override
    public void finishCreateView(Bundle state) {
        hideKeyboard();
        setCaps(searchUnitm);

        String psearch = "";

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            psearch = bundle.getString("search");
        }

        cpvStart(cpv, bp);

        try {
            getItemList(psearch);
        } catch (Exception e) {
        }

//        cpvStop(cpv, bp);

        swipeRefresh(refreshLayout, R.id.nav_masuk);
    }

    public void getItemList(String nopol) {

        if (nopol != null && !nopol.isEmpty()) {

            RetrofitHelper.getUnitMasukApiTaksasiServiceALPHA()
                    .getSearchUnitm(nopol)
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(unitMasukKeluarHomelists -> {
                        StaticUnitHomelist.setLuMasukKeluar(unitMasukKeluarHomelists);
                        try {
                            if (unitMasukKeluarHomelists.size() != 0) {
                                getUnitm(unitMasukKeluarHomelists);

                            } else {
                                showToast("Tidak ada data");
                            }
                        } catch (Exception e) {
                        }
                        cpvStop(cpv, bp);

                    }, throwable -> cpvStop(cpv, bp)
                    );

        } else {

            RetrofitHelper.getUnitMasukApiTaksasiServiceALPHA()
                    .getUnitM()
                    .compose(bindToLifecycle())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(unitMasukKeluarHomelists -> {
                        StaticUnitHomelist.setLuMasukKeluar(unitMasukKeluarHomelists);
                        try {
                            if (unitMasukKeluarHomelists.size() != 0) {
                                getUnitm(unitMasukKeluarHomelists);

                            } else {
                                showToast("Tidak ada data");
                            }
                        } catch (Exception e) {
                        }
                        cpvStop(cpv, bp);

                    }, throwable -> cpvStop(cpv, bp)
                    );
        }
    }

    public void getUnitm(List<UnitMasukKeluarHomelist> lu) {
        try {
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
                imgStyle(id, row, paramImg, lu.get(i).getAuction().getIdauction_item());
                textStyle(no_pol, row, param1, lu.get(i).getAuction().getNo_polisi());
                textStyle(tgl_doc, row, param1, lu.get(i).getAuction().getTgl_serah_msk());
                textStyle(pengemudi, row, param1, lu.get(i).getAuction().getNama_pengemudi_msk());
                textStyle(merk, row, param1, lu.get(i).getAuctiondetail().getNama_merk());
                String tipe = "";
                for (Attribute t : lu.get(i).getAuctiondetail().getTipe()) {
                    if (t.getAttributedetail() != null) {
                        if (tipe.equals("")) {
                            tipe = t.getAttributedetail();
                        } else {
                            tipe = tipe + " " + t.getAttributedetail();
                        }
                    }
                }
                textStyle(type, row, param2, tipe.concat(" " + lu.get(i).getAuctiondetail().getModel()).concat(" " + lu.get(i).getAuctiondetail().getTransmisi()).concat(" " + lu.get(i).getAuctiondetail().getTahun()));
                tl.addView(row);
            }
        } catch (Exception e) {
        }
    }

    public void imgStyle(ImageButton imageButton, TableRow row, TableRow.LayoutParams imgParam, int idAuction) {

        imageButton.setLayoutParams(imgParam);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.search);
        Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 30, imgParam.height, true);
        imageButton.setImageBitmap(resizedbitmap);
        imageButton.setBackgroundDrawable(null);
        imageButton.setOnClickListener(v -> {
            Fragment fragment = DetailMasukFrag.newInstance();
            Bundle args = new Bundle();
            args.putInt("idAuction", idAuction);
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
