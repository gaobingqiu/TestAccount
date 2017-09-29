package com.gbq.myaccount.moduce.start;


import android.os.CountDownTimer;

import com.gbq.myaccount.R;

/**
 * Created by gbq on 2017-6-23.
 */

class StartPresenter implements StartContract.Presenter {
    private StartContract.View mView;
    private final int[] mImages = new int[]{
            R.mipmap.start,
    };
    private CountDownTimer timer;

    StartPresenter(StartContract.View view) {
        this.mView = view;
        this.start();
    }

    @Override
    public void start() {
        mView.setBackImg(mImages[0]);
        timer = new CountDownTimer(3500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (isViewActive())
                    mView.setText((int) (millisUntilFinished / 1000 + 0.1));
            }

            @Override
            public void onFinish() {
                if (isViewActive()) {
                    mView.setText(0);
                    mView.startMainActivity();
                }
            }
        };
        timer.start();
    }

    @Override
    public void onDestroy() {
        mView = null;
    }

    @Override
    public boolean isViewActive() {
        return mView != null && mView.isActive();
    }

    @Override
    public void cancelTimer() {
        timer.cancel();
    }
}
