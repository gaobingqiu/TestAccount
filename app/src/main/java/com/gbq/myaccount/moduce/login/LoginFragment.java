package com.gbq.myaccount.moduce.login;

import android.content.Intent;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.gbq.myaccount.R;
import com.gbq.myaccount.base.ui.page.BaseFragment;
import com.gbq.myaccount.moduce.home.FragmentEnum;
import com.gbq.myaccount.moduce.news.NewsActivity;

import butterknife.BindView;

/**
 * 登录页面
 * Created by gbq on 2017-7-11.
 */

public class LoginFragment extends BaseFragment implements View.OnClickListener, LoginContract.View {
    @BindView(R.id.et_user)
    EditText mUserEt;
    @BindView(R.id.et_password)
    EditText mPasswordEt;
    @BindView(R.id.cb_remember)
    CheckBox mSaveUserCb;

    private LoginContract.Presenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initViewsAndEvents() {
        findIds();
        setPresenter(new LoginPresenter(this));
        mPresenter.start();
    }

    private void findIds() {
        findViewById(R.id.bt_login).setOnClickListener(this);
        findViewById(R.id.tv_reset).setOnClickListener(this);
        findViewById(R.id.tv_register).setOnClickListener(this);
        findViewById(R.id.iv_qq).setOnClickListener(this);
        findViewById(R.id.iv_weibo).setOnClickListener(this);
        findViewById(R.id.iv_weixin).setOnClickListener(this);
        findViewById(R.id.iv_qq).setVisibility(View.GONE);
        findViewById(R.id.iv_weibo).setVisibility(View.GONE);
        findViewById(R.id.iv_weixin).setVisibility(View.GONE);
        mSaveUserCb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mPresenter.onSaveCbChange(isChecked);
            }
        });
    }

    @Override
    public void setUserAndPassword(String user, String password) {
        mUserEt.setText(user);
        mPasswordEt.setText(password);
    }

    @Override
    public void onLoginSuccess() {
        Intent intent = new Intent();
        intent.setClass(mActivity, NewsActivity.class);
        startActivity(intent);
    }

    private void toRegister() {
        mFragmentCtrl.setCurrentFragment(FragmentEnum.REGISTER.getTag(), null);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
                mPresenter.login(mUserEt.getText().toString(), mPasswordEt.getText().toString(), mSaveUserCb.isChecked());
                break;
            case R.id.tv_reset:
                break;
            case R.id.tv_register:
                toRegister();
                break;
            case R.id.iv_qq:
                break;
            case R.id.iv_weibo:
                break;
            case R.id.iv_weixin:
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}
