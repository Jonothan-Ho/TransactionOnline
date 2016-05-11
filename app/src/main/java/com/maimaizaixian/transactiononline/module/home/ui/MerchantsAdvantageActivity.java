package com.maimaizaixian.transactiononline.module.home.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;

public class MerchantsAdvantageActivity extends ActionBarOneActivity {

    @ViewInject(R.id.tv_content)
    private TextView mTextContent;


    @Override
    protected int getCustomView() {
        return R.layout.activity_home_merchants_advantage_service;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initSubView() {
        setTitleBarHeadline("我们的优势");
        setTitleBarHint("商户主页");

        String content = getIntent().getStringExtra("object");
        mTextContent.setText(content);

    }
}
