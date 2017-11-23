package com.example.android.ibidsera.view.activity;

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
import android.util.Log;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseActivity;
import com.example.android.ibidsera.util.HelperConstant;
import com.example.android.ibidsera.view.fragment.DrawView;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Randi Dwi Nandra on 17/11/2017.
 * randi.dwinandra@gmail.com
 */

public class PemeriksaanActivity extends AppCompatActivity {

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
        if(lampiranType == HelperConstant.LAMPIRAN_SEDAN){
            bitmapBackground = BitmapFactory.decodeResource(resources, R.drawable.ibid_sedan);
        }else{
            bitmapBackground = BitmapFactory.decodeResource(resources, R.drawable.ibid_niaga);
        }

        mDrawView = new DrawView(this, bitmapBackground, mLinCanvas);

        mLinCanvas.addView(mDrawView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mBtnClear.setOnClickListener(view -> mDrawView.clearAll());
        mBtnSave.setOnClickListener(view -> saveBitmap(mDrawView.getBitmap()));
        mBtnBaret.setOnClickListener(view -> changePaint(DrawView.COLOR_BARET, mBtnBaret));
        mBtnPenyok.setOnClickListener(view -> changePaint(DrawView.COLOR_PENYOK, mBtnPenyok));
        mBtnRetak.setOnClickListener(view -> changePaint(DrawView.COLOR_RETAK, mBtnRetak));
        mBtnPecah.setOnClickListener(view -> changePaint(DrawView.COLOR_PECAH, mBtnPecah));
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
        bitmap.compress(Bitmap.CompressFormat.PNG, 90, bs);
        i.putExtra("bitmapArray", bs.toByteArray());


        Log.d("checking", "check");

        setResult(RESULT_OK, i);
        this.finish();
    }

    private void changePaint(String paintType, Button btn) {
        resetAllPaintButtonState();
        btn.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_SELECTED);
        btn.setTypeface(btn.getTypeface(), Typeface.BOLD);
        btn.setTextColor(Color.BLACK);
        mDrawView.changePaintType(paintType);
    }

    private void resetAllPaintButtonState() {
        mBtnBaret.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_NOT_SELECTED);
        mBtnBaret.setTypeface(mBtnBaret.getTypeface(), Typeface.NORMAL);
        mBtnBaret.setTextColor(Color.WHITE);

        mBtnPenyok.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_NOT_SELECTED);
        mBtnPenyok.setTypeface(mBtnPenyok.getTypeface(), Typeface.NORMAL);
        mBtnPenyok.setTextColor(Color.WHITE);

        mBtnRetak.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_NOT_SELECTED);
        mBtnRetak.setTypeface(mBtnRetak.getTypeface(), Typeface.NORMAL);
        mBtnRetak.setTextColor(Color.WHITE);

        mBtnPecah.setTextSize(TypedValue.COMPLEX_UNIT_SP, TEXT_SIZE_NOT_SELECTED);
        mBtnPecah.setTypeface(mBtnPecah.getTypeface(), Typeface.NORMAL);
        mBtnPecah.setTextColor(Color.WHITE);

        mBtnEraser.setColorFilter(ContextCompat.getColor(
                this,
                android.R.color.white),
                PorterDuff.Mode.MULTIPLY);
    }


}
