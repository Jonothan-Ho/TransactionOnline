package com.maimaizaixian.transactiononline.module.account.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.MainActivity;
import com.maimaizaixian.transactiononline.module.account.mvc.controller.IAccountController;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.account.mvc.service.IAccountService;
import com.maimaizaixian.transactiononline.module.account.mvc.service.impl.AccountServiceImpl;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;

/**
 * this is login activity
 */
public class LoginActivity extends ActionBarOneActivity implements View.OnClickListener, IAccountController<User> {

    @ViewInject(R.id.iv_logo)
    private ImageView mImgLogo;

    @ViewInject(R.id.et_username)
    private EditText mEditUsername;
    @ViewInject(R.id.et_passwd)
    private EditText mEditPassword;

    @ViewInject(R.id.btn_login)
    private Button mBtnLogin;
    @ViewInject(R.id.btn_regist)
    private Button mBtnRegist;
    @ViewInject(R.id.text_forget)
    private TextView mTextForget;

    //business service framework
    private IAccountService mLoginService;


    @Override
    protected int getCustomView() {
        return R.layout.activity_account_login;
    }

    @Override
    protected void initListener() {
        mBtnLogin.setOnClickListener(this);
        mBtnRegist.setOnClickListener(this);
        mTextForget.setOnClickListener(this);
    }

    @Override
    protected void initSubView() {
        setTitleBarHeadline("登录");
        setTitleBarHint("取消", true);

        mLoginService = new AccountServiceImpl(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_regist:
                Intent registIntent = new Intent(this, RegisterActivity.class);
                startActivity(registIntent);
                break;
            case R.id.text_forget:
                Intent forgetIntent = new Intent(this, ForgetActivity.class);
                startActivity(forgetIntent);
                break;
        }
    }


    /**
     * login
     */
    private void login() {
        String username = mEditUsername.getText().toString().trim();
        String password = mEditPassword.getText().toString().trim();
        mLoginService.login(username, password);
    }

    @Override
    public void completeValidCode(String code) {

    }

    @Override
    public void complete(User user) {
        PreferencesUtil.getInstance(this).put(user, User.class);
        PreferencesUtil.getInstance(this).put(PreferencesUtil.KEY_USERNAME, mEditUsername.getText().toString().trim());
        PreferencesUtil.getInstance(this).put(PreferencesUtil.KEY_PASSWORD, mEditPassword.getText().toString().trim());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void closeActivity() {

    }
}
