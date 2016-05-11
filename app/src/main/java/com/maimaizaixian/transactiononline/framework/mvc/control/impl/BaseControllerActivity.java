package com.maimaizaixian.transactiononline.framework.mvc.control.impl;

import android.view.View;

import com.maimaizaixian.transactiononline.framework.activity.BaseActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.utils.CommonUtil;

/**
 * Created by Administrator on 2015/11/20.
 */
public abstract class BaseControllerActivity extends BaseActivity implements IBaseController {

    private OnDialogLauncherAble mProgressDialog;

    @Override
    public View getRootView() {
        return getWindow().getDecorView();
    }

    @Override
    public void openDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = CommonUtil.getProgressDialog(this);
        }
        mProgressDialog.startDialog();
    }

    @Override
    public void closeDialog() {
        if (mProgressDialog != null) {
            mProgressDialog.closeDialog();
        }
    }
}

