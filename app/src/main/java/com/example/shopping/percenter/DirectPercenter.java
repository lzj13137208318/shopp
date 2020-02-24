package com.example.shopping.percenter;

import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.direct.DirectContract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.BrandBean;
import com.example.shopping.model.http.HttpManager;

public class DirectPercenter extends BasePersenter<DirectContract.View> implements DirectContract.Percenter {

    @Override
    public void getBrandData(int page, int size) {
        addSubscribe(HttpManager.getShopApi().getBrandData(page,size)
        .compose(RxUtils.<BrandBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<BrandBean>(mView){
            @Override
            public void onNext(BrandBean brandBean) {
                mView.BrandDatareturn(brandBean);
            }
        })
        );
    }
}
