package com.gbq.myaccount.moduce.news;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import com.gbq.myaccount.R;
import com.gbq.myaccount.base.ui.page.LazyFragment;
import com.gbq.myaccount.constans.KeyConstants;
import com.gbq.myaccount.model.bean.News;
import com.gbq.myaccount.moduce.news.adapter.NewsAdapter;
import com.gbq.myaccount.moduce.news.contract.NewsFragContract;
import com.gbq.myaccount.moduce.news.presenter.NewsFragPresenter;
import com.gbq.myaccount.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 新闻列表
 * Created by gbq on 2017-8-24.
 */

public class NewsFragment extends LazyFragment implements NewsFragContract.View, NewsAdapter.OnRecyclerItemClickListener,
        View.OnTouchListener {
    private int mType;

    @BindView(R.id.rv_news)
    RecyclerView mRecyclerView;
    @BindView(R.id.fragment_news)
    LinearLayout mLayout;
    private NewsAdapter mAdapter;
    private NewsFragContract.Presenter mPresenter;
    private boolean mCanLoad = true;

    public static NewsFragment newInstance(int type) {
        NewsFragment fragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("type", type);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_news;
    }

    @Override
    protected void initViewsAndEvents() {
        Bundle bundle = getArguments();
        mType = bundle.getInt("type");
        List<News> newsList = new ArrayList<>();
        mAdapter = new NewsAdapter(getActivity(), newsList);
        mAdapter.setOnRecyclerItemClickListener(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mRecyclerView.setAdapter(mAdapter);
        mPresenter = new NewsFragPresenter(this);
        mRecyclerView.setOnTouchListener(this);
        mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                //canScrollVertically(-1)判断能否向上滚动
                mCanLoad = !recyclerView.canScrollVertically(-1);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mCanLoad = false;
            }
        });
        super.initViewsAndEvents();
    }

    @Override
    public void setNewsList(final List<News> newsList) {
        mAdapter.setList(newsList);
    }

    @Override
    public void setPresenter(NewsFragContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void OnRecyclerItemClick(String url) {
        if (!isActive()) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(mActivity, NewsDetailActivity.class);
        intent.putExtra(KeyConstants.NEWS_URL, url);
        startActivity(intent);
    }

    @Override
    protected void lazyLoad() {
        mPresenter.loadNews(mType);
    }


    int startX = 0;
    int startY = 0;
    int moveX = 0;
    int moveY = 0;

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = (int) event.getRawX();
                startY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                moveX = (int) (event.getRawX() - startX);
                moveY = (int) (event.getRawY() - startY);
                LogUtil.d(String.valueOf(moveX) + "," + moveY);
                if (moveX > 100 || moveX < -100) {
                    break;
                } else {
                    if (moveY > 100 && mCanLoad) {
                        LogUtil.e("need flash");
                        mPresenter.refreshNews(mType);
                    }
                    break;
                }
        }
        return false;
    }
}
