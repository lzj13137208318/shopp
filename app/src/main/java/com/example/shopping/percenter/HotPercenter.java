package com.example.shopping.percenter;

import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.hot.HotConstract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.HotBean;
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
}
