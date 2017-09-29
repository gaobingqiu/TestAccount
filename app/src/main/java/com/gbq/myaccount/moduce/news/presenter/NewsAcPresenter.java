package com.gbq.myaccount.moduce.news.presenter;

import com.gbq.myaccount.constans.Configs;
import com.gbq.myaccount.moduce.news.contract.NewsAcContract;


public class NewsAcPresenter implements NewsAcContract.Presenter {
    private NewsAcContract.View mView;

    public NewsAcPresenter(NewsAcContract.View view) {
        mView = view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position) {
            case Configs.NewsType.NEWS_GLOBAL:
                mView.setGlobeActive();
                break;
            case Configs.NewsType.NEWS_SCIENCE:
                mView.setTEActive();
                break;
            case Configs.NewsType.NEWS_SPORT:
                mView.setPEActive();
                break;
            default:
                mView.setGlobeActive();
                break;
        }
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
