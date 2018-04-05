package com.example.android.ibidsera.service;

/**
 * Created by Fahmi Hakim on 07/03/2018.
 * for SERA
 */

import com.example.android.ibidsera.AppController;
import com.example.android.ibidsera.util.ApiConstants;
import com.example.android.ibidsera.util.CommonUtils;
import com.example.android.ibidsera.util.RetrofitUtil;
import com.facebook.stetho.okhttp3.StethoInterceptor;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class RetrofitHelper {
    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
    }


    public static APICall getUnitMasukApiTaksasiServiceALPHA() {
        return createApi(APICall.class, ApiConstants.ALPHA_TAKSASI_URL);
    }

    public static APICall getUnitMasukDetailApiTaksasiServiceALPHA() {
        return createApi(APICall.class, ApiConstants.ALPHA_TAKSASI_URL);
    }

    public static APICall getUnitMasukSearchByNopolStokServiceALPHA() {
        return createApiStokMasuk(APICall.class, ApiConstants.ALPHA_STOK_URL);
    }

    public static APICall postGambarAddKeluarTaksasiServiceALPHA() {
        return createApiTaksasiKeluar(APICall.class, ApiConstants.ALPHA_TAKSASI_URL);
    }

    public static APICall postGambarAddMasukTaksasiServiceALPHA() {
        return createApiTaksasiKeluar(APICall.class, ApiConstants.ALPHA_TAKSASI_URL);
    }

    public static APICall postUnitKeluarApiStokServiceALPHA() {
        return createApiPostAddKeluar(APICall.class, ApiConstants.ALPHA_STOK_URL);
    }

    public static APICall getUnitKeluarApiTaksasiServiceALPHA() {
        return createApiTaksasiKeluar(APICall.class, ApiConstants.ALPHA_TAKSASI_URL);
    }

    public static APICall getUnitKeluarDetailApiTaksasiServiceALPHA() {
        return createApi(APICall.class, ApiConstants.ALPHA_TAKSASI_URL);
    }

    public static APICall getUnitKeluarSearchByNopolTaksasiServiceALPHA() {
        return createApiTaksasiKeluar(APICall.class, ApiConstants.ALPHA_TAKSASI_URL);
    }

    /*public static APICall getUnitKeluarSearchByNopolTaksasiServiceALPHA() {
        return createApiTaksasiKeluar(APICall.class, ApiConstants.ALPHA_TAKSASI_URL);
    }*/


    public static APICall getAuctionDevService() {
        return createApi(APICall.class, ApiConstants.DEV_URL);
    }

    public static APICall getAuctionAzureStockService() {
        return createApi(APICall.class, ApiConstants.AZURE_STOCK_URL);
    }

    public static APICall getAuctionAzureCetakService() {
        return createApi(APICall.class, ApiConstants.AZURE_TAKSASI_URL);
    }


    private static <T> T createApi(Class<T> clazz, String baseUrl) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(clazz);
    }

    private static <T> T createApiStokMasuk(Class<T> clazz, String baseUrl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ALPHA_STOK_URL) //BuildConfig.URI
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(RetrofitUtil.getGson()))
                .client(httpClient.build())
                .build();
        return retrofit.create(clazz);
    }

    private static <T> T createApiTaksasiKeluar(Class<T> clazz, String baseUrl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ALPHA_TAKSASI_URL) //BuildConfig.URI
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(RetrofitUtil.getGson()))
                .client(httpClient.build())
                .build();
        return retrofit.create(clazz);
    }

    private static <T> T createApiPostAddKeluar(Class<T> clazz, String baseUrl) {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        // add your other interceptors …

        // add logging as last interceptor
        httpClient.addInterceptor(logging);  // <-- this is the important line!
        httpClient.connectTimeout(120, TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiConstants.ALPHA_STOK_URL) //BuildConfig.URI
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(RetrofitUtil.getGson()))
                .client(httpClient.build())
                .build();
        return retrofit.create(clazz);
    }


    private static void initOkHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    //Cache cache = new Cache(new File(AppController.getInstance().getCacheDir(), "HttpCache"), 1024 * 1024 * 10);
                    mOkHttpClient = new OkHttpClient.Builder()
                            //.cache(cache)
                            .addInterceptor(interceptor)
                            /*.addNetworkInterceptor(new CacheInterceptor())
                            .addNetworkInterceptor(new StethoInterceptor())*/
                            .retryOnConnectionFailure(true)
                            .connectTimeout(120, TimeUnit.SECONDS)
                            .writeTimeout(20, TimeUnit.SECONDS)
                            .readTimeout(120, TimeUnit.SECONDS)
                            .addInterceptor(new UserAgentInterceptor())
                            .build();
                }
            }
        }
    }



    private static class UserAgentInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request requestWithUserAgent = originalRequest.newBuilder()
                    .removeHeader("User-Agent")
                    .addHeader("User-Agent", ApiConstants.DEV_URL)
                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }


    private static class CacheInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            int maxAge = 60 * 60;
            int maxStale = 60 * 60 * 24;
            Request request = chain.request();
            request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            Response response = chain.proceed(request);
            response = response.newBuilder()
                    .removeHeader("Pragma")
                    .header("Cache-Control", "public, max-age=" + maxAge)
                    .build();
            /*if (CommonUtils.isNetworkAvailable(AppController.getInstance())) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            } else {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_NETWORK).build();
            }
            Response response = chain.proceed(request);
            if (CommonUtils.isNetworkAvailable(AppController.getInstance())) {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                response = response.newBuilder()
                        .removeHeader("Pragma")
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }*/
            return response;
        }
    }



}

