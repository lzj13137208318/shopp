package com.example.shopping.adapter;

import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.CatalogItem;

import java.util.List;


public class Rec_sortAdapter extends BaseAdapter {

    public Rec_sortAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_sort;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CatalogItem list = (CatalogItem) mDatas.get(positon);
        ImageView img = (ImageView) holder.getView(R.id.iv_item_sort_img);
        Glide.with(mContext).load(list.img).into(img);

        TextView title = (TextView) holder.getView(R.id.tv_item_sort_title);
        title.setText(list.name);
    }
}
