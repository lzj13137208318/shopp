package com.example.shopping.interfaces.cart;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.CartListsBean;

public interface CartContract {

    interface View extends IBaseView{
        void cartDataReturn(CartListsBean cartListsBean);
    }
    interface Percenter extends IPersenter<View>{
        void getCartListData();
    }
}
