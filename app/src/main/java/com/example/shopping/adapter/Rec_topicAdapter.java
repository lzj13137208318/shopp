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

public class Rec_topicAdapter extends RecyclerView.Adapter<Rec_topicAdapter.Vh> {
    private List<ShouYeBean.DataBean.TopicListBean> list;
    private Context con;

    public Rec_topicAdapter(List<ShouYeBean.DataBean.TopicListBean> list, Context con) {
        this.list = list;
        this.con = con;
    }

    @NonNull
    @Override
    public Vh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(con, R.layout.item_topic, null);
        return new Vh(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Vh holder, int position) {
        holder.tv_item_topic_title.setText(list.get(position).getTitle());
        holder.tv_item_topic_price.setText(list.get(position).getPrice_info()+"元起");
        holder.tv_item_topic_desc.setText(list.get(position).getSubtitle());
        Glide.with(con).load(list.get(position).getItem_pic_url()).into(holder.img);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Vh extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv_item_topic_title;
        TextView tv_item_topic_price;
        TextView tv_item_topic_desc;
        public Vh(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.iv_item_topic);
            tv_item_topic_title = itemView.findViewById(R.id.tv_item_topic_title);
            tv_item_topic_price = itemView.findViewById(R.id.tv_item_topic_price);
            tv_item_topic_desc = itemView.findViewById(R.id.tv_item_topic_desc);
        }
    }
}
