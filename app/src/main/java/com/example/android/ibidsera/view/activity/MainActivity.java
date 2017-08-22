package com.example.android.ibidsera.view.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.android.ibidsera.R;
import com.example.android.ibidsera.base.BaseActivity;
import com.example.android.ibidsera.view.fragment.AddKeluar;
import com.example.android.ibidsera.view.fragment.AddMasuk;
import com.example.android.ibidsera.view.fragment.AddPersiapan;
import com.example.android.ibidsera.view.fragment.Home;
import com.example.android.ibidsera.view.fragment.Persiapan;
import com.example.android.ibidsera.view.fragment.Report;
import com.example.android.ibidsera.view.fragment.UnitKeluar;
import com.example.android.ibidsera.view.fragment.UnitMasuk;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.android.ibidsera.R.id.Addk_button;
import static com.example.android.ibidsera.R.id.Addm_button;
import static com.example.android.ibidsera.R.id.Addp_button;
import static com.example.android.ibidsera.R.id.Persiapan_search;
import static com.example.android.ibidsera.R.id.Unitk_search;
import static com.example.android.ibidsera.R.id.Unitm_search;

public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.toolbar) Toolbar toolbar;
    @BindView(R.id.drawer_layout) DrawerLayout drawer;
    @BindView(R.id.nav_view) NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        displaySelectedScreen(R.id.nav_home);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the HomeModel/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        //calling the method displayselectedscreen and passing the id of selected menuvs
        displaySelectedScreen(item.getItemId());
        return true;
    }

    public void displaySelectedScreen(int itemId) {

        //creating fragment object
        Fragment fragment = null;

        //initializing the fragment object which is selected
        switch (itemId) {
            case R.id.nav_home:
                fragment = new Home();
                break;
            case R.id.nav_persiapan:
                fragment = new Persiapan();
                break;
            case R.id.nav_masuk:
                fragment = new UnitMasuk();
                break;
            case R.id.nav_keluar:
                fragment = new UnitKeluar();
                break;
            case R.id.nav_report:
                fragment = new Report();
                break;
            case R.id.nav_signout:
                SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(this).edit();
                editor.clear().apply();
                openNewActivity(LoginActivity.class);
                break;
        }

        //replacing the fragment
        if (fragment != null) {
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }

    public void ClickHandler(View v) {
        Fragment fragment = null;
        EditText editText;
        Bundle bundle;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        switch(v.getId()) {
            case Addk_button:
                fragment = new AddKeluar();
                ft.addToBackStack("1");
                break;
            case Addm_button:
                fragment = new AddMasuk();
                ft.addToBackStack("1");
                break;
            case Addp_button:
                fragment = new AddPersiapan();
                ft.addToBackStack("1");
                break;
            case Unitk_search:
                fragment = new UnitKeluar();
                editText = (EditText)findViewById(R.id.et_unitk);
                bundle = new Bundle();
                bundle.putString("search", editText.getText().toString());
                fragment.setArguments(bundle);
                break;
            case Unitm_search:
                fragment = new UnitMasuk();
                editText = (EditText)findViewById(R.id.et_unitm);
                bundle = new Bundle();
                bundle.putString("search", editText.getText().toString());
                fragment.setArguments(bundle);
                break;
            case Persiapan_search:
                fragment = new Persiapan();
                editText = (EditText)findViewById(R.id.et_persiapan);
                bundle = new Bundle();
                bundle.putString("search", editText.getText().toString());
                fragment.setArguments(bundle);
                break;
        }

        if (fragment != null) {
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
    }
}
