package com.example.android.ibidsera.view.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseActivity;
import com.example.android.ibidsera.view.fragment.DrawView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Randi Dwi Nandra on 17/11/2017.
 * randi.dwinandra@gmail.com
 */

public class PemeriksaanActivity extends BaseActivity {

    @BindView(R.id.linear_forcanvas)
    LinearLayout mLinCanvas;

    @BindView(R.id.btn_paint_baret)
    Button mBtnBaret;

    @BindView(R.id.btn_paint_penyok)
    Button mBtnPenyok;

    @BindView(R.id.btn_paint_retak)
    Button mBtnRetak;

    @BindView(R.id.btn_paint_pecah)
    Button mBtnPecah;

    DrawView mDrawView;
    private Bitmap bitmapBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemeriksaan);
        ButterKnife.bind(this);

        Resources resources = this.getResources();
        bitmapBackground = BitmapFactory.decodeResource(resources, R.drawable.ibid_sedan);
        mDrawView = new DrawView(this, bitmapBackground);

        mLinCanvas.addView(mDrawView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mBtnBaret.setOnClickListener(view -> mDrawView.changePaintType(DrawView.COLOR_BARET));
        mBtnPenyok.setOnClickListener(view -> mDrawView.changePaintType(DrawView.COLOR_PENYOK));
        mBtnRetak.setOnClickListener(view -> mDrawView.changePaintType(DrawView.COLOR_RETAK));
        mBtnPecah.setOnClickListener(view -> mDrawView.changePaintType(DrawView.COLOR_PECAH));

    }


}
