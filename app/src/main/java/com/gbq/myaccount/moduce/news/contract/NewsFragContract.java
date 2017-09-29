package com.gbq.myaccount.moduce.news.contract;

import com.gbq.myaccount.base.ui.BasePresenter;
import com.gbq.myaccount.base.ui.page.BaseView;
import com.gbq.myaccount.model.bean.News;

import java.util.List;

/**
 * 新闻活动
 * Created by gbq on 2017-8-25.
 */

public class NewsFragContract {
    public interface View extends BaseView<Presenter> {
        void setNewsList(List<News> newsList);
    }

    public interface Presenter extends BasePresenter {
        void loadNews(int type);

        void refreshNews(int type);
    }
}
