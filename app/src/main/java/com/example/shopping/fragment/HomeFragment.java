package com.example.shopping.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import com.example.shopping.DescActivity;
import com.example.shopping.R;
import com.example.shopping.adapter.Rec_HotAdapter;
import com.example.shopping.adapter.Rec_livingHomeAdapter;
import com.example.shopping.adapter.Rec_shouye_yisiAdapter;
import com.example.shopping.adapter.Rec_shouye_zhigongAdapter;
import com.example.shopping.adapter.Rec_topicAdapter;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.interfaces.home.HomeContract;
import com.example.shopping.model.bean.ShouYeBean;
import com.example.shopping.percenter.HomePersenter;
import com.google.android.material.tabs.TabLayout;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends BaseFragment implements HomeContract.View{
    private Banner banner;
    private TabLayout tabHome;
    private RecyclerView rec_shouye_direct;
    private Rec_shouye_zhigongAdapter adapter;
    private RecyclerView rec_shouye_yisi;
    private RecyclerView rec_shouye_livinghome;
    private RecyclerView rec_shouye_hot;
    private RecyclerView rec_shouye_topic;
    private Rec_shouye_yisiAdapter yisiAdapter;
    private TextView tvDirect;
    private TextView tvYisi;
    private TextView tvHot;
    private TextView tvTopic;
    private TextView tvlivinghome;
    private Rec_HotAdapter rec_hotAdapter;
    private Rec_livingHomeAdapter rec_livingHomeAdapter;
    private Rec_topicAdapter rec_topicAdapter;
    private List<ShouYeBean.DataBean.TopicListBean> topicList;
    private List<ShouYeBean.DataBean.HotGoodsListBean> hotGoodsList;
    private List<ShouYeBean.DataBean.NewGoodsListBean> newGoodsList;
    private List<ShouYeBean.DataBean.BrandListBean> brandList;
    private List<ShouYeBean.DataBean.CategoryListBean.GoodsListBean> goodsList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        tabHome = (TabLayout) view.findViewById(R.id.tab_home);
//direct 直供
        rec_shouye_direct = (RecyclerView) view.findViewById(R.id.rec_shouye_direct);
        rec_shouye_yisi = (RecyclerView) view.findViewById(R.id.rec_shouye_yisi);
        rec_shouye_hot = (RecyclerView) view.findViewById(R.id.rec_shouye_hot);
//topic 专题
        rec_shouye_topic = (RecyclerView) view.findViewById(R.id.rec_shouye_topic);
        rec_shouye_livinghome = (RecyclerView) view.findViewById(R.id.rec_shouye_livinghome);

        tvDirect = (TextView) view.findViewById(R.id.tv_direct);
        tvYisi = (TextView) view.findViewById(R.id.tv_yisi);
        tvHot = (TextView) view.findViewById(R.id.tv_hot);
        tvTopic = (TextView) view.findViewById(R.id.tv_topic);
        tvlivinghome = (TextView) view.findViewById(R.id.tv_livinghome);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2){

            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rec_shouye_direct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rec_shouye_livinghome.setLayoutManager(gridLayoutManager);
        rec_shouye_topic.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rec_shouye_hot.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec_shouye_hot.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        rec_shouye_yisi.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        //rec直供的适配器
        brandList = new ArrayList<>();
        adapter = new Rec_shouye_zhigongAdapter(brandList);
        rec_shouye_direct.setAdapter(adapter);
//点击监听
        adapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "直供"+position, Toast.LENGTH_SHORT).show();
                GoToDesc(brandList.get(position).getId());
            }
        });

        //rec一四首发
        newGoodsList = new ArrayList<>();
        yisiAdapter = new Rec_shouye_yisiAdapter(newGoodsList);
        rec_shouye_yisi.setAdapter(yisiAdapter);
//点击监听
        yisiAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "一四首发"+position, Toast.LENGTH_SHORT).show();
                GoToDesc(newGoodsList.get(position).getId());
            }
        });

        //rec人气推荐
        hotGoodsList = new ArrayList<>();
        rec_hotAdapter = new Rec_HotAdapter(hotGoodsList);
        rec_shouye_hot.setAdapter(rec_hotAdapter);
//点击监听
        rec_hotAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "人气推荐"+position, Toast.LENGTH_SHORT).show();
                GoToDesc(hotGoodsList.get(position).getId());
            }
        });

        //rec专题精选
        topicList = new ArrayList<>();
        rec_topicAdapter = new Rec_topicAdapter(topicList);
        rec_shouye_topic.setAdapter(rec_topicAdapter);
//点击监听
        rec_topicAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "专题精选"+position, Toast.LENGTH_SHORT).show();
                GoToDesc(topicList.get(position).getId());
            }
        });

        //rec居家
        goodsList = new ArrayList<>();
        rec_livingHomeAdapter = new Rec_livingHomeAdapter(goodsList);
        rec_shouye_livinghome.setAdapter(rec_livingHomeAdapter);
//点击监听
        rec_livingHomeAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "居家"+position, Toast.LENGTH_SHORT).show();
                GoToDesc(goodsList.get(position).getId());
            }
        });

        //rec餐厨
    }

    private void GoToDesc(int id) {
        Intent intent = new Intent(activity, DescActivity.class);
        intent.putExtra("id",id);
        activity.startActivity(intent);
    }


    @Override
    protected void initData() {
        ((HomePersenter)persenter).getHomeData();
    }

    @Override
    protected IPersenter createPersenter() {
        return new HomePersenter();
    }

    @Override
    public void HomeDataReturn(final ShouYeBean shouYeBean) {

        List<ShouYeBean.DataBean.BannerBean> banners = shouYeBean.getData().getBanner();

        //banner图片资源的集合
        final ArrayList<String> imgs = new ArrayList<>();
        for (int i = 0; i < banners.size(); i++) {
            String image_url = banners.get(i).getImage_url();
            imgs.add(image_url);
        }

        //定义tab集合
        final ArrayList<String> tabs = new ArrayList<>();
        List<ShouYeBean.DataBean.CategoryListBean> categoryList = shouYeBean.getData().getCategoryList();
        for (int i = 0; i < categoryList.size(); i++) {
            String name = categoryList.get(i).getName();
            tabs.add(name);
        }

        getActivity().runOnUiThread(new Runnable() {


            @Override
            public void run() {

                tvDirect.setText("品牌制造商直供");
                tvYisi.setText("周一周四·新品首发");
                tvHot.setText("人气推荐");
                tvTopic.setText("专题精选");
                tvlivinghome.setText("居家");

                //banner轮播图的实现
                banner.setImages(imgs).setImageLoader(new ImageLoader() {
                    @Override
                    public void displayImage(Context context, Object path, ImageView imageView) {
                        Glide.with(context).load((String) path).into(imageView);
                    }
                }).start();

                //banner的点击监听
                banner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

                    }

                    @Override
                    public void onPageSelected(int position) {

                    }

                    @Override
                    public void onPageScrollStateChanged(int state) {

                    }
                });

                //tab的显示
                for (int i = 0; i < tabs.size(); i++) {
                    tabHome.addTab(tabHome.newTab().setText(tabs.get(i)));
                }

                //rec直供的适配器
                adapter.addData(shouYeBean.getData().getBrandList());

                //rec一四首发
                yisiAdapter.addData(shouYeBean.getData().getNewGoodsList());

                //rec人气推荐
                rec_hotAdapter.addData(shouYeBean.getData().getHotGoodsList());

                //rec专题精选
                rec_topicAdapter.addData(shouYeBean.getData().getTopicList());

                //rec居家
                rec_livingHomeAdapter.addData(shouYeBean.getData().getCategoryList().get(0).getGoodsList());
                //rec餐厨
            }
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }


}
