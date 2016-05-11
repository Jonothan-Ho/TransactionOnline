package com.maimaizaixian.transactiononline.module.neighborhood.ui;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarTwoActivity;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

public class ComplaintActivity extends ActionBarTwoActivity implements IBusinessController {

    @ViewInject(R.id.et_content)
    private EditText mEditContent;
    private IBusinessService mBusinessService;
    private String id;

    @Override
    protected void initSubView() {
        setTitleText("投诉");
        setActionText("发送");
        mEditContent.setHint("请输入投诉内容");

        id = getIntent().getStringExtra("id");
        mBusinessService = new BusinessServiceImpl(this);
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_idea;
    }

    @Override
    protected void initListener() {
    }

    @Override
    public void onActionClick(View view) {
        String content = mEditContent.getText().toString();
        if (TextUtils.isEmpty(content)) {
            ViewUtil.showSnackbar(getRootView(),"投诉内容不能为空");
            return;
        }

        mBusinessService.report(id, content);

    }

    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {

    }

    @Override
    public void onCompleteBusiness(Business business,Business_Action action) {

    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {

    }

    @Override
    public void onComplete(Business_Type type) {
        finish();
    }
}
