package com.example.shopping.fragment.home.activity;


import android.content.Intent;
import android.view.View;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.shopping.R;
import com.example.shopping.Utils.RecyclerViewSpacesItemDecoration;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.base.BaseAdapter;
import com.example.shopping.interfaces.direct.DirectContract;
import com.example.shopping.model.bean.BrandBean;
import com.example.shopping.percenter.DirectPercenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//homgfragment中品牌制造商直供跳转到此
public class DirectActivity extends BaseActivity<DirectContract.View,DirectContract.Percenter> implements DirectContract.View, BaseAdapter.OnItemClickListener {

    private RecyclerView rec;
    private Rec_directAdapter rec_directAdapter;
    private List<BrandBean.DataBeanX.DataBean> list;

    @Override
    protected int getLayout() {
        return R.layout.activity_direct;
    }

    @Override
    protected void initView() {
        rec = findViewById(R.id.rec_direct);
        rec.setLayoutManager(new GridLayoutManager(context,1));

        HashMap<String, Integer> stringIntegerHashMap = new HashMap<>();
        //添加item之间的间距
        stringIntegerHashMap.put(RecyclerViewSpacesItemDecoration.BOTTOM_DECORATION,10);//底部间距
        rec.addItemDecoration(new RecyclerViewSpacesItemDecoration(stringIntegerHashMap));
        list = new ArrayList<>();
        rec_directAdapter = new Rec_directAdapter(list);
        rec.setAdapter(rec_directAdapter);

        rec_directAdapter.setOnItemClickListener(this);
    }

    @Override
    protected void initData() {
        persenter.getBrandData(1,100);
    }

    @Override
    protected DirectContract.Percenter createPersenter() {
        return new DirectPercenter();
    }

    @Override
    public void BrandDatareturn(BrandBean brandBean) {
        List<BrandBean.DataBeanX.DataBean> data = brandBean.getData().getData();
        rec_directAdapter.addData(data);
    }

    @Override
    public void onItemClick(View v, int position) {
        Intent intent = new Intent(this, DirectDescActivity.class);
        intent.putExtra("id",list.get(position).getId());
        startActivity(intent);
    }
}
