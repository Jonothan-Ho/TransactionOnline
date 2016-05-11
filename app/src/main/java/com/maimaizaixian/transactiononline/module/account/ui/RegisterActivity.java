package com.maimaizaixian.transactiononline.module.account.ui;

import android.content.Intent;
import android.view.View;

import com.maimaizaixian.transactiononline.module.MainActivity;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;

/**
 * this is register activity
 */
public class RegisterActivity extends RegisterForgetActivity implements View.OnClickListener {

    @Override
    protected void initSubView() {
        super.initSubView();
        setTitleBarHeadline("注册");
        setTitleBarHint("登录");
    }

    @Override
    public void handle(String username, String passwd, String validCode) {
        service.regist(username, passwd, validCode);
    }

    @Override
    public void complete(User obj) {
        PreferencesUtil.getInstance(this).put(obj, User.class);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
