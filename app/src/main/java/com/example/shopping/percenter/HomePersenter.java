package com.example.shopping.percenter;


import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.home.HomeContract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.ShouYeBean;
import com.example.shopping.model.http.HttpManager;

public class HomePersenter extends BasePersenter<HomeContract.View> implements HomeContract.Persenter{

    @Override
    public void getHomeData() {
        addSubscribe(HttpManager.getShopApi().getShouYeBean()
                .compose(RxUtils.<ShouYeBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<ShouYeBean>(mView){
                    @Override
                    public void onNext(ShouYeBean shouYeBean) {
                        mView.HomeDataReturn(shouYeBean);
                    }
                }));
    }
}
