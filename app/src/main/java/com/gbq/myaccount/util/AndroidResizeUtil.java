package com.gbq.myaccount.util;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * 在输入法弹出隐藏时重新布局
 * Created by gbq on 2017-7-18.
 */

public class AndroidResizeUtil {
    private View mChildOfContent;
    private FrameLayout.LayoutParams mLayoutParams;
    private int mUsablePreviousHeight = 0;
    private int mNormalHeight = 0;

    public AndroidResizeUtil(Activity activity) {
        FrameLayout content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);
        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                resizeChildOfContent();
            }
        });
        mLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
        mNormalHeight = mLayoutParams.height;
        mUsablePreviousHeight = mNormalHeight;
    }

    private void resizeChildOfContent() {
        int nowUsableHeight = computeUsableHeight();
        if (nowUsableHeight != mUsablePreviousHeight) {
            int sansKeyboardUsableHeight = mChildOfContent.getRootView().getHeight();
            int heightDiff = sansKeyboardUsableHeight - nowUsableHeight;
            if (heightDiff > (sansKeyboardUsableHeight / 4)) {
                mLayoutParams.height = nowUsableHeight;
            } else {
                if (mNormalHeight == 0 || mLayoutParams.height == mNormalHeight)
                    return;
                mLayoutParams.height = mNormalHeight;
            }
            mChildOfContent.requestLayout();
            mUsablePreviousHeight = nowUsableHeight;
        }
    }

    private int computeUsableHeight() {
        Rect rect = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(rect);
        return rect.bottom - rect.top + PhoneUtil.px2dip(25);
    }
}
