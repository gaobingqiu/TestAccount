package com.gbq.myaccount.moduce.home;

import android.content.Intent;

import com.gbq.myaccount.base.ui.page.BaseFragment;

/**
 * 启动页
 * Created by gbq on 2017-5-16.
 */

public class HomePresenter implements HomeContract.Presenter {
    private HomeContract.View mView;

    HomePresenter(HomeContract.View view) {
        this.mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void init(Intent intent) {
        String action = intent.getAction();
        String tag = ActionEnum.getTag(action);
        mView.replaceCurrentFragment(getFragment(tag),tag, intent.getExtras());
    }

    @Override
    public BaseFragment getFragment(String tag) {
        return FragmentEnum.getFragment(tag);
    }


    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public boolean isViewActive() {
        return mView != null && mView.isActive();
    }
}