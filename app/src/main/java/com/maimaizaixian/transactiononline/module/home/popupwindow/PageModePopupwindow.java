package com.maimaizaixian.transactiononline.module.home.popupwindow;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;

/**
 * Created by Administrator on 2015/10/30.
 */
public class PageModePopupwindow extends BasePopupWindow implements View.OnClickListener {

    private TextView mTextSingle;
    private TextView mTextMap;
    private TextView mTextList;

    private OnItemClickListener mItemClickListener;


    public PageModePopupwindow(Activity activity) {
        super(activity);
    }


    @Override
    public int getCustomView() {
        return R.layout.popupwindow_home_page_mode;
    }

    @Override
    public int getWindowWidth() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    @Override
    public int getWindowHeight() {
        return ViewGroup.LayoutParams.WRAP_CONTENT;
    }

    @Override
    protected void initView(View rootView) {
        mTextList = (TextView) rootView.findViewById(R.id.tv_list);
        mTextSingle = (TextView) rootView.findViewById(R.id.tv_single);
        mTextMap = (TextView) rootView.findViewById(R.id.tv_map);

        mTextList.setOnClickListener(this);
        mTextSingle.setOnClickListener(this);
        mTextMap.setOnClickListener(this);
    }

    @Override
    public void showAsDropDown(View view) {
        mPopupWindow.showAsDropDown(view);
    }

    @Override
    public void onClick(View v) {
        int position = 0;
        switch (v.getId()) {
            case R.id.tv_list:
                position = 0;
                break;
            case R.id.tv_map:
                position = 1;
                break;
            case R.id.tv_single:
                position = 2;
                break;
        }

        if (mItemClickListener != null) {
            mItemClickListener.click(v, position);
        }

    }

    public OnItemClickListener getmItemClickListener() {
        return mItemClickListener;
    }

    public void setItemClickListener(OnItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }


}
