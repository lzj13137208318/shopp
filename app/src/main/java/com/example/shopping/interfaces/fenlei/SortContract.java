package com.example.shopping.interfaces.fenlei;


import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.FenLeiBean;
import com.example.shopping.model.bean.FenLei_listBean;

public interface SortContract {
    interface View extends IBaseView {
        void FenLeiTabDataReturn(FenLeiBean fenLeiBean);

        void FenLeiListDataReturn(FenLei_listBean fenLei_listBean);
    }

    interface Persenter extends IPersenter<View> {
        void getFenLeiTabData();

        void getFenLeiListData(int id);
    }
}
