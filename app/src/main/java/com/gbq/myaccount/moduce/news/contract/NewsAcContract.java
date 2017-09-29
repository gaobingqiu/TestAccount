package com.gbq.myaccount.moduce.news.contract;

import com.gbq.myaccount.base.ui.BasePresenter;
import com.gbq.myaccount.base.ui.page.BaseView;

/**
 * 新闻活动
 * Created by gbq on 2017-8-25.
 */

public class NewsAcContract {
    public interface View extends BaseView<Presenter> {
        void setGlobeActive();

        void setTEActive();

        void setPEActive();
    }

    public interface Presenter extends BasePresenter {
        void onPageSelected(int position);
    }
}
