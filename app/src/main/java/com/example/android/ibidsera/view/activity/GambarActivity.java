package com.example.android.ibidsera.view.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.util.CanvasView;
import com.example.android.ibidsera.util.PointChecklist;
import com.example.android.ibidsera.view.fragment.DrawView;

import java.io.ByteArrayOutputStream;

public class GambarActivity extends AppCompatActivity {

    private CanvasView mCanvas = null;
    private String bmpBase64;

private  String mTextNotes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gambar);


        Button saveBtn = (Button) findViewById(R.id.save);
        Button undoBtn = (Button) findViewById(R.id.undo);
        Button redoBtn = (Button) findViewById(R.id.clear);
        Button alertBtn = (Button) findViewById(R.id.btn_paint_baret);
        EditText notes = (EditText) findViewById(R.id.notes);
        ImageView gbr = (ImageView) findViewById(R.id.resultGbr);
        FrameLayout ll = (FrameLayout) findViewById(R.id.linear_forcanvas);




        this.mCanvas = (CanvasView)this.findViewById(R.id.canvas);
        Bitmap sedan = BitmapFactory.decodeResource(getResources(), R.drawable.ibid_sedan);
        mCanvas.drawBitmap(sedan);
        mCanvas.setMode(CanvasView.Mode.DRAW);
        mCanvas.setDrawer(CanvasView.Drawer.PEN);
        mCanvas.setPaintStyle(Paint.Style.STROKE);
        mCanvas.setPaintStrokeColor(Color.RED);
        mCanvas.setPaintStrokeWidth(2F);
        mCanvas.setLineCap(Paint.Cap.ROUND);


        alertBtn.setOnClickListener(view -> {
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Masukan catatan kerusakan : ");
            alertDialog.setCancelable(true);

            final EditText input = new EditText(this);

            FrameLayout container = new FrameLayout(this);
            FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 20;
            params.rightMargin = 20;
            input.setLayoutParams(params);
            container.addView(input);
            alertDialog.setView(container);
            input.setText("");

            alertDialog.setPositiveButton("OK", (dialog, which) -> {
                mTextNotes  = input.getText().toString();
                notes.setText(mTextNotes);
            });
            alertDialog.setNegativeButton("Cancel", (dialog, which) -> dialog.cancel());
            alertDialog.create().show();

        });






        saveBtn.setOnClickListener((view) -> {
            ll.setDrawingCacheEnabled(true);
            ll.buildDrawingCache();
            Bitmap bmp = Bitmap.createBitmap(ll.getDrawingCache());
            ll.setDrawingCacheEnabled(false);
            //Bitmap bmp2 = mCanvas.getBitmap();
            //bmpBase64 = base64Encode(bmp);
            //Toast.makeText(this, bmpBase64, Toast.LENGTH_LONG).show();
            gbr.setImageBitmap(bmp);
        });


        undoBtn.setOnClickListener(view -> {
                mCanvas.undo();
        });
        redoBtn.setOnClickListener(view -> {
            mCanvas.redo();
        });

    }


    private String base64Encode(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);
        byte[] byteArray = byteArrayOutputStream2.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }




}
