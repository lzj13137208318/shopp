package com.example.shopping.fragment.home.activity;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.HotBean;

import java.util.List;

public class Rec_HotAdapter extends BaseAdapter {

    public Rec_HotAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_home_yisi;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        HotBean.DataBeanX.GoodsListBean list = (HotBean.DataBeanX.GoodsListBean) mDatas.get(positon);

        ImageView img = (ImageView) holder.getView(R.id.iv_item_shouye_yisi);
        TextView name = (TextView) holder.getView(R.id.tv_item_shouye_yisiname);
        TextView price = (TextView) holder.getView(R.id.tv_item_shouye_yisiprice);

        name.setText(list.getName());
        price.setText("￥"+list.getRetail_price());
        Glide.with(mContext).load(list.getList_pic_url()).into(img);
    }

}
