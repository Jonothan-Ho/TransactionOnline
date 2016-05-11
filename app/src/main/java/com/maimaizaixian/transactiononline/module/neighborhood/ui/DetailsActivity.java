package com.maimaizaixian.transactiononline.module.neighborhood.ui;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarThreeActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.neighborhood.popupwindow.MorePopupwindow;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.AutoScrollViewPager;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.List;

public class DetailsActivity extends ActionBarThreeActivity implements OnItemClickListener, BasePopupWindow.OnDismissListener, IBusinessController {

    @ViewInject(R.id.tv_name)
    private TextView mTextProductName;
    @ViewInject(R.id.iv_portrait)
    private CircleImageView mImgPortrait;
    @ViewInject(R.id.tv_name_date)
    private TextView mTextNameDate;
    @ViewInject(R.id.tv_collect)
    private TextView mTextCollect;
    @ViewInject(R.id.viewpager_auto)
    private AutoScrollViewPager mScrollViewPager;
    @ViewInject(R.id.tv_img_num)
    private TextView mTextNum;
    @ViewInject(R.id.tv_content)
    private TextView mTextContent;

    private MorePopupwindow mMorePopupwindow;
    private boolean isCollect = false;

    private Business mBusiness;
    private IBusinessService mBusinessService;

    @Override
    protected void initSubView() {
        setTitleText("详情");
        setCancelText("附近");
        setHintArrowVisible(true);
        setButtonActionStyle(R.drawable.selector_btn_more_style_one);

        mBusiness = (Business) getIntent().getSerializableExtra("object");
        if (mBusiness == null) {
            ViewUtil.showSnackbar(getRootView(), "商机对象丢失");
            finish();
            return;
        }


        mBusinessService = new BusinessServiceImpl(this);
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ViewGroup.LayoutParams params = mScrollViewPager.getLayoutParams();
        params.height = metrics.widthPixels;
        mScrollViewPager.setLayoutParams(params);


        mScrollViewPager.setUrls(mBusiness.getImageList());

        BitmapUtil.getInstance(this).displayImage(mImgPortrait, mBusiness.getHead_link());

        mMorePopupwindow = new MorePopupwindow(this);
        mMorePopupwindow.setItemClickListener(this);
        mMorePopupwindow.setOnDismissListener(this);

        mTextProductName.setText(mBusiness.getTitle());
        mTextNameDate.setText(mBusiness.getDate());
        mTextNum.setText(mBusiness.getImageList().length + "张");
        mTextContent.setText(mBusiness.getNeed_desc());

        if (mBusiness.getHas_collect() == 1) {
            collect();
        }

    }


    /**
     * @param
     */
    public void collect() {
        Drawable drawable = null;
        isCollect = !isCollect;
        if (isCollect) {
            drawable = getResources().getDrawable(R.mipmap.icon_collect_selected);
        } else {
            drawable = getResources().getDrawable(R.mipmap.icon_collect_normal);
        }

        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        mTextCollect.setCompoundDrawables(drawable, null, null, null);

    }


    @Override
    public void onActionClick(View view) {
        mMorePopupwindow.showAsDropDown(mBtnAction);
        mBtnAction.setSelected(true);
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_neighborhood_details;
    }

    @Override
    protected void initListener() {
        mTextCollect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isCollect) {
                    mBusinessService.unCollect(mBusiness.getId());
                } else {
                    mBusinessService.collect(mBusiness.getId());
                }
            }
        });
    }

    @Override
    public void click(View v, int position) {
        switch (position) {
            case 0:
                mMorePopupwindow.closePopupWindow();
                if (isCollect) {
                    mBusinessService.unCollect(mBusiness.getId());
                } else {
                    mBusinessService.collect(mBusiness.getId());
                }
                break;
            case 1:
                Intent intent = new Intent(this, ComplaintActivity.class);
                intent.putExtra("id", mBusiness.getId());
                startActivity(intent);
                break;
        }
    }

    @Override
    public void onDismiss(PopupWindow popupWindow) {
        mBtnAction.setSelected(false);
    }

    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {

    }

    @Override
    public void onCompleteBusiness(Business business, Business_Action action) {

    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {

    }

    @Override
    public void onComplete(Business_Type type) {
        collect();
        if (isCollect) {
            mBusiness.setHas_collect(1);
        } else {
            mBusiness.setHas_collect(0);
        }
    }
}
