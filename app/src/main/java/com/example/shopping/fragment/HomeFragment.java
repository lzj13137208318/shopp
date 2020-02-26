package com.example.shopping.fragment;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.shopping.DirectActivity;
import com.example.shopping.DirectDescActivity;
import com.example.shopping.HotActivity;
import com.example.shopping.R;
import com.example.shopping.adapter.Rec_home_HotAdapter;
import com.example.shopping.adapter.Rec_home_directAdapter;
import com.example.shopping.adapter.Rec_home_livingHomeAdapter;
import com.example.shopping.adapter.Rec_home_topicAdapter;
import com.example.shopping.adapter.Rec_home_yisiAdapter;
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

import butterknife.BindView;
import butterknife.OnClick;


public class HomeFragment extends BaseFragment implements HomeContract.View {
    private List<ShouYeBean.DataBean.TopicListBean> topicList;
    private List<ShouYeBean.DataBean.HotGoodsListBean> hotGoodsList;
    private List<ShouYeBean.DataBean.NewGoodsListBean> newGoodsList;
    private List<ShouYeBean.DataBean.BrandListBean> brandList;
    private List<ShouYeBean.DataBean.CategoryListBean.GoodsListBean> goodsList;
    private Rec_home_directAdapter rec_home_directAdapter;

    @BindView(R.id.tv_yisi)
    TextView tvYisi;

    @BindView(R.id.tv_hot)
    TextView tvHot;

    @BindView(R.id.tv_direct)
    TextView tvDirect;

    @BindView(R.id.tv_topic)
    TextView tvTopic;

    @BindView(R.id.tv_livinghome)
    TextView tvlivinghome;

    @BindView(R.id.tv_kitchen)
    TextView tvkitchen;

    @BindView(R.id.tab_home)
    TabLayout tabHome;

    @BindView(R.id.rec_home_livinghome)
    RecyclerView rec_shouye_livinghome;

    //topic 专题
    @BindView(R.id.rec_home_topic)
    RecyclerView rec_shouye_topic;

    //direct 直供
    @BindView(R.id.rec_home_direct)
    RecyclerView rec_shouye_direct;

    @BindView(R.id.rec_home_yisi)
    RecyclerView rec_shouye_yisi;

    @BindView(R.id.rec_home_hot)
    RecyclerView rec_shouye_hot;

    //kitchen 餐厨
    @BindView(R.id.rec_home_kitchen)
    RecyclerView rec_shouye_kitchen;

    @BindView(R.id.banner)
    Banner banner;
    private Rec_home_yisiAdapter rec_home_yisiAdapter;
    private Rec_home_HotAdapter rec_home_hotAdapter;
    private Rec_home_topicAdapter rec_home_topicAdapter;
    private Rec_home_livingHomeAdapter rec_home_livingHomeAdapter;


    @Override
    protected int getLayout() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initView(View view) {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        rec_shouye_direct.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rec_shouye_livinghome.setLayoutManager(gridLayoutManager);
        rec_shouye_topic.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        rec_shouye_hot.setLayoutManager(new LinearLayoutManager(getActivity()));
        rec_shouye_hot.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        rec_shouye_yisi.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        rec_shouye_kitchen.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        //rec直供的适配器
        brandList = new ArrayList<>();
        rec_home_directAdapter = new Rec_home_directAdapter(brandList);
        rec_shouye_direct.setAdapter(rec_home_directAdapter);
//点击监听
        rec_home_directAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "直供" + brandList.get(position).getId(), Toast.LENGTH_SHORT).show();
                GoToDesc(brandList.get(position).getId());
            }
        });

        //rec一四首发
        newGoodsList = new ArrayList<>();
        rec_home_yisiAdapter = new Rec_home_yisiAdapter(newGoodsList);
        rec_shouye_yisi.setAdapter(rec_home_yisiAdapter);
//点击监听
        rec_home_yisiAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "一四首发" + position, Toast.LENGTH_SHORT).show();

            }
        });

        //rec人气推荐
        hotGoodsList = new ArrayList<>();
        rec_home_hotAdapter = new Rec_home_HotAdapter(hotGoodsList);
        rec_shouye_hot.setAdapter(rec_home_hotAdapter);
//点击监听
        rec_home_hotAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "人气推荐" + position, Toast.LENGTH_SHORT).show();

            }
        });

        //rec专题精选
        topicList = new ArrayList<>();
        rec_home_topicAdapter = new Rec_home_topicAdapter(topicList);
        rec_shouye_topic.setAdapter(rec_home_topicAdapter);
//点击监听
        rec_home_topicAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "专题精选" + position, Toast.LENGTH_SHORT).show();

            }
        });

        //rec居家
        goodsList = new ArrayList<>();
        rec_home_livingHomeAdapter = new Rec_home_livingHomeAdapter(goodsList);
        rec_shouye_livinghome.setAdapter(rec_home_livingHomeAdapter);
//点击监听
        rec_home_livingHomeAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Toast.makeText(context, "居家" + position, Toast.LENGTH_SHORT).show();

            }
        });

        //rec餐厨

        // rec_shouye_kitchen.setAdapter(rec_home_livingHomeAdapter);

    }

    //品牌制造商直供 点击监听
    @OnClick(R.id.tv_direct)
    public void OnDirect(){
        activity.startActivity(new Intent(activity, DirectActivity.class));
    }

    //新品首发的点击监听
    @OnClick(R.id.tv_yisi)
    public void Onyisi(){
        activity.startActivity(new Intent(activity, HotActivity.class).putExtra("id",1));
    }

    //人气推荐的点击监听
    @OnClick(R.id.tv_hot)
    public void OnHot(){
        activity.startActivity(new Intent(activity, HotActivity.class));
    }

    private void GoToDesc(int id) {
        Intent intent = new Intent(activity, DirectDescActivity.class);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }


    @Override
    protected void initData() {
        ((HomePersenter) persenter).getHomeData();
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
                rec_home_directAdapter.addData(shouYeBean.getData().getBrandList());

                //rec一四首发
                rec_home_yisiAdapter.addData(shouYeBean.getData().getNewGoodsList());

                //rec人气推荐
                rec_home_hotAdapter.addData(shouYeBean.getData().getHotGoodsList());

                //rec专题精选
                rec_home_topicAdapter.addData(shouYeBean.getData().getTopicList());

                if (shouYeBean.getData().getCategoryList().size() > 2) {
                    //rec居家
                    tvlivinghome.setText(shouYeBean.getData().getCategoryList().get(0).getName());
                    rec_home_livingHomeAdapter.upData(shouYeBean.getData().getCategoryList().get(0).getGoodsList());
                    //rec餐厨
                    tvkitchen.setText(shouYeBean.getData().getCategoryList().get(1).getName());
                   /* goodsList.clear();
                    for (int i = 0; i <shouYeBean.getData().getCategoryList().get(1).getGoodsList().size(); i++) {
                        ShouYeBean.DataBean.CategoryListBean.GoodsListBean goodsListBean = new ShouYeBean.DataBean.CategoryListBean.GoodsListBean();
                        goodsListBean.setName(shouYeBean.getData().getCategoryList().get(1).getGoodsList().get(i).getName());
                        goodsListBean.setList_pic_url(shouYeBean.getData().getCategoryList().get(1).getGoodsList().get(i).getList_pic_url());
                        goodsListBean.setRetail_price(shouYeBean.getData().getCategoryList().get(1).getGoodsList().get(i).getRetail_price());
                        goodsList.add(goodsListBean);
                    }
                    rec_home_livingHomeAdapter.notifyDataSetChanged();*/
                }


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
