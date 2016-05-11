package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.common.domain.PayBalance;

public class AgentSetActivity extends ActionBarOneActivity implements View.OnClickListener {

    @ViewInject(R.id.tv_invite_rewards)
    private TextView mTextInviteRewards;
    @ViewInject(R.id.tv_pay_rewards)
    private TextView mTextPayRewards;
    @ViewInject(R.id.tv_withdraw)
    private TextView mTextWithdraw;
    @ViewInject(R.id.tv_sum)
    private TextView mTextSum;

    @ViewInject(R.id.layout_bind_card)
    private RelativeLayout mLayoutBindCard;
    @ViewInject(R.id.layout_withdraw)
    private RelativeLayout mLayoutWithdraw;
    @ViewInject(R.id.layout_records)
    private RelativeLayout mLayoutRecords;
    @ViewInject(R.id.layout_agent_rule)
    private RelativeLayout mLayoutAgentRule;
    @ViewInject(R.id.layout_withdraw_rule)
    private RelativeLayout mLayoutWithdrawRule;

    private PayBalance payBalance;


    @Override
    protected int getCustomView() {
        return R.layout.activity_me_agent_set;
    }

    @Override
    protected void initListener() {
        mLayoutBindCard.setOnClickListener(this);
        mLayoutWithdrawRule.setOnClickListener(this);
        mLayoutAgentRule.setOnClickListener(this);
        mLayoutRecords.setOnClickListener(this);
        mLayoutWithdraw.setOnClickListener(this);
    }

    @Override
    protected void initSubView() {
        setTitleBarHeadline("代理人设置");
        setTitleBarHint("我");

        payBalance = (PayBalance) getIntent().getSerializableExtra("object");
        mTextInviteRewards.setText(payBalance.getAgent_invite_award_amount() + "");
        mTextPayRewards.setText(payBalance.getAgent_recharge_award_amount() + "");
        mTextWithdraw.setText((payBalance.getAgent_invite_award_amount() + payBalance.getAgent_recharge_award_amount()) + "");
        mTextSum.setText((payBalance.getAgent_invite_award_sum() + payBalance.getAgent_recharge_award_sum() + payBalance.getAward_sum()) + "");
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.layout_bind_card:
                intent.setClass(this, BindBankCardActivity.class);
                break;
            case R.id.layout_withdraw:
                intent.setClass(this, ApplyWithdrawActivity.class);
                intent.putExtra("money", payBalance.getAgent_invite_award_amount() + payBalance.getAgent_recharge_award_amount());
                break;
            case R.id.layout_records:
                intent.setClass(this, WithdrawRecordsActivity.class);
                break;
            case R.id.layout_agent_rule:
                intent.setClass(this, AgentRuleActivity.class);
                break;
            case R.id.layout_withdraw_rule:
                break;
        }
        startActivity(intent);
    }
}
