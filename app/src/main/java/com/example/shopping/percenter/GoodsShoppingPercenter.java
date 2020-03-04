package com.example.shopping.percenter;

import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.shop.GoodsShoppingConstract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.CartBean;
import com.example.shopping.model.bean.DetailBean;
import com.example.shopping.model.bean.GoodsShoppingBottomListBean;
import com.example.shopping.model.http.HttpManager;

public class GoodsShoppingPercenter extends BasePersenter<GoodsShoppingConstract.View> implements GoodsShoppingConstract.Percenter {
    @Override
    public void getDetailData(int id) {
        addSubscribe(HttpManager.getShopApi().getDetailData(id)
        .compose(RxUtils.<DetailBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<DetailBean>(mView) {
             @Override
              public void onNext(DetailBean detailBean) {
                     mView.DetailDataReturn(detailBean);
                  }
               })
        );
    }

    @Override
    public void getGoodsShoppingBottomListData(int id) {
        addSubscribe(HttpManager.getShopApi().getGoodsShoppingBottomListlData(id)
                .compose(RxUtils.<GoodsShoppingBottomListBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<GoodsShoppingBottomListBean>(mView) {
                    @Override
                    public void onNext(GoodsShoppingBottomListBean result) {
                        mView.GoodsShoppingBottomListDataReturn(result);
                    }
                })
        );
    }

    @Override
    public void addCartData(String goodsId, int number, String productId) {
        addSubscribe(HttpManager.getShopApi().getCarNum(goodsId,number,productId)
        .compose(RxUtils.<CartBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<CartBean>(mView) {
            @Override
            public void onNext(CartBean cartBean) {
                mView.CartDataReturn(cartBean);
            }
        }));
    }
}
