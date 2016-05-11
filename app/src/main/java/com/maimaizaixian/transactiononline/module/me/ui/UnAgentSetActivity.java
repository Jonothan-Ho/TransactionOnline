package com.maimaizaixian.transactiononline.module.me.ui;


import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.me.dialog.CompleteDataDialog;

public class UnAgentSetActivity extends ActionBarOneActivity {

    @ViewInject(R.id.tv_rule)
    private TextView mTextAgentRule;
    @ViewInject(R.id.btn_agent)
    private Button mBtnAgent;

    private CompleteDataDialog mCompleteDataDialog;

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_un_agent_set;
    }

    @Override
    protected void initListener() {
        mBtnAgent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCompleteDataDialog.startDialog();
            }
        });

        mTextAgentRule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnAgentSetActivity.this, AgentRuleActivity.class));
            }
        });

        mCompleteDataDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(UnAgentSetActivity.this, AgentDataActivity.class));
                mCompleteDataDialog.closeDialog();
            }
        });

    }

    @Override
    protected void initSubView() {
        setTitleBarHeadline("代理人设置");
        setTitleBarHint("我");

        mCompleteDataDialog = new CompleteDataDialog(this);
    }
}
