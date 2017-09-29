package com.gbq.myaccount.moduce.news;

import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gbq.myaccount.R;
import com.gbq.myaccount.base.ui.page.SuperActivity;
import com.gbq.myaccount.constans.KeyConstants;
import com.gbq.myaccount.util.LogUtil;

import butterknife.BindView;


public class NewsDetailActivity extends SuperActivity {
    @BindView(R.id.wv_news)
    WebView mWebView;

    @Override
    public int getContentViewId() {
        return R.layout.activity_web;
    }

    @Override
    protected void initViewsAndEvents() {
        Bundle bundle = getIntent().getExtras();
        String url = null;
        if (bundle != null) {
            url = bundle.getString(KeyConstants.NEWS_URL, "");
        }
        load(url);
    }

    private void load(final String url) {
        WebSettings wvSettings = mWebView.getSettings();
        wvSettings.setAppCacheEnabled(false);
        wvSettings.setBuiltInZoomControls(true);
        // 设置允许JS弹窗
        wvSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        wvSettings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        WebView.setWebContentsDebuggingEnabled(true);
        mWebView.setWebViewClient(new AuthorizeWebViewClient());
        mWebView.loadUrl(url);
    }

    private class AuthorizeWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            LogUtil.d("onPageStarted:" + url);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @SuppressWarnings("deprecation")
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }

        @SuppressWarnings("deprecation")
        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
            handler.proceed();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();//返回上一页面
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        ViewParent parent = mWebView.getParent();
        if(parent !=null){
            ((ViewGroup)parent).removeView(mWebView);
            mWebView.destroy();
        }
    }
}
