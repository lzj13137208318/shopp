package com.example.shopping.interfaces.hot;

import com.example.shopping.interfaces.IBaseView;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.BannerInfo_Bean;
import com.example.shopping.model.bean.HotBean;
import com.example.shopping.model.bean.NewPageListBean;

public interface HotConstract {

    interface View extends IBaseView{
        void HotDataReturn(HotBean hotBean);
        void BannerInfo_BeanReturn(BannerInfo_Bean bannerInfo_bean);
        void NewPageListBeanReturn(NewPageListBean newPageListBean);
    }
    interface Percenter extends IPersenter<View>{
        void getHotData(int ishot,int page,int size,String order,String sort,int categoryId);
        void getBannerInfo_Bean();
        void getNewPageListBean(int ishot,int page,int size,String order,String sort,int categoryId);
    }}
