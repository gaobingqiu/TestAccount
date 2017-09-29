package com.gbq.myaccount.moduce.news;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gbq.myaccount.R;
import com.gbq.myaccount.base.ui.page.ToolBarActivity;
import com.gbq.myaccount.constans.Configs;
import com.gbq.myaccount.moduce.home.IFragmentCtrl;
import com.gbq.myaccount.moduce.news.adapter.MyFragmentPagerAdapter;
import com.gbq.myaccount.moduce.news.contract.NewsAcContract;
import com.gbq.myaccount.moduce.news.presenter.NewsAcPresenter;
import com.gbq.myaccount.util.LogUtil;
import com.gbq.myaccount.util.PhoneUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * 新闻页面
 * Created by gbq on 2017-8-24.
 */

public class NewsActivity extends ToolBarActivity implements View.OnClickListener, ViewPager.OnPageChangeListener, IFragmentCtrl,
        NewsAcContract.View {
    @BindView(R.id.vp_news)
    ViewPager mViewpager;

    @BindView(R.id.tv_news_global)
    TextView mGlobalTv;
    @BindView(R.id.tv_news_science)
    TextView mScienceTv;
    @BindView(R.id.tv_news_sports)
    TextView mSportsTv;
    @BindView(R.id.iv_tab_line)
    ImageView mColorLine;

    private NewsAcContract.Presenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_news;
    }

    @Override
    protected void initViewsAndEvents() {
        findViewById(R.id.tv_news_global).setOnClickListener(this);
        findViewById(R.id.tv_news_sports).setOnClickListener(this);
        findViewById(R.id.tv_news_science).setOnClickListener(this);
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(NewsFragment.newInstance(Configs.NewsType.NEWS_GLOBAL));
        fragmentArrayList.add(NewsFragment.newInstance(Configs.NewsType.NEWS_SPORT));
        fragmentArrayList.add(NewsFragment.newInstance(Configs.NewsType.NEWS_SCIENCE));
        MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentArrayList);
        mViewpager.setAdapter(adapter);
        mViewpager.addOnPageChangeListener(this);
        setPresenter(new NewsAcPresenter(this));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_news_global:
                setPositionPage(Configs.NewsType.NEWS_GLOBAL);
                setGlobeActive();
                break;
            case R.id.tv_news_sports:
                setPositionPage(Configs.NewsType.NEWS_SPORT);
                setPEActive();
                break;
            case R.id.tv_news_science:
                setPositionPage(Configs.NewsType.NEWS_SCIENCE);
                setTEActive();
                break;
            default:
                break;
        }
    }

    private void setPositionPage(int type) {
        LogUtil.d("setPositionPage:" + type);
        mViewpager.setCurrentItem(type);
    }

    public void setGlobeActive() {
        mGlobalTv.setTextColor(ContextCompat.getColor(this, R.color.color_active));
        mScienceTv.setTextColor(ContextCompat.getColor(this, R.color.color_normal));
        mSportsTv.setTextColor(ContextCompat.getColor(this, R.color.color_normal));
        PhoneUtil.setViewMargin(mColorLine, true, 0, 0, 0, 0);
    }

    public void setPEActive() {
        mGlobalTv.setTextColor(ContextCompat.getColor(this, R.color.color_normal));
        mScienceTv.setTextColor(ContextCompat.getColor(this, R.color.color_normal));
        mSportsTv.setTextColor(ContextCompat.getColor(this, R.color.color_active));
        PhoneUtil.setViewMargin(mColorLine, true, 120, 0, 0, 0);
    }

    public void setTEActive() {
        mGlobalTv.setTextColor(ContextCompat.getColor(this, R.color.color_normal));
        mScienceTv.setTextColor(ContextCompat.getColor(this, R.color.color_active));
        mSportsTv.setTextColor(ContextCompat.getColor(this, R.color.color_normal));
        PhoneUtil.setViewMargin(mColorLine, true, 240, 0, 0, 0);
    }


    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @Override
    public void onPageSelected(int position) {
        mPresenter.onPageSelected(position);
        LogUtil.d(String.valueOf(position));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void setCurrentFragment(String tag, Bundle bundle) {

    }

    @Override
    public void setPresenter(NewsAcContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

}
