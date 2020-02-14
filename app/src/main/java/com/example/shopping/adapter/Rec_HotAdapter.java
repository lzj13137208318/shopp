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
import com.example.shopping.model.bean.ShouYeBean;

import java.util.List;

public class Rec_HotAdapter extends RecyclerView.Adapter<Rec_HotAdapter.Vh> {
    private List<ShouYeBean.DataBean.HotGoodsListBean> list;
    private Context con;

    public Rec_HotAdapter(List<ShouYeBean.DataBean.HotGoodsListBean> hotGoodsList, Context con) {
        this.list = hotGoodsList;
        this.con = con;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(con, R.layout.item_hot, null);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.tv_hot_title.setText(list.get(position).getName());
        holder.tv_hot_desc.setText(list.get(position).getGoods_brief());
        holder.tv_hot_price.setText("￥ "+list.get(position).getRetail_price());
        Glide.with(con).load(list.get(position).getList_pic_url()).into(holder.iv_hot_img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        ImageView iv_hot_img;
        TextView tv_hot_title;
        TextView tv_hot_desc;
        TextView tv_hot_price;
        public Vh(@NonNull View itemView) {
            super(itemView);
            iv_hot_img = itemView.findViewById(R.id.iv_hot_img);
            tv_hot_title = itemView.findViewById(R.id.tv_hot_title);
            tv_hot_desc = itemView.findViewById(R.id.tv_hot_desc);
            tv_hot_price = itemView.findViewById(R.id.tv_hot_price);
        }
    }
}
