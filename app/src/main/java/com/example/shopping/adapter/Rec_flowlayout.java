package com.example.shopping.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.model.bean.TextBean;
import com.example.shopping.ui.FlowLayout;

import java.util.List;

public class Rec_flowlayout extends BaseAdapter {

    public Rec_flowlayout(List mDatas) {
        super(mDatas);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.item_flowlayout;
    }

    @Override
    protected void bindData(BaseViewHolder holder, int positon, Object o) {

        FlowLayout flowLayout = (FlowLayout) holder.getView(R.id.flowlayout);
        //往容器内添加TextView数据
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.setMargins(10,5,10,5);
        if (flowLayout != null){
            flowLayout.removeAllViews();
        }
        for (int i = 0; i < mDatas.size(); i++) {
            TextView inflate = (TextView) View.inflate(mContext, R.layout.text, null);
            TextBean text = (TextBean) mDatas.get(i);
            inflate.setText(text.name);
            flowLayout.addView(inflate);
        }
    }

}
