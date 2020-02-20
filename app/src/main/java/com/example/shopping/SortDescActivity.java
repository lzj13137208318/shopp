package com.example.shopping;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.adapter.Rec_home_livingHomeAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.interfaces.sort.SortContract;
import com.example.shopping.model.bean.CatalogItem;
import com.example.shopping.model.bean.ShouYeBean;
import com.example.shopping.model.bean.SortItemListBean;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SortDescActivity extends BaseActivity<SortContract.View, SortContract.Percenter> implements TabLayout.BaseOnTabSelectedListener, SortContract.View {

    private TextView mTvtitle;
    private TextView mTvdesc;
    private RecyclerView mRecGoods;
    private TabLayout mTabdesc;
    private ArrayList<CatalogItem> lists;

    @Override
    protected int getLayout() {
        return R.layout.activity_goods_desc;
    }

    @Override
    protected void initView() {
        mTvtitle = (TextView) findViewById(R.id.tv_goods_title);
        mTvdesc = (TextView) findViewById(R.id.tv_goods_desc);
        mTabdesc = (TabLayout) findViewById(R.id.tab_desc);

        lists = (ArrayList<CatalogItem>) getIntent().getSerializableExtra("data");
        int posi = getIntent().getIntExtra("posi", -1);
        setTitle("");

        mTvtitle.setText(lists.get(posi).name);
        mTvdesc.setText(lists.get(posi).desc);
        mRecGoods = (RecyclerView) findViewById(R.id.rec_goods);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        mRecGoods.setLayoutManager(gridLayoutManager);
        ArrayList<ShouYeBean.DataBean.CategoryListBean.GoodsListBean> goodsListBeans = new ArrayList<>();
        new Rec_home_livingHomeAdapter(goodsListBeans);
        //mRecGoods.setAdapter();

        //实现tab的动态添加文字
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

        //tab的点击监听
        mTabdesc.addOnTabSelectedListener(this);
    }


    @Override
    protected void initData() {

    }

    @Override
    protected SortContract.Percenter createPersenter() {
        return null;
    }

    //tab的点击监听
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mTvtitle.setText(lists.get(tab.getPosition()).name);
        mTvdesc.setText(lists.get(tab.getPosition()).desc);
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

    @Override
    public void SortListDataReturn(SortItemListBean sortItemListBean) {
        List<SortItemListBean.DataBeanX.GoodsListBean> goodsList = sortItemListBean.getData().getGoodsList();

    }
}
