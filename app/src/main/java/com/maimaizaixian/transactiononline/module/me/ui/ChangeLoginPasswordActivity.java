package com.maimaizaixian.transactiononline.module.me.ui;

import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IPasswordController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IPasswordService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.PasswordServiceImpl;

public class ChangeLoginPasswordActivity extends ActionBarOneActivity implements IPasswordController{

    @ViewInject(R.id.et_old_passwd)
    private EditText mEditOldPasswd;
    @ViewInject(R.id.et_new_passwd)
    private EditText mEditNewPasswd;
    @ViewInject(R.id.checkbox_passwd_show)
    private CheckBox mCheckBox;
    @ViewInject(R.id.btn_confirm)
    private Button mBtnConfirm;

    private IPasswordService mPasswordService;

    @Override
    protected void initSubView() {
        setTitleBarHeadline("修改登录密码");
        setTitleBarHint("更多");

        mPasswordService = new PasswordServiceImpl(this);
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_change_login_password;
    }

    @Override
    protected void initListener() {
        mBtnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPasswordService.updateLoginPasswd(mEditOldPasswd.getText().toString().trim(), mEditNewPasswd.getText().toString().trim());
            }
        });

        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mEditNewPasswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_NORMAL);
                } else {
                    mEditNewPasswd.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
            }
        });
    }

    @Override
    public void onComplete(Passwd_Action action) {
    }
}
