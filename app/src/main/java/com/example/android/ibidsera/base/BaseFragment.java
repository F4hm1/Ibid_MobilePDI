package com.example.android.ibidsera.base;

import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.text.InputFilter;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

//import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;
import io.github.douglasjunior.androidSimpleTooltip.SimpleTooltip;
import retrofit2.Call;

/**
 * Created by harfi on 03/08/2017.
 */

public class BaseFragment<T> extends Fragment {


    ////////////////

/*
    @Override
    public int getLayoutResId() {
        return 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        return super.onCreateView(inflater, container, state);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void finishCreateView(Bundle state) {

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public FragmentActivity getSupportActivity() {
        return super.getSupportActivity();
    }

    @Override
    public ActionBar getSupportActionBar() {
        return super.getSupportActionBar();
    }

    @Override
    public Context getApplicationContext() {
        return super.getApplicationContext();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    protected void onVisible() {
        super.onVisible();
    }

    @Override
    protected void lazyLoad() {
        super.lazyLoad();
    }

    @Override
    protected void onInvisible() {
        super.onInvisible();
    }

    @Override
    protected void loadData() {
        super.loadData();
    }

    @Override
    protected void showProgressBar() {
        super.showProgressBar();
    }

    @Override
    protected void hideProgressBar() {
        super.hideProgressBar();
    }

    @Override
    protected void initRecyclerView() {
        super.initRecyclerView();
    }

    @Override
    protected void initRefreshLayout() {
        super.initRefreshLayout();
    }

    @Override
    protected void finishTask() {
        super.finishTask();
    }

    @Override
    public <T extends View> T $(int id) {
        return super.$(id);
    }
*/


    //////////////////


    protected void errorRetrofit(Call<T> pv, Throwable t){
        try {
            showToast("Tidak Ada Internet");
            Log.e("error", t.getMessage());
        }catch (Exception e){}
    }

    protected void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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

    protected void datePicker(EditText editText, int temp){
        Calendar myCalendar = Calendar.getInstance();
        if(temp != 1){
            updateLabel(myCalendar, editText);
        }
        DatePickerDialog.OnDateSetListener date = (view, year, monthOfYear, dayOfMonth) -> {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel(myCalendar, editText);
        };

        editText.setOnClickListener(v -> {
            new DatePickerDialog(getContext(), date, myCalendar
                    .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
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
        alertDialog.setCancelable(false);
        if(id == 1){
            alertDialog.setPositiveButton("OK", (dialog, which) ->
                    getActivity().getSupportFragmentManager().popBackStack()).show();
        }else {
            alertDialog.setPositiveButton("OK", (dialog, which) -> {}).show();
        }
    }

    protected String base64Encode(Bitmap bitmap){
        ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream2);
        byte[] byteArray = byteArrayOutputStream2.toByteArray();
        return Base64.encodeToString(byteArray, Base64.DEFAULT);
    }

    protected Bitmap decodeImg(String encode){
        Bitmap decodedByte = null;
        try {
            byte[] decodedString = Base64.decode(encode.replaceAll("\n", ""), Base64.DEFAULT);
            decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
        }catch (Exception e){}

        return decodedByte;
    }

    protected void toolTip(EditText editText, String text){
        editText.setOnFocusChangeListener((v, hasFocus) -> {
            if(hasFocus){
                new SimpleTooltip.Builder(getContext())
                        .anchorView(editText)
                        .text(text)
                        .gravity(Gravity.END)
                        .animated(true)
                        .build()
                        .show();
            }
        });
    }

    protected void setDisabled(View v){
        v.setEnabled(false);
    }

    protected void setVisible(View view){
        view.setVisibility(View.VISIBLE);
    }

    protected void setGone(View view){
        view.setVisibility(View.GONE);
    }

    protected TextView textView(){ return new TextView(getContext()); }

    protected CheckBox checkBox(){ return new CheckBox(getContext()); }

    protected ImageView imageView(){ return new ImageView(getContext()); }

    protected ImageButton imageButton(){ return new ImageButton(getContext()); }

    protected TableRow tableRow(){ return new TableRow(getContext()); }

    protected TableLayout tableLayout(){ return new TableLayout(getContext()); }





}
