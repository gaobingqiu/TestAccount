package com.gbq.myaccount.net.api;

public interface HttpCallBack<T> {

    void onSuccess(T t);

    void onError(int code, String errorMsg);
}
