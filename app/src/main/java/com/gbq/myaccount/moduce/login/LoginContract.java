package com.gbq.myaccount.moduce.login;

import com.gbq.myaccount.base.ui.BasePresenter;
import com.gbq.myaccount.base.ui.page.BaseView;

public interface LoginContract {

    interface View extends BaseView<Presenter> {
        void setUserAndPassword(String user, String password);

        void onLoginSuccess();
    }

    interface Presenter extends BasePresenter {
        void login(final String userName, final String password, final boolean isSave);

        void onSaveCbChange(boolean isCheck);
    }
}
