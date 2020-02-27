package com.example.shopping.fragment;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.fragment.home.Rec_home_livingHomeAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.shop.GoodsShoppingConstract;
import com.example.shopping.model.bean.DetailBean;
import com.example.shopping.model.bean.GoodsShoppingBottomListBean;
import com.example.shopping.model.bean.ShouYeBean;
import com.example.shopping.percenter.GoodsShoppingPercenter;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

//商品购买详情页      由 分类SortDescActivity item点击 ， hotActivity中 item点击跳转至此
public class GoodsShoppingActivity extends BaseActivity<GoodsShoppingConstract.View, GoodsShoppingConstract.Percenter> implements GoodsShoppingConstract.View {


    private ImageView selectNum;
    private Banner banner;
    private androidx.recyclerview.widget.RecyclerView recGoodsshopping;
    private ArrayList<ShouYeBean.DataBean.CategoryListBean.GoodsListBean> lists;
    private Rec_home_livingHomeAdapter rec_home_livingHomeAdapter;
    private android.widget.TextView textCollect;
    private ImageView imgCart;
    private android.widget.TextView textShop;
    private android.widget.TextView textCart;

    @Override
    protected int getLayout() {
        return R.layout.activity_goods_shopping;
    }

    @Override
    protected void initView() {
        textCollect = (TextView) findViewById(R.id.text_collect);
        imgCart = (ImageView) findViewById(R.id.img_cart);
        textShop = (TextView) findViewById(R.id.text_shop);
        textCart = (TextView) findViewById(R.id.text_cart);
        selectNum = (ImageView) findViewById(R.id.select_num);
        banner = (Banner) findViewById(R.id.banner);
        recGoodsshopping = (RecyclerView) findViewById(R.id.rec_goodsshopping);
        recGoodsshopping.setLayoutManager(new GridLayoutManager(this,2));
        //复用适配器
        lists = new ArrayList<>();
        rec_home_livingHomeAdapter = new Rec_home_livingHomeAdapter(lists);
        recGoodsshopping.setAdapter(rec_home_livingHomeAdapter);

    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", -1);
        persenter.getDetailData(id);
        persenter.getGoodsShoppingBottomListData(id);
    }

    @Override
    protected GoodsShoppingConstract.Percenter createPersenter() {
        return new GoodsShoppingPercenter();
    }

    @Override
    public void DetailDataReturn(DetailBean detailBean) {

    }

    @Override
    public void GoodsShoppingBottomListDataReturn(GoodsShoppingBottomListBean goodsShoppingBottomListBean) {
        List<GoodsShoppingBottomListBean.DataBean.GoodsListBean> goodsList = goodsShoppingBottomListBean.getData().getGoodsList();
        lists.clear();
        for (GoodsShoppingBottomListBean.DataBean.GoodsListBean item : goodsList){
            ShouYeBean.DataBean.CategoryListBean.GoodsListBean goodsListBean = new ShouYeBean.DataBean.CategoryListBean.GoodsListBean();
            goodsListBean.setName(item.getName());
            goodsListBean.setRetail_price(item.getRetail_price()+"");
            goodsListBean.setList_pic_url(item.getList_pic_url());
            lists.add(goodsListBean);
        }
        rec_home_livingHomeAdapter.notifyDataSetChanged();
    }
}
