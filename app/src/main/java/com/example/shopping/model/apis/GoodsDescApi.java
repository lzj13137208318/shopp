package com.example.shopping.model.apis;

import com.example.shopping.model.bean.GoodsDescBean;
import com.example.shopping.model.bean.GoodsDescListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GoodsDescApi {

    @GET("brand/detail")
    Flowable<GoodsDescBean> getGoodsDesc(@Query("id" )int id);

    @GET("goods/list")
    Flowable<GoodsDescListBean> getGoodsList(@Query("brandId") int id, @Query("page") int page, @Query("size") int size);
}
