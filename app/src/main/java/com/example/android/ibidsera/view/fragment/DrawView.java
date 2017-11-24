package com.example.android.ibidsera.view.fragment;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.android.ibidsera.util.PathColored;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

/**
 * Created by Randi Dwi Nandra on 17/11/2017.
 * randi.dwinandra@gmail.com
 */

public class DrawView extends View implements View.OnTouchListener {
    private Paint mBitmapPaint;
    private Canvas mCanvas;
    private Canvas bgCanvas;
    private Path mPath;

    private Paint mPaint;
    private Paint mPaintTransparent;
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
    public static final String COLOR_ERASER = "0";

    //    private Canvas temp;
    private Bitmap tempBitmap;
    private Bitmap drawableBitmap;

    private boolean isClearing = false;
    private FrameLayout mContainer;
    private Bitmap targetSaveBitmap;
    private float decreasedBitmapScaleSize;

    public DrawView(Context context, Bitmap mBitmap, FrameLayout mContainer, float decreasedBitmapScaleSize) {
        super(context);
        this.mContainer = mContainer;
        mBitmapPaint = new Paint(Paint.DITHER_FLAG);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        mPaint = new Paint();

        mPaintTransparent = new Paint();
        mPaintTransparent.setAntiAlias(true);
        mPaintTransparent.setDither(true);
        mPaintTransparent.setColor(getResources().getColor(android.R.color.transparent));
        mPaintTransparent.setStyle(Paint.Style.STROKE);
        mPaintTransparent.setStrokeJoin(Paint.Join.ROUND);
        mPaintTransparent.setStrokeCap(Paint.Cap.ROUND);
        mPaintTransparent.setStrokeWidth(15);
        mPaintTransparent.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));


        mPaintBaret = getColorPaint(COLOR_BARET, 4);
        mPaintPenyok = getColorPaint(COLOR_PENYOK, 4);
        mPaintRetak = getColorPaint(COLOR_RETAK, 4);
        mPaintPecah = getColorPaint(COLOR_PECAH, 4);

        this.mBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        this.tempBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);
        mCanvas = new Canvas(this.mBitmap);
        bgCanvas = new Canvas(this.mBitmap);

        mPath = new Path();
        paths.add(mPath);

        mPaint = mPaintBaret;
        pathSaved.add(new PathColored(mPaint, mPath));
        this.decreasedBitmapScaleSize = decreasedBitmapScaleSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Bitmap dest = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        float originalWidth = mBitmap.getWidth();
        float originalHeight = mBitmap.getHeight();

        mCanvas = new Canvas(dest);

        float xScale = (float) w / originalWidth;
        float yScale = (float) h / originalHeight;
        float scale = Math.max(xScale, yScale);
        scale -= decreasedBitmapScaleSize;

        float xTranslation = (w - originalWidth * scale) / 2.0f;
        float yTranslation = (h - originalHeight * scale) / 2.0f;

        mTransformation = new Matrix();
        mTransformation.postTranslate(xTranslation, yTranslation);
        mTransformation.preScale(scale, scale);


        mBitmapPaint = new Paint();
        mBitmapPaint.setFilterBitmap(true);

        drawableBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        bgCanvas = new Canvas(drawableBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(tempBitmap, mTransformation, mBitmapPaint);
        for (PathColored p : pathSaved) {
            bgCanvas.drawPath(p.getPath(), p.getPaint());
        }
        if(isClearing) {
            bgCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            isClearing = false;
        }
        canvas.drawBitmap(drawableBitmap, 0,0, mBitmapPaint);
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
//        mCanvas.drawPath(mPath, mPaint);

//        pathSaved.add(new PathColored(mPaint, mPath));

        mPath = new Path();
//        paths.add(mPath);

        pathSaved.add(new PathColored(mPaint, mPath));

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

    public void changePaintType(String colorType) {
        switch (colorType) {
            case COLOR_BARET: {
                mPaint = mPaintBaret;
                break;
            }
            case COLOR_PENYOK: {
                mPaint = mPaintPenyok;
                break;
            }
            case COLOR_RETAK: {
                mPaint = mPaintRetak;
                break;
            }
            case COLOR_PECAH: {
                mPaint = mPaintPecah;
                break;
            }
            case COLOR_ERASER:{
                mPaint = mPaintTransparent;
                break;
            }
        }
        pathSaved.get(pathSaved.size() - 1).setPaint(mPaint);
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

    private Paint getColorPaint(String color, int strokeSize){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setColor(Color.parseColor(color));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeSize);
        return paint;
    }

    public void clearAll(){
        mPath.reset();
        for (PathColored p : pathSaved) {
            p.getPath().reset();
        }
        pathSaved.clear();
        pathSaved.add(new PathColored(mPaint, mPath));
        isClearing = true;
        invalidate();
    }

    public Bitmap getBitmap() {
        if (targetSaveBitmap == null) {
            targetSaveBitmap = Bitmap.createBitmap(mContainer.getWidth(), mContainer.getHeight(), Bitmap.Config.RGB_565);
        }
        return targetSaveBitmap;
    }

    public ArrayList<PathColored> getPathSaved() {
        return pathSaved;
    }

    public void setPathSaved(ArrayList<PathColored> pathSaved) {
        this.pathSaved = pathSaved;
        pathSaved.get(pathSaved.size() - 1).setPaint(mPaint);
        pathSaved.get(pathSaved.size() - 1).setPath(mPath);
    }
}
