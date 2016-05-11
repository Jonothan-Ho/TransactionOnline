package com.maimaizaixian.transactiononline.module.me.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.me.domain.SystemNotice;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.AutoScrollViewPager;
import com.maimaizaixian.transactiononline.view.CircleImageView;

public class SystemNoticeDetailsActivity extends ActionBarOneActivity {

    @ViewInject(R.id.tv_name)
    private TextView mTextProductName;
    @ViewInject(R.id.tv_date)
    private TextView mTextDate;
    @ViewInject(R.id.viewpager_auto)
    private AutoScrollViewPager mScrollViewPager;
    @ViewInject(R.id.tv_img_num)
    private TextView mTextNum;
    @ViewInject(R.id.tv_content)
    private TextView mTextContent;

    private SystemNotice mSystemNotice;


    @Override
    protected void initSubView() {
        setTitleBarHeadline("详情");
        setTitleBarHint("系统公告");

        mSystemNotice = (SystemNotice) getIntent().getSerializableExtra("object");

        if (mSystemNotice == null) {
            ViewUtil.showSnackbar(getRootView(), "系统公告对象丢失，请重试");
            finish();
            return;
        }


        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ViewGroup.LayoutParams params = mScrollViewPager.getLayoutParams();
        params.height = metrics.widthPixels;
        mScrollViewPager.setLayoutParams(params);

        mScrollViewPager.setUrls(mSystemNotice.getImgpiclist_link());
        mTextContent.setText(mSystemNotice.getContent());
        mTextDate.setText(mSystemNotice.getCreate_time());
        mTextNum.setText(mSystemNotice.getImgpiclist_link().length + "张");
        mTextProductName.setText(mSystemNotice.getTitle());

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_system_notice_details;
    }

    @Override
    protected void initListener() {

    }
}
