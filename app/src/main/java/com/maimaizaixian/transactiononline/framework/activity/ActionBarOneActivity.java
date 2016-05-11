package com.maimaizaixian.transactiononline.framework.activity;

import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.mvc.control.impl.BaseControllerActivity;

/**
 * Activity Layout file with view_action_bar_one can extends the class
 * Created by Administrator on 2015/10/20.
 */
public abstract class ActionBarOneActivity extends BaseControllerActivity {

    protected TextView mTextHint;
    protected TextView mTextTitle;
    protected RelativeLayout mLayoutRoot;

    @Override
    protected void initView() {
        ViewUtils.inject(this);
        mTextTitle = (TextView) findViewById(R.id.tv_title);
        mTextHint = (TextView) findViewById(R.id.tv_hint);
        mLayoutRoot = (RelativeLayout) findViewById(R.id.layout_root);

        mTextHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        initSubView();
    }

    protected abstract void initSubView();


    /**
     * @param textHint
     */
    protected void setTitleBarHint(String textHint) {
        mTextHint.setText(textHint);
    }

    /**
     * @param textHint
     */
    protected void setTitleBarHint(String textHint, boolean isHide) {
        mTextHint.setText(textHint);
        if (isHide) {
            mTextHint.setCompoundDrawables(null, null, null, null);
        }
    }

    protected void setBackground(@ColorRes int color) {
        mLayoutRoot.setBackgroundColor(getResources().getColor(color));
    }

    /**
     * @param textTitle
     */
    protected void setTitleBarHeadline(String textTitle) {
        mTextTitle.setText(textTitle);
    }


}
