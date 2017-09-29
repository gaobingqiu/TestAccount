package com.gbq.myaccount.moduce.home;

import android.text.TextUtils;

import com.gbq.myaccount.base.ui.page.BaseFragment;
import com.gbq.myaccount.moduce.login.LoginFragment;
import com.gbq.myaccount.moduce.register.RegisterFragment;
import com.gbq.myaccount.moduce.success.SuccessFragment;

/**
 * 所有Fragment
 * Created by gbq on 2017-7-28.
 */

public enum FragmentEnum {
    LOGIN("1", new LoginFragment()),REGISTER("2", new RegisterFragment()),SUCCESS("3", new SuccessFragment());

    private String mTag;
    private BaseFragment mFragment;

    FragmentEnum(String tag, BaseFragment fragment) {
        this.mTag = tag;
        this.mFragment = fragment;
    }

    public static BaseFragment getFragment(String tag) {
        for (FragmentEnum fragment : FragmentEnum.values()) {
            if (TextUtils.equals(fragment.getTag(), tag)) {
                return fragment.getFragment();
            }
        }
        return LOGIN.getFragment();
    }

    public String getTag() {
        return mTag;
    }

    public BaseFragment getFragment() {
        return mFragment;
    }
}
