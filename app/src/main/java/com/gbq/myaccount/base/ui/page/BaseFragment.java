package com.gbq.myaccount.base.ui.page;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.gbq.myaccount.base.ui.ProcessListener;
import com.gbq.myaccount.moduce.home.IFragmentCtrl;
import com.gbq.myaccount.util.LogUtil;

import java.lang.reflect.Field;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment extends Fragment {
    /**
     * 依附的activity
     */
    protected SuperActivity mActivity;

    protected IFragmentCtrl mFragmentCtrl;

    /**
     * 根view
     */
    protected View mRootView;


    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (getActivity() instanceof SuperActivity) {
            mActivity = (SuperActivity) getActivity();
        }
        if (mActivity instanceof IFragmentCtrl) {
            mFragmentCtrl = (IFragmentCtrl) mActivity;
        }
        LogUtil.d(this.getClass().getName());
    }

    @Override
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getContentViewId(), container, false);
            ButterKnife.bind(this, mRootView);
            mUnbinder = ButterKnife.bind(this, mRootView);
            initViewsAndEvents();
        }
        return mRootView;
    }

    /**
     * 设置根布局资源id
     *
     * @return
     */
    public abstract int getContentViewId();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (null != mRootView) {
            ((ViewGroup) mRootView.getParent()).removeView(mRootView);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            mUnbinder.unbind();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mActivity = null;
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract void initViewsAndEvents();

    public void showProcess(int rId) {
        this.showProcess(getString(rId));
    }

    public void showProcess(String message) {
        this.showProcess(message, null);
    }

    public void showProcess(String message, final ProcessListener listener) {
        if (!isActive()) {
            return;
        }
        mActivity.showProcess(message, listener);
    }

    public void closeProcess() {
        if (!isActive()) {
            return;
        }
        mActivity.closeProcess();
    }

    public boolean isActive() {
        return isAdded();
    }

    protected View getContentView() {
        return mRootView;
    }

    protected View findViewById(int id) {
        if (mRootView != null)
            return mRootView.findViewById(id);
        return null;
    }

    /**
     * 设置标题
     */
    protected void setTitle(int resId) {
        if (mActivity instanceof ToolBarActivity) {
            ToolBarActivity activity = (ToolBarActivity) mActivity;
            activity.setToolBarTitle(getString(resId));
        }
    }

    /**
     * 是否拦截回退事件
     */
    public boolean onBackPressed() {
        return false;
    }
}
