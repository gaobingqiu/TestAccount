package com.gbq.myaccount.moduce.register;

import android.text.TextUtils;

import com.gbq.myaccount.net.HttpRequest;

/**
 * Created by gbq on 2017-9-14.
 */

public class RegisterPresenter implements RegisterContract.Presenter {
    private RegisterContract.View mView;

    RegisterPresenter(RegisterContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public boolean isViewActive() {
        return false;
    }

    @Override
    public void getCode(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return;
        }
    }

    @Override
    public void doQuickRegister(String phone, String code, boolean isAgree) {

    }

    @Override
    public void doRegister(String phone, String code, String password, boolean isAgree) {
        mView.toRegisterSuccessActivity(phone,code);
    }
}
