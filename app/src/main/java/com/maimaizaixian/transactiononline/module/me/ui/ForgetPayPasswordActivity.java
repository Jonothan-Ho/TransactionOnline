package com.maimaizaixian.transactiononline.module.me.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.account.mvc.controller.IAccountController;
import com.maimaizaixian.transactiononline.module.account.mvc.service.IAccountService;
import com.maimaizaixian.transactiononline.module.account.mvc.service.impl.AccountServiceImpl;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IPasswordController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IPasswordService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.PasswordServiceImpl;

public class ForgetPayPasswordActivity extends ActionBarOneActivity implements IAccountController,IPasswordController {

    @ViewInject(R.id.et_phone)
    private EditText mEditPhone;
    @ViewInject(R.id.et_passwd)
    private EditText mEditPasswd;
    @ViewInject(R.id.et_code)
    private EditText mEditCode;

    @ViewInject(R.id.checkbox_passwd_show)
    private CheckBox mCheckBox;
    @ViewInject(R.id.btn_code)
    private Button mBtnCode;
    @ViewInject(R.id.btn_confirm)
    private Button mBtnConfirm;

    private IAccountService mAccountService;
    private IPasswordService mPasswordService;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("忘记支付密码");
        setTitleBarHint("更多");

        mAccountService = new AccountServiceImpl(this);
        mPasswordService = new PasswordServiceImpl(this);
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_forget_pay_password;
    }

    @Override
    protected void initListener() {
        mBtnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAccountService.getValidCode(mEditPhone.getText().toString().trim());
            }
        });

        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPasswordService.updatePayPasswd(mEditPhone.getText().toString().trim(), mEditPasswd.getText().toString().trim(), mEditCode.getText().toString().trim());
            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEditPasswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                } else {
                    mEditPasswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });


    }

    @Override
    public void completeValidCode(String code) {
        mEditCode.setText(code);
    }

    @Override
    public void complete(Object o) {

    }

    @Override
    public void closeActivity() {

    }

    @Override
    public void onComplete(Passwd_Action action) {

    }
}
