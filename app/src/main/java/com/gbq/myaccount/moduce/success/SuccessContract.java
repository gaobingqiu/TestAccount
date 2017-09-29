package com.gbq.myaccount.moduce.success;

import android.os.Bundle;

import com.gbq.myaccount.base.ui.BasePresenter;
import com.gbq.myaccount.base.ui.page.BaseView;

/**
 * Created by gbq on 2017-9-14.
 */

public interface SuccessContract {
    interface View extends BaseView<Presenter> {
        void setUser(String user,String password);
    }

    interface Presenter extends BasePresenter {
        void init(Bundle bundle);
    }
}
