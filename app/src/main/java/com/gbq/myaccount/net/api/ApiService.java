package com.gbq.myaccount.net.api;

import com.gbq.myaccount.model.bean.News;
import com.gbq.myaccount.model.bean.TestBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public interface ApiService {

    @FormUrlEncoded
    @POST("query?key=7c2d1da3b8634a2b9fe8848c3a9edcba")
    Observable<ApiResponse<TestBean>> getDatas(@Field("pno") int pno, @Field("ps") int ps, @Field("dtype") String dtype);

    @FormUrlEncoded
    @POST("newsInterface/getGlobalNews.do")
    Observable<ApiResponse<List<News>>> getGlobalNewDatas(@Field("page") int page, @Field("num") int newsCount);

    @FormUrlEncoded
    @POST("newsInterface/getTeNews.do")
    Observable<ApiResponse<List<News>>> getScienceNewDatas(@Field("page") int page, @Field("num") int newsCount);

    @FormUrlEncoded
    @POST("newsInterface/getPENews.do")
    Observable<ApiResponse<List<News>>> getSportsNewDatas(@Field("page") int page, @Field("num") int newsCount);


}
