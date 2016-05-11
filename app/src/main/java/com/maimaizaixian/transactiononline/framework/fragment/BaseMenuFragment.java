package com.maimaizaixian.transactiononline.framework.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lidroid.xutils.ViewUtils;
import com.maimaizaixian.transactiononline.framework.mvc.control.impl.BaseControllerFragment;
import com.maimaizaixian.transactiononline.utils.LogUtil;

/**
 * Classify Menu Fragment's super class in MainActivity,extends the class for FragmentTabHost
 * Created by Administrator on 2015/10/9.
 */
public abstract class BaseMenuFragment extends BaseControllerFragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        if (rootView == null) {
            return super.onCreateView(inflater, container, savedInstanceState);
        } else if (rootView != null && rootView.getParent() != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            parent.removeView(rootView);
            return rootView;
        } else if (rootView != null && rootView.getParent() == null) {
            return rootView;
        } else {
            throw new RuntimeException("Don't load" + this.getClass().getSimpleName() + "Fragment");
        }
    }

    @Override
    protected void initView() {
        ViewUtils.inject(this, rootView);
        initSubView();
    }

    public abstract void initSubView();

}
