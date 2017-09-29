    package com.gbq.myaccount;

import android.app.Application;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.gbq.myaccount.net.NetChangeReceive;

/**
 * 参考
 * https://github.com/smuyyh/SprintNBA.git
 */
public class MyApplication extends Application {
    private static MyApplication mInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        registerNetChangeReceive();
    }

    public static MyApplication getInstance() {
        return mInstance;
    }

    private void registerNetChangeReceive() {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(new NetChangeReceive(), filter);
    }
}
