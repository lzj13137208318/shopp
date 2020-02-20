package com.example.shopping.model.apis;

import com.example.shopping.model.bean.SortItemListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SortItemListApi {

    @GET("goods/list")
    Flowable<SortItemListBean> getSortItemListData(@Query("id") int id, @Query("page") int page, @Query("size") int size);
}
