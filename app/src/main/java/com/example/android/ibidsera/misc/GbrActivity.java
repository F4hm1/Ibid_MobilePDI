package com.example.android.ibidsera.misc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.android.ibidsera.R;

/**
 * Created by Fahmi Hakim on 20/03/2018.
 * for SERA
 */

public class GbrActivity extends Activity {
    ImageView iv1;

    public static  boolean IMAGEDRAWN;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final GbrView drawView = new GbrView(this);
        setContentView(R.layout.gbr_layout);
        IMAGEDRAWN = false;
        FrameLayout frm_layout=(FrameLayout) findViewById(R.id.main_frame);
        frm_layout.addView(drawView);
        Button btn_undo=(Button) findViewById(R.id.button1);
        btn_undo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                drawView.onClickUndo();
            }
        });

        Button btn_redo=(Button) findViewById(R.id.button2);
        btn_redo.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                drawView.onClickRedo();
            }
        });
    }

}
