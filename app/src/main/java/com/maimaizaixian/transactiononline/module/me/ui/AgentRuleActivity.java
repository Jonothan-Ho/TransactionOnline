package com.maimaizaixian.transactiononline.module.me.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;

public class AgentRuleActivity extends ActionBarOneActivity {

    @Override
    protected void initSubView() {
        setTitleBarHeadline("代理人规则");
        setTitleBarHint("代理人设置");
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_agent_rule;
    }

    @Override
    protected void initListener() {

    }
}
