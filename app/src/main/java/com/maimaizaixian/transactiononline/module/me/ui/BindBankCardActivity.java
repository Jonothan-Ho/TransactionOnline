package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.account.mvc.controller.IAccountController;
import com.maimaizaixian.transactiononline.module.account.mvc.service.IAccountService;
import com.maimaizaixian.transactiononline.module.account.mvc.service.impl.AccountServiceImpl;
import com.maimaizaixian.transactiononline.module.me.domain.ConsumptionRecords;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IConsumptionRecordsController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IConsumptionRecordsService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.ConsumptionRecordsServiceImpl;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;

import java.util.List;

public class BindBankCardActivity extends ActionBarOneActivity implements IAccountController, IConsumptionRecordsController {

    @ViewInject(R.id.et_name)
    private EditText mEditName;
    @ViewInject(R.id.et_bank)
    private EditText mEditNumber;
    @ViewInject(R.id.et_code)
    private EditText mEditCode;

    @ViewInject(R.id.tv_bank)
    private TextView mTextBank;

    @ViewInject(R.id.layout_bank)
    private RelativeLayout mLayoutBank;


    @ViewInject(R.id.btn_code)
    private Button mBtnCode;
    @ViewInject(R.id.btn_bind)
    private Button mBtnBind;

    private IConsumptionRecordsService mService;
    private IAccountService mAccountService;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("银行卡绑定");
        setTitleBarHint("代理人设置");

        mService = new ConsumptionRecordsServiceImpl(this);
        mAccountService = new AccountServiceImpl(this);
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_bind_bank_card;
    }

    @Override
    protected void initListener() {
        mLayoutBank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BindBankCardActivity.this, BankListActivity.class);
                startActivity(intent);
            }
        });

        mBtnCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = PreferencesUtil.getInstance(BindBankCardActivity.this).get(User.class);
                mAccountService.getValidCode(user.getMobile());
            }
        });

        mBtnBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mEditName.getText().toString().trim();
                String account = mEditNumber.getText().toString().trim();
                String code = mEditCode.getText().toString().trim();
                String bank = mTextBank.getText().toString().trim();

                mService.bindBandCard(name, account, bank, code);

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
    public void onComplete(List<ConsumptionRecords> recordses, int page) {

    }

    @Override
    public void onComplete(Consumption_Action action) {
        finish();
    }

    @Override
    public void onComplete(Consumption_Action action, String data) {

    }
}
