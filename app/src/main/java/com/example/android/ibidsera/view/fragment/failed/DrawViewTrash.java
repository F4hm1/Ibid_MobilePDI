package com.example.android.ibidsera.view.fragment.failed;

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
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.util.PathColored;
import com.example.android.ibidsera.util.PointChecklist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fahmi Hakim on 30/03/2018.
 * for SERA
 */

public class DrawViewTrash extends View implements View.OnTouchListener {
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
    private ArrayList<PathColored> undoPathColored = new ArrayList<PathColored>();


    private Bitmap mBitmap;
    private Matrix mTransformation;

    public static final String COLOR_BARET = "#00aaea";
    public static final String COLOR_PENYOK = "#0bea00";
    public static final String COLOR_RETAK = "#eae300";
    public static final String COLOR_PECAH = "#ea0043";
    public static final String COLOR_TEXT = "#A6000000";
    public static final String COLOR_TEXT_BACKGROUND = "#A6eae300";
    public static final String COLOR_ERASER = "0";

    //    private Canvas temp;
    private Bitmap tempBitmap;
    private Bitmap drawableBitmap;

    private boolean isClearing = false;
    private FrameLayout mContainer;
    private Bitmap targetSaveBitmap;
    private float decreasedBitmapScaleSize;

    private Bitmap mBitmapNumber;

    private Context mContext;
    private String textToDraw;
    private Paint mPaintTextBg;
    private TextPaint mPaintText;
    private List<PointChecklist> listPointCheckList = new ArrayList<>();
    private PointChecklist pointChecklist;
    private boolean isTouchUp = true;
    private String currentText = "1";

    public DrawViewTrash (Context context, Bitmap mBitmap, FrameLayout mContainer, float decreasedBitmapScaleSize) {
        super(context);
        mContext = context;
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

        mPaintTextBg = getColorPaint(COLOR_TEXT_BACKGROUND, 1);
        mPaintTextBg.setStyle(Paint.Style.FILL);
        mPaintTextBg.setTextSize(15);
        mPaintText = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mPaintText.setColor(Color.rgb(61, 61, 61));
        mPaintText.setTextSize(15);
        mPaintText.setStyle(Paint.Style.FILL);

        this.mBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true); //  get Drawable Master Sedan or Niaga
        //this.tempBitmap = mBitmap.copy(Bitmap.Config.ARGB_8888, true);  //  get Drawable Master Sedan or Niaga
        //mCanvas = new Canvas(this.mBitmap);
        //bgCanvas = new Canvas(this.mBitmap);


        textToDraw = "1";
        mPaint = mPaintBaret;

        if (mPath == null) {
            mPath = new Path();
            paths.add(mPath);
        }


        //pathSaved.add(new PathColored(mPaint, mPath, xCurTargetPoint, yCurTargetPoint,currentText));

        pointChecklist = new PointChecklist(mBitmapNumber, "");
        this.decreasedBitmapScaleSize = decreasedBitmapScaleSize;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        Bitmap dest = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);


        float originalWidth = mBitmap.getWidth();
        float originalHeight = mBitmap.getHeight();

        //bgCanvas = new Canvas(tempBitmap);

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
        mBitmapPaint.setTextSize(50);
        Typeface typeface = Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD);
        // Set the paint font
        mBitmapPaint.setTypeface(typeface);




        //drawableBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        //mCanvas = new Canvas(drawableBitmap);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        mCanvas = canvas;
        mCanvas.drawBitmap(mBitmap, mTransformation, mBitmapPaint);


        if (!isTouchUp) {
            //bgCanvas.drawBitmap(mBitmapNumber, xCurTargetPoint, yCurTargetPoint, pathSaved.get(0).getPaint());

        }

        for (PathColored p : pathSaved) {
            //bgCanvas.drawPath(p.getPath(), p.getPaint());
            mCanvas.drawPath(p.getPath(), p.getPaint());
            Rect rectangle = new Rect();
            mBitmapPaint.getTextBounds(
                    p.getTextNumber(), // text
                    0, // start
                    p.getTextNumber().length(), // end
                    rectangle // bounds
            );
            mCanvas.drawText(
                    p.getTextNumber(), // Text to draw
                    p.getxNum(), // x
                    p.getyNum(), // y
                    mBitmapPaint // Paint
            );
        }

        if (isClearing) {
            //bgCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
            isClearing = false;
        }


        //canvas.drawBitmap(drawableBitmap, 0,0, mBitmapPaint);
        //canvas.drawBitmap(mBitmap, 0, 0, mBitmapPaint);
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
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_up() {
        mPath.lineTo(mX, mY);
        currentText = textToDraw;
        pathSaved.add(new PathColored(mPaint, mPath, mX, mY,currentText));
        pathSaved.get(pathSaved.size() - 1).setPaint(mPaint);

        mPath = new Path();
        //pathSaved.add(new PathColored(mPaint, mPath, xCurTargetPoint, yCurTargetPoint, textToDraw));


        /*pointChecklist.setText(textToDraw);
        listPointCheckList.add(pointChecklist);
        isTouchUp = false;
        pointChecklist = new PointChecklist(mBitmapNumber, "");*/
        /*AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);
        alertDialog.setTitle("Masukan catatan kerusakan : ");
        alertDialog.setCancelable(true);

        final EditText input = new EditText(mContext);

        FrameLayout container = new FrameLayout(mContext);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.leftMargin = 20;
        params.rightMargin = 20;
        input.setLayoutParams(params);
        container.addView(input);
        alertDialog.setView(container);
        input.setText("");

        alertDialog.setPositiveButton("OK", (dialog, which) -> {
            currentText = input.getText().toString();
            pointChecklist.setText(input.getText().toString());
            listPointCheckList.add(pointChecklist);
            isTouchUp = false;
            pointChecklist = new PointChecklist(mBitmapNumber, "");
        });
        alertDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
        alertDialog.create().show();*/

//        mPathColored = new PathColored(mPaint, mPath);
    }

    public void onClickUndo() {
        if (pathSaved.size() > 0) {
            undoPathColored.add(pathSaved.remove(pathSaved.size() - 1));
            invalidate();
        }
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
        Bitmap bitmapNumber = bitmapNumber = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.one);
        switch (colorType) {
            case COLOR_BARET: {
                mPaint = mPaintBaret;
                bitmapNumber = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.one);
                mBitmapNumber = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.one);
                textToDraw = "1";
                break;
            }
            case COLOR_PENYOK: {
                mPaint = mPaintPenyok;
                bitmapNumber = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.two);
                mBitmapNumber = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.two);
                textToDraw = "2";
                break;
            }
            case COLOR_RETAK: {
                mPaint = mPaintRetak;
                bitmapNumber = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.three);
                mBitmapNumber = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.three);
                textToDraw = "3";
                break;
            }
            case COLOR_PECAH: {
                mPaint = mPaintPecah;
                bitmapNumber = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.four);
                mBitmapNumber = BitmapFactory.decodeResource(mContext.getResources(), R.drawable.four);
                textToDraw = "4";
                break;
            }
            case COLOR_ERASER: {
                mPaint = mPaintTransparent;
                break;
            }
        }
        //pathSaved.get(pathSaved.size() - 1).setPaint(mPaint);
        //pathSaved.get(pathSaved.size() - 1).setPaint(mPaint);
        pointChecklist.setTargetBitmap(bitmapNumber);
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
                putPoint(x, y);
                invalidate();
                break;
        }
        return true;
    }

    private float xCurTargetPoint = 0f;
    private float yCurTargetPoint = 0f;

    private float xCurTargetText = 0f;
    private float yCurTargetText = 0f;

    private void putPoint(float x, float y) {
        xCurTargetPoint = x - (mBitmapNumber.getWidth() / 2f);
        yCurTargetPoint = y - (mBitmapNumber.getHeight() / 2f);

        xCurTargetText = x + (mBitmapNumber.getWidth() / 2f);
        yCurTargetText = y - (mBitmapNumber.getHeight() / 2f);
    }

    private Paint getColorPaint(String color, int strokeSize) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setTextSize(50);
        Typeface typeface = Typeface.create(Typeface.SANS_SERIF,Typeface.BOLD);
        paint.setTypeface(typeface);
        paint.setColor(Color.parseColor(color));
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(strokeSize);
        return paint;
    }

    public void clearAll() {
        mPath.reset();
        for (PathColored p : pathSaved) {
            p.getPath().reset();
        }
        pathSaved.clear();
        pathSaved.add(new PathColored(mPaint, mPath, xCurTargetPoint, yCurTargetPoint, currentText));
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