package com.example.shopping.fragment;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;

import com.example.shopping.R;
import com.example.shopping.adapter.Rec_HotAdapter;
import com.example.shopping.adapter.Rec_livingHomeAdapter;
import com.example.shopping.adapter.Rec_shouye_yisiAdapter;
import com.example.shopping.adapter.Rec_shouye_zhigongAdapter;
import com.example.shopping.adapter.Rec_topicAdapter;
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


public class HomeFragment extends BaseFragment implements HomeContract.View {
    private Banner banner;
    private TabLayout tabShouye;
    private RecyclerView rec_shouye_zhigong;
    private Rec_shouye_zhigongAdapter adapter;
    private RecyclerView rec_shouye_yisi;
    private RecyclerView rec_shouye_livinghome;
    private RecyclerView rec_shouye_hot;
    private RecyclerView rec_shouye_topic;
    private Rec_shouye_yisiAdapter yisiAdapter;
    private TextView tvZhigong;
    private TextView tvYisi;
    private TextView tvHot;
    private TextView tvTopic;
    private TextView tvlivinghome;
    private Rec_HotAdapter rec_hotAdapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        banner = (Banner) view.findViewById(R.id.banner);
        tabShouye = (TabLayout) view.findViewById(R.id.tab_shouye);

        rec_shouye_zhigong = (RecyclerView) view.findViewById(R.id.rec_shouye_zhigong);
        rec_shouye_yisi = (RecyclerView) view.findViewById(R.id.rec_shouye_yisi);
        rec_shouye_hot = (RecyclerView) view.findViewById(R.id.rec_shouye_hot);
        rec_shouye_topic = (RecyclerView) view.findViewById(R.id.rec_shouye_topic);
        rec_shouye_livinghome = (RecyclerView) view.findViewById(R.id.rec_shouye_livinghome);

        tvZhigong = (TextView) view.findViewById(R.id.tv_zhigong);
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
        rec_shouye_zhigong.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rec_shouye_livinghome.setLayoutManager(gridLayoutManager);
        rec_shouye_topic.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rec_shouye_hot.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec_shouye_hot.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        rec_shouye_yisi.setLayoutManager(new GridLayoutManager(getActivity(), 2));
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

            private Rec_livingHomeAdapter rec_livingHomeAdapter;
            private Rec_topicAdapter rec_topicAdapter;

            @Override
            public void run() {

                tvYisi.setText("周一周四·新品首发");
                tvZhigong.setText("品牌制造商直供");
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
                    tabShouye.addTab(tabShouye.newTab().setText(tabs.get(i)));
                }

                //rec直供的适配器
                List<ShouYeBean.DataBean.BrandListBean> brandList = shouYeBean.getData().getBrandList();
                adapter = new Rec_shouye_zhigongAdapter(brandList);
                rec_shouye_zhigong.setAdapter(adapter);

                //rec一四首发
                List<ShouYeBean.DataBean.NewGoodsListBean> newGoodsList = shouYeBean.getData().getNewGoodsList();
                yisiAdapter = new Rec_shouye_yisiAdapter(newGoodsList, getActivity());
                rec_shouye_yisi.setAdapter(yisiAdapter);

                //rec人气推荐
                List<ShouYeBean.DataBean.HotGoodsListBean> hotGoodsList = shouYeBean.getData().getHotGoodsList();
                rec_hotAdapter = new Rec_HotAdapter(hotGoodsList, getActivity());
                rec_shouye_hot.setAdapter(rec_hotAdapter);

                //rec专题精选
                List<ShouYeBean.DataBean.TopicListBean> topicList = shouYeBean.getData().getTopicList();
                rec_topicAdapter = new Rec_topicAdapter(topicList, getActivity());
                rec_shouye_topic.setAdapter(rec_topicAdapter);

                //rec居家
                List<ShouYeBean.DataBean.CategoryListBean.GoodsListBean> goodsList = shouYeBean.getData().getCategoryList().get(0).getGoodsList();
                rec_livingHomeAdapter = new Rec_livingHomeAdapter(goodsList, getActivity());
                rec_shouye_livinghome.setAdapter(rec_livingHomeAdapter);
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
