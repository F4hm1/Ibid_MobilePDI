package com.example.android.ibidsera.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseActivity;
import com.example.android.ibidsera.model.Login;
import com.example.android.ibidsera.model.api.AuctionService;
import com.example.android.ibidsera.util.RetrofitUtil;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        hideKeyboard();

        AuctionService auctionService = RetrofitUtil.getAuctionService();
        SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();

        signin.setOnClickListener(v -> {
            String strEmail = email.getText().toString();
            String strPassword = password.getText().toString();

            if (strEmail.isEmpty()) {
                showToast("Email must be filled!");
            } else if (strPassword.isEmpty()) {
                showToast("Password must be filled!");
            } else {
                auctionService.getLogin(setLogin(strEmail, strPassword)).enqueue(new Callback<Login>() {
                    @Override
                    public void onResponse(Call<Login> call, Response<Login> response) {
                        Log.i("message", response.body().getMessage());
                        if(response.body().getStatus().equals("200")){
                            editor.putString("email", strEmail);
                            editor.putString("password", strPassword);
                            editor.putBoolean("login", true);
                            editor.apply();
                            openNewActivity(MainActivity.class);
                        }else {
                            alertDialog("Incorrect email or password!");
                        }
                    }

                    @Override
                    public void onFailure(Call<Login> call, Throwable t) {
                        errorRetrofit(t);
                    }
                });
            }
        });
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
        email.setText("");
        password.setText("");
        email.requestFocus();
    }
}