package com.example.shopping.percenter;

import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.hot.HotConstract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.BannerInfo_Bean;
import com.example.shopping.model.bean.HotBean;
import com.example.shopping.model.bean.NewPageListBean;
import com.example.shopping.model.http.HttpManager;

public class HotPercenter extends BasePersenter<HotConstract.View> implements HotConstract.Percenter {
    @Override
    public void getHotData(int ishot, int page, int size, String order, String sort, int categoryId) {
       addSubscribe(HttpManager.getShopApi().getHotData(ishot,page,size,order,sort,categoryId)
       .compose(RxUtils.<HotBean>rxScheduler())
       .subscribeWith(new CommonSubscriber<HotBean>(mView) {
           @Override
           public void onNext(HotBean hotBean) {
               mView.HotDataReturn(hotBean);
           }
       }));
    }

    @Override
    public void getBannerInfo_Bean() {
        addSubscribe(HttpManager.getShopApi().getBannerInfo_Bean()
                .compose(RxUtils.<BannerInfo_Bean>rxScheduler())
                .subscribeWith(new CommonSubscriber<BannerInfo_Bean>(mView) {
                    @Override
                    public void onNext(BannerInfo_Bean result) {
                        mView.BannerInfo_BeanReturn(result);
                    }
                }));
    }

    @Override
    public void getNewPageListBean(int ishot, int page, int size, String order, String sort, int categoryId) {
        addSubscribe(HttpManager.getShopApi().getNewPageListBean(ishot,page,size,order,sort,categoryId)
                .compose(RxUtils.<NewPageListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<NewPageListBean>(mView) {
                    @Override
                    public void onNext(NewPageListBean result) {
                        mView.NewPageListBeanReturn(result);
                    }
                }));
    }
}
