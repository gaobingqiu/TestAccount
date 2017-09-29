package com.gbq.myaccount.base.ui.page;

import com.gbq.myaccount.util.LogUtil;

/**
 * 只有当Fragment真正展示的时候
 * Created by gbq on 2017-9-7.
 */

public abstract class LazyFragment extends BaseFragment {
    protected boolean isVisible;
    // 标志位，标志已经初始化完成。
    private boolean isPrepared = false;


    @Override
    protected void initViewsAndEvents() {
        isPrepared = true;
        //第一个显示的页面，会先触发懒加载，此处必须重新加载
        onVisible();
    }

    /**
     * 在这里实现Fragment数据的缓加载.
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        if (isPrepared) {
            LogUtil.d(this.getClass().getName() + "---lazyLoad----");
            lazyLoad();
        }
    }

    protected abstract void lazyLoad();

    protected void onInvisible() {
    }
}
