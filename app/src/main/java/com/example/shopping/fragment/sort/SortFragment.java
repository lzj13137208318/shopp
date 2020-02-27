package com.example.shopping.fragment.sort;

import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.bumptech.glide.Glide;
import com.example.shopping.fragment.sort.activity.SortDescActivity;
import com.example.shopping.R;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.interfaces.fenlei.SortContract;
import com.example.shopping.model.bean.CatalogItem;
import com.example.shopping.model.bean.FenLeiBean;
import com.example.shopping.model.bean.FenLei_listBean;
import com.example.shopping.percenter.SortPersenter;

import java.util.ArrayList;
import java.util.List;

import q.rorbin.verticaltablayout.VerticalTabLayout;
import q.rorbin.verticaltablayout.adapter.TabAdapter;
import q.rorbin.verticaltablayout.widget.ITabView;
import q.rorbin.verticaltablayout.widget.TabView;

public class SortFragment extends BaseFragment implements SortContract.View,BaseAdapter.OnItemClickListener{
    private VerticalTabLayout tabFenlei;
    private ImageView img;
    private TextView tv_des;
    private TextView tv_title;
    private RecyclerView rec_fenlei;

    private ArrayList<CatalogItem> lists;
    private Rec_sortAdapter rec_sortAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    protected void initView(View view) {
        tabFenlei = (VerticalTabLayout) view.findViewById(R.id.tab_fenlei);
        img = (ImageView) view.findViewById(R.id.iv_sort_img);
        tv_des = (TextView) view.findViewById(R.id.tv_sort_des);
        tv_title = (TextView) view.findViewById(R.id.tv_sort_title);

        rec_fenlei = view.findViewById(R.id.rec_sort);

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
        rec_sortAdapter = new Rec_sortAdapter(lists);
        rec_fenlei.setAdapter(rec_sortAdapter);

        rec_sortAdapter.setOnItemClickListener(this);
    }


    @Override
    protected void initData() {
        ((SortPersenter)persenter).getFenLeiTabData();
    }

    @Override
    protected IPersenter createPersenter() {
        return new SortPersenter();
    }

    //分类的tab数据
    @Override
    public void FenLeiTabDataReturn(FenLeiBean fenLeiBean) {
        tv_title.setText(fenLeiBean.getData().getCategoryList().get(0).getName()+"分类");
        Glide.with(context).load(fenLeiBean.getData().getCurrentCategory().getWap_banner_url()).into(img);
        tv_des.setText(fenLeiBean.getData().getCategoryList().get(0).getFront_name());

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
            catalog.desc = item.getFront_desc();
            lists.add(catalog);
        }

        rec_sortAdapter.notifyDataSetChanged();

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
                ((SortPersenter)persenter).getFenLeiListData(categoryList.get(position).getId());
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
            catalog.desc = item.getFront_name();
            lists.add(catalog);
        }
        rec_sortAdapter.notifyDataSetChanged();
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
        Intent intent = new Intent(context, SortDescActivity.class);
        intent.putExtra("posi",position);
        intent.putExtra("data",lists);
        activity.startActivity(intent);
    }
}
