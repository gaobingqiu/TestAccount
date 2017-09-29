package com.gbq.myaccount.moduce.success;

import android.view.View;
import android.widget.TextView;

import com.gbq.myaccount.R;
import com.gbq.myaccount.base.ui.page.BaseFragment;

import butterknife.BindView;

/**
 * 注册成功页面
 * Created by gbq on 2017-7-28.
 */

public class SuccessFragment extends BaseFragment implements View.OnClickListener, SuccessContract.View {
    @BindView(R.id.show_userName)
    TextView mPhoneTv;
    @BindView(R.id.show_password)
    TextView mPasswordTv;
    @BindView(R.id.tv_to_personal_activity)
    TextView mToPersonalTv;


    @Override
    public int getContentViewId() {
        return R.layout.fragment_register_success;
    }

    @Override
    protected void initViewsAndEvents() {
        mToPersonalTv.setOnClickListener(this);
    }

    @Override
    public void setUser(String user, String password) {
        mPasswordTv.setText(password);
        mPhoneTv.setText(user);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void setPresenter(SuccessContract.Presenter presenter) {

    }
}
