package com.example.shopping.interfaces.login;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.UserBean;

public interface LoginConstract {

    interface View extends IBaseView {
        void loginReturn(UserBean result);
        void showTips(String error);
    }

    interface Persenter extends IPersenter<View> {
        void login(String nickname,String password);
    }
}
