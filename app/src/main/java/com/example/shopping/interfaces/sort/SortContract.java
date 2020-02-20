package com.example.shopping.interfaces.sort;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.SortItemListBean;

public interface SortContract {

    interface View extends IBaseView{
        void SortListDataReturn(SortItemListBean sortItemListBean);
    }
    interface Percenter extends IPersenter<View>{
        void getSortListData(int id, int page , int size);
    }
}
