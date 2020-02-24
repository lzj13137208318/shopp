package com.example.shopping.interfaces.hot;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.HotBean;

public interface HotConstract {

    interface View extends IBaseView{
        void HotDataReturn(HotBean hotBean);
    }
    interface Percenter extends IPersenter<View>{
        void getHotData(int ishot,int page,int size,String order,String sort,int categoryId);
    }}
