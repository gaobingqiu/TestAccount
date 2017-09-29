package com.gbq.myaccount.moduce.register;

import com.gbq.myaccount.base.ui.BasePresenter;
import com.gbq.myaccount.base.ui.page.BaseView;

public interface RegisterContract {
    interface View extends BaseView<Presenter> {
        void toRegisterSuccessActivity(String userName, String password);
    }

    interface Presenter extends BasePresenter {
        void getCode(String phone);

        void doQuickRegister(String phone, String code,boolean isAgree);

        void doRegister(String phone, String code, String password,boolean isAgree);
    }
}
