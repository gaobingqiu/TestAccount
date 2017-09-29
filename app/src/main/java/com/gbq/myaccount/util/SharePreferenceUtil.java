package com.gbq.myaccount.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.gbq.myaccount.MyApplication;

/**
 * Created by gbq on 2017-9-26.
 */

public class SharePreferenceUtil {
    private final static String NAME = "myaccount";

    private volatile static SharePreferenceUtil mInstance;
    private SharedPreferences mSharedPreferences;

    public static SharePreferenceUtil getInstance() {
        if (mInstance == null) {
            synchronized (SharePreferenceUtil.class) {
                if (mInstance == null) {
                    mInstance = new SharePreferenceUtil();
                }
            }
        }
        return mInstance;
    }

    private SharePreferenceUtil() {
        mSharedPreferences = MyApplication.getInstance().getSharedPreferences(NAME, Context.MODE_PRIVATE);
    }

    public String getString(String key){
        return mSharedPreferences.getString(key,"");
    }

    public void setString(String key,String value){
        mSharedPreferences.edit().putString(key,value).apply();
    }

    public int getInt(String key){
        return mSharedPreferences.getInt(key,0);
    }

    public void setInt(String key,int value){
         mSharedPreferences.edit().putInt(key,value).apply();
    }


}
