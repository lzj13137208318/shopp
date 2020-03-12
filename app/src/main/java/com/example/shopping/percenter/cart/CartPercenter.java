package com.example.shopping.percenter.cart;

import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.cart.CartContract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.CartListsBean;
import com.example.shopping.model.bean.CartUpdataBean;
import com.example.shopping.model.http.HttpManager;

public class CartPercenter extends BasePersenter<CartContract.View> implements CartContract.Percenter {
    @Override
    public void getCartListData() {
        addSubscribe(HttpManager.getShopApi().getCartListsData()
        .compose(RxUtils.<CartListsBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartListsBean>(mView) {
                    @Override
                    public void onNext(CartListsBean cartListsBean) {
                        mView.cartDataReturn(cartListsBean);
                    }
                })
        );
    }

    @Override
    public void getCartGoodsUpdata(String productId, String goodsId, String number, String id) {
        addSubscribe(HttpManager.getShopApi().setGoodsMum(productId,goodsId,number,id)
                .compose(RxUtils.<CartUpdataBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<CartUpdataBean>(mView) {
                    @Override
                    public void onNext(CartUpdataBean cartUpdataBean) {
                        mView.cartGoodsUpdataReturn(cartUpdataBean);
                    }
                })
        );
    }
}
