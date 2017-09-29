package com.gbq.myaccount.net;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.text.TextUtils;

import com.gbq.myaccount.util.LogUtil;

/**
 * 监听网络状态变化
 * Created by gbq on 2017-7-28.
 */

public class NetChangeReceive extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (TextUtils.equals(ConnectivityManager.CONNECTIVITY_ACTION, intent.getAction())) {
            LogUtil.d("NetChangeReceive");
        }
    }
}
