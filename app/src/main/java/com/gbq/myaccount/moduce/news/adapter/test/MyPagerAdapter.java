package com.gbq.myaccount.moduce.news.adapter.test;

import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 无缓存viewPager的适配器
 * Created by gbq on 2017-9-7.
 */

public class MyPagerAdapter extends PagerAdapter {
    private List<Fragment> list;
    private FragmentTransaction mCurTransaction = null;
    private final FragmentManager mFragmentManager;

    public MyPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
        mFragmentManager = fm;
        this.list = list;
    }

    /**
     * 实例化 一个 页卡
     */
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        Fragment fragment = mFragmentManager.findFragmentByTag(String.valueOf(position));
        if (fragment != null) {
            mCurTransaction.attach(fragment);
        } else {
            fragment = list.get(position);
            mCurTransaction.add(container.getId(), fragment, String.valueOf(position));
        }
        return fragment;
    }

    @Override
    public void finishUpdate(ViewGroup container) {
        if (mCurTransaction != null) {
            mCurTransaction.commitNowAllowingStateLoss();
            mCurTransaction = null;
        }
    }


    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {

    }

    public void onDetroy() {
        if (mCurTransaction == null) {
            mCurTransaction = mFragmentManager.beginTransaction();
        }
        for (Fragment fragment : list) {
            mCurTransaction.detach(fragment);
        }
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
}
