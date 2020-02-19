package com.example.shopping;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.shopping.Utils.ShowAndHindUtils;
import com.example.shopping.fragment.SortFragment;
import com.example.shopping.fragment.HomeFragment;
import com.example.shopping.fragment.MeFragment;
import com.example.shopping.fragment.CartFragment;
import com.example.shopping.fragment.SpecialFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Toolbar mTool;
    /**
     * 首页
     */
    private RadioButton mTvShouye;
    /**
     * 专题
     */
    private RadioButton mTvZhuanti;
    /**
     * 分类
     */
    private RadioButton mTvFenlei;
    /**
     * 购物车
     */
    private RadioButton mTvGouwuche;
    /**
     * 我的
     */
    private RadioButton mTvWode;
    private TextView mTvToolbar;
    private HomeFragment homeFragment;
    private SpecialFragment specialFragment;
    private SortFragment sortFragment;
    private CartFragment cartFragment;
    private MeFragment meFragment;
    private Fragment old;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_topic, R.id.navigation_sort, R.id.navigation_cart, R.id.navigation_me)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
    }

   /* private void initFragment() {
        homeFragment = new HomeFragment();
        specialFragment = new SpecialFragment();
        sortFragment = new SortFragment();
        cartFragment = new CartFragment();
        meFragment = new MeFragment();
        old = homeFragment;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fl,homeFragment)
                .add(R.id.fl, specialFragment)
                .add(R.id.fl, sortFragment)
                .add(R.id.fl, cartFragment)
                .add(R.id.fl,meFragment)
                .show(old)
                .hide(sortFragment)
                .hide(cartFragment)
                .hide(specialFragment)
                .hide(meFragment)
                .commit();
        mTvShouye.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this,homeFragment,old);
                mTvToolbar.setText("仿网易严选");
                mTool.setBackgroundResource(R.drawable.white);
                mTvToolbar.setTextColor(Color.BLACK);
                old = homeFragment;
            }
        });
        mTvZhuanti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this, specialFragment,old);
                mTool.setBackgroundResource(R.drawable.white);
                mTvToolbar.setText("仿网易严选");
                mTvToolbar.setTextColor(Color.BLACK);
                old = specialFragment;
            }
        });
        mTvFenlei.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this, sortFragment,old);
                mTvToolbar.setText("仿网易严选");
                mTvToolbar.setTextColor(Color.BLACK);
                mTool.setBackgroundResource(R.drawable.white);
                old = sortFragment;
            }
        });
        mTvGouwuche.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this, cartFragment,old);
                mTvToolbar.setText("仿网易严选");
                mTvToolbar.setTextColor(Color.BLACK);
                mTool.setBackgroundResource(R.drawable.white);
                old = cartFragment;
            }
        });
        mTvWode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAndHindUtils.showhind(MainActivity.this,meFragment,old);
                mTvToolbar.setText("我的");
                mTvToolbar.setTextColor(Color.WHITE);
                mTool.setBackgroundResource(R.drawable.bulake);
                old = meFragment;
            }
        });
    }*/

    private void initView() {
        mTool = (Toolbar) findViewById(R.id.tool);
        setTitle("");
        setSupportActionBar(mTool);

    }
}
