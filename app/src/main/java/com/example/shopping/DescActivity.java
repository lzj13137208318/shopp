package com.example.shopping;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IPersenter;

public class DescActivity extends BaseActivity {


    private ImageView ivDesc;
    private TextView tvDescTitle;
    private TextView tvDescDesc;
    private RecyclerView recDesc;

    @Override
    protected int getLayout() {
        return R.layout.activity_desc;
    }

    @Override
    protected void initView() {
        ivDesc = (ImageView) findViewById(R.id.iv_desc);
        tvDescTitle = (TextView) findViewById(R.id.tv_desc_title);
        tvDescDesc = (TextView) findViewById(R.id.tv_desc_desc);
        recDesc = (RecyclerView) findViewById(R.id.rec_desc);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(context, 2) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recDesc.setLayoutManager(gridLayoutManager);
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", -1);
    }

    @Override
    protected IPersenter createPersenter() {
        return null;
    }
}
