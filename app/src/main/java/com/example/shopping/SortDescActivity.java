package com.example.shopping;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.model.bean.CatalogItem;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class SortDescActivity extends BaseActivity {

    private TextView mTvtitle;
    private TextView mTvdesc;
    private RecyclerView mRecGoods;
    private TabLayout mTabdesc;

    @Override
    protected int getLayout() {
        return R.layout.activity_goods_desc;
    }

    @Override
    protected void initView() {
        mTvtitle = (TextView) findViewById(R.id.tv_goods_title);
        mTvdesc = (TextView) findViewById(R.id.tv_goods_desc);
        mTabdesc = (TabLayout) findViewById(R.id.tab_desc);

        ArrayList<CatalogItem> lists = (ArrayList<CatalogItem>) getIntent().getSerializableExtra("data");
        int posi = getIntent().getIntExtra("posi", -1);
        setTitle("");

        mTvtitle.setText(lists.get(posi).name);
        mRecGoods = (RecyclerView) findViewById(R.id.rec_goods);

        for (int i = 0; i < lists.size(); i++) {
            mTabdesc.addTab(mTabdesc.newTab().setText(lists.get(i).name));
        }
        //实现tab跳转到指定位置
        mTabdesc.postDelayed(new Runnable() {
            @Override
            public void run() {
                mTabdesc.getTabAt(posi).select();
            }
        },0);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected IPersenter createPersenter() {
        return null;
    }
}
