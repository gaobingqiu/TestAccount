package com.gbq.myaccount.moduce.personal;

import android.graphics.Bitmap;

import com.gbq.myaccount.base.ui.BasePresenter;
import com.gbq.myaccount.base.ui.page.BaseView;

public interface PersonalContract {
    interface View extends BaseView<Presenter> {
        void setUserName(String userName);

        void setHeadIv(Bitmap bitmap);

        void logout();
    }


    interface Presenter extends BasePresenter {

    }
}
