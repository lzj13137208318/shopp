package com.example.shopping.percenter.cart;

import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.CartListsBean;

import java.util.HashMap;
import java.util.List;

public class CartAdapter extends BaseAdapter {

    private boolean b = true;
    private HashMap<CartListsBean.DataBean.CartListBean, Boolean> map;
    // true 说明是正常状态
    // false 说明是编辑状态

    public CartAdapter(List mDatas) {
        super(mDatas);
        map = new HashMap<>();
    }
    public void selectAll(boolean b){
        this.b = b;
        if (b){
            List<CartListsBean.DataBean.CartListBean> list = mDatas;
            for (int i = 0; i < list.size(); i++) {
                list.get(i).setIsselect(true);
            }
        }else {
            List<CartListsBean.DataBean.CartListBean> list = mDatas;
            for (int i = 0; i < list.size(); i++) {
                map.put(list.get(i),true);
            }
        }
        notifyDataSetChanged();
    }
    public void showAndHind(boolean b){
        this.b = b;
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_cart;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CartListsBean.DataBean.CartListBean bean = (CartListsBean.DataBean.CartListBean) mDatas.get(positon);
        //正常下控件
        ConstraintLayout view = (ConstraintLayout) holder.getView(R.id.item_cart_ConstraintLayout);
        ImageView img = (ImageView) holder.getView(R.id.item_cart_img);
        TextView name = (TextView) holder.getView(R.id.item_cart_name);
        TextView num = (TextView) holder.getView(R.id.item_cart_number);
        TextView price = (TextView) holder.getView(R.id.item_cart_price);
        //编辑下控件
        ConstraintLayout viewEdit = (ConstraintLayout) holder.getView(R.id.item_cart_edit_ConstraintLayout);
        holder.getView(R.id.item_cart_edit_add);
        holder.getView(R.id.item_cart_edit_num);
        holder.getView(R.id.item_cart_edit_subtrect);


        Glide.with(mContext).load(bean.getList_pic_url()).into(img);
        name.setText(bean.getGoods_name());
        num.setText(bean.getNumber());
        price.setText("￥"+bean.getNumber()*bean.getRetail_price());

        RadioButton select = (RadioButton) holder.getView(R.id.item_cart_select);
        // true 说明是正常状态
        if (b){
            //判断正常界面是否隐藏
            if (view.getVisibility() == View.GONE)view.setVisibility(View.VISIBLE);
            //隐藏 编辑界面
            viewEdit.setVisibility(View.GONE);

            select.setChecked(bean.isIsselect());
            //单击标记
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (select.isChecked())
                        bean.setIsselect((bean.isIsselect() == true) ? false : true);
                }
            });
        }else {
            //隐藏 正常界面，显示 编辑界面
            view.setVisibility(View.GONE);
            viewEdit.setVisibility(View.VISIBLE);

            select.setChecked(map.get(positon));
            //单击标记
            select.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (select.isChecked()) {
                        map.put(bean,map.get(positon) ==true ? false : true);
                    }
                }
            });
        }

    }
}
