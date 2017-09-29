package com.gbq.myaccount.moduce.register;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.gbq.myaccount.R;
import com.gbq.myaccount.base.ui.page.BaseFragment;
import com.gbq.myaccount.moduce.home.FragmentEnum;

import butterknife.BindView;

/**
 * 注册页面
 * Created by gbq on 2017-7-28.
 */

public class RegisterFragment extends BaseFragment implements View.OnClickListener, RegisterContract.View {
    @BindView(R.id.et_tel)
    EditText mPhoneEt;
    @BindView(R.id.et_code)
    EditText mCodeEt;
    @BindView(R.id.et_password)
    EditText mPasswordEt;
    @BindView(R.id.cb_agree)
    CheckBox mAgreeCb;
    @BindView(R.id.bt_fetch_code)
    Button mGetCodeBt;
    @BindView(R.id.bt_register)
    Button mRegistBt;
    @BindView(R.id.bt_quick_register)
    Button mQuickRegistBt;

    private RegisterContract.Presenter mPresenter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_register;
    }

    @Override
    protected void initViewsAndEvents() {
        mGetCodeBt.setOnClickListener(this);
        mRegistBt.setOnClickListener(this);
        mQuickRegistBt.setOnClickListener(this);
        mPresenter = new RegisterPresenter(this);
    }

    @Override
    public void toRegisterSuccessActivity(String userName, String password) {
        Bundle bundle = new Bundle();
        bundle.putString("userName", userName);
        bundle.putString("password", password);
        mFragmentCtrl.setCurrentFragment(FragmentEnum.SUCCESS.getTag(), bundle);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_fetch_code:
                mPresenter.getCode(mPhoneEt.getText().toString());
                break;
            case R.id.bt_register:
                mPresenter.doRegister(mPhoneEt.getText().toString(), mCodeEt.getText().toString(),
                        mPasswordEt.getText().toString(), mAgreeCb.isChecked());
                break;
            case R.id.bt_quick_register:
                mPresenter.doQuickRegister(mPhoneEt.getText().toString(), mCodeEt.getText().toString(),
                        mAgreeCb.isChecked());
                break;
            default:
                break;
        }
    }

    @Override
    public void setPresenter(RegisterContract.Presenter presenter) {

    }
}
