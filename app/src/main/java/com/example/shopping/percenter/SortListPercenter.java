package com.example.shopping.percenter;

import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.sort.SortContract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.SortItemListBean;
import com.example.shopping.model.http.HttpManager;

public class SortListPercenter extends BasePersenter<SortContract.View> implements SortContract.Percenter {

    @Override
    public void getSortListData(int id, int page, int size) {
        addSubscribe(HttpManager.getShopApi().getSortItemListData(id,page,size)
        .compose(RxUtils.<SortItemListBean>rxScheduler())
        .subscribeWith(new CommonSubscriber<SortItemListBean>(mView){

            @Override
            public void onNext(SortItemListBean sortItemListBean) {
                mView.SortListDataReturn(sortItemListBean);
            }
        }));
    }
}
