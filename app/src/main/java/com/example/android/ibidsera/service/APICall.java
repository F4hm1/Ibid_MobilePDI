package com.example.android.ibidsera.service;

/**
 * Created by Fahmi Hakim on 07/03/2018.
 * for SERA
 */

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
import com.example.android.ibidsera.model.PhotoChecklist;
import com.example.android.ibidsera.model.PhotoTtdCustomer;
import com.example.android.ibidsera.model.PhotoTtdIbid;
import com.example.android.ibidsera.model.ReportModel;
import com.example.android.ibidsera.model.Sign;
import com.example.android.ibidsera.model.SignValue;
import com.example.android.ibidsera.model.Unit;
import com.example.android.ibidsera.model.UnitMasukKeluar;
import com.example.android.ibidsera.model.homelist.UnitMasukKeluarHomelist;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface APICall {

    @GET("index.php/home?api_key=" + BuildConfig.API_KEY) Observable<HomeModel> getHomeAuction();
    @GET("pdi/persiapan") Observable<List<Unit>> getPersiapan(); // API Url from Stock
    @GET("pdi/search") Observable<List<Unit>> getSearchPersiapan(@Query("nopolisi") String nopol);

    @GET("pdi/persiapan") Observable<List<UnitMasukKeluarHomelist>> getUnitM(); // API Url from Taksasi
    @GET("pdi/persiapan/search") Observable<List<UnitMasukKeluarHomelist>> getSearchUnitm(@Query("nopolisi") String nopol);
    @GET("pdi/search/nopolmasuk") Observable<List<NoPolUnit>> getNoPolUnitM(@Query("nopolisi") String noPolkey);
    @GET("pdi/searchkeluar/nopolisi") Observable<List<NoPolUnit>> getNoPolUnitK(@Query("nopolisi") String noPolkey);
    @GET("pdi/search/masukbyid") Observable<Unit> getDetailUnitPersiapan(@Query("AuctionItemId") String auctionItemId);
    @GET("pdi/persiapan/keluarbyid") Observable<Unit> getDetailUnitKeluar(@Query("AuctionItemId") String auctionItemId);
    @POST("pdi/UnitMasuk/insert") Observable<GetStatus> insertUnitMasuk(@Body InsertUnit insertUnit);
    @POST("pdi/unitkeluar/insert") Observable<GetStatus> insertUnitKeluar(@Body InsertUnit insertUnit);
    @POST("pdi/unitkeluar/insert") Call<GetStatus> insertUnitKeluarFix(@Body InsertUnit insertUnit);


    @POST("pdi/UnitMasuk/gambarceklis") Call<GetStatus> postRawJsonGbrMasukChecklist(@Body PhotoChecklist base64);
    @POST("pdi/UnitMasuk/gambarttdibid") Call<GetStatus> postRawJsonGbrMasukTtdIbid(@Body PhotoTtdIbid base64);
    @POST("pdi/UnitMasuk/gambarttdcust") Call<GetStatus> postRawJsonGbrMasukTtdCust(@Body PhotoTtdCustomer base64);


    @POST("pdi/UnitMasuk/gambarceklis") Observable<GetStatus> postObsRawJsonGbrMasukChecklist(@Body PhotoChecklist base64);
    @POST("pdi/UnitMasuk/gambarttdibid") Observable<GetStatus> postObsRawJsonGbrMasukTtdIbid(@Body PhotoTtdIbid base64);
    @POST("pdi/UnitMasuk/gambarttdcust") Observable<GetStatus> postObsRawJsonGbrMasukTtdCust(@Body PhotoTtdCustomer base64);

    @POST("pdi/unitkeluar/gambarceklis") Observable<GetStatus> postObsRawGbrKeluarJsonChecklist(@Body PhotoChecklist base64);
    @POST("pdi/unitkeluar/gambarttdibid") Observable<GetStatus> postObsRawGbrKeluarJsonTtdIbid(@Body PhotoTtdIbid base64);
    @POST("pdi/unitkeluar/gambarttdcust") Observable<GetStatus> postObsRawGbrKeluarJsonTtdCust(@Body PhotoTtdCustomer base64);

    @POST("pdi/unitkeluar/gambarceklis") Call<GetStatus> postRawJsonChecklist(@Body PhotoChecklist base64);
    @POST("pdi/unitkeluar/gambarttdibid") Call<GetStatus> postRawJsonTtdIbid(@Body PhotoTtdIbid base64);
    @POST("pdi/unitkeluar/gambarttdcust") Call<GetStatus> postRawJsonTtdCust(@Body PhotoTtdCustomer base64);


    @FormUrlEncoded
    @POST("pdi/UnitMasuk/gambar") Call<GetStatus> insertPicMasuk (
            @Field("idauctionitem") String idAuctionItem,
            @Field("photo1") String photo1,
            @Field("photo2") String photo2,
            @Field("photo3") String photo3);

    @GET("pdi/masuk/keluarbyid") Observable<UnitMasukKeluar> getUnitMDetailForm(@Query("AuctionItemId") int AuctionItemId); // API Url from Taksasi
    @GET("pdi/persiapansudahkeluar") Observable<List<UnitMasukKeluarHomelist>> getUnitK();
    @GET("pdi/persiapansudahkeluar/search") Observable<List<UnitMasukKeluarHomelist>> getSearchUnitk(@Query("nopolisi") String nopol);
    @GET("pdi/keluar/keluarbyid") Observable<UnitMasukKeluar> getUnitKDetailForm(@Query("AuctionItemId") int AuctionItemId); // API Url from Taksasi


    @GET("index.php/stockmanagement?api_key=" + BuildConfig.API_KEY) Observable<List<ReportModel>> getReport();
    @GET("index.php/masteritem/warna/{key}?api_key=" + BuildConfig.API_KEY) Observable<List<Attribute>> getMasterItemWarna(@Path("key") String key);
    @GET("index.php/masteritem/penitip/{key}?api_key=" + BuildConfig.API_KEY) Observable<List<Penitip>> getMasterItemPenitip(@Path("key") String key);
    @GET("http://ibidadmsdevservicetaksasi.azurewebsites.net/index.php/pdi/persiapan/keluarbyid") Observable<List<UnitMasukKeluar>> getDetailUnitMasuk(@Query("AuctionItemId") String auctionItemId);
    //@POST("http://ibidadmsdevservicestock.azurewebsites.net/index.php/pdi/UnitMasuk/insert") Observable<GetStatus> insertUnitMasuk(@Body InsertUnit insertUnit);
    @POST("index.php/persiapan/insert?api_key=" + BuildConfig.API_KEY) Observable<GetStatus> insertUnit(@Body PersiapanPost persiapanPost);
    @POST("index.php/masteritem/search?api_key=" + BuildConfig.API_KEY) Observable<List<Attribute>> getMasterItem(@Body Attribute item);
    @POST("http://ibidadmsdevserviceaccount.azurewebsites.net/index.php/pdi/auth") Observable<Login> getLogin(@Body Login login);
    @POST("index.php/masteritem/post_sign_masuk?api_key=" + BuildConfig.API_KEY) Observable<SignValue> postSignMasuk(@Body Sign item);
    @POST("index.php/masteritem/post_sign_keluar?api_key=" + BuildConfig.API_KEY) Observable<SignValue> postSignKeluar(@Body Sign item);
    @POST("index.php/masteritem/post_lampiran?api_key=" + BuildConfig.API_KEY) Observable<GetStatus> postLampiran(@Body List<Lampiran> item);

}
