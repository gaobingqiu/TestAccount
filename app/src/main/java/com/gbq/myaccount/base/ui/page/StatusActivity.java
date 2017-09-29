package com.gbq.myaccount.base.ui.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.gbq.myaccount.MyApplication;
import com.gbq.myaccount.R;


/**
 * 透明状态栏
 * Created by gbq on 2017-5-3.
 */

public abstract class StatusActivity extends SuperActivity {
    private View statusView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusColor(getResources().getColor(R.color.grey_toolbar));//和toolbar一个颜色
    }

    /**
     * 设置状态栏颜色
     *
     * @param color 状态栏颜色值
     */
    public void setStatusColor(int color) {
        // 设置状态栏透明
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 生成一个状态栏大小的矩形

        // 添加 statusView 到布局中
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();

        if (statusView != null) {
            decorView.removeView(statusView);
        }
        statusView = createStatusView(color);
        decorView.addView(statusView);
        // 设置根布局的参数
        ViewGroup rootView = (ViewGroup) ((ViewGroup) findViewById(android.R.id.content)).getChildAt(0);
        rootView.setFitsSystemWindows(true);
        rootView.setClipToPadding(true);
    }

    /**
     * 生成一个和状态栏大小相同的矩形条
     *
     * @param color 状态栏颜色值
     * @return 状态栏矩形条
     */
    private View createStatusView(int color) {
        // 获得状态栏高度
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        int statusBarHeight = getResources().getDimensionPixelSize(resourceId);

        // 绘制一个和状态栏一样高的矩形
        View statusView = new View(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                statusBarHeight);
        statusView.setLayoutParams(params);
        statusView.setBackgroundColor(color);
        return statusView;
    }


    /**
     * 提供remove方法，在不需要状态栏时可以移除
     */
    public void removeView() {
        ViewGroup decorView = (ViewGroup) getWindow().getDecorView();
        if (statusView != null) {
            decorView.removeView(statusView);
        }
    }

    public void showSoftInput(View view) {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
    }

    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        View focus = getCurrentFocus();
        if (focus != null) {
            imm.hideSoftInputFromWindow(focus.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
