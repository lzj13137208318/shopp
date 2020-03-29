package com.example.shopping.interfaces.cart;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.CartDeleteBean;
import com.example.shopping.model.bean.CartListsBean;
import com.example.shopping.model.bean.CartUpdataBean;

public interface CartContract {

    interface View extends IBaseView{
        void cartDataReturn(CartListsBean cartListsBean);
        void cartGoodsUpdataReturn(CartUpdataBean cartUpdataBean);
        void cartGoodsDeleteDatareturn(CartDeleteBean cartDeleteBean);
    }
    interface Percenter extends IPersenter<View>{
        void getCartListData();
        void getCartGoodsUpdata(String productId,String goodsId,String number,String id);
        void getCartGoodsDeleteData(String productIds);
    }
}
