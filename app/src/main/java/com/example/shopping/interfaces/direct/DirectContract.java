package com.example.shopping.interfaces.direct;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.BrandBean;

public interface DirectContract {
    interface View extends IBaseView{
        void BrandDatareturn(BrandBean brandBean);
    }

    interface Percenter extends IPersenter<View>{
        void getBrandData(int page,int size);
    }
}
