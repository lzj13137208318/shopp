package com.example.shopping.percenter;


import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.fenlei.SortContract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.FenLeiBean;
import com.example.shopping.model.bean.FenLei_listBean;
import com.example.shopping.model.http.HttpManager;

public class SortPersenter extends BasePersenter<SortContract.View> implements SortContract.Persenter{

    @Override
    public void getFenLeiTabData() {
        addSubscribe(HttpManager.getSortApi().getFenLeiTabBean()
                .compose(RxUtils.<FenLeiBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<FenLeiBean>(mView){
                    @Override
                    public void onNext(FenLeiBean fenLeiBean) {
                        mView.FenLeiTabDataReturn(fenLeiBean);
                    }
                }));
    }

    @Override
    public void getFenLeiListData(int id) {
        addSubscribe(HttpManager
                .getSortApi()
                .getFenLeiListBean(id)
                .compose(RxUtils.<FenLei_listBean> rxScheduler())
                .subscribeWith(new CommonSubscriber<FenLei_listBean>(mView){
                    @Override
                    public void onNext(FenLei_listBean result) {
                        mView.FenLeiListDataReturn(result);
                    }
                }));
    }
}
