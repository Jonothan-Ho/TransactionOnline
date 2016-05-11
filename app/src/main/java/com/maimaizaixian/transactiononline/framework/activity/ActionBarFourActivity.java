package com.maimaizaixian.transactiononline.framework.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.mvc.control.impl.BaseControllerActivity;

/**
 * Activity Layout file with view_action_bar_two can extends the class
 * Created by Administrator on 2015/10/28.
 */
public abstract class ActionBarFourActivity extends BaseControllerActivity {

    protected TextView mTextCancel;
    protected TextView mTextTitle;
    protected TextView mTextMore;
    protected RelativeLayout mLayoutRoot;


    @Override
    protected void initView() {
        ViewUtils.inject(this);
        mTextTitle = (TextView) findViewById(R.id.tv_title);
        mTextCancel = (TextView) findViewById(R.id.tv_cancel);
        mTextMore = (TextView) findViewById(R.id.tv_more);
        mLayoutRoot = (RelativeLayout) findViewById(R.id.layout_root);

        mTextCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinish();
            }
        });

        mTextMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onActionClick(v);
            }
        });

        initSubView();
    }

    protected abstract void initSubView();

    /**
     * mBtnAction click method
     *
     * @param view
     */
    public void onActionClick(View view) {

    }


    /**
     * @param isVisible
     */
    public void setHintArrowVisible(boolean isVisible) {
        if (isVisible) {
            Drawable drawable = getResources().getDrawable(R.mipmap.btn_arrow_left);
            drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
            mTextCancel.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        } else {
            mTextCancel.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        }
    }

    protected void setBackground(@ColorRes int color) {
        mLayoutRoot.setBackgroundColor(getResources().getColor(color));
    }

    /**
     * mBtnCancel click method
     */
    public void onFinish() {
        finish();
    }


    /**
     * set title content
     *
     * @param title
     */
    protected void setTitleText(String title) {
        mTextTitle.setText(title);
    }

    /**
     * @param text
     */
    protected void setActionText(String text) {
        mTextMore.setText(text);
    }

    /**
     * set cancel content
     *
     * @param text
     */
    protected void setCancelText(String text) {
        mTextCancel.setText(text);
    }

}
