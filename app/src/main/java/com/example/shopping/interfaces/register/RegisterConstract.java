package com.example.shopping.interfaces.register;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.RegisterBean;

public interface RegisterConstract {

    interface View extends IBaseView {
        void RegisterReturn(RegisterBean registerBean);
    }

    interface Persenter extends IPersenter<View> {
        void getRegisterData(String nickname, String password);
    }
}
