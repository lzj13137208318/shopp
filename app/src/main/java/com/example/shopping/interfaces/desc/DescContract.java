package com.example.shopping.interfaces.desc;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.GoodsDescBean;

public interface DescContract {

    interface View extends IBaseView {
        void DescDataReturn(GoodsDescBean goodsDescBean);

    }

    interface Persenter extends IPersenter<View> {
        void getDescData(int id);

    }
}
