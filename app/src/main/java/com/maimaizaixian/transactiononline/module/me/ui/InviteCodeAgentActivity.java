package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.domain.PayBalance;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;

public class InviteCodeAgentActivity extends ActionBarOneActivity implements View.OnClickListener {

    @ViewInject(R.id.tv_code)
    private TextView mTextInviteCode;
    @ViewInject(R.id.tv_rewards_money)
    private TextView mTextRewards;

    @ViewInject(R.id.btn_withdraw)
    private Button mBtnWithDraw;

    @ViewInject(R.id.tv_weixin)
    private TextView mTextWeixin;
    @ViewInject(R.id.tv_friend)
    private TextView mTextFriend;
    @ViewInject(R.id.tv_qzone)
    private TextView mTextQzone;
    @ViewInject(R.id.tv_qq)
    private TextView mTextQQ;
    @ViewInject(R.id.tv_weibo)
    private TextView mTextWeibo;

    private int money;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("邀请码");
        setTitleBarHint("我");

        PayBalance payBalance = (PayBalance) getIntent().getSerializableExtra("object");
        mTextRewards.setText(payBalance.getAward_amount() + "元");
        User user = PreferencesUtil.getInstance(this).get(User.class);
        mTextInviteCode.setText(user.getIncode());
        money = payBalance.getAward_amount() + payBalance.getAgent_invite_award_amount() + payBalance.getAgent_recharge_award_amount();


    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_invite_code_agent;
    }

    @Override
    protected void initListener() {
        mTextFriend.setOnClickListener(this);
        mTextQQ.setOnClickListener(this);
        mTextQzone.setOnClickListener(this);
        mTextWeibo.setOnClickListener(this);
        mTextWeixin.setOnClickListener(this);

        mBtnWithDraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InviteCodeAgentActivity.this, ApplyWithdrawActivity.class);
                intent.putExtra("money", money);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_friend:
                break;
            case R.id.tv_qq:
                break;
            case R.id.tv_qzone:
                break;
            case R.id.tv_weibo:
                break;
            case R.id.tv_weixin:
                break;

        }
    }
}
