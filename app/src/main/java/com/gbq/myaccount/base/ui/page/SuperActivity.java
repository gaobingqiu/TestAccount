package com.gbq.myaccount.base.ui.page;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.gbq.myaccount.MyApplication;
import com.gbq.myaccount.base.ui.BaseAppManager;
import com.gbq.myaccount.base.ui.ProcessListener;
import com.gbq.myaccount.util.LogUtil;

import butterknife.ButterKnife;

/**
 * 生命周期
 * Created by gbq on 2017-5-3.
 */

public abstract class SuperActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        ButterKnife.bind(this);
        initViewsAndEvents();
        BaseAppManager.getInstance().addActivity(this);
        LogUtil.d("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
//        LogUtil.d(getClass().getName() + "--->onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
//        LogUtil.d(getClass().getName() + "--->onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
//        LogUtil.d(getClass().getName() + "--->onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
//        LogUtil.d(getClass().getName() + "--->onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.d(getClass().getName() + "--->onDestroy");
        mProgressDialog = null;
        BaseAppManager.getInstance().removeActivity(this);
    }

    public abstract int getContentViewId();

    /**
     * init all views and add events
     */
    protected abstract void initViewsAndEvents();

    public void showProcess(int rId) {
        this.showProcess(getString(rId));
    }

    public void showProcess(String message) {
        this.showProcess(message, null);
    }

    public void showProcess(String message, final ProcessListener listener) {
        if (!isActive()) {
            return;
        }
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            if (TextUtils.isEmpty(message)) {
                mProgressDialog.setMessage("玩命加载中...");
            } else {
                mProgressDialog.setMessage(message);
            }
        }
        mProgressDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialog) {
                if (listener != null) {
                    listener.onCancel();
                }
            }
        });
        mProgressDialog.show();
    }

    public void closeProcess() {
        if (!isActive()) {
            return;
        }
        if (mProgressDialog != null) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    public boolean isActive() {
        return !this.isFinishing();
    }

}
