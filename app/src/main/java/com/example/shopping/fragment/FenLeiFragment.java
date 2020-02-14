package com.example.shopping.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.shopping.GoodsDescActivity;
import com.example.shopping.R;
import com.example.shopping.adapter.Rec_fenleiAdapter;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.interfaces.fenlei.FenLeiContract;
import com.example.shopping.model.bean.CatalogItem;
import com.example.shopping.model.bean.FenLeiBean;
import com.example.shopping.model.bean.FenLei_listBean;
import com.example.shopping.percenter.FenLeiPersenter;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class FenLeiFragment extends BaseFragment implements FenLeiContract.View,BaseAdapter.OnItemClickListener{
    private EditText etFenlei;
    private VerticalTabLayout tabFenlei;
    private ImageView img;
    private TextView tv_des;
    private TextView tv_title;
    private RecyclerView rec_fenlei;

    private ArrayList<CatalogItem> lists;
    private Rec_fenleiAdapter rec_fenleiAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_fenlei;
    }

    @Override
    protected void initView(View view) {
        etFenlei = (EditText) view.findViewById(R.id.et_fenlei);
        tabFenlei = (VerticalTabLayout) view.findViewById(R.id.tab_fenlei);
        img = (ImageView) view.findViewById(R.id.iv_fenlei_img);
        tv_des = (TextView) view.findViewById(R.id.tv_fenlei_des);
        tv_title = (TextView) view.findViewById(R.id.tv_fenlei_title);

        rec_fenlei = view.findViewById(R.id.rec_fenlei);

        //禁止垂直滑动
        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 3) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rec_fenlei.setLayoutManager(gridLayoutManager);

        //列表数据
        lists = new ArrayList<>();
        rec_fenleiAdapter = new Rec_fenleiAdapter(lists);
        rec_fenlei.setAdapter(rec_fenleiAdapter);

        rec_fenleiAdapter.setOnItemClickListener(this);
    }


    @Override
    protected void initData() {
        ((FenLeiPersenter)persenter).getFenLeiTabData();
    }

    @Override
    protected IPersenter createPersenter() {
        return new FenLeiPersenter();
    }

    //分类的tab数据
    @Override
    public void FenLeiTabDataReturn(FenLeiBean fenLeiBean) {
        final List<FenLeiBean.DataBean.CategoryListBean> categoryList = fenLeiBean.getData().getCategoryList();
        //定义tab集合
        final ArrayList<String> tabs = new ArrayList<>();

        for (int i = 0; i < categoryList.size(); i++) {
            String name = categoryList.get(i).getName();
            tabs.add(name);
        }
        //列表list的数据
        lists.clear();
        List<FenLeiBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = fenLeiBean.getData().getCurrentCategory().getSubCategoryList();
        for (FenLeiBean.DataBean.CurrentCategoryBean.SubCategoryListBean item : subCategoryList){
            CatalogItem catalog = new CatalogItem();
            catalog.id = item.getId();
            catalog.img= item.getWap_banner_url();
            catalog.name = item.getName();
            lists.add(catalog);
        }
        rec_fenleiAdapter.addData(lists);

        //tab的适配器
        tabFenlei.setTabAdapter(new TabAdapter() {
            @Override
            public int getCount() {
                return tabs.size();
            }

            @Override
            public ITabView.TabBadge getBadge(int position) {
                return null;
            }

            @Override
            public ITabView.TabIcon getIcon(int position) {
                return null;
            }

            @Override
            public ITabView.TabTitle getTitle(int position) {
                ITabView.TabTitle title = new ITabView.TabTitle.Builder()
                        .setContent(tabs.get(position))//从集合中获取标题
                        .setTextColor(Color.RED, Color.BLACK)
                        .build();
                return title;
            }
            @Override
            public int getBackground(int position) {
                return 0;
            }
        });

        //tab的点击监听
        tabFenlei.addOnTabSelectedListener(new VerticalTabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabView tab, int position) {
                tv_title.setText(categoryList.get(position).getName()+"分类");
                ((FenLeiPersenter)persenter).getFenLeiListData(categoryList.get(position).getId());
            }

            @Override
            public void onTabReselected(TabView tab, int position) {

            }

        });



    }

    //分类列表数据
    @Override
    public void FenLeiListDataReturn(FenLei_listBean fenLei_listBean) {
        String front_name = fenLei_listBean.getData().getCurrentCategory().getFront_name();
        Glide.with(context).load(fenLei_listBean.getData().getCurrentCategory().getWap_banner_url()).into(img);
        tv_des.setText(front_name);

        lists.clear();
        List<FenLei_listBean.DataBean.CurrentCategoryBean.SubCategoryListBean> subCategoryList = fenLei_listBean.getData().getCurrentCategory().getSubCategoryList();
        for (FenLei_listBean.DataBean.CurrentCategoryBean.SubCategoryListBean item : subCategoryList){
            CatalogItem catalog = new CatalogItem();
            catalog.id = item.getId();
            catalog.img= item.getWap_banner_url();
            catalog.name = item.getName();
            lists.add(catalog);
        }
        rec_fenleiAdapter.addData(lists);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }

    //列表条目的点击监听
    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(context, GoodsDescActivity.class);
        intent.putExtra("posi",position);
        intent.putExtra("data",lists);
        startActivity(intent);
    }
}
