package com.example.shopping.ui.ress;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.interfaces.ress.RessConstract;
import com.example.shopping.model.bean.RessBean;
import com.example.shopping.percenter.ress.RessPercenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RessActivity extends BaseActivity<RessConstract.View, RessConstract.Percenter> implements RessConstract.View {


    @BindView(R.id.rec_ress)
    RecyclerView mRecRess;
    @BindView(R.id.tv_ress_news)
    TextView mTvRessNews;
    private Rec_ressAdapter rec_ressAdapter;
    private List<RessBean.DataBean> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_ress;
    }

    @Override
    protected void initView() {
        mRecRess.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();
        rec_ressAdapter = new Rec_ressAdapter(list);
        mRecRess.setAdapter(rec_ressAdapter);
    }

    @Override
    protected void initData() {
        persenter.getressData();
    }

    @Override
    protected RessConstract.Percenter createPersenter() {
        return new RessPercenter();
    }

    @OnClick({R.id.tv_ress_news})
    public void onClick(View v) {
        switch (v.getId()) {
            //添加新的地址
            case R.id.tv_ress_news:
                Intent intent = new Intent(this,AddRessActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void RessDataReturn(RessBean ressBean) {
        List<RessBean.DataBean> data = ressBean.getData();
        if (data != null && data.size()>0){
            rec_ressAdapter.upData(data);
        }else {
            Toast.makeText(context, "第一次进来 没添加地址", Toast.LENGTH_SHORT).show();
        }
    }
}
