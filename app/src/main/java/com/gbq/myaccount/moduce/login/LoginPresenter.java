package com.gbq.myaccount.moduce.login;

import android.content.Context;
import android.content.SharedPreferences;

import com.gbq.myaccount.MyApplication;
import com.gbq.myaccount.constans.Configs;
import com.gbq.myaccount.constans.KeyConstants;

class LoginPresenter implements LoginContract.Presenter {
    private LoginContract.View mView;
    private SharedPreferences mSharedPreferences;

    LoginPresenter(LoginContract.View view) {
        mView = view;
        mSharedPreferences = MyApplication.getInstance().getSharedPreferences(Configs.SAVE_ACCOUNT, Context.MODE_PRIVATE);
    }

    @Override
    public void start() {
        String user = mSharedPreferences.getString(KeyConstants.USER, "");
        String password = mSharedPreferences.getString(KeyConstants.PASSWORD, "");
        mView.setUserAndPassword(user, password);
    }

    public void onSaveCbChange(boolean isCheck) {
        if (!isCheck) {
            mView.setUserAndPassword("", "");
        }
    }

    public void login(final String userName, final String password, final boolean isSave) {
        mView.onLoginSuccess();
    }

    @Override
    public void onDestroy() {
        mView = null;
        mSharedPreferences = null;
    }

    @Override
    public boolean isViewActive() {
        return mView != null && mView.isActive();
    }
}
