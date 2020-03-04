package com.example.shopping.interfaces.shop;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.CartBean;
import com.example.shopping.model.bean.DetailBean;
import com.example.shopping.model.bean.GoodsShoppingBottomListBean;

public interface GoodsShoppingConstract {
    interface View extends IBaseView{
        void DetailDataReturn(DetailBean detailBean);
        void GoodsShoppingBottomListDataReturn(GoodsShoppingBottomListBean goodsShoppingBottomListBean);
        //购物车数据返回
        void CartDataReturn(CartBean cartBean);
    }
    interface Percenter extends IPersenter<View>{
        void getDetailData(int id);
        void getGoodsShoppingBottomListData(int id);
        //添加购物车
        void addCartData(String goodsId,int number, String productId);
    }
}
