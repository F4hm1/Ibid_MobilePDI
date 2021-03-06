package com.example.android.ibidsera.view.fragment.migrate;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.util.HelperConstant;
import com.example.android.ibidsera.view.fragment.DrawView;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Fahmi Hakim on 20/03/2018.
 * for SERA
 */

public class BackupPemeriksaanActivity extends AppCompatActivity {

    @BindView(R.id.linear_forcanvas)
    FrameLayout mLinCanvas;

    @BindView(R.id.btn_paint_baret)
    Button mBtnBaret;

    @BindView(R.id.btn_paint_penyok)
    Button mBtnPenyok;

    @BindView(R.id.btn_paint_retak)
    Button mBtnRetak;

    @BindView(R.id.btn_paint_pecah)
    Button mBtnPecah;

    @BindView(R.id.save)
    Button mBtnSave;

    @BindView(R.id.undo)
    Button mBtnUndo;

    @BindView(R.id.clear)
    Button mBtnClear;

    @BindView(R.id.btn_paint_eraser)
    ImageButton mBtnEraser;

    DrawView mDrawView;
    private Bitmap bitmapBackground;
    private int lampiranType = HelperConstant.LAMPIRAN_SEDAN;

    private static final float TEXT_SIZE_SELECTED = 17;
    private static final float TEXT_SIZE_NOT_SELECTED = 12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemeriksaan);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        if (extras != null) {
            if (extras.containsKey(HelperConstant.LAMPIRAN_KEY)) {
                lampiranType = extras.getInt(HelperConstant.LAMPIRAN_KEY);
            }
        }

        Resources resources = this.getResources();
        float decreasedBitmapScaleSize;
        if (lampiranType == HelperConstant.LAMPIRAN_SEDAN) {
            bitmapBackground = BitmapFactory.decodeResource(resources, R.drawable.ibid_sedan);
            decreasedBitmapScaleSize = 0.7f;
        } else {
            bitmapBackground = BitmapFactory.decodeResource(resources, R.drawable.ibid_niaga);
            decreasedBitmapScaleSize = 1f;
        }

        mDrawView = new DrawView(this, bitmapBackground, mLinCanvas, decreasedBitmapScaleSize);

        if (lampiranType == HelperConstant.LAMPIRAN_SEDAN && HelperConstant.sPathSavedSedan != null) {
            mDrawView.setPathSaved(HelperConstant.sPathSavedSedan);
        } else if (lampiranType == HelperConstant.LAMPIRAN_NIAGA && HelperConstant.sPathSavedNiaga != null) {
            mDrawView.setPathSaved(HelperConstant.sPathSavedNiaga);
        }

        mLinCanvas.addView(mDrawView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mBtnClear.setOnClickListener(view -> mDrawView.clearAll());
        mBtnSave.setOnClickListener(view -> saveBitmap(mDrawView.getBitmap()));
        mBtnBaret.setOnClickListener(view -> changePaint(DrawView.COLOR_BARET, mBtnBaret));
        mBtnPenyok.setOnClickListener(view -> changePaint(DrawView.COLOR_PENYOK, mBtnPenyok));
        mBtnRetak.setOnClickListener(view -> changePaint(DrawView.COLOR_RETAK, mBtnRetak));
        mBtnPecah.setOnClickListener(view -> changePaint(DrawView.COLOR_PECAH, mBtnPecah));
        mBtnUndo.setOnClickListener(view ->   undoPaint());//startActivity(new Intent(this, GbrActivity.class)));
        mBtnEraser.setOnClickListener(view -> {
            resetAllPaintButtonState();
            mBtnEraser.setColorFilter(ContextCompat.getColor(
                    this,
                    android.R.color.black),
                    PorterDuff.Mode.MULTIPLY);
            mDrawView.changePaintType(DrawView.COLOR_ERASER);
        });
        changePaint(DrawView.COLOR_BARET, mBtnBaret);
    }

    private void saveBitmap(Bitmap bitmap) {
        Canvas canvas = new Canvas(bitmap);
        mLinCanvas.draw(canvas);

        Intent i = new Intent();
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bs);

        if (lampiranType == HelperConstant.LAMPIRAN_SEDAN) {
            HelperConstant.mTempBitmapSedan = bitmap;
            HelperConstant.sPathSavedSedan = mDrawView.getPathSaved();
        } else {
            HelperConstant.mTempBitmapNiaga = bitmap;
            HelperConstant.sPathSavedNiaga = mDrawView.getPathSaved();
        }

//        i.putExtra("bitmapArray", bs.toByteArray());

        setResult(RESULT_OK, i);
        this.finish();
    }

    private void changePaint(String paintType, Button btn) {
        resetAllPaintButtonState();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        );
        params.setMargins(0, 0, 0, 0);
//        params.setMargins(getPxFromDp(3), getPxFromDp(3), getPxFromDp(3), getPxFromDp(3));
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_SELECTED);
        btn.setTypeface(btn.getTypeface(), Typeface.BOLD);
        btn.setTextColor(Color.BLACK);
        btn.setLayoutParams(params);
        mDrawView.changePaintType(paintType);
    }

    private void undoPaint() {
        mDrawView.onClickUndo();
    }

    private void resetAllPaintButtonState() {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.MATCH_PARENT,
                1
        );
//        params.setMargins(0, 0, 0, 0);
        params.setMargins(getPxFromDp(3), getPxFromDp(3), getPxFromDp(3), getPxFromDp(3));

        mBtnBaret.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_NOT_SELECTED);
        mBtnBaret.setTypeface(mBtnBaret.getTypeface(), Typeface.NORMAL);
        mBtnBaret.setTextColor(Color.WHITE);
        mBtnBaret.setLayoutParams(params);

        mBtnPenyok.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_NOT_SELECTED);
        mBtnPenyok.setTypeface(mBtnPenyok.getTypeface(), Typeface.NORMAL);
        mBtnPenyok.setTextColor(Color.WHITE);
        mBtnPenyok.setLayoutParams(params);

        mBtnRetak.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_NOT_SELECTED);
        mBtnRetak.setTypeface(mBtnRetak.getTypeface(), Typeface.NORMAL);
        mBtnRetak.setTextColor(Color.WHITE);
        mBtnRetak.setLayoutParams(params);

        mBtnPecah.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_NOT_SELECTED);
        mBtnPecah.setTypeface(mBtnPecah.getTypeface(), Typeface.NORMAL);
        mBtnPecah.setTextColor(Color.WHITE);
        mBtnPecah.setLayoutParams(params);

        mBtnEraser.setColorFilter(ContextCompat.getColor(
                this,
                android.R.color.white),
                PorterDuff.Mode.MULTIPLY);
    }

    private int getPxFromDp(int dp) {
        int px = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp,
                getResources().getDisplayMetrics()
        );
        return px;
    }


}
