package com.gbq.myaccount.moduce.personal;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gbq.myaccount.R;
import com.gbq.myaccount.base.ui.page.ToolBarActivity;

import butterknife.BindView;

/**
 * 用户中心
 * Created by gbq on 2017-9-14.
 */

public class PersonalActivity extends ToolBarActivity implements View.OnClickListener, PersonalContract.View {
    @BindView(R.id.tv_username)
    TextView mNameTv;
    @BindView(R.id.tv_to_news)
    TextView mNewsTv;
    @BindView(R.id.iv_user_img)
    ImageView mHeadIv;
    @BindView(R.id.bt_user_out)
    Button mOutBt;
    private PersonalContract.Presenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_personal;
    }

    @Override
    protected void initViewsAndEvents() {
        mNewsTv.setOnClickListener(this);
        mOutBt.setOnClickListener(this);
        mHeadIv.setOnClickListener(this);
        mPresenter = new PersonalPresenter();
        mPresenter.start();
    }

    @Override
    public void setUserName(String userName) {
        mNameTv.setText(userName);
    }

    @Override
    public void setHeadIv(Bitmap bitmap) {
        mHeadIv.setImageBitmap(bitmap);
    }

    @Override
    public void logout() {

    }

    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroy() {
        mPresenter = null;
        super.onDestroy();
    }

    @Override
    public void setPresenter(PersonalContract.Presenter presenter) {

    }
}
