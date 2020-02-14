package com.example.shopping.adapter;

import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.ShouYeBean;

import java.util.List;

public class Rec_shouye_zhigongAdapter extends BaseAdapter {

    public Rec_shouye_zhigongAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_shouye_zhigong;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        ShouYeBean.DataBean.BrandListBean  baean = (ShouYeBean.DataBean.BrandListBean) mDatas.get(positon);
        ImageView img = (ImageView) holder.getView(R.id.iv_item_shouye);
        TextView tv = (TextView) holder.getView(R.id.tv_item_shouye_ck);
        TextView price = (TextView) holder.getView(R.id.tv_item_shouye_price);
        tv.setText(baean.getName());
        price.setText(baean.getFloor_price()+"元起");
        Glide.with(mContext).load(baean.getNew_pic_url()).into(img);
    }
}
