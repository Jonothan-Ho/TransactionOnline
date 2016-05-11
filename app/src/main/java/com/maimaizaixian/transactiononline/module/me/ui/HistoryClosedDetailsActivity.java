package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarFourActivity;
import com.maimaizaixian.transactiononline.framework.dialog.MessageOneDialog;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.home.ui.PublishActivity;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.AutoScrollViewPager;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.List;

public class HistoryClosedDetailsActivity extends ActionBarFourActivity implements IBusinessController {

    @ViewInject(R.id.tv_name)
    private TextView mTextName;
    @ViewInject(R.id.tv_date)
    private TextView mTextDate;
    @ViewInject(R.id.text_category)
    private TextView mTextCategory;
    @ViewInject(R.id.text_contact)
    private TextView mTextContact;
    @ViewInject(R.id.text_number)
    private TextView mTextNumber;
    @ViewInject(R.id.text_phone)
    private TextView mTextPhone;
    @ViewInject(R.id.text_property)
    private TextView mTextProperty;
    @ViewInject(R.id.text_address)
    private TextView mTextAddress;

    @ViewInject(R.id.viewpager_auto)
    private AutoScrollViewPager mViewPager;
    @ViewInject(R.id.tv_img_num)
    private TextView mTextImgNum;
    @ViewInject(R.id.tv_content)
    private TextView mTextContent;

    /*@ViewInject(R.id.btn_show_contact)
    private Button mBtnContact;*/

    /*  @ViewInject(R.id.layout_details)
      private LinearLayout mLayoutDetails;*/
    @ViewInject(R.id.iv_portrait)
    private CircleImageView mCirclePortrait;
    @ViewInject(R.id.tv_contact_name)
    private TextView mTextContactName;

    @ViewInject(R.id.btn_delete)
    private Button mBtnDelete;

    private MessageOneDialog messageDialog;
    private IBusinessService mBusinessService;
    private String mId;
    private int mPosition;
    private Business business;


    @Override
    protected void initSubView() {
        setTitleText("详情");
        setCancelText("历史发布");
        setActionText("编辑");

        business = (Business) getIntent().getSerializableExtra("object");
        if (business == null) {
            ViewUtil.showSnackbar(getRootView(), "商机对象丢失，请重试");
            finish();
            return;
        }

        mPosition = getIntent().getIntExtra("position", -1);
        mId = business.getId();
        mBusinessService = new BusinessServiceImpl(this);
        messageDialog = new MessageOneDialog(this);
        messageDialog.setContent("确定要删除消息吗?");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = metrics.widthPixels;
        mViewPager.setLayoutParams(params);
        mViewPager.setUrls(business.getImgpiclist_link());

        BitmapUtil.getInstance(this).displayImage(mCirclePortrait, business.getHead_link());
        mTextAddress.setText(business.getAddress());
        mTextCategory.setText(business.getCategory_title());
        mTextContact.setText(business.getReal_name());
        mTextPhone.setText(business.getContact());
        mTextContactName.setText(business.getReal_name());
        mTextContent.setText(business.getNeed_desc());
        mTextDate.setText(business.getDate());
        mTextImgNum.setText(business.getImageList().length + "张");
        mTextName.setText(business.getTitle());
        mTextNumber.setText(business.getNum());
        mTextProperty.setText("1".equals(business.getPriority_selection()) ? "规模优先" : "附近优先");
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_history_closed_details;
    }

    @Override
    public void onActionClick(View view) {
        Intent intent = new Intent(this, PublishActivity.class);
        intent.putExtra("object", business);
        startActivity(intent);
    }

    @Override
    protected void initListener() {
        mBtnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageDialog.startDialog();
            }
        });

        messageDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageDialog.closeDialog();
                mBusinessService.deleteBusiness(mId);
            }
        });

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
        Intent intent = new Intent();
        intent.putExtra("position", mPosition);
        setResult(RESULT_OK, intent);
        finish();
    }
}
