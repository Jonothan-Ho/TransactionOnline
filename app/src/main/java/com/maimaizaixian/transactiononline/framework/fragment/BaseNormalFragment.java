package com.maimaizaixian.transactiononline.framework.fragment;

import com.lidroid.xutils.ViewUtils;
import com.maimaizaixian.transactiononline.framework.mvc.control.impl.BaseControllerFragment;

/**
 *
 * normal fragment extends the fragment
 *
 * Created by Administrator on 2015/10/26.
 */
public abstract class BaseNormalFragment extends BaseControllerFragment {


    @Override
    protected void initView() {
        ViewUtils.inject(this, rootView);
        initSubView();
    }

    public abstract void initSubView();

}
