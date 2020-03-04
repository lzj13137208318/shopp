package com.example.shopping;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.shopping.base.BaseActivity;
import com.example.shopping.interfaces.IPersenter;
import com.example.shopping.interfaces.register.RegisterConstract;
import com.example.shopping.model.bean.RegisterBean;
import com.example.shopping.percenter.register.RegisterPercenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends BaseActivity<RegisterConstract.View, RegisterConstract.Persenter> implements RegisterConstract.View {


    @BindView(R.id.register_username)
    EditText mEditUsername;
    @BindView(R.id.register_pw1)
    EditText mEditPw1;
    @BindView(R.id.register_pw2)
    EditText mEditPw2;
    @BindView(R.id.register_verify)
    EditText mEditVerify;
    @BindView(R.id.layout_list)
    LinearLayout mLayoutList;

    @OnClick(R.id.btn_register)
    public void onRegister(){
        String uses = mEditUsername.getText().toString();
        String pwd1 = mEditPw1.getText().toString();
        String pwd2 = mEditPw2.getText().toString();
        if (pwd1.length() >=6 && pwd1.equals(pwd2)){
            persenter.getRegisterData(uses,pwd1);
        }else {
            showError("密码长度不对，或两次密码不一致");
        }
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    @Override
    protected RegisterConstract.Persenter createPersenter() {
        return new RegisterPercenter();
    }

    @Override
    public void RegisterReturn(RegisterBean registerBean) {
        int errno = registerBean.getErrno();
        if (errno == 0){
            Toast.makeText(context, "注册成功", Toast.LENGTH_SHORT).show();
            finish();
        }
        if (errno == 1000){
            showError("用户已存在");
        }
    }
}
