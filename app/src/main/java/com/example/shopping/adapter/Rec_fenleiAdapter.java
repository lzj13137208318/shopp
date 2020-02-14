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
import com.example.shopping.model.bean.CatalogItem;
import com.example.shopping.model.bean.FenLei_listBean;

import java.util.ArrayList;
import java.util.List;


public class Rec_fenleiAdapter extends BaseAdapter {

    public Rec_fenleiAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_fenlei;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CatalogItem list = (CatalogItem) mDatas.get(positon);
        ImageView img = (ImageView) holder.getView(R.id.iv_item_fenleii_img);
        Glide.with(mContext).load(list.img).into(img);

        TextView title = (TextView) holder.getView(R.id.tv_item_fenlei_title);
        title.setText(list.name);
    }
}
