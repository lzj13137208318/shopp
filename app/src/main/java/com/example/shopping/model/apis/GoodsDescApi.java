package com.example.shopping.model.apis;

import com.example.shopping.model.bean.GoodsDescBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoodsDescApi {

    @GET("brand/detail")
    Flowable<GoodsDescBean> getGoodsDesc(@Query("id" )int id);
}
