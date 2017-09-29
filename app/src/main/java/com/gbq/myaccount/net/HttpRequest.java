package com.gbq.myaccount.net;

import android.util.Log;

import com.gbq.myaccount.MyApplication;
import com.gbq.myaccount.constans.Configs;
import com.gbq.myaccount.model.bean.News;
import com.gbq.myaccount.model.bean.TestBean;
import com.gbq.myaccount.net.api.ApiResponse;
import com.gbq.myaccount.net.api.ApiService;
import com.google.gson.Gson;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.gbq.myaccount.net.NetDefine.BASE_URL;
import static com.gbq.myaccount.net.NetDefine.DEFAULT_TIMEOUT;

/**
 * 网络请求
 * Created by gbq on 2017-5-3.
 */

public class HttpRequest {
    private volatile static HttpRequest instance;
    private ApiService mApiService;
    private final CacheProvider mCacheProvider;

    public static HttpRequest getInstance() {
        if (instance == null) {
            synchronized (HttpRequest.class) {
                if (instance == null) {
                    instance = new HttpRequest();
                }
            }
        }
        return instance;
    }

    private HttpRequest() {
        HttpLoggingInterceptor.Level level = HttpLoggingInterceptor.Level.BODY;
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.i("HttpManager", message);
            }
        });
        loggingInterceptor.setLevel(level);
        //拦截请求和响应日志并输出，其实有很多封装好的日志拦截插件，大家也可以根据个人喜好选择。
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .retryOnConnectionFailure(true)
                .addInterceptor(loggingInterceptor);

        OkHttpClient okHttpClient = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build();

        mCacheProvider = new RxCache.Builder()
                .persistence(MyApplication.getInstance().getFilesDir(), new GsonSpeaker())
                .using(CacheProvider.class);

        mApiService = retrofit.create(ApiService.class);
    }

    private <T> void toSubscribe(Observable<ApiResponse<T>> o, Observer<T> s) {
        o.subscribeOn(Schedulers.io())
                .map(new Function<ApiResponse<T>, T>() {
                    @Override
                    public T apply(@NonNull ApiResponse<T> response) throws Exception {
                        int code = response.getCode();
                        if (!response.isSuccess()) {
                            throw new ApiException(code, response.getMsg());
                        } else {
                            return response.getDatas();
                        }
                    }
                })
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    public void getCode(Observable<String> subscriber,String phone){
    }

    public void getGlobalNewDatas(Observer<List<News>> subscriber, int page) {
        toSubscribe(mApiService.getGlobalNewDatas(page, Configs.NEWS_COUNT), subscriber);
    }

    public void getScienceNewDatas(Observer<List<News>> subscriber, int page) {
        toSubscribe(mApiService.getScienceNewDatas(page, Configs.NEWS_COUNT), subscriber);
    }

    public void getSportsNewDatas(Observer<List<News>> subscriber, int page) {
        toSubscribe(mApiService.getSportsNewDatas(page, Configs.NEWS_COUNT), subscriber);
    }

    public void getGlobalNewDatasWithCache(Observer<List<News>> subscriber, int page, boolean update) {
        toSubscribe(mCacheProvider.getGlobalNewDatas(mApiService.getGlobalNewDatas(page, Configs.NEWS_COUNT),
                new EvictProvider(update)), subscriber);
    }

    public void getScienceNewDatasWithCache(Observer<List<News>> subscriber, int page, boolean update) {
        toSubscribe(mCacheProvider.getScienceNewDatas(mApiService.getScienceNewDatas(page, Configs.NEWS_COUNT),
                new EvictProvider(update)), subscriber);
    }

    public void getSportsNewDatasWithCache(Observer<List<News>> subscriber, int page, boolean update) {
        toSubscribe(mCacheProvider.getSportsNewDatas(mApiService.getSportsNewDatas(page, Configs.NEWS_COUNT),
                new EvictProvider(update)), subscriber);
    }
}
