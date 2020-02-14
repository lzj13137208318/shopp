package com.example.shopping;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopping.model.bean.CatalogItem;

import java.util.ArrayList;

public class GoodsDescActivity extends AppCompatActivity {

    private TextView mTv123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_desc);
        initView();


    }

    private void initView() {
        ArrayList<CatalogItem> lists = (ArrayList<CatalogItem>) getIntent().getSerializableExtra("data");
        int posi = getIntent().getIntExtra("posi", -1);
        setTitle("");
        mTv123 = (TextView) findViewById(R.id.tvv);
        mTv123.setText(lists.get(posi).name);
    }
}
