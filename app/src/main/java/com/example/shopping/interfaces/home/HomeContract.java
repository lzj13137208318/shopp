package com.example.shopping.interfaces.home;


import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.ShouYeBean;

public interface HomeContract {
    interface View extends IBaseView {
        void HomeDataReturn(ShouYeBean shouYeBean);
    }

    interface Persenter extends IPersenter<View> {
        void getHomeData();
    }
}
