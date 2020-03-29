package com.example.shopping.fragment.cart;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.LoginActivity;
import com.example.shopping.OrderActivity;
import com.example.shopping.R;
import com.example.shopping.Utils.SpUtils;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.cart.CartContract;
import com.example.shopping.model.bean.CartDeleteBean;
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
    private CartAdapter cartAdapter;
    private List<CartListsBean.DataBean.CartListBean> list;

    //购物车数据
    @BindView(R.id.cart_rec)
    RecyclerView cartRec;

    //选中商品的数量
    @BindView(R.id.cart_select_number)
    TextView cartNum;

    //选中商品的总价
    @BindView(R.id.cart_select_price)
    TextView cartPrice;

    //全选
    @BindView(R.id.cart_all)
    TextView cartAll;

    //下单
    @BindView(R.id.cart_order)
    TextView cartOrder;

    //编辑
    @BindView(R.id.cart_edit)
    TextView cartEdit;

    @OnClick({R.id.cart_edit,R.id.cart_all,R.id.cart_order})
    public void onEdit(View view){
        switch (view.getId()){
            //下单 or 删除
            case R.id.cart_order:
                if (!getPageIsEditor()){
                    startActivity(new Intent(context, OrderActivity.class));
                }else {
                    StringBuilder stb = new StringBuilder();
                    for (int i = 0; i < list.size(); i++) {
                        boolean delect = list.get(i).isDelect();
                        if (delect){
                            stb.append(list.get(i).getProduct_id()+",");
                        }
                    }
                    if(stb.length() > 0){
                        //去掉末尾的逗号
                        stb.deleteCharAt(stb.length()-1);
                        String pids = stb.toString();
                        //调用删除商品的接口
                        persenter.getCartGoodsDeleteData(pids);
                    }else{
                        showError("没有选中任何商品");
                    }
                }
            //编辑
            case R.id.cart_edit:
                //判断是编辑 还是删除
                if (!getPageIsEditor()){
                    cartEdit.setText("完成");
                    cartOrder.setText("删除");
                    cartAdapter.showAndHind(false);
                }else {
                    cartEdit.setText("编辑");
                    cartOrder.setText("下单");
                    cartAdapter.showAndHind(true);
                }
                break;
            //全选
            case R.id.cart_all:
                if (getPageIsEditor()){
                    //让商品添加永久标记  true为 编辑 说明是正常状态  false为 完成 说明是编辑状态
                    cartAdapter.selectAll(true);
                }else {
                    //让商品添加临时标记
                    cartAdapter.selectAll(false);
                }
                break;


        }
    }
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
        //进行修改的网络请求
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
        String token = SpUtils.getInstance().getString("token1");
        if (!TextUtils.isEmpty(token)){
            persenter.getCartListData();
        }else {
            Intent intent = new Intent(context, LoginActivity.class);
            startActivityForResult(intent,100);
        }
    }

    @Override
    public void startActivityForResult(Intent intent, int requestCode, @Nullable Bundle options) {
        super.startActivityForResult(intent, requestCode, options);
        if(requestCode == 100){
            if(persenter != null) persenter.getCartListData();
        }
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
        List<CartUpdataBean.DataBean.CartListBean> cartList = cartUpdataBean.getData().getCartList();
        for (int i = 0; i < cartList.size() ;i++) {
            if (list.get(i).getGoods_id() == cartList.get(i).getGoods_id())
                list.get(i).setNumber(cartList.get(i).getNumber());
        }
        cartAdapter.notifyDataSetChanged();
    }
    //删除数据
    @Override
    public void cartGoodsDeleteDatareturn(CartDeleteBean cartDeleteBean) {
        List<CartDeleteBean.DataBean.CartListBean> cartList = cartDeleteBean.getData().getCartList();
        int size = list.size();
        int cartListsize = cartList.size();
        for (int i = 0; i < size; i++) {
            CartListsBean.DataBean.CartListBean listBean = list.get(i);
            for (int j = 0; j < cartListsize; j++) {
                if (listBean.getId() == cartList.get(j).getId()){
                    list.remove(i);
                    i--;
                }
            }
        }
        cartAdapter.notifyDataSetChanged();
    }

    // 判断是不是编辑
    private boolean getPageIsEditor(){
        String str = cartEdit.getText().toString();
        return str.equals("编辑") ? false : true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
