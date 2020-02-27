package com.example.shopping.fragment.home.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.BrandBean;

import java.util.List;

public class Rec_directAdapter extends BaseAdapter {
    public Rec_directAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_direct;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        BrandBean.DataBeanX.DataBean list = (BrandBean.DataBeanX.DataBean) mDatas.get(positon);
        ImageView img = (ImageView) holder.getView(R.id.iv_item_direct);
        TextView  tv = (TextView) holder.getView(R.id.tv_item_direct);

        tv.setText(list.getName());
        Glide.with(mContext).load(list.getApp_list_pic_url()).into(img);
    }
}
