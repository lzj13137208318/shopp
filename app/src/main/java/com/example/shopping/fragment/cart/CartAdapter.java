package com.example.shopping.fragment.cart;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.CartListsBean;
import com.example.shopping.model.bean.CatalogItem;

import org.greenrobot.eventbus.EventBus;

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
    //管理正常界面 和 编辑界面的 显示隐藏 并初始化编辑下的单选按钮为false未选中
    public void showAndHind(boolean b){
        this.b = b;
        List<CartListsBean.DataBean.CartListBean> list = mDatas;
        for (int i = 0; i < list.size(); i++) {
            map.put(list.get(i),false);
        }
        notifyDataSetChanged();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_cart;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {
        CartListsBean.DataBean.CartListBean bean = (CartListsBean.DataBean.CartListBean) mDatas.get(positon);

        //默认为不选中
        map.put(bean,false);

        //正常下控件
        ConstraintLayout view = (ConstraintLayout) holder.getView(R.id.item_cart_ConstraintLayout);
        ImageView img = (ImageView) holder.getView(R.id.item_cart_img);
        TextView name = (TextView) holder.getView(R.id.item_cart_name);
        TextView num = (TextView) holder.getView(R.id.item_cart_number);
        TextView price = (TextView) holder.getView(R.id.item_cart_price);
        //编辑下控件
        ConstraintLayout viewEdit = (ConstraintLayout) holder.getView(R.id.item_cart_edit_ConstraintLayout);
        TextView add = (TextView) holder.getView(R.id.item_cart_edit_add);
        TextView numEdit = (TextView) holder.getView(R.id.item_cart_edit_num);
        TextView subtrect = (TextView) holder.getView(R.id.item_cart_edit_subtrect);

        Glide.with(mContext).load(bean.getList_pic_url()).into(img);
        name.setText(bean.getGoods_name());
        num.setText(bean.getNumber()+"");
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
            //false 说明是编辑状态
        }else {
            //隐藏 正常界面，显示 编辑界面
            view.setVisibility(View.GONE);
            viewEdit.setVisibility(View.VISIBLE);

            numEdit.setText(bean.getNumber()+"");

            //用于修改商品的网络请求
            int product_id = bean.getProduct_id();
            int goods_id = bean.getGoods_id();
            int id = bean.getId();
            CatalogItem catalogItem = new CatalogItem();
            catalogItem.productId = product_id+"";
            catalogItem.goodsId = goods_id+"";
            catalogItem.id = id;
//加
            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = numEdit.getText().toString();
                    int i = Integer.parseInt(s)+1;
                    numEdit.setText(i+"");
                    catalogItem.number = i+"";
                    EventBus.getDefault().post(catalogItem);
                }
            });
//减
            subtrect.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = numEdit.getText().toString();
                    int i = Integer.parseInt(s);
                    if (i==1){
                        i=1;
                    }else {
                        i -= 1;
                    }
                    numEdit.setText(i+"");
                    catalogItem.number = i+"";
                    EventBus.getDefault().post(catalogItem);
                }
            });



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
