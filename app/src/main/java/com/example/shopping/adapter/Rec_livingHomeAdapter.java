package com.example.shopping.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.ShouYeBean;

import java.util.List;

public class Rec_livingHomeAdapter extends BaseAdapter {

    public Rec_livingHomeAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_shouye_yisi;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {

        ShouYeBean.DataBean.CategoryListBean.GoodsListBean list = (ShouYeBean.DataBean.CategoryListBean.GoodsListBean) mDatas.get(positon);
        ImageView img = (ImageView) holder.getView(R.id.tv_item_shouye_yisiprice);
        TextView name = (TextView) holder.getView(R.id.tv_item_shouye_yisiname);
        TextView price = (TextView) holder.getView(R.id.iv_item_shouye_yisi);

        name.setText(list.getName());
        price.setText("￥"+list.getRetail_price());
        Glide.with(mContext).load(list.getList_pic_url()).into(img);
    }
}
