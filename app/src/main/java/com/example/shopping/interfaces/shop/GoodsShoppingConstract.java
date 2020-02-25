package com.example.shopping.interfaces.shop;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.DetailBean;
import com.example.shopping.model.bean.GoodsShoppingBottomListBean;

public interface GoodsShoppingConstract {
    interface View extends IBaseView{
        void DetailDataReturn(DetailBean detailBean);
        void GoodsShoppingBottomListDataReturn(GoodsShoppingBottomListBean goodsShoppingBottomListBean);
    }
    interface Percenter extends IPersenter<View>{
        void getDetailData(int id);
        void getGoodsShoppingBottomListData(int id);
    }
}
