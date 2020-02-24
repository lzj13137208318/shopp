package com.example.shopping;

import android.os.Bundle;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.adapter.Rec_sortItemAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.sort.SortContract;
import com.example.shopping.model.bean.CatalogItem;
import com.example.shopping.model.bean.SortItemListBean;
import com.example.shopping.percenter.SortListPercenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class SortDescActivity extends BaseActivity<SortContract.View, SortContract.Percenter> implements TabLayout.BaseOnTabSelectedListener, SortContract.View {

    private TextView mTvtitle;
    private TextView mTvdesc;
    private RecyclerView mRecGoods;
    private TabLayout mTabdesc;
    private ArrayList<CatalogItem> lists;
    private Rec_sortItemAdapter rec_sortItemAdapter;
    private int posi;

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
        posi = getIntent().getIntExtra("posi", -1);
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
        ArrayList<SortItemListBean.DataBeanX.GoodsListBean> sortItemListBeans = new ArrayList<>();
        mRecGoods.setLayoutManager(gridLayoutManager);
        rec_sortItemAdapter = new Rec_sortItemAdapter(sortItemListBeans);
        mRecGoods.setAdapter(rec_sortItemAdapter);

        //实现tab的动态添加文字
        for (int i = 0; i < lists.size(); i++) {
            mTabdesc.addTab(mTabdesc.newTab().setText(lists.get(i).name));
        }
        //实现点击位置跳转到tab指定位置
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
        persenter.getSortListData(lists.get(posi).id,1,100);
    }

    @Override
    protected SortContract.Percenter createPersenter() {
        return new SortListPercenter();
    }

    //tab的点击监听
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        mTvtitle.setText(lists.get(tab.getPosition()).name);
        mTvdesc.setText(lists.get(tab.getPosition()).desc);
        persenter.getSortListData(lists.get(tab.getPosition()).id,1,100);
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
        rec_sortItemAdapter.upData(goodsList);
    }
}
