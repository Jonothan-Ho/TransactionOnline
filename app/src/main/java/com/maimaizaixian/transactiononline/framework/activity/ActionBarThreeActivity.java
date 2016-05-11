package com.maimaizaixian.transactiononline.framework.activity;

import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.mvc.control.impl.BaseControllerActivity;

/**
 * Activity Layout file with view_action_bar_two can extends the class
 * Created by Administrator on 2015/10/28.
 */
public abstract class ActionBarThreeActivity extends BaseControllerActivity {

    protected TextView mTextCancel;
    protected TextView mTextTitle;
    protected ImageButton mBtnAction;


    @Override
    protected void initView() {
        ViewUtils.inject(this);
        mTextTitle = (TextView) findViewById(R.id.tv_title);
        mTextCancel = (TextView) findViewById(R.id.tv_cancel);
        mBtnAction = (ImageButton) findViewById(R.id.btn_action);

        mTextCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFinish();
            }
        });

        mBtnAction.setOnClickListener(new View.OnClickListener() {
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
     * @param resource
     */
    public void setButtonActionStyle(@DrawableRes int resource) {
        mBtnAction.setImageResource(resource);
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
     * set cancel content
     *
     * @param text
     */
    protected void setCancelText(String text) {
        mTextCancel.setText(text);
    }

}
