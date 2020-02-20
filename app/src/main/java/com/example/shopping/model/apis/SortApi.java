package com.example.shopping.model.apis;



import com.example.shopping.model.bean.FenLeiBean;
import com.example.shopping.model.bean.FenLei_listBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface SortApi {

    @GET("catalog/index")
    Flowable<FenLeiBean> getFenLeiTabBean();

    @GET("catalog/current")
    Flowable<FenLei_listBean> getFenLeiListBean(@Query("id") int id);
}
