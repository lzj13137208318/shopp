package com.example.shopping.percenter.cart;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.CartListsBean;

import java.util.List;

public class CartAdapter extends BaseAdapter {

    public CartAdapter(List mDatas) {
        super(mDatas);

    }
    public void selectAll(){
        List<CartListsBean.DataBean.CartListBean> list = mDatas;
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setIsselect(true);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_cart;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CartListsBean.DataBean.CartListBean bean = (CartListsBean.DataBean.CartListBean) mDatas.get(positon);
        //正常
        ImageView img = (ImageView) holder.getView(R.id.item_cart_img);
        TextView name = (TextView) holder.getView(R.id.item_cart_name);
        TextView num = (TextView) holder.getView(R.id.item_cart_number);
        TextView price = (TextView) holder.getView(R.id.item_cart_price);
        RadioButton select = (RadioButton) holder.getView(R.id.item_cart_select);

        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bean.setIsselect((bean.isIsselect() == true) ? false : true);
            }
        });
        //编辑
        holder.getView(R.id.item_cart_edit_ConstraintLayout);
        holder.getView(R.id.item_cart_edit_add);
        holder.getView(R.id.item_cart_edit_num);
        holder.getView(R.id.item_cart_edit_subtrect);

        Glide.with(mContext).load(bean.getList_pic_url()).into(img);
        name.setText(bean.getGoods_name());
        num.setText(bean.getNumber());
        price.setText("￥"+bean.getNumber()*bean.getRetail_price());
    }
}
