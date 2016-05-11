package com.maimaizaixian.transactiononline.module.home.ui;

import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;

public class MerchantsServiceActivity extends ActionBarOneActivity {

    @ViewInject(R.id.tv_content)
    private TextView mTextContent;


    @Override
    protected int getCustomView() {
        return R.layout.activity_home_merchants_advantage_service;
    }

    @Override
    protected void initListener() {
        String content = getIntent().getStringExtra("object");
        mTextContent.setText(content);
    }

    @Override
    protected void initSubView() {
        setTitleBarHeadline("我们的服务");
        setTitleBarHint("商户主页");
    }
}
