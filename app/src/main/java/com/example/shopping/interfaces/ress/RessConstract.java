package com.example.shopping.interfaces.ress;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.RessBean;

public interface RessConstract {

    interface View extends IBaseView{
        void RessDataReturn(RessBean ressBean);
    }
    interface Percenter extends IPersenter<View>{
        void getressData();
    }
}
