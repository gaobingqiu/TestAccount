package com.gbq.myaccount.util;

import android.widget.Toast;

import com.gbq.myaccount.MyApplication;

/**
 * 吐丝工具类,请勿在子线程调用该类
 * Created by gbq on 2017-8-16.
 */

public class ToastUtil {

    public static void showToast(String message){
        Toast.makeText(MyApplication.getInstance(),message,Toast.LENGTH_SHORT).show();
    }

    public static void showToast(int rId){
        Toast.makeText(MyApplication.getInstance(),rId,Toast.LENGTH_SHORT).show();
    }

    public static void showLongToast(String message){
        Toast.makeText(MyApplication.getInstance(),message,Toast.LENGTH_LONG).show();
    }

    public static void showLongToast(int rId){
        Toast.makeText(MyApplication.getInstance(),rId,Toast.LENGTH_LONG).show();
    }

}
