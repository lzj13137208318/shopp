package com.example.shopping.percenter.ress;

import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.ress.RessConstract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.RessBean;
import com.example.shopping.model.http.HttpManager;

public class RessPercenter extends BasePersenter<RessConstract.View> implements RessConstract.Percenter {
    @Override
    public void getressData() {
        addSubscribe(HttpManager.getShopApi().getAddress()
        .compose(RxUtils.<RessBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<RessBean>(mView) {
            @Override
            public void onNext(RessBean ressBean) {
                mView.RessDataReturn(ressBean);
            }
        }));
    }
}
