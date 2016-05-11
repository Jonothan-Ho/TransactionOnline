package com.maimaizaixian.transactiononline.module.me.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.common.domain.PayBalance;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.ArrayList;

public class BalanceUnAgentActivity extends ActionBarOneActivity implements CompoundButton.OnCheckedChangeListener {

    @ViewInject(R.id.tv_balance_money)
    private TextView mTextBalance;
    @ViewInject(R.id.tv_rewards_money)
    private TextView mTextRewards;
    @ViewInject(R.id.tv_sum_rewards)
    private TextView mTextSumRewards;
    @ViewInject(R.id.tv_level)
    private TextView mTextLevel;

    @ViewInject(R.id.radio_button3)
    private RadioButton mBtnPayMoney50;
    @ViewInject(R.id.radio_button4)
    private RadioButton mBtnPayMoney100;
    @ViewInject(R.id.radio_button5)
    private RadioButton mBtnPayMoney200;
    @ViewInject(R.id.radio_button6)
    private RadioButton mBtnPayMoney500;

    @ViewInject(R.id.et_pay_money)
    private EditText mEditPay;

    @ViewInject(R.id.layout_vip)
    private RelativeLayout mLayoutVip;
    @ViewInject(R.id.layout_pay)
    private RelativeLayout mLayoutPay;

    @ViewInject(R.id.radio_group)
    private RadioGroup mRadioPay;

    @ViewInject(R.id.btn_pay)
    private Button mBtnPay;


    private ArrayList<RadioButton> mArrayPayMoney;
    private RadioButton mRadioCheckedPayMoney;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("余额");
        setTitleBarHint("我");

        PayBalance payBalance = (PayBalance) getIntent().getSerializableExtra("object");
        if (payBalance == null) {
            ViewUtil.showSnackbar(getRootView(), "余额对象为空");
            finish();
            return;
        }

        mTextBalance.setText(payBalance.getRecharge_amount() + "元");
        mTextRewards.setText(payBalance.getAward_amount() + "元");
        mTextSumRewards.setText(payBalance.getAward_sum() + "元");

        String vip = getIntent().getStringExtra("vip");
        if (!TextUtils.isEmpty(vip)) {
            mTextLevel.setText(vip);
        }


        mArrayPayMoney = new ArrayList<>();
        mArrayPayMoney.add(mBtnPayMoney50);
        mArrayPayMoney.add(mBtnPayMoney100);
        mArrayPayMoney.add(mBtnPayMoney200);
        mArrayPayMoney.add(mBtnPayMoney500);
        mRadioCheckedPayMoney = mBtnPayMoney50;


    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_balance_un_agent;
    }

    @Override
    protected void initListener() {
        mBtnPayMoney50.setOnCheckedChangeListener(this);
        mBtnPayMoney100.setOnCheckedChangeListener(this);
        mBtnPayMoney200.setOnCheckedChangeListener(this);
        mBtnPayMoney500.setOnCheckedChangeListener(this);

        mLayoutVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mLayoutPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mBtnPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        for (int i = 0; i < mArrayPayMoney.size(); i++) {
            if (mArrayPayMoney.get(i).getId() == buttonView.getId()) {
                mRadioCheckedPayMoney.setChecked(false);
                mRadioCheckedPayMoney = (RadioButton) buttonView;
                continue;
            }
        }
    }
}
