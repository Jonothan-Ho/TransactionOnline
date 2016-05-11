package com.maimaizaixian.transactiononline.module.neighborhood.popupwindow;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2015/11/9.
 */
public class MorePopupwindow extends BasePopupWindow implements View.OnClickListener {

    private TextView mTextCollect;
    private TextView mTextComplaint;


    public MorePopupwindow(Activity activity) {
        super(activity);
    }

    @Override
    protected int getCustomView() {
        return R.layout.popupwindow_neighborhood_details_more;
    }

    @Override
    protected int getWindowWidth() {
        return 250;
    }

    @Override
    protected int getWindowHeight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected void initView(View rootView) {
        mTextCollect = (TextView) rootView.findViewById(R.id.tv_collect);
        mTextComplaint = (TextView) rootView.findViewById(R.id.tv_complaint);

        mTextCollect.setOnClickListener(this);
        mTextComplaint.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (mItemClickListener == null) {
            return;
        }

        switch (v.getId()) {
            case R.id.tv_collect:
                mItemClickListener.click(v, 0);
                break;
            case R.id.tv_complaint:
                mItemClickListener.click(v, 1);
                break;
        }
    }
}
