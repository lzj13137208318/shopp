package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shopping.Utils.RecyclerViewSpacesItemDecoration;
import com.example.shopping.adapter.Rec_HotAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.interfaces.hot.HotConstract;
import com.example.shopping.model.bean.CatalogItem;
import com.example.shopping.model.bean.HotBean;
import com.example.shopping.percenter.HotPercenter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HotActivity extends BaseActivity<HotConstract.View, HotConstract.Percenter> implements HotConstract.View,TabLayout.BaseOnTabSelectedListener {

    private android.widget.ImageView ivHot;
    private android.widget.TextView tvHotEveryoneIsBuyingCarefullySelectedGoods;
    private com.google.android.material.tabs.TabLayout tabHot;
    private androidx.recyclerview.widget.RecyclerView recHot;
    private int ishot = 1;   //是否热门 0否 1是
    private int page = 1;
    private int size = 100;
    private String ORDER = "asc";         //asc升序 desc降序
    private String SORT = "default";    //default默认 price价格 category类别
    private int categoryId = 0;       //0全部 1居家 2配件 3饮食 4志趣
    private Rec_HotAdapter rec_hotAdapter;

    @Override
    protected int getLayout() {
        return R.layout.activity_hot;
    }

    @Override
    protected void initView() {
        ivHot = (ImageView) findViewById(R.id.iv_hot);
        tvHotEveryoneIsBuyingCarefullySelectedGoods = (TextView) findViewById(R.id.tv_hot_Everyone_is_buying_carefully_selected_goods);
        tabHot = (TabLayout) findViewById(R.id.tab_hot);

        tabHot.addTab(tabHot.newTab().setText("综合"));
        tabHot.addTab(tabHot.newTab().setText("价格"));
        tabHot.addTab(tabHot.newTab().setText("分类"));

        recHot = (RecyclerView) findViewById(R.id.rec_hot);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };

        recHot.setLayoutManager(gridLayoutManager);
        List<HotBean.DataBeanX.GoodsListBean> goodsList = new ArrayList<>();
        rec_hotAdapter = new Rec_HotAdapter(goodsList);
        recHot.setAdapter(rec_hotAdapter);
        //tab的点击监听
        tabHot.addOnTabSelectedListener(this);
    }

    @Override
    protected void initData() {
        persenter.getHotData(ishot,page,size,ORDER,SORT,categoryId);
    }

    @Override
    protected HotConstract.Percenter createPersenter() {
        return new HotPercenter();
    }

    @Override
    public void HotDataReturn(HotBean hotBean) {

        List<HotBean.DataBeanX.GoodsListBean> goodsList = hotBean.getData().getGoodsList();
        rec_hotAdapter.upData(goodsList);
    }

    //tab的点击监听
    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()){
            case 0:
                persenter.getHotData(ishot,page,size,ORDER,SORT,categoryId);
               break;
            case 1:
                persenter.getHotData(ishot,page,size,"asc",SORT,categoryId);
                break;
            case 2:

                break;

        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if (tab.getPosition()==1){
            if ("asc".equals(ORDER)){
                persenter.getHotData(ishot,page,size,"desc",SORT,categoryId);
            }else {
                persenter.getHotData(ishot,page,size,"asc",SORT,categoryId);
            }
        }


    }
}
