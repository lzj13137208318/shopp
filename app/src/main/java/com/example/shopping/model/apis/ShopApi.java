package com.example.shopping.model.apis;

import com.example.shopping.model.bean.BannerInfo_Bean;
import com.example.shopping.model.bean.BrandBean;
import com.example.shopping.model.bean.DetailBean;
import com.example.shopping.model.bean.FenLeiBean;
import com.example.shopping.model.bean.FenLei_listBean;
import com.example.shopping.model.bean.GoodsDescBean;
import com.example.shopping.model.bean.GoodsDescListBean;
import com.example.shopping.model.bean.GoodsShoppingBottomListBean;
import com.example.shopping.model.bean.HotBean;
import com.example.shopping.model.bean.NewPageListBean;
import com.example.shopping.model.bean.ShouYeBean;
import com.example.shopping.model.bean.SortItemListBean;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopApi {
    //首页
    @GET("index/index")
    Flowable<ShouYeBean> getShouYeBean();

    //品牌直供详情
    @GET("brand/detail")
    Flowable<GoodsDescBean> getGoodsDesc(@Query("id" )int id);
    //品牌直供详情列表数据
    @GET("goods/list")
    Flowable<GoodsDescListBean> getGoodsList(@Query("brandId") int id, @Query("page") int page, @Query("size") int size);
    //分类tab数据
    @GET("catalog/index")
    Flowable<FenLeiBean> getFenLeiTabBean();
    //分类tab对应的list数据
    @GET("catalog/current")
    Flowable<FenLei_listBean> getFenLeiListBean(@Query("id") int id);
    //分类详情页的list数据
    @GET("goods/list")
    Flowable<SortItemListBean> getSortItemListData(@Query("categoryId") int id, @Query("page") int page, @Query("size") int size);

    //制造商品牌列表
    @GET("brand/list")
    Flowable<BrandBean> getBrandData(@Query("page") int page, @Query("size") int size);

    //点击新品首发跳转页面的img数据
    @GET("goods/hot")
    Flowable<BannerInfo_Bean> getBannerInfo_Bean();

    //点击新品首发跳转页面的list数据
    @GET("goods/list")
    Flowable<NewPageListBean> getNewPageListBean(@Query("isNew") int news,
                                                 @Query("page") int page,
                                                 @Query("size") int size,
                                                 @Query("order") String order,
                                                 @Query("sort") String sort,
                                                 @Query("categoryId") int id);

    //人气推荐数据
    @GET("goods/list")
    Flowable<HotBean> getHotData(@Query("isHot") int hot,
                                 @Query("page") int page,
                                 @Query("size") int size,
                                 @Query("order") String order,
                                 @Query("sort") String sort,
                                 @Query("categoryId") int id);

    //商品购买详情页
    @GET("goods/detail")
    Flowable<DetailBean> getDetailData(@Query("id") int id);
    //商品详情页底部list数据
    @GET("goods/related")
    Flowable<GoodsShoppingBottomListBean> getGoodsShoppingBottomListlData(@Query("id") int id);

}
