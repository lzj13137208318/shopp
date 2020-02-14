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

public class Rec_livingHomeAdapter extends RecyclerView.Adapter<Rec_livingHomeAdapter.Vh> {
    List<ShouYeBean.DataBean.CategoryListBean.GoodsListBean> list;
    private Context con;

    public Rec_livingHomeAdapter(List<ShouYeBean.DataBean.CategoryListBean.GoodsListBean> list, Context con) {
        this.list = list;
        this.con = con;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(con, R.layout.item_shouye_yisi, null);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.name.setText(list.get(position).getName());
        holder.price.setText("￥"+list.get(position).getRetail_price());
        Glide.with(con).load(list.get(position).getList_pic_url()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        ImageView img;
        TextView name;
        TextView price;
        public Vh(@NonNull View itemView) {
            super(itemView);
            img =  itemView.findViewById(R.id.iv_item_shouye_yisi);
            name =  itemView.findViewById(R.id.tv_item_shouye_yisiname);
            price =  itemView.findViewById(R.id.tv_item_shouye_yisiprice);
        }
    }
}
