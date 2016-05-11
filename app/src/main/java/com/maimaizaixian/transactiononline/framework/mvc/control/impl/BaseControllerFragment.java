package com.maimaizaixian.transactiononline.framework.mvc.control.impl;

import android.view.View;

import com.maimaizaixian.transactiononline.framework.fragment.BaseFragment;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.utils.CommonUtil;

/**
 * Created by Administrator on 2015/11/25.
 */
public abstract class BaseControllerFragment extends BaseFragment implements IBaseController {

    private OnDialogLauncherAble mProgressDialog;

    @Override
    public View getRootView() {
        return getActivity().getWindow().getDecorView();
    }

    @Override
    public void openDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = CommonUtil.getProgressDialog(getActivity());
        }
        mProgressDialog.startDialog();
    }

    @Override
    public void closeDialog() {
        mProgressDialog.closeDialog();
    }
}
