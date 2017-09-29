package com.gbq.myaccount.moduce.start;


import com.gbq.myaccount.base.ui.BasePresenter;
import com.gbq.myaccount.base.ui.page.BaseView;

public interface StartContract {
    interface View extends BaseView<Presenter> {
        void setBackImg(int rid);

        void startMainActivity();

        void setText(long second);
    }

    interface Presenter extends BasePresenter {
        void cancelTimer();
    }
}
