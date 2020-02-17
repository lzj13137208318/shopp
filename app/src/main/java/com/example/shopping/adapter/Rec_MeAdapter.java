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
import com.example.shopping.model.bean.MeBean;

import java.util.ArrayList;
import java.util.List;

public class Rec_MeAdapter extends BaseAdapter {

    public Rec_MeAdapter(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_me;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        MeBean list = (MeBean) mDatas.get(positon);
        ImageView img = (ImageView) holder.getView(R.id.iv_me_item);
        TextView tv = (TextView) holder.getView(R.id.tv_me_item_text);

        tv.setText(list.getText());
        Glide.with(mContext).load(list.getImgpath()).into(img);
    }

}
