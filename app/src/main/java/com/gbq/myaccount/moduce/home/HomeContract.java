package com.gbq.myaccount.moduce.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.gbq.myaccount.base.ui.BasePresenter;
import com.gbq.myaccount.base.ui.page.BaseFragment;
import com.gbq.myaccount.base.ui.page.BaseView;

/**
 * 启动页
 * Created by gbq on 2017-5-16.
 */

public interface HomeContract {
    interface View extends BaseView<Presenter> {
        void replaceCurrentFragment(Fragment fragment, String tag, Bundle bundle);
    }

    interface Presenter extends BasePresenter {
        void init(Intent intent);

        BaseFragment getFragment(String tag);
    }
}