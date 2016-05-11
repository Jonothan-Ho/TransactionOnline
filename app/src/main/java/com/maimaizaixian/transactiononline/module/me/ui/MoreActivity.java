package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.easemob.chat.EMChatManager;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.util.ActivityManager;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;

public class MoreActivity extends ActionBarOneActivity implements View.OnClickListener {

    @ViewInject(R.id.layout_about_us)
    private RelativeLayout mLayoutAboutUs;
    @ViewInject(R.id.layout_idea)
    private RelativeLayout mLayoutIdea;
    @ViewInject(R.id.layout_recruit)
    private RelativeLayout mLayoutRecruit;
    @ViewInject(R.id.layout_update)
    private RelativeLayout mLayoutUpdate;
    @ViewInject(R.id.layout_cache)
    private RelativeLayout mLayoutCache;
    @ViewInject(R.id.layout_login_password)
    private RelativeLayout mLayoutLoginPassword;
    @ViewInject(R.id.layout_pay_password)
    private RelativeLayout mLayoutPayPassword;
    @ViewInject(R.id.btn_exit)
    private Button mBtnExit;


    @Override
    protected void initSubView() {
        setTitleBarHint("我");
        setTitleBarHeadline("更多");
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_more;
    }

    @Override
    protected void initListener() {
        mLayoutAboutUs.setOnClickListener(this);
        mLayoutIdea.setOnClickListener(this);
        mLayoutRecruit.setOnClickListener(this);
        mLayoutUpdate.setOnClickListener(this);
        mLayoutCache.setOnClickListener(this);
        mLayoutLoginPassword.setOnClickListener(this);
        mLayoutPayPassword.setOnClickListener(this);
        mBtnExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        Intent intent = new Intent();

        switch (v.getId()) {
            case R.id.layout_about_us:
                break;
            case R.id.layout_idea:
                intent.setClass(this, IdeaActivity.class);
                break;
            case R.id.layout_recruit:
                intent.setClass(this, GroupRecruitActivity.class);
                break;
            case R.id.layout_update:
                break;
            case R.id.layout_cache:
                break;
            case R.id.layout_login_password:
                intent.setClass(this, ChangeLoginPasswordActivity.class);
                break;
            case R.id.layout_pay_password:
                intent.setClass(this, ChangePayPasswordActivity.class);
                break;
            case R.id.btn_exit:
                EMChatManager.getInstance().logout();
                PreferencesUtil.getInstance(this).put(PreferencesUtil.KEY_USERNAME, "");
                PreferencesUtil.getInstance(this).put(PreferencesUtil.KEY_PASSWORD, "");
                PreferencesUtil.getInstance(this).put(null, User.class);
                ActivityManager.getInstance().clearAll();
                System.exit(0);
                break;
        }

        startActivity(intent);
    }
}
