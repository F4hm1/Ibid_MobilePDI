package com.example.android.ibidsera.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseActivity;
import com.example.android.ibidsera.model.Login;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.github.rahatarmanahmed.cpv.CircularProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {
    @BindView(R.id.email) EditText email;
    @BindView(R.id.password) EditText password;
    @BindView(R.id.signin) Button signin;
    @BindView(R.id.progress_view)
    CircularProgressView cpv;
    @BindView(R.id.background_progress)
    RelativeLayout bp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        hideKeyboard();



        AuctionService auctionService = RetrofitUtil.getAuctionService();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();

        signin.setOnClickListener(v -> {
            bp.setVisibility(View.VISIBLE);
            bp.setBackgroundColor(getResources().getColor(R.color.dark_transparent));
            cpv.setVisibility(View.VISIBLE);
            String strEmail = email.getText().toString();
            String strPassword = password.getText().toString();

            if (strEmail.isEmpty()) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    bp.setVisibility(View.GONE);
                    bp.setBackgroundColor(getResources().getColor(R.color.transparent));
                    cpv.setVisibility(View.INVISIBLE);
                }, 1000);
                showToast("Email must be filled!");
            } else if (strPassword.isEmpty()) {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    bp.setVisibility(View.GONE);
                    bp.setBackgroundColor(getResources().getColor(R.color.transparent));
                    cpv.setVisibility(View.INVISIBLE);
                }, 1000);
                showToast("Password must be filled!");
            } else {
                auctionService.getLogin(setLogin(strEmail, strPassword)).enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Log.i("message", response.body().getMessage());
                        Login login = response.body();
                        if(response.body().getStatus().equals("200")){
                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                bp.setVisibility(View.GONE);
                                bp.setBackgroundColor(getResources().getColor(R.color.transparent));
                                cpv.setVisibility(View.INVISIBLE);
                            }, 1000);
                            editor.putString("email", login.getEmail());
                            editor.putString("nama", login.getName());
                            editor.putInt("userId", login.getUser_id());
                            editor.putBoolean("login", true);
                            editor.apply();
                            openNewActivity(MainActivity.class);

                        }else {
                            Handler handler = new Handler();
                            handler.postDelayed(() -> {
                                bp.setVisibility(View.GONE);
                                bp.setBackgroundColor(getResources().getColor(R.color.transparent));
                                cpv.setVisibility(View.INVISIBLE);
                            }, 1000);
                            alertDialog("Incorrect email or password!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        Handler handler = new Handler();
                        handler.postDelayed(() -> {
                            bp.setVisibility(View.GONE);
                            bp.setBackgroundColor(getResources().getColor(R.color.transparent));
                            cpv.setVisibility(View.INVISIBLE);
                        }, 1000);
                        errorRetrofit(t);
                    }
                });
            }
        });
    }

    @Override
    public void onStop() {
        super.onStop();
        stopDisconnectTimer();
    }

    private void alertDialog(String title){
        AlertDialog.Builder alertDialog  = new AlertDialog.Builder(this);
        alertDialog.setTitle(title);
        alertDialog.setCancelable(true);
        alertDialog.setPositiveButton("OK", (dialog, which) ->
                incorrectLogin()).show();
    }

    private Login setLogin(String strEmail, String strPassword){
        Login login = new Login();
        login.setEmail(strEmail);
        login.setPassword(strPassword);
        return login;
    }

    private void incorrectLogin(){
        password.setText("");
        password.requestFocus();
    }
}