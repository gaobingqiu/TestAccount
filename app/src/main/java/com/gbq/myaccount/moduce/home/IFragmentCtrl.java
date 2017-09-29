package com.gbq.myaccount.moduce.home;

import android.os.Bundle;

/**
 * Fragment操作
 * Created by gbq on 2017-7-28.
 */
 public interface IFragmentCtrl {
    void setCurrentFragment(String tag, Bundle bundle);
    void onBackPressed();
}
