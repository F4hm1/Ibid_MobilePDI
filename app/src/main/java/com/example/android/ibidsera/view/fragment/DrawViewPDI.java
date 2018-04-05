package com.example.android.ibidsera.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.example.android.ibidsera.util.PathColored;

import java.util.ArrayList;

/**
 * Created by Randi Dwi Nandra on 22/11/2017.
 * randi.dwinandra@gmail.com
 */

public class DrawViewPDI extends View implements View.OnTouchListener {
    private Paint mBitmapPaint;
    private Canvas mCanvas;
    private Path mPath;
    private PathColored mPathColored;

    private Paint mPaint;
    private Paint mPaintBaret;
    private Paint mPaintPenyok;
    private Paint mPaintRetak;
    private Paint mPaintPecah;

    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();
    private ArrayList<PathColored> pathSaved = new ArrayList<PathColored>();

    private Bitmap mBitmap;
    private Matrix mTransformation;

    public static final String COLOR_BARET = "#00aaea";
    public static final String COLOR_PENYOK = "#0bea00";
    public static final String COLOR_RETAK = "#eae300";
    public static final String COLOR_PECAH = "#ea0043";


    public DrawViewPDI(Context context, Bitmap mBitmap) {
        super(context);
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.parseColor(COLOR_PECAH));
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(6);


        mPaintBaret = new Paint();
        mPaintBaret.setAntiAlias(true);
        mPaintBaret.setDither(true);
        mPaintBaret.setColor(Color.parseColor(COLOR_BARET));
        mPaintBaret.setStyle(Paint.Style.STROKE);
        mPaintBaret.setStrokeJoin(Paint.Join.ROUND);
        mPaintBaret.setStrokeCap(Paint.Cap.ROUND);
        mPaintBaret.setStrokeWidth(6);

        mPaintPenyok = new Paint();
        mPaintPenyok.setAntiAlias(true);
        mPaintPenyok.setDither(true);
        mPaintPenyok.setColor(Color.parseColor(COLOR_PENYOK));
        mPaintPenyok.setStyle(Paint.Style.STROKE);
        mPaintPenyok.setStrokeJoin(Paint.Join.ROUND);
        mPaintPenyok.setStrokeCap(Paint.Cap.ROUND);
        mPaintPenyok.setStrokeWidth(6);

        mPaintRetak = new Paint();
        mPaintRetak.setAntiAlias(true);
        mPaintRetak.setDither(true);
        mPaintRetak.setColor(Color.parseColor(COLOR_RETAK));
        mPaintRetak.setStyle(Paint.Style.STROKE);
        mPaintRetak.setStrokeJoin(Paint.Join.ROUND);
        mPaintRetak.setStrokeCap(Paint.Cap.ROUND);
        mPaintRetak.setStrokeWidth(6);

        mPaintPecah = new Paint();
        mPaintPecah.setAntiAlias(true);
        mPaintPecah.setDither(true);
        mPaintPecah.setColor(Color.parseColor(COLOR_PECAH));
        mPaintPecah.setStyle(Paint.Style.STROKE);
        mPaintPecah.setStrokeJoin(Paint.Join.ROUND);
        mPaintPecah.setStrokeCap(Paint.Cap.ROUND);
        mPaintPecah.setStrokeWidth(6);



//        this.mBitmap = mBitmap;
        this.mBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        mCanvas = new Canvas(this.mBitmap);

        mPath = new Path();
        paths.add(mPath);

        //mPathColored = new PathColored(mPaint, mPath);
        pathSaved.add(mPathColored);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Bitmap dest = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        float originalWidth = mBitmap.getWidth();
        float originalHeight = mBitmap.getHeight();

        mCanvas = new Canvas(dest);

        float xScale = (float) w/ originalWidth;
        float yScale = (float) h / originalHeight;
        float scale = Math.max(xScale, yScale);
        scale -= 0.5f;

        float xTranslation = (w - originalWidth * scale) / 2.0f;
        float yTranslation = (h - originalHeight * scale) / 2.0f;

        mTransformation = new Matrix();
        mTransformation.postTranslate(xTranslation, yTranslation);
        mTransformation.preScale(scale, scale);

        mBitmapPaint = new Paint();
        mBitmapPaint.setFilterBitmap(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
        canvas.drawBitmap(mBitmap, mTransformation, mBitmapPaint);
//        for (Path p : paths) {
//            canvas.drawPath(p, mPaint);
//        }
        for (PathColored p : pathSaved) {
            canvas.drawPath(p.getPath(), p.getPaint());
        }
    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
        Log.d("PAINT", "start");
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
        Log.d("PAINT", "move");
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);

        //pathSaved.add(new PathColored(mPaint, mPath));

        mPath = new Path();
//        paths.add(mPath);
//        Log.d("PAINT", "up");

//        mPathColored = new PathColored(mPaint, mPath);


    }

    public void onClickUndo() {
        if (paths.size() > 0) {
            undonePaths.add(paths.remove(paths.size() - 1));
            invalidate();
        } else {

        }
        // toast the user
    }

    public void onClickRedo() {
        if (undonePaths.size() > 0) {
            paths.add(undonePaths.remove(undonePaths.size() - 1));
            invalidate();
        } else {

        }
        // toast the user
    }

    public void changePaintType(String colorType){
        switch (colorType){
            case COLOR_BARET:{
                mPaint = mPaintBaret;
                break;
            }
            case COLOR_PENYOK:{
                mPaint = mPaintPenyok;
                break;
            }
            case COLOR_RETAK:{
                mPaint = mPaintRetak;
                break;
            }
            case COLOR_PECAH:{
                mPaint = mPaintPecah;
                break;
            }
        }
        mPathColored.setPaint(mPaint);
//        mPaint.setColor(Color.parseColor(colorType));
    }

    @Override
    public boolean onTouch(View arg0, MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
        }
        return true;
    }

}
