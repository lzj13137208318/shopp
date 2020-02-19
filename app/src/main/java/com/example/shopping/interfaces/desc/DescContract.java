package com.example.shopping.interfaces.desc;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.GoodsDescBean;
import com.example.shopping.model.bean.GoodsDescListBean;

public interface DescContract {

    interface View extends IBaseView {
        void DescDataReturn(GoodsDescBean goodsDescBean);
        void DescListDataReturn(GoodsDescListBean goodsDescListBean);
    }

    interface Persenter extends IPersenter<View> {
        void getDescData(int id);
        void getDescListData(int id);
    }
}
