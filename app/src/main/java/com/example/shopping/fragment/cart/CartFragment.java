package com.example.shopping.fragment.cart;

import android.view.View;

import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IPersenter;


public class CartFragment extends BaseFragment {


    @Override
    protected int getLayout() {
        return R.layout.fragment_cart;
    }

    @Override
    protected void initView(View view) {

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
