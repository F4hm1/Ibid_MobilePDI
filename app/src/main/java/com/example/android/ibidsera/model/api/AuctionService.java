package com.example.android.ibidsera.model.api;

import com.example.android.ibidsera.BuildConfig;
import com.example.android.ibidsera.model.Attribute;
import com.example.android.ibidsera.model.HomeModel;
import com.example.android.ibidsera.model.InsertUnit;
import com.example.android.ibidsera.model.Lampiran;
import com.example.android.ibidsera.model.Login;
import com.example.android.ibidsera.model.Penitip;
import com.example.android.ibidsera.model.PersiapanPost;
import com.example.android.ibidsera.model.PersiapanValue;
import com.example.android.ibidsera.model.SignPost;
import com.example.android.ibidsera.model.SignValue;
import com.example.android.ibidsera.model.StockManagement;
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
    @GET("index.php/home?api_key=" + BuildConfig.API_KEY) Call<HomeModel> getHome();
    @GET("index.php/persiapan/add_data?api_key=" + BuildConfig.API_KEY) Call<PersiapanValue> getAddPersiapan();
    @GET("index.php/persiapan?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getPersiapan();
    @GET("index.php/unitmasuk?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getUnitM();
    @GET("index.php/unitkeluar?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getUnitK();
    @GET("index.php/stockmanagement?api_key=" + BuildConfig.API_KEY) Call<List<StockManagement>> getReport();
    @GET("index.php/persiapan/search/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getSearchPersiapan(@Path("nopol") String nopol);
    @GET("index.php/unitmasuk/search/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getSearchUnitm(@Path("nopol") String nopol);
    @GET("index.php/unitkeluar/search/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getSearchUnitk(@Path("nopol") String nopol);
    @GET("index.php/masteritem/warna/{key}?api_key=" + BuildConfig.API_KEY) Call<List<Attribute>> getMasterItemWarna(@Path("key") String key);
    @GET("index.php/masteritem/penitip/{key}?api_key=" + BuildConfig.API_KEY) Call<List<Penitip>> getMasterItemPenitip(@Path("key") String key);
    @GET("index.php/masteritem/get_lampiran/{key}?api_key=" + BuildConfig.API_KEY)
    Call<List<Lampiran>> getLampiran(@Path("key") int key);
    @GET("index.php/unitmasuk/auto/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getAutoUnitm(@Path("nopol") String nopol);
    @GET("index.php/unitkeluar/auto/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getAutoUnitk(@Path("nopol") String nopol);


    //    @Headers({
//            "ClientService : frontend-client",
//            "Auth-Key : simplerestapi",
//            "Content-Type : application/json"
//    })
    @POST("index.php/unitmasuk/insert?api_key=" + BuildConfig.API_KEY) Call<InsertUnit> insertUnitMasuk(@Body InsertUnit insertUnit);
    @POST("index.php/unitkeluar/insert?api_key=" + BuildConfig.API_KEY) Call<InsertUnit> insertUnitKeluar(@Body InsertUnit insertUnit);
    @POST("index.php/persiapan/insert?api_key=" + BuildConfig.API_KEY) Call<String> insertUnit(@Body PersiapanPost persiapanPost);
    @POST("index.php/masteritem/search?api_key=" + BuildConfig.API_KEY) Call<List<Attribute>> getMasterItem(@Body Attribute item);
    @POST("index.php/auth/login?api_key=" + BuildConfig.API_KEY) Call<Login> getLogin(@Body Login login);
    @POST("index.php/masteritem/get_sign_masuk?api_key=" + BuildConfig.API_KEY) Call<SignValue> getSignMasuk(@Body SignPost item);
    @POST("index.php/masteritem/get_sign_keluar?api_key=" + BuildConfig.API_KEY) Call<SignValue> getSignKeluar(@Body SignPost item);
    @POST("index.php/masteritem/post_lampiran?api_key=" + BuildConfig.API_KEY) Call<Lampiran> postLampiran(@Body Lampiran item);

}
