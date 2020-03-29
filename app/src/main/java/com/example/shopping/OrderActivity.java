package com.example.shopping;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.ui.ress.RessActivity;

import butterknife.BindView;
import butterknife.OnClick;

//下单 跳转至此
public class OrderActivity extends BaseActivity {

    @OnClick(R.id.order_finishi)
    public void onFinishi(){
        finish();
    }
    @BindView(R.id.order_name)
    TextView mOrderName;
    @BindView(R.id.order_phone)
    TextView mOrderPhone;
    @BindView(R.id.order_site)
    TextView mOrderSite;
    @BindView(R.id.order_goods_img)
    ImageView mOrderGoodsImg;
    @BindView(R.id.order_goods_desc)
    TextView mOrderGoodsDesc;
    @BindView(R.id.order_goods_price)
    TextView mOrderGoodsPrice;
    @BindView(R.id.order_goods_num)
    TextView mOrderGoodsNum;
    @BindView(R.id.order_site_discount)
    TextView mOrderSiteDiscount;
    @BindView(R.id.pay_now)
    Button mPayNow;



    @OnClick(R.id.order_site_updata)
    public void onTachView(View view){
        switch (view.getId()){
            //选择收货地址
            case R.id.order_site_updata:
                startActivity(new Intent(this, RessActivity.class));
                break;
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_order;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPersenter createPersenter() {
        return null;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
