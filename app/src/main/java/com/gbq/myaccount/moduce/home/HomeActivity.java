package com.gbq.myaccount.moduce.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.gbq.myaccount.R;
import com.gbq.myaccount.base.ui.page.BaseFragment;
import com.gbq.myaccount.base.ui.page.ToolBarActivity;

import java.util.LinkedList;
import java.util.List;

/**
 * 启动页
 * Created by gbq on 2017-5-16.
 */

public class HomeActivity extends ToolBarActivity implements IFragmentCtrl, HomeContract.View {
    private FragmentManager mFragmentManager;
    private HomeContract.Presenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViewsAndEvents() {
        mFragmentManager = getSupportFragmentManager();
        setPresenter(new HomePresenter(this));
        mPresenter.init(getIntent());
    }

    public void setCurrentFragment(String tag, Bundle bundle) {
        hideSoftInput();
        Fragment fragment = mFragmentManager.findFragmentByTag(tag);
        if (fragment != null) {
            mFragmentManager.popBackStackImmediate();
        } else {
            fragment = mPresenter.getFragment(tag);
        }
        replaceCurrentFragment(fragment, tag, bundle);
    }

    public void replaceCurrentFragment(Fragment fragment, String tag, Bundle bundle) {
        FragmentTransaction transaction = mFragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        transaction.replace(R.id.content_fragment, fragment, tag);
        //加入回退栈，对应popBackStackImmediate
        transaction.addToBackStack(tag);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onBackPressed() {
        List<Fragment> fragments = mFragmentManager.getFragments();
        LinkedList<Fragment> backFragments = new LinkedList<>();
        for (Fragment fragment : fragments) {
            backFragments.addLast(fragment);
        }
        if (backFragments.size() > 1) {
            BaseFragment baseFragment = (BaseFragment) backFragments.removeLast();
            if (!baseFragment.onBackPressed()) {
                super.onBackPressed();
            }
        } else {
            finish();
        }
    }

    @Override
    public void setPresenter(HomeContract.Presenter presenter) {
        this.mPresenter = presenter;
    }
}