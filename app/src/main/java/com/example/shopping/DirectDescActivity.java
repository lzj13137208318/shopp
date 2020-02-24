package com.example.shopping;

import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.shopping.adapter.Rec_home_descAdapter;
import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.desc.DescContract;
import com.example.shopping.model.bean.GoodsDescBean;
import com.example.shopping.model.bean.GoodsDescListBean;
import com.example.shopping.percenter.GoodsDescPercenter;

import java.util.ArrayList;
import java.util.List;

public class DirectDescActivity extends BaseActivity<DescContract.View, DescContract.Persenter> implements DescContract.View {


    private ImageView ivDesc;
    private TextView tvDescTitle;
    private TextView tvDescDesc;
    private RecyclerView recDesc;
    private List<GoodsDescListBean.DataBeanX.DataBean> list;
    private Rec_home_descAdapter rec_home_descAdapter;

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

        list = new ArrayList<>();
        rec_home_descAdapter = new Rec_home_descAdapter(list);
        recDesc.setAdapter(rec_home_descAdapter);
    }

    @Override
    protected void initData() {
        int id = getIntent().getIntExtra("id", -1);
        if (id != -1){
            persenter.getDescData(id);
            persenter.getDescListData(id);
        }

    }

    @Override
    protected DescContract.Persenter createPersenter() {
        return new GoodsDescPercenter();
    }

    @Override
    public void DescDataReturn(GoodsDescBean goodsDescBean) {
        GoodsDescBean.DataBean.BrandBean brand = goodsDescBean.getData().getBrand();
        tvDescTitle.setText(brand.getName());
        tvDescDesc.setText(brand.getSimple_desc());
        Glide.with(context).load(brand.getNew_pic_url()).into(ivDesc);
    }

    @Override
    public void DescListDataReturn(GoodsDescListBean goodsDescListBean) {
        List<GoodsDescListBean.DataBeanX.DataBean> data = goodsDescListBean.getData().getData();
        rec_home_descAdapter.addData(data);
    }
}
