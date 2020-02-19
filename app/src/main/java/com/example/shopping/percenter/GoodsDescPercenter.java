package com.example.shopping.percenter;

import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.desc.DescContract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.GoodsDescBean;
import com.example.shopping.model.http.HttpManager;

public class GoodsDescPercenter extends BasePersenter<DescContract.View> implements DescContract.Persenter {
    @Override
    public void getDescData(int id) {
        addSubscribe(HttpManager.getGoodsDescApi().getGoodsDesc(id)
        .compose(RxUtils.<GoodsDescBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<GoodsDescBean>(mView){

            @Override
            public void onNext(GoodsDescBean goodsDescBean) {
                mView.DescDataReturn(goodsDescBean);
            }
        }));
    }
}