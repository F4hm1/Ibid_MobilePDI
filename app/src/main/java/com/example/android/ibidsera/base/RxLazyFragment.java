package com.example.android.ibidsera.base;

import android.app.Activity;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.text.InputFilter;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import com.example.android.ibidsera.AppController;
import com.example.android.ibidsera.R;
import com.example.android.ibidsera.service.eventbus.EventListener;
import com.example.android.ibidsera.view.activity.MainActivity;
import com.example.android.ibidsera.view.fragment.AddMasuk;
import com.example.android.ibidsera.view.fragment.Persiapan;
import com.example.android.ibidsera.view.fragment.migrate.PersiapanFrag;
import com.github.rahatarmanahmed.cpv.CircularProgressView;
import com.google.common.eventbus.EventBus;
import com.trello.rxlifecycle2.components.support.RxFragment;

import javax.inject.Inject;
import javax.security.auth.PrivateCredentialPermission;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;


public abstract class RxLazyFragment extends RxFragment {

    /// Tambahan

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

    protected void swipeRefresh(SwipeRefreshLayout refreshLayout, int id){
        refreshLayout.setOnRefreshListener(() -> new Handler().postDelayed(() -> {
            ((MainActivity)getActivity()).displaySelectedScreen(id);
            refreshLayout.setRefreshing(false);
        }, 1000));
    }


    protected void errorRetrofit(Call pv, Throwable t){
        try {
            showToast("Tidak Ada Internet");
            Log.e("error", t.getMessage());
        }catch (Exception e){}
    }

    protected void showToast(String message){
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
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


    protected TextView textView(){ return new TextView(getContext()); }

    protected CheckBox checkBox(){ return new CheckBox(getContext()); }

    protected ImageView imageView(){ return new ImageView(getContext()); }

    protected ImageButton imageButton(){ return new ImageButton(getContext()); }

    protected TableRow tableRow(){ return new TableRow(getContext()); }

    protected TableLayout tableLayout(){ return new TableLayout(getContext()); }

    protected TableRow.LayoutParams tableRowLP(int width, int height, float weight){
        return new TableRow.LayoutParams(width, height, weight);
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

    protected void rowColor(TableRow row, int i){
        if(i%2==0){
            row.setBackgroundColor(getResources().getColor(R.color.colorTableOdd));
        }else{
            row.setBackgroundColor(getResources().getColor(R.color.colorTableEven));
        }
    }

    public void imgStyle(ImageView imageView, TableRow row, TableRow.LayoutParams imgParam) {
        imageView.setLayoutParams(imgParam);
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.checklist);
        Bitmap resizedbitmap = Bitmap.createScaledBitmap(bmp, 20, imgParam.height, true);
        imageView.setImageBitmap(resizedbitmap);
        imageView.setBackgroundDrawable(null);
        row.addView(imageView);
    }

    public void checklist(TextView textView, TableRow row, TableRow.LayoutParams param, int id) {

        int[][] states = new int[][]{
                new int[]{android.R.attr.state_pressed}, // pressed
                new int[]{android.R.attr.state_focused}, // focused
                new int[]{android.R.attr.state_enabled} // enabled
        };

        int[] colors = new int[]{
                Color.parseColor("#005599"), // dark blue
                Color.parseColor("#005599"), // dark blue
                Color.parseColor("#2196F3")  // light blue
        };

        ColorStateList list = new ColorStateList(states, colors);
        textView.setLayoutParams(param);
        textView.setClickable(true);
        textView.setTextColor(list);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
        textView.setText("Checklist");
        textView.setOnClickListener(v -> {
            Fragment fragment = new AddMasuk();
            Bundle bundle = new Bundle();
            bundle.putInt("id", id);
            fragment.setArguments(bundle);
            FragmentTransaction ft = this.getActivity().getSupportFragmentManager().beginTransaction();
            ft.addToBackStack(fragment.getClass().getName());
            if (fragment != null) {
                ft.replace(R.id.content_frame, fragment);
                ft.commit();
            }
        });
        row.addView(textView);
    }


    // call by UnitDetailMasuk

    protected void cancelListener(Button cancel){
        cancel.setOnClickListener(v -> {
            getActivity().getSupportFragmentManager().popBackStack();
        });
    }

    ///


    private View parentView;
    private FragmentActivity activity;

    protected boolean isPrepared;

    protected boolean isVisible;
    private Unbinder bind;


    public abstract
    @LayoutRes
    int getLayoutResId();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle state) {
        parentView = inflater.inflate(getLayoutResId(), container, false);
        activity = getSupportActivity();
        return parentView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bind = ButterKnife.bind(this, view);
        finishCreateView(savedInstanceState);
    }


    public abstract void finishCreateView(Bundle state);


    @Override
    public void onResume() {
        super.onResume();
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        bind.unbind();

    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = (FragmentActivity) activity;

    }


    @Override
    public void onDetach() {
        super.onDetach();
        this.activity = null;
    }


    public FragmentActivity getSupportActivity() {
        return super.getActivity();
    }


    public android.app.ActionBar getSupportActionBar() {
        return getSupportActivity().getActionBar();
    }


    public Context getApplicationContext() {
        return this.activity == null ? (getActivity() == null ?
                null : getActivity().getApplicationContext()) : this.activity.getApplicationContext();
    }



    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void lazyLoad() {
    }


    protected void onInvisible() {
    }


    protected void loadData() {
    }


    protected void showProgressBar() {
    }


    protected void hideProgressBar() {
    }


    protected void initRecyclerView() {
    }


    protected void initRefreshLayout() {
    }


    protected void finishTask() {
    }


    @SuppressWarnings("unchecked")
    public <T extends View> T $(int id) {
        return (T) parentView.findViewById(id);
    }
}
