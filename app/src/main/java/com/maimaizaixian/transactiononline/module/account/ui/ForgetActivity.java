package com.maimaizaixian.transactiononline.module.account.ui;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.account.domain.User;


/**
 * this is forget password activity
 */
public class ForgetActivity extends RegisterForgetActivity {

    @Override
    protected void initSubView() {
        super.initSubView();
        setTitleBarHeadline("忘记密码");
        setTitleBarHint("登录");
        mBtnAction.setText("确定");
    }

    @Override
    public void handle(String username, String passwd, String validCode) {
        service.findPasswd(username, passwd, validCode);
    }

    @Override
    public void complete(User obj) {

    }
}

