package com.example.android.ibidsera.model.api;

import com.example.android.ibidsera.model.Attribute;
import com.example.android.ibidsera.model.HomeModel;
import com.example.android.ibidsera.model.InsertUnit;
import com.example.android.ibidsera.model.Login;
import com.example.android.ibidsera.model.PersiapanPost;
import com.example.android.ibidsera.model.PersiapanValue;
import com.example.android.ibidsera.model.Unit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by Yosefricaro on 25/07/2017.
 */

public interface AuctionService {
    @GET("index.php/home") Call<HomeModel> getHome();
    @GET("index.php/persiapan/add_data") Call<PersiapanValue> getAddPersiapan();
    @GET("index.php/persiapan") Call<List<Unit>> getPersiapan();
    @GET("index.php/unitmasuk") Call<List<Unit>> getUnitM();
    @GET("index.php/unitkeluar") Call<List<Unit>> getUnitK();
    @GET("index.php/stockmanagement") Call<List<Unit>> getReport();
    @GET("index.php/persiapan/search/{nopol}") Call<List<Unit>> getSearchPersiapan(@Path("nopol") String nopol);
    @GET("index.php/unitmasuk/search/{nopol}") Call<List<Unit>> getSearchUnitm(@Path("nopol") String nopol);
    @GET("index.php/unitkeluar/search/{nopol}") Call<List<Unit>> getSearchUnitk(@Path("nopol") String nopol);

//    @Headers({
//            "ClientService : frontend-client",
//            "Auth-Key : simplerestapi",
//            "Content-Type : application/json"
//    })
    @POST("index.php/unitmasuk/insert") Call<InsertUnit> insertUnitMasuk(@Body InsertUnit insertUnit);
    @POST("index.php/unitkeluar/insert") Call<String> insertUnitKeluar(@Body InsertUnit insertUnit);
    @POST("index.php/persiapan/insert") Call<String> insertUnit(@Body PersiapanPost persiapanPost);
    @POST("index.php/masteritem/search") Call<List<Attribute>> getMasterItem(@Body Attribute item);
    @POST("index.php/auth/login") Call<Login> getLogin(@Body Login login);
}
