package com.maimaizaixian.transactiononline.module.hall.popupwindow;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2015/11/5.
 */
public class ConditionTypePopupWindow extends BasePopupWindow implements View.OnClickListener {

    private TextView mTextBuy;
    private TextView mTextSale;
    private TextView mTextLease;
    private TextView mTextHire;
    private LinearLayout mLayoutRoot;

    public ConditionTypePopupWindow(Activity activity) {
        super(activity);
    }

    @Override
    protected int getCustomView() {
        return R.layout.popupwindow_hall_condition_type;
    }

    @Override
    protected int getWindowWidth() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    protected int getWindowHeight() {
        return ViewGroup.LayoutParams.MATCH_PARENT;
    }

    @Override
    public int getPopupWindowAnim() {
        return R.style.popupWindowAnimTwo;
    }

    @Override
    protected void initView(View rootView) {
        mTextBuy = (TextView) rootView.findViewById(R.id.tv_buy);
        mTextSale = (TextView) rootView.findViewById(R.id.tv_sale);
        mTextLease = (TextView) rootView.findViewById(R.id.tv_lease);
        mTextHire = (TextView) rootView.findViewById(R.id.tv_hire);
        mLayoutRoot = (LinearLayout) rootView.findViewById(R.id.layout_root);

        mTextBuy.setOnClickListener(this);
        mTextSale.setOnClickListener(this);
        mTextLease.setOnClickListener(this);
        mTextHire.setOnClickListener(this);
        mLayoutRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPopupWindow.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        int position = 0;
        switch (v.getId()) {
            case R.id.tv_buy:
                position = 0;
                break;
            case R.id.tv_sale:
                position = 1;
                break;
            case R.id.tv_lease:
                position = 2;
                break;
            case R.id.tv_hire:
                position = 3;
                break;
        }

        if (mItemClickListener != null) {
            mItemClickListener.click(v, position);
        }

        closePopupWindow();

    }
}
