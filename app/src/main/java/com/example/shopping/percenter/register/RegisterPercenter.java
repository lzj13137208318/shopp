package com.example.shopping.percenter.register;

import com.example.shopping.Utils.RxUtils;
import com.example.shopping.base.BasePersenter;
import com.example.shopping.interfaces.register.RegisterConstract;
import com.example.shopping.model.CommonSubscriber;
import com.example.shopping.model.bean.RegisterBean;
import com.example.shopping.model.http.HttpManager;

public class RegisterPercenter extends BasePersenter<RegisterConstract.View> implements RegisterConstract.Persenter {

    @Override
    public void getRegisterData(String nickname, String password) {
        addSubscribe(HttpManager.getShopApi().register(nickname,password)
                .compose(RxUtils.<RegisterBean>rxScheduler())
                .subscribeWith(new CommonSubscriber<RegisterBean>(mView) {
                    @Override
                    public void onNext(RegisterBean registerBean) {
                        mView.RegisterReturn(registerBean);
                    }
                })
        );
    }
}
