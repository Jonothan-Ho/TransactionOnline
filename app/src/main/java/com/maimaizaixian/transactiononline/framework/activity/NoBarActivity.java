package com.maimaizaixian.transactiononline.framework.activity;

import com.lidroid.xutils.ViewUtils;
import com.maimaizaixian.transactiononline.framework.mvc.control.impl.BaseControllerActivity;

/**
 * without title bar of super class activity extends this activity
 * Created by Administrator on 2015/10/9.
 */
public abstract class NoBarActivity extends BaseControllerActivity {

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        initSubView();
    }

    protected abstract void initSubView();
}
