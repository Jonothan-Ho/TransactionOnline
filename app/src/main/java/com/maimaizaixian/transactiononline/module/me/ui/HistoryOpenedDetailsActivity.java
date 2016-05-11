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
import com.maimaizaixian.transactiononline.module.home.ui.MatchResultActivity;
import com.maimaizaixian.transactiononline.module.home.ui.PublishActivity;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.AutoScrollViewPager;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.List;

public class HistoryOpenedDetailsActivity extends ActionBarFourActivity implements IBusinessController {

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

    @ViewInject(R.id.btn_show_contact)
    private Button mBtnContact;

    @ViewInject(R.id.layout_details)
    private LinearLayout mLayoutDetails;
    @ViewInject(R.id.iv_portrait)
    private CircleImageView mCirclePortrait;
    @ViewInject(R.id.tv_contact_name)
    private TextView mTextContactName;

    @ViewInject(R.id.btn_close)
    private Button mBtnClose;
    @ViewInject(R.id.btn_check)
    private Button mBtnCheck;

    private MessageOneDialog messageOneDialog;

    private IBusinessService mBusinessService;
    private String mId;
    private int mPosition;
    private Business mBusiness;


    @Override
    protected void initSubView() {
        setTitleText("详情");
        setCancelText("历史发布");
        setActionText("编辑");

        mBusiness = (Business) getIntent().getSerializableExtra("object");
        if (mBusiness == null) {
            ViewUtil.showSnackbar(getRootView(), "商机对象丢失，请重试");
            finish();
            return;
        }

        mPosition = getIntent().getIntExtra("position", -1);
        mId = mBusiness.getId();
        mBusinessService = new BusinessServiceImpl(this);

        messageOneDialog = new MessageOneDialog(this);
        messageOneDialog.setContent("确定要关闭消息吗？");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = metrics.widthPixels;
        mViewPager.setLayoutParams(params);

        mViewPager.setUrls(mBusiness.getImgpiclist_link());
        BitmapUtil.getInstance(this).displayImage(mCirclePortrait, mBusiness.getHead_link());
        mTextAddress.setText(mBusiness.getAddress());
        mTextCategory.setText(mBusiness.getCategory_title());
        mTextContact.setText(mBusiness.getReal_name());
        mTextPhone.setText(mBusiness.getContact());
        mTextContactName.setText(mBusiness.getReal_name());
        mTextContent.setText(mBusiness.getNeed_desc());
        mTextDate.setText(mBusiness.getDate());
        mTextImgNum.setText(mBusiness.getImageList().length + "张");
        mTextName.setText(mBusiness.getTitle());
        mTextNumber.setText(mBusiness.getNum());
        mTextProperty.setText("1".equals(mBusiness.getPriority_selection()) ? "规模优先" : "附近优先");
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_history_opened_details;
    }

    @Override
    protected void initListener() {
        mBtnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageOneDialog.startDialog();
            }
        });

        mBtnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HistoryOpenedDetailsActivity.this, MatchResultActivity.class);
                intent.putExtra("object", mBusiness);
                startActivity(intent);
            }
        });

        messageOneDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                messageOneDialog.closeDialog();
                mBusinessService.closeBusiness(mId);
            }
        });

    }

    @Override
    public void onActionClick(View view) {
        Intent intent = new Intent(this, PublishActivity.class);
        intent.putExtra("object", mBusiness);
        startActivity(intent);
    }

    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {

    }

    @Override
    public void onCompleteBusiness(Business business, Business_Action action) {
        Intent intent = new Intent();
        intent.putExtra("position", mPosition);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {

    }

    @Override
    public void onComplete(Business_Type type) {

    }
}
