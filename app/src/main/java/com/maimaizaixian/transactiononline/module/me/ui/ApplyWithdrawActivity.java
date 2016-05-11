package com.maimaizaixian.transactiononline.module.me.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.me.domain.ConsumptionRecords;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IConsumptionRecordsController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IConsumptionRecordsService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.ConsumptionRecordsServiceImpl;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

public class ApplyWithdrawActivity extends ActionBarOneActivity implements IConsumptionRecordsController {

    @ViewInject(R.id.tv_withdraw)
    private TextView mTextWithdraw;

    @ViewInject(R.id.et_withdraw)
    private EditText mEditWithdraw;

    @ViewInject(R.id.btn_withdraw)
    private Button mBtnWithdraw;

    private int money;

    private IConsumptionRecordsService mService;


    @Override
    protected int getCustomView() {
        return R.layout.activity_me_apply_withdraw;
    }

    @Override
    protected void initListener() {
        mBtnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = mEditWithdraw.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    ViewUtil.showSnackbar(getRootView(), "提现金额不能为空");
                    return;
                }
                int nowMoney = Integer.parseInt(content);

                if (nowMoney > money) {
                    ViewUtil.showSnackbar(getRootView(), "提现金额不能大于可提现金额");
                    return;
                }

                mService.withDrawal(content);

            }
        });
    }

    @Override
    protected void initSubView() {
        setTitleBarHeadline("申请提现");
        setTitleBarHint("代理人设置");

        money = getIntent().getIntExtra("money", -1);
        if (money == -1) {
            finish();
            return;
        }

        mTextWithdraw.setText(money + "元");

        mService = new ConsumptionRecordsServiceImpl(this);

    }

    @Override
    public void onComplete(List<ConsumptionRecords> recordses, int page) {

    }

    @Override
    public void onComplete(Consumption_Action action) {
        String content = mEditWithdraw.getText().toString().trim();
        int nowMoney = Integer.parseInt(content);
        money -= nowMoney;
        mTextWithdraw.setText(money + "元");
    }

    @Override
    public void onComplete(Consumption_Action action, String data) {

    }
}
