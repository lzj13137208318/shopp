package com.example.shopping.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.ShouYeBean;

import java.util.List;

public class Rec_home_HotAdapter extends BaseAdapter {

    public Rec_home_HotAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_home_hot;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        ShouYeBean.DataBean.HotGoodsListBean hotGoodsList  = (ShouYeBean.DataBean.HotGoodsListBean) mDatas.get(positon);
        ImageView iv_hot_img = (ImageView) holder.getView(R.id.iv_hot_img);
        TextView tv_hot_title = (TextView) holder.getView(R.id.tv_hot_title);
        TextView tv_hot_desc = (TextView) holder.getView(R.id.tv_hot_desc);
        TextView tv_hot_price = (TextView) holder.getView(R.id.tv_hot_price);

        tv_hot_title.setText(hotGoodsList.getName());
        tv_hot_desc.setText(hotGoodsList.getGoods_brief());
        tv_hot_price.setText("￥ "+hotGoodsList.getRetail_price());
        Glide.with(mContext).load(hotGoodsList.getList_pic_url()).into(iv_hot_img);
    }

}
