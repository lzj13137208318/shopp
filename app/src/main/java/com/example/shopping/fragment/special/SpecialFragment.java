package com.example.shopping.fragment.special;

import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.shopping.R;
import com.example.shopping.base.BaseFragment;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.interfaces.home.HomeContract;
import com.example.shopping.model.bean.ShouYeBean;
import com.example.shopping.percenter.HomePersenter;

import java.util.ArrayList;
import java.util.List;
//主题
public class SpecialFragment extends BaseFragment implements HomeContract.View {


    private RecyclerView rec;
    private List<ShouYeBean.DataBean.TopicListBean> list;
    private Rec_specialAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_topic;
    }

    @Override
    protected void initView(View view) {
        rec = view.findViewById(R.id.rec_zhuanti);
        rec.setLayoutManager(new LinearLayoutManager(context));
        list = new ArrayList<>();
        adapter = new Rec_specialAdapter(list);
        rec.setAdapter(adapter);
    }

    @Override
    protected void initData() {
        ((HomePersenter)persenter).getHomeData();
    }

    @Override
    protected IPersenter createPersenter() {
        return new HomePersenter();
    }

    @Override
    public void HomeDataReturn(ShouYeBean shouYeBean) {
        List<ShouYeBean.DataBean.TopicListBean> topicList = shouYeBean.getData().getTopicList();
        list.addAll(topicList);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void showError(String err) {

    }
}
