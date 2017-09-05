package com.example.android.ibidsera.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseFragment;
import com.example.android.ibidsera.model.ReportModel;
import com.example.android.ibidsera.model.StaticReport;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Yosefricaro on 05/09/2017.
 */

public class DetailReport extends BaseFragment {
    @BindView(R.id.table_detailr) TableLayout tl;
    @BindView(R.id.back) Button back;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View myFragment = inflater.inflate(R.layout.content_detailr, container, false);
        ButterKnife.bind(this, myFragment);

        getReport(StaticReport.getLr());

        cancelListener(back);

        return myFragment;
    }

    public void getReport(List<ReportModel> lu){
        for (int i = 0; i < lu.size(); i++) {
            TableRow row = tableRow();
            TextView unit = textView();
            TextView cabang = textView();
            TextView no_pol = textView();
            TextView merk = textView();
            TextView type = textView();
            TextView tahun = textView();
            TextView penggerak = textView();
            TextView pemilik = textView();
            TextView expedisi = textView();
            TextView tgl_in = textView();
            TextView lama_in = textView();
            TextView tgl_sold = textView();
            TextView tgl_out = textView();
            TextView ikut_lelang = textView();
            TextView cases = textView();

            TableRow.LayoutParams param1 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 1f);
            TableRow.LayoutParams param2 = tableRowLP(0, TableRow.LayoutParams.WRAP_CONTENT, 2f);

            rowColor(row, i);

            textStyle(unit, row, param1, "1");
            textStyle(cabang, row, param1, lu.get(i).getCabang());
            textStyle(no_pol, row, param1, lu.get(i).getNopol());
            textStyle(merk, row, param1, lu.get(i).getNama_merk());
            String tipe = lu.get(i).getNama_merk()+" "+lu.get(i).getCode_b()+" "+
                    lu.get(i).getModel()+" "+lu.get(i).getTransmisi();
            textStyle(type, row, param2, tipe);
            textStyle(tahun, row, param1, lu.get(i).getTahun());
            textStyle(penggerak, row, param1, lu.get(i).getPenggerak());
            textStyle(pemilik, row, param2, lu.get(i).getPemilik());
            textStyle(expedisi, row, param1, lu.get(i).getNama_exps());
            textStyle(tgl_in, row, param1, lu.get(i).getTgl_serah_msk());
            textStyle(lama_in, row, param1, lu.get(i).getNama_exps());
            textStyle(tgl_sold, row, param1, lu.get(i).gettgl_sold());
            textStyle(tgl_out, row, param1, lu.get(i).getTgl_out());
            textStyle(ikut_lelang, row, param1, lu.get(i).getIkutlelang());
            textStyle(cases, row, param1, lu.get(i).getCases());
            tl.addView(row);
        }
    }
}
