package com.example.shopping.fragment.cart;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.OrderActivity;
import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.cart.CartContract;
import com.example.shopping.model.bean.CartListsBean;
import com.example.shopping.model.bean.CartUpdataBean;
import com.example.shopping.model.bean.CatalogItem;
import com.example.shopping.percenter.cart.CartPercenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;


public class CartFragment extends BaseFragment<CartContract.View, CartContract.Percenter> implements CartContract.View {
    private List<CartListsBean.DataBean.CartListBean> list;
    private CartAdapter cartAdapter;
    //全选
    @BindView(R.id.cart_all)
    TextView cartAll;
    @OnClick(R.id.cart_all)
    public void onAll(){
        String s = cartEdit.getText().toString();
        if (s.equals("编辑")){
            //让商品添加永久标记
            // true为 编辑 说明是正常状态
            // false为 完成 说明是编辑状态
            cartAdapter.selectAll(true);
        }else {
            //让商品添加临时标记
            cartAdapter.selectAll(false);
        }
    }

    //编辑
    @BindView(R.id.cart_edit)
    TextView cartEdit;
    @OnClick(R.id.cart_edit)
    public void onEdit(){
        String s = cartEdit.getText().toString();
        if (s.equals("编辑")){
            cartEdit.setText("完成");
            cartAdapter.showAndHind(false);
        }else {
            cartEdit.setText("编辑");
            cartAdapter.showAndHind(true);
        }
    }


    //下单
    @OnClick(R.id.cart_order)
    public void onOrder(){
        startActivity(new Intent(context, OrderActivity.class));
    }

    //购物车数据
    @BindView(R.id.cart_rec)
    RecyclerView cartRec;
    //选中商品的数量
    @BindView(R.id.cart_select_number)
    TextView cartNum;
    //选中商品的总价
    @BindView(R.id.cart_select_price)
    TextView cartPrice;



    @Override
    protected int getLayout() {
        if (!EventBus.getDefault().isRegistered(this)){
            EventBus.getDefault().register(this);
        }
        return R.layout.fragment_cart;
    }
    //接收Eventbus
    @Subscribe
    public void getNum(CatalogItem catalogItem){
        //进行网络请求
        persenter.getCartGoodsUpdata(catalogItem.productId,catalogItem.goodsId,catalogItem.number,catalogItem.id+"");
    }
    @Override
    protected void initView(View view) {
        cartRec.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        cartAdapter = new CartAdapter(list);
        cartRec.setAdapter(cartAdapter);
    }

    @Override
    protected void initData() {
        persenter.getCartListData();
    }

    @Override
    protected CartContract.Percenter createPersenter() {
        return new CartPercenter();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }

    @Override
    public void cartDataReturn(CartListsBean cartListsBean) {
        List<CartListsBean.DataBean.CartListBean> cartList = cartListsBean.getData().getCartList();
        cartAdapter.addData(cartList);
    }

    //修改数据
    @Override
    public void cartGoodsUpdataReturn(CartUpdataBean cartUpdataBean) {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
