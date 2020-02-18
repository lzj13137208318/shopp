package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IPersenter;

public class DescActivity extends BaseActivity {


    @Override
    protected int getLayout() {
        return R.layout.activity_desc;
    }

    @Override
    protected void initView() {
        TextView tvv = findViewById(R.id.tvvv);
        int id = getIntent().getIntExtra("id", -1);
        tvv.setText(id+"");
    }

    @Override
    protected void initData() {

    }

    @Override
    protected IPersenter createPersenter() {
        return null;
    }
}
