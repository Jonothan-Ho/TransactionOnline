package com.maimaizaixian.transactiononline.module.me.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarTwoActivity;
import com.maimaizaixian.transactiononline.module.me.domain.GroupRecruit;
import com.maimaizaixian.transactiononline.module.me.domain.Resume;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IMoreController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IMoreService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.MoreServiceImpl;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

public class IdeaActivity extends ActionBarTwoActivity implements IMoreController{

    @ViewInject(R.id.et_content)
    private EditText mEditContent;

    private IMoreService moreService;


    @Override
    protected void initSubView() {
        setTitleText("意见反馈");
        setActionText("发送");
        moreService = new MoreServiceImpl(this);
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
        //ViewUtil.showSnackbar(getWindow().getDecorView(), "非常感谢您提出诚挚和宝贵的意见，我们相信：这对我们的成长非常重要；我们会认真处理您的建议并可能对您的建议进行回复");
        moreService.feedBack(mEditContent.getText().toString().trim());
    }

    @Override
    public void onComplete(More_Action action) {
        finish();
    }

    @Override
    public void onComplete(List<GroupRecruit> groupRecruits, int page) {

    }

    @Override
    public void onComplete(Resume resume, More_Action action) {

    }
}
