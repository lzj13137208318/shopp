package com.example.shopping.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.LoginActivity;
import com.example.shopping.R;
import com.example.shopping.Utils.SpUtils;
import com.example.shopping.fragment.home.Rec_home_livingHomeAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.shop.GoodsShoppingConstract;
import com.example.shopping.model.bean.CartBean;
import com.example.shopping.model.bean.DetailBean;
import com.example.shopping.model.bean.GoodsShoppingBottomListBean;
import com.example.shopping.model.bean.ShouYeBean;
import com.example.shopping.percenter.GoodsShoppingPercenter;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

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
    private DetailBean detailBean;
    private PopupWindow pop;

    @BindView(R.id.webview)
    WebView web;

    @BindView(R.id.shop_cart_num)
    TextView shop_num;
    private TextView num;
    private CartBean cartBean;

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

        if (cartBean != null && cartBean.getData() != null && cartBean.getData().getCartList() != null){
            shop_num.setText(cartBean.getData().getCartList().size());
        }

    }
    //选择购买数量 弹出popupwind
    @OnClick(R.id.select_num)
    public void onNum(){
        showPoP();
        Alpha(0.5f);
    }
    //
    @OnClick(R.id.text_cart)
    public void onCart(){
        if (pop != null && pop.isShowing()){
            String token = SpUtils.getInstance().getString("token");
            pop.dismiss();
            Alpha(1.0f);
            //说明已经登录过了
            if (!token.equals("")){
                persenter.addCartData(detailBean.getData().getInfo().getId()+"",
                        Integer.parseInt(num.getText().toString()),
                        detailBean.getData().getGallery().get(0).getId()+"");
            }else {
                Intent intent = new Intent(this, LoginActivity.class);
                startActivityForResult(intent,100);
            }
        }else {
            showPoP();
            Alpha(0.5f);
        }
    }


    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        if (requestCode == 100){

        }
    }

    private void Alpha(float alpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        lp.alpha =alpha;
        getWindow().setAttributes(lp);
    }


    private void showPoP() {
        View view = LayoutInflater.from(context).inflate(R.layout.shopping_pop, null);
        pop = new PopupWindow(view, RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT);
        pop.setBackgroundDrawable(null);
        pop.setOutsideTouchable(true);

        TextView add = view.findViewById(R.id.pop_add);
        TextView price = view.findViewById(R.id.pop_price);
        num = view.findViewById(R.id.pop_num);
        TextView fini = view.findViewById(R.id.pop_finishi);
        TextView subtract = view.findViewById(R.id.pop_subtract);
        ImageView img = view.findViewById(R.id.pop_img);

        if (detailBean != null){

            Glide.with(context).load(detailBean.getData().getInfo().getList_pic_url()).into(img);
            price.setText("价格："+detailBean.getData().getInfo().getRetail_price());

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    num.setText(Integer.parseInt(num.getText().toString())+1+"");
                }
            });
            subtract.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int i = Integer.parseInt(num.getText().toString());
                    if (i == 1){
                        return;
                    }else {
                        num.setText(Integer.parseInt(num.getText().toString())-1+"");
                    }
                }
            });

            fini.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pop.dismiss();
                    Alpha(1.0f);
                }
            });
        }
        pop.showAtLocation(selectNum, Gravity.BOTTOM,0,110);
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

        this.detailBean = detailBean;
        List<DetailBean.DataBeanX.GalleryBean> gallery = detailBean.getData().getGallery();
        //设置banner
        setBaaner(gallery);
        //设置web
        String goods_desc = detailBean.getData().getInfo().getGoods_desc();
        setWeb(goods_desc);
    }

    private void setWeb(String goods_desc) {
        String css_str = getResources().getString(R.string.css_goods);
        StringBuilder sb = new StringBuilder();
        sb.append("<html><head>");
        sb.append("<style>"+css_str+"</style></head><body>");
        sb.append(goods_desc+"</body></html>");
        web.loadData(sb.toString(),"text/html","utf-8");
    }

    private void setBaaner(List<DetailBean.DataBeanX.GalleryBean> gallery) {
        ArrayList<String> banns = new ArrayList<>();
        for (int i = 0; i < gallery.size(); i++) {
            banns.add(gallery.get(i).getImg_url());
        }
        banner.setImages(banns).setImageLoader(new ImageLoader() {
            @Override
            public void displayImage(Context context, Object path, ImageView imageView) {
                Glide.with(context).load((String)path).into(imageView);
            }
        }).start();
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

    @Override
    public void CartDataReturn(CartBean cartBean) {
        this.cartBean = cartBean;
        if (cartBean.getErrno()==400){
            Toast.makeText(context, cartBean.getErrmsg(), Toast.LENGTH_SHORT).show();
        }else {
            shop_num.setText(cartBean.getData().getCartList().size());
        }
    }
}
