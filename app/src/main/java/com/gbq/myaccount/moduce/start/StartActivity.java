package com.gbq.myaccount.moduce.start;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.gbq.myaccount.R;
import com.gbq.myaccount.base.ui.page.SuperActivity;
import com.gbq.myaccount.moduce.home.HomeActivity;
import com.gbq.myaccount.moduce.news.NewsDetailActivity;
import com.gbq.myaccount.view.CircularStatisticsView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 启动页
 * Created by gbq on 2017-5-16.
 */

public class StartActivity extends SuperActivity implements StartContract.View {
    @BindView(R.id.ivBg)
    ImageView ivBg;
    @BindView(R.id.tvSkip)
    TextView tvSkip;

    private StartContract.Presenter mStartPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_start;
    }

    @Override
    protected void initViewsAndEvents() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
        setPresenter(new StartPresenter(this));
    }


    @Override
    public void onResume(){
        super.onResume();
    }

    @OnClick(R.id.tvSkip)
    public void skip() {
        mStartPresenter.cancelTimer();
        startMainActivity();
    }

    @Override
    public void setBackImg(int rid) {
        ivBg.setImageResource(rid);
    }

    @Override
    public void startMainActivity() {
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    @Override
    public void setText(long second) {
        tvSkip.setText(String.format(getResources().getString(R.string.skip), second));
    }

    @Override
    protected void onDestroy() {
        mStartPresenter.cancelTimer();
        mStartPresenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void setPresenter(@NonNull StartContract.Presenter presenter) {
        mStartPresenter = presenter;
    }
}