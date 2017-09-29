package com.gbq.myaccount.net;


import com.gbq.myaccount.model.bean.News;
import com.gbq.myaccount.model.bean.TestBean;
import com.gbq.myaccount.net.api.ApiResponse;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;

public interface CacheProvider {

    @LifeCache(duration = 5, timeUnit = TimeUnit.HOURS)
    Observable<ApiResponse<TestBean>> getDatas(Observable<ApiResponse<TestBean>> oRepos, EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.HOURS)
    Observable<ApiResponse<List<News>>> getGlobalNewDatas(Observable<ApiResponse<List<News>>> oRepos, EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.HOURS)
    Observable<ApiResponse<List<News>>> getScienceNewDatas(Observable<ApiResponse<List<News>>> oRepos, EvictProvider evictDynamicKey);

    @LifeCache(duration = 5, timeUnit = TimeUnit.HOURS)
    Observable<ApiResponse<List<News>>> getSportsNewDatas(Observable<ApiResponse<List<News>>> oRepos, EvictProvider evictDynamicKey);

}