package com.example.android.ibidsera.misc;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.util.ConvertBitmapMutable;

import java.util.ArrayList;

/**
 * Created by Fahmi Hakim on 20/03/2018.
 * for SERA
 */

/*
public class GbrView extends View {

    // for bitmap
    private Bitmap mainBitmap;
    private Canvas mainCanvas;
    private Paint mainbitmapPaint;

    // for canvas
    private Path currentPath;
    private Paint currentpathPaint;


    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();

    public GbrView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialiseMyComponents();
    }

    public GbrView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialiseMyComponents();
    }

    public GbrView(Context context) {
        super(context);
        initialiseMyComponents();
    }

    private void initialiseMyComponents() {
        currentPath = new Path();

        currentpathPaint = new Paint();
        currentpathPaint.setColor(Color.BLACK);
        currentpathPaint.setStrokeWidth(10);
        currentpathPaint.setStyle(Paint.Style.STROKE);
        currentpathPaint.setStrokeJoin(Paint.Join.ROUND);
        currentpathPaint.setStrokeCap(Paint.Cap.ROUND);
        currentpathPaint.setAntiAlias(true);

        mainbitmapPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for (Path p : paths){
            canvas.drawPath(p, currentpathPaint);
        }
        canvas.drawPath(currentPath, currentpathPaint);

        canvas.drawBitmap(mainBitmap, 0, 0, mainbitmapPaint);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        if (mainBitmap == null) {
            //mainBitmap = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.one);
            mainBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
            mainCanvas = new Canvas(mainBitmap);
            mainCanvas.drawColor(Color.WHITE);
        }
    }

    float lastX;
    float lastY;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        float x = event.getX();
        float y = event.getY();

        switch (action) {
            case MotionEvent.ACTION_DOWN:
                undonePaths.clear();


                GbrActivity.IMAGEDRAWN = true;
                currentPath.moveTo(x, y);
                lastX = x;
                lastY = y;
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                currentPath.quadTo(lastX, lastY, (lastX + x) / 2, (lastY + y) / 2);
                mainCanvas.drawPath(currentPath, currentpathPaint);
                lastX = x;
                lastY = y;
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                currentPath.lineTo(x, y);
                mainCanvas.drawPath(currentPath, currentpathPaint);

                // kill this so we don't double draw
                paths.add(currentPath);
                currentPath = new Path();

                currentPath.rewind();
                invalidate();
                break;
        }

        return true;
    }



    // method to get bitmap
    public Bitmap getMainBitmap() {
        return mainBitmap;
    }

    // method to set bitmap
    public void setMainBitmap(Bitmap mpt) {
        mainBitmap = mpt;
        mainCanvas = new Canvas(mainBitmap);
        postInvalidate();

    }

    public void onClickUndo () {

        if (paths.size()>0)
        {
            undonePaths.add(paths.remove(paths.size()-1));
            invalidate();
        }
    }

    public void onClickRedo (){

        if (undonePaths.size()>0)
        {
            paths.add(undonePaths.remove(undonePaths.size()-1));
            invalidate();
        }
    }
}*/

public class GbrView extends View implements View.OnTouchListener {
    private Canvas mCanvas;
    private Canvas dCanvas;
    private Paint mainbitmapPaint;
    private Bitmap mutableBitmap;
    private Path mPath;
    private Paint mPaint;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();

    private boolean isTouchUp = true;

    private Bitmap logo;
    public GbrView(Context context)
    {
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        this.setOnTouchListener(this);
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(0x77000000);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(12);

        //dCanvas = new Canvas();
        mPath = new Path();
        paths.add(mPath);

        mainbitmapPaint = new Paint();
        logo = BitmapFactory.decodeResource(context.getResources(), R.drawable.one);
        mutableBitmap = logo.copy(Bitmap.Config.ARGB_8888, true);
        mCanvas = new Canvas(); // ConvertBitmapMutable.convertToMutable(mutableBitmap)
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        for (Path p : paths){
            canvas.drawPath(p, mPaint);
            if (!isTouchUp) canvas.drawBitmap(logo, mX, mY, mainbitmapPaint);
        }

    }

    private float mX, mY;
    private static final float TOUCH_TOLERANCE = 4;

    private void touch_start(float x, float y) {
        isTouchUp = true;
        mPath.reset();
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }
    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
            mX = x;
            mY = y;
        }
    }
    private void touch_up() {
        mPath.lineTo(mX, mY);
        // commit the path to our offscreen
        mCanvas.drawPath(mPath, mPaint);
        isTouchUp = false;
        // kill this so we don't double draw
        mPath = new Path();
        paths.add(mPath);
    }

    public void onClickUndo () {
        if (paths.size()>0)
        {
            undonePaths.add(paths.remove(paths.size()-1));
            invalidate();
        }
        else
        {

        }
        //toast the user
    }

    public void onClickRedo (){
        if (undonePaths.size()>0)
        {
            paths.add(undonePaths.remove(undonePaths.size()-1));
            invalidate();
        }
        else
        {

        }
        //toast the user
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
