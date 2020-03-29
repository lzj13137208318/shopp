package com.example.shopping.fragment.me;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.shopping.R;
import com.example.shopping.adapter.VPAdapter;
import com.example.shopping.fragment.wode.Fragment1;
import com.example.shopping.ui.MyVp;
import com.example.shopping.ui.Myscrollview;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MeFragment extends Fragment {
    private TabLayout tab;
    private MyVp vp;
    private Myscrollview scrollView;
    int toolBarPositionY = 0;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_me, null);
        initView(view);

        return view;
    }


    private void initView(View view) {
        tab = (TabLayout) view.findViewById(R.id.tab);
        vp = (MyVp) view.findViewById(R.id.vp);
        scrollView = (Myscrollview) view.findViewById(R.id.scrollView);

        ArrayList<Fragment> fragments = new ArrayList<>();
        ArrayList<String> tabs = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Fragment1 fragment1 = new Fragment1();
            tabs.add("测试" + i);
            fragments.add(fragment1);
        }
         VPAdapter adapter = new VPAdapter(getActivity().getSupportFragmentManager(), fragments, tabs);
        tab.setupWithViewPager(vp);
        vp.setAdapter(adapter);


        scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int[] location = new int[2];
                tab.getLocationOnScreen(location);
                int xPosition = location[0];
                int yPosition = location[1];
                if (yPosition < toolBarPositionY) {
                  //  tab.setVisibility(View.VISIBLE);
                    scrollView.setNeedScroll(false);
                } else {
                   // tab.setVisibility(View.GONE);
                    scrollView.setNeedScroll(true);
                }

            }
        });
    }
}
