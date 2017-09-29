package com.gbq.myaccount.moduce.home;

import android.text.TextUtils;

/**
 * 所有Fragment
 * Created by gbq on 2017-7-28.
 */

public enum ActionEnum {
    LOGIN("1", "com.gbq.myaccount.LOGIN");

    private String mTag;
    private String mAction;

    ActionEnum(String tag, String action) {
        this.mTag = tag;
        this.mAction = action;
    }

    public static String getTag(String action) {
        for (ActionEnum actionEnum : ActionEnum.values()) {
            if (TextUtils.equals(actionEnum.getAction(), action)) {
                return actionEnum.getTag();
            }
        }
        return LOGIN.getTag();
    }

    public String getAction() {
        return mAction;
    }

    public String getTag() {
        return mTag;
    }
}
