package com.example.shopping;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.shopping.adapter.Rec_HotAdapter;
import com.example.shopping.adapter.Rec_flowlayout;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.interfaces.hot.HotConstract;
import com.example.shopping.model.bean.HotBean;
import com.example.shopping.model.bean.TextBean;
import com.example.shopping.percenter.HotPercenter;
import com.example.shopping.ui.FlowLayout;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class HotActivity extends BaseActivity<HotConstract.View, HotConstract.Percenter> implements HotConstract.View,TabLayout.BaseOnTabSelectedListener,BaseAdapter.OnItemClickListener {

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
    private List<HotBean.DataBeanX.GoodsListBean> goodsList;

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
        goodsList = new ArrayList<>();
        rec_hotAdapter = new Rec_HotAdapter(goodsList);
        recHot.setAdapter(rec_hotAdapter);
        //tab的点击监听
        tabHot.addOnTabSelectedListener(this);
        //list中item的点击监听
        rec_hotAdapter.setOnItemClickListener(this);
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
                SORT = "default";
                persenter.getHotData(ishot,page,size,ORDER,SORT,categoryId);
               break;
            case 1:
                SORT = "price";
                if ("asc".equals(ORDER)){
                    persenter.getHotData(ishot,page,size,"asc",SORT,categoryId);
                    ORDER = "desc";
                }
                if ("desc".equals(ORDER)){
                    persenter.getHotData(ishot,page,size,"asc",SORT,categoryId);
                    ORDER = "asc";
                }
                break;
            case 2:
                //分类展开
                /*LinearLayout lin = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.re, null);
                RecyclerView recyclerView = lin.findViewById(R.id.rec_flowlayout);

                ArrayList<TextBean> textBeans = new ArrayList<>();
                textBeans.add(new TextBean("全部"));
                textBeans.add(new TextBean("居家"));
                textBeans.add(new TextBean("配件"));
                textBeans.add(new TextBean("饮食"));
                textBeans.add(new TextBean("志趣"));

                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                Rec_flowlayout rec_flowlayout = new Rec_flowlayout(textBeans);
                recyclerView.setAdapter(rec_flowlayout);

                PopupWindow popupWindow = new PopupWindow(recyclerView, LinearLayout.LayoutParams.WRAP_CONTENT,LinearLayout.LayoutParams.WRAP_CONTENT);
                popupWindow.setContentView(recyclerView);
                popupWindow.showAsDropDown(tabHot,0,0);*/

        }
    }
    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if (tab.getPosition()==1){
            SORT = "price";
            if ("asc".equals(ORDER)){
                ORDER = "desc";
                persenter.getHotData(ishot,page,size,ORDER,SORT,categoryId);
            }else {
                ORDER = "asc";
                persenter.getHotData(ishot,page,size,ORDER,SORT,categoryId);
            }
        }

    }

    //item的点击监听
    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, GoodsShoppingActivity.class);
        intent.putExtra("id",goodsList.get(position).getId());
        startActivity(intent);
    }
}
