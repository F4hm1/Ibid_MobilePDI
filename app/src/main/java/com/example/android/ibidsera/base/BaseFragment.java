package com.example.android.ibidsera.base;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.view.activity.MainActivity;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;

/**
 * Created by harfi on 03/08/2017.
 */

public class BaseFragment<T> extends Fragment {

    protected void errorRetrofit(Call<T> pv, Throwable t){
        Toast.makeText(getContext(), "Tidak ada internet", Toast.LENGTH_SHORT).show();
        Log.e("error", t.getMessage());
    }

    protected ArrayAdapter<String> getAdapter(List<String> list){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(),
                android.R.layout.simple_spinner_dropdown_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    protected ArrayAdapter<CharSequence> setDropdown(int array){
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return adapter;
    }

    protected void textStyle(TextView textView, TableRow row, TableRow.LayoutParams param, String text) {
        textView.setPadding(3, 3, 3, 3);
        textView.setGravity(Gravity.CENTER);
        textView.setTextSize(15);
        textView.setTextColor(getResources().getColor(R.color.black));
        textView.setLayoutParams(param);
        textView.setText(text);
        row.addView(textView);
    }

    protected void swipeRefresh(SwipeRefreshLayout refreshLayout, int id){
        refreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            ((MainActivity)getActivity()).displaySelectedScreen(id);
            refreshLayout.setRefreshing(false);
        }, 1000));
    }

    protected void cancelListener(Button cancel){
        cancel.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    protected void checkAllListener(CheckBox checkBox, String s, int size, HashMap<String, CheckBox> h){
        checkBox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if ( isChecked )
            {
                for (int i = 0; i < size; i++) {
                    h.get(s+i).setChecked(true);
                }
            }else {
                for (int i = 0; i < size; i++) {
                    h.get(s+i).setChecked(false);
                }
            }
        });
    }

    protected void checkboxStyle(CheckBox checkBox, TableRow row, TableRow.LayoutParams param, int i, String id, HashMap<String, CheckBox> h){
        h.put(id+i, checkBox);
        int style = Resources.getSystem().getIdentifier("btn_check_holo_light", "drawable", "android");
        checkBox.setButtonDrawable(style);
        checkBox.setLayoutParams(param);
        row.addView(checkBox);
    }

    protected TableRow.LayoutParams tableRowLP(int width, int height, float weight){
        return new TableRow.LayoutParams(width, height, weight);
    }

    protected void rowColor(TableRow row, int i){
        if(i%2==0){
            row.setBackgroundColor(getResources().getColor(R.color.colorTableOdd));
        }else{
            row.setBackgroundColor(getResources().getColor(R.color.colorTableEven));
        }
    }

    protected void hideKeyboard(){
        View view = getActivity().getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    protected void setCaps(EditText editText){
        editText.setFilters(new InputFilter[] {new InputFilter.AllCaps()});
    }

    protected List<String> required(HashMap<String, EditText> map){
        List<String> ls = new ArrayList<>();

        for (String key : map.keySet()) {
            if (map.get(key).getText().toString().equals("") ){
                map.get(key).setError(key+" is required!");
                ls.add(key);
            }
        }
        return ls;
    }

    protected void cpvStart(CircularProgressView cpv, RelativeLayout bp){
        bp.setBackgroundColor(getResources().getColor(R.color.dark_transparent));
        cpv.setVisibility(View.VISIBLE);
    }

    protected void cpvStop(CircularProgressView cpv, RelativeLayout bp){
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            bp.setBackgroundColor(getResources().getColor(R.color.transparent));
            cpv.setVisibility(View.INVISIBLE);
        }, 1000);
    }

    protected void datePicker(EditText editText){
        Calendar myCalendar = Calendar.getInstance();
        updateLabel(myCalendar, editText);
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(myCalendar, editText);
        };

        editText.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(getContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private void updateLabel(Calendar myCalendar, EditText editText) {
        String myFormat = "MM/dd/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        editText.setText(sdf.format(myCalendar.getTime()));

    }

    protected List<String> requiredSpinner(HashMap<String, Spinner> map){
        List<String> ls = new ArrayList<>();

        for (String key : map.keySet()) {
            if (map.get(key).getSelectedItemPosition() == 0){
                map.get(key).setPrompt(key+" is required!");
                ls.add(key);
            }
        }
        return ls;
    }

    protected void alertLogic(List<String> ls2){
        String required = "";
        for (int i = 0; i <= ls2.size() - 1; i++) {
            if (i == ls2.size() - 1) {
                required = required + ls2.get(i);
            } else
                required = required + ls2.get(i) + ", ";
        }
        alertDialog("Maaf " + required + " belum anda masukan !!", 2);
    }


    protected void alertDialog(String title, int id){
        AlertDialog.Builder alertDialog  = new AlertDialog.Builder(getContext());
        alertDialog.setTitle(title);
        alertDialog.setCancelable(true);
        if(id == 1){
            alertDialog.setPositiveButton("OK", (dialog, which) ->
                    getActivity().getSupportFragmentManager().popBackStack()).show();
        }else {
            alertDialog.setPositiveButton("OK", (dialog, which) -> {
            }).show();
        }
    }

    protected void setDisabled(View v){
        v.setEnabled(false);
    }

    protected TextView textView(){ return new TextView(getContext()); }

    protected CheckBox checkBox(){ return new CheckBox(getContext()); }

    protected ImageView imageView(){ return new ImageView(getContext()); }

    protected ImageButton imageButton(){ return new ImageButton(getContext()); }

    protected TableRow tableRow(){ return new TableRow(getContext()); }

    protected TableLayout tableLayout(){ return new TableLayout(getContext()); }
}
