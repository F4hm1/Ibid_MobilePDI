package com.example.android.ibidsera.model.api;

import com.example.android.ibidsera.BuildConfig;
import com.example.android.ibidsera.model.Attribute;
import com.example.android.ibidsera.model.GetStatus;
import com.example.android.ibidsera.model.HomeModel;
import com.example.android.ibidsera.model.InsertUnit;
import com.example.android.ibidsera.model.Lampiran;
import com.example.android.ibidsera.model.Login;
import com.example.android.ibidsera.model.NoPolUnit;
import com.example.android.ibidsera.model.Penitip;
import com.example.android.ibidsera.model.PersiapanPost;
import com.example.android.ibidsera.model.PersiapanValue;
import com.example.android.ibidsera.model.PhotoChecklist;
import com.example.android.ibidsera.model.PhotoTtdCustomer;
import com.example.android.ibidsera.model.PhotoTtdIbid;
import com.example.android.ibidsera.model.ReportModel;
import com.example.android.ibidsera.model.Sign;
import com.example.android.ibidsera.model.SignPost;
import com.example.android.ibidsera.model.SignValue;
import com.example.android.ibidsera.model.Unit;
import com.example.android.ibidsera.model.UnitMasukKeluar;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Yosefricaro on 25/07/2017.
 */

public interface AuctionService {

    @GET("index.php/home?api_key=" + BuildConfig.API_KEY) Call<HomeModel> getHome();
    @GET("index.php/persiapan/add_data?api_key=" + BuildConfig.API_KEY) Call<PersiapanValue> getAddPersiapan();
//    @GET("index.php/persiapan?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getPersiapan();
    @GET("http://ibidadmsdevservicestock.azurewebsites.net/index.php/pdi/persiapan") Call<List<Unit>> getPersiapan();
    @GET("persiapan") Call<List<Unit>> getPersiapanUnit();

//    @GET("index.php/unitmasuk?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getUnitM();
    @GET("http://ibidadmsdevservicetaksasi.azurewebsites.net/index.php/pdi/persiapan") Call<List<UnitMasukKeluar>> getUnitM();

//    @GET("index.php/unitkeluar?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getUnitK();
    @GET("http://ibidadmsdevservicetaksasi.azurewebsites.net/index.php/pdi/persiapansudahkeluar") Call<List<UnitMasukKeluar>> getUnitK();


    @GET("index.php/stockmanagement?api_key=" + BuildConfig.API_KEY) Call<List<ReportModel>> getReport();


//    @GET("index.php/persiapan/search/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getSearchPersiapan(@Path("nopol") String nopol);
    @GET("http://ibidadmsdevservicestock.azurewebsites.net/index.php/pdi/search") Call<List<Unit>> getSearchPersiapan(@Query("nopolisi") String nopol);

//    @GET("index.php/unitmasuk/search/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getSearchUnitm(@Path("nopol") String nopol);
    @GET("http://ibidadmsdevservicetaksasi.azurewebsites.net/index.php/pdi/persiapan/search/") Call<List<UnitMasukKeluar>> getSearchUnitm(@Query("nopolisi") String nopol);

//    @GET("index.php/unitkeluar/search/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getSearchUnitk(@Path("nopol") String nopol);
    @GET("http://ibidadmsdevservicetaksasi.azurewebsites.net/index.php/pdi/persiapansudahkeluar/search") Call<List<UnitMasukKeluar>> getSearchUnitk(@Query("nopolisi") String nopol);


    @GET("index.php/masteritem/warna/{key}?api_key=" + BuildConfig.API_KEY) Call<List<Attribute>> getMasterItemWarna(@Path("key") String key);
    @GET("index.php/masteritem/penitip/{key}?api_key=" + BuildConfig.API_KEY) Call<List<Penitip>> getMasterItemPenitip(@Path("key") String key);
    @GET("index.php/masteritem/get_lampiran/{key}?api_key=" + BuildConfig.API_KEY) Call<List<Lampiran>> getLampiran(@Path("key") String key);

//    @GET("index.php/unitmasuk/auto/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getAutoUnitm(@Path("nopol") String nopol);
    @GET("index.php/unitmasuk/auto/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getAutoUnitm(@Path("nopol") String nopol);



    //Get NoPol untuk dropdown list target checklist unit masuk
    @GET("http://ibidadmsdevservicestock.azurewebsites.net/index.php/pdi/search/nopolmasuk") Call<List<NoPolUnit>> getNoPolUnitM(@Query("nopolisi") String noPolkey);

    //Get NoPol untuk dropdown list target checklist unit keluar
    @GET("http://ibidadmsdevservicetaksasi.azurewebsites.net/index.php/pdi/searchkeluar/nopolisi") Call<List<NoPolUnit>> getNoPolUnitK(@Query("nopolisi") String noPolkey);

    //Get detail unit persiapan based on id
    @GET("http://ibidadmsdevservicestock.azurewebsites.net/index.php/pdi/search/masukbyid") Call<Unit> getDetailUnitPersiapan(@Query("AuctionItemId") String auctionItemId);

    //Get detail unit masuk based on id
    @GET("http://ibidadmsdevservicetaksasi.azurewebsites.net/index.php/pdi/persiapan/keluarbyid") Call<UnitMasukKeluar> getDetailUnitMasuk(@Query("AuctionItemId") String auctionItemId);


    @GET("index.php/unitkeluar/auto/{nopol}?api_key=" + BuildConfig.API_KEY) Call<List<Unit>> getAutoUnitk(@Path("nopol") String nopol);


//        @Headers({
//            "ClientService : frontend-client",
//            "Auth-Key : simplerestapi",
//            "Content-Type : application/json"
//    })

//    @POST("index.php/unitmasuk/insert?api_key=" + BuildConfig.API_KEY) Call<GetStatus> insertUnitMasuk(@Body InsertUnit insertUnit);
    //@POST("http://ibidadmsdevservicestock.azurewebsites.net/index.php/pdi/UnitMasuk/insert") Call<GetStatus> insertUnitMasuk(@Body InsertUnit insertUnit);
    @POST("pdi/UnitMasuk/insert") Call<GetStatus> insertUnitMasuk(@Body InsertUnit insertUnit);

    @POST("pdi/UnitMasuk/gambarceklis") Call<GetStatus> postRawJsonChecklist(@Body PhotoChecklist base64);

    @POST("pdi/UnitMasuk/gambarttdibid") Call<GetStatus> postRawJsonTtdIbid(@Body PhotoTtdIbid base64);

    @POST("pdi/UnitMasuk/gambarttdcust") Call<GetStatus> postRawJsonTtdCust(@Body PhotoTtdCustomer base64);

    @FormUrlEncoded
    @POST("pdi/UnitMasuk/gambarceklis") Call<GetStatus> insertGbrCeklistMasuk (
            @Field("idauctionitem") String idAuctionItem,
            @Field("photoceklis") String photoceklis);

    @FormUrlEncoded
    @POST("pdi/UnitMasuk/gambarttdibid") Call<GetStatus> insertGbrIbidMasuk (
            @Field("idauctionitem") String idAuctionItem,
            @Field("photottdibid") String photottdibid);

    @FormUrlEncoded
    @POST("pdi/UnitMasuk/gambarttdcust") Call<GetStatus> insertGbrCustMasuk (
            @Field("idauctionitem") String idAuctionItem,
            @Field("photottdcust") String photottdcust);



//    @POST("index.php/unitkeluar/insert?api_key=" + BuildConfig.API_KEY) Call<GetStatus> insertUnitKeluar(@Body InsertUnit insertUnit);
    @POST("http://ibidadmsdevservicestock.azurewebsites.net/index.php/pdi/unitkeluar/insert") Call<GetStatus> insertUnitKeluar(@Body InsertUnit insertUnit);

    @POST("index.php/persiapan/insert?api_key=" + BuildConfig.API_KEY) Call<GetStatus> insertUnit(@Body PersiapanPost persiapanPost);
    @POST("index.php/masteritem/search?api_key=" + BuildConfig.API_KEY) Call<List<Attribute>> getMasterItem(@Body Attribute item);
//    @POST("index.php/auth/login?api_key=" + BuildConfig.API_KEY) Call<Login> getLogin(@Body Login login);
    @POST("https://ibid.astra.co.id/backend/service/akun/pdi/auth") Call<Login> getLogin(@Body Login login);

    @POST("index.php/masteritem/get_sign_masuk?api_key=" + BuildConfig.API_KEY) Call<SignValue> getSignMasuk(@Body SignPost item);
    @POST("index.php/masteritem/get_sign_keluar?api_key=" + BuildConfig.API_KEY) Call<SignValue> getSignKeluar(@Body SignPost item);
    @POST("index.php/masteritem/post_sign_masuk?api_key=" + BuildConfig.API_KEY) Call<SignValue> postSignMasuk(@Body Sign item);
    @POST("index.php/masteritem/post_sign_keluar?api_key=" + BuildConfig.API_KEY) Call<SignValue> postSignKeluar(@Body Sign item);
    @POST("index.php/masteritem/post_lampiran?api_key=" + BuildConfig.API_KEY) Call<GetStatus> postLampiran(@Body List<Lampiran> item);


}
