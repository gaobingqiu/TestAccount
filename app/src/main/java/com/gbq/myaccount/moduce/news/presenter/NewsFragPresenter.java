package com.gbq.myaccount.moduce.news.presenter;

import com.gbq.myaccount.R;
import com.gbq.myaccount.constans.Configs;
import com.gbq.myaccount.constans.KeyConstants;
import com.gbq.myaccount.model.bean.News;
import com.gbq.myaccount.moduce.news.contract.NewsFragContract;
import com.gbq.myaccount.net.HttpRequest;
import com.gbq.myaccount.net.api.HttpCallBack;
import com.gbq.myaccount.net.subscriber.HttpSubscriber;
import com.gbq.myaccount.util.LogUtil;
import com.gbq.myaccount.util.SharePreferenceUtil;

import java.util.List;


public class NewsFragPresenter implements NewsFragContract.Presenter {
    private NewsFragContract.View mView;
    private HttpSubscriber mHttpObserver;
    private static int page_global = SharePreferenceUtil.getInstance().getInt(KeyConstants.GLOBAL_COUNT);
    private static int page_science = SharePreferenceUtil.getInstance().getInt(KeyConstants.SCIENCE_COUNT);
    private static int page_sports = SharePreferenceUtil.getInstance().getInt(KeyConstants.SPORT_COUNT);

    public NewsFragPresenter(NewsFragContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadNews(int type) {
        mHttpObserver = new HttpSubscriber(new HttpCallBack<List<News>>() {
            @Override
            public void onSuccess(List<News> newses) {
                if (isViewActive() && !newses.isEmpty()) {
                    mView.setNewsList(newses);
                }
            }

            @Override
            public void onError(int code, String errorMsg) {
                LogUtil.d(code + errorMsg);
            }
        });
        switch (type) {
            case Configs.NewsType.NEWS_GLOBAL:
                HttpRequest.getInstance().getGlobalNewDatasWithCache(mHttpObserver, page_global, true);
                page_global++;
                SharePreferenceUtil.getInstance().setInt(KeyConstants.GLOBAL_COUNT, page_global);
//                HttpRequest.getInstance().getGlobalNewDatas(mHttpObserver, page_global);
                break;
            case Configs.NewsType.NEWS_SCIENCE:
                HttpRequest.getInstance().getScienceNewDatasWithCache(mHttpObserver, page_science, true);
                page_science++;
                SharePreferenceUtil.getInstance().setInt(KeyConstants.SCIENCE_COUNT, page_science);
//                HttpRequest.getInstance().getScienceNewDatas(mHttpObserver, page_science);
                break;
            case Configs.NewsType.NEWS_SPORT:
                HttpRequest.getInstance().getSportsNewDatasWithCache(mHttpObserver, page_sports, true);
                page_sports++;
                SharePreferenceUtil.getInstance().setInt(KeyConstants.SPORT_COUNT, page_sports);
//                HttpRequest.getInstance().getSportsNewDatas(mHttpObserver, page_sports);
                break;
            default:
                break;
        }
    }

    @Override
    public void refreshNews(int type) {
        mView.showProcess(R.string.please_wait);
        mHttpObserver = new HttpSubscriber(new HttpCallBack<List<News>>() {
            @Override
            public void onSuccess(List<News> newses) {
                if (isViewActive() && !newses.isEmpty()) {
                    mView.setNewsList(newses);
                }
                mView.closeProcess();
            }

            @Override
            public void onError(int code, String errorMsg) {
                LogUtil.d(code + errorMsg);
            }
        });
        switch (type) {
            case Configs.NewsType.NEWS_GLOBAL:
                HttpRequest.getInstance().getGlobalNewDatas(mHttpObserver, page_global);
                page_global++;
                SharePreferenceUtil.getInstance().setInt(KeyConstants.GLOBAL_COUNT, page_global);
                break;
            case Configs.NewsType.NEWS_SCIENCE:
                HttpRequest.getInstance().getScienceNewDatas(mHttpObserver, page_science);
                page_science++;
                SharePreferenceUtil.getInstance().setInt(KeyConstants.SCIENCE_COUNT, page_science);
                break;
            case Configs.NewsType.NEWS_SPORT:
                HttpRequest.getInstance().getSportsNewDatas(mHttpObserver, page_sports);
                page_sports++;
                SharePreferenceUtil.getInstance().setInt(KeyConstants.SPORT_COUNT, page_sports);
                break;
            default:
                break;
        }
    }


    @Override
    public void onDestroy() {
        mView = null;
        mHttpObserver.unSubscribe();
    }

    @Override
    public boolean isViewActive() {
        return mView != null && mView.isActive();
    }

}
