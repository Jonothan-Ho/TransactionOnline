package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarTwoActivity;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

public class OurAdvantageActivity extends ActionBarTwoActivity {

    @ViewInject(R.id.et_content)
    private EditText mEditContent;

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_our_advantage;
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void initSubView() {
        setTitleText("我们的优势");
        setCancelText("取消");
    }

    @Override
    public void onActionClick(View view) {
        String content = mEditContent.getText().toString().trim();
        if (TextUtils.isEmpty(content)) {
            ViewUtil.showSnackbar(getRootView(), "内容不能为空");
            return;
        }

        Intent intent = new Intent();
        intent.putExtra("object", content);
        setResult(RESULT_OK, intent);
        finish();
    }
}
