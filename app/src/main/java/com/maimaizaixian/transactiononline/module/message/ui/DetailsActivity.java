package com.maimaizaixian.transactiononline.module.message.ui;


import android.content.Intent;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarThreeActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.framework.popupwindow.BasePopupWindow;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.domain.PayBalance;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IPayController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IPayService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.PayServiceImpl;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.message.dialog.PayDialog;
import com.maimaizaixian.transactiononline.module.neighborhood.popupwindow.MorePopupwindow;
import com.maimaizaixian.transactiononline.module.neighborhood.ui.ComplaintActivity;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.AutoScrollViewPager;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.List;

public class DetailsActivity extends ActionBarThreeActivity implements OnItemClickListener, IBusinessController, IPayController, PayDialog.OnPayListener, BasePopupWindow.OnDismissListener, View.OnClickListener  {

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
    @ViewInject(R.id.tv_call)
    private TextView mTextActionCall;
    @ViewInject(R.id.tv_address)
    private TextView mTextActionAddress;
    @ViewInject(R.id.tv_session)
    private TextView mTextActionSession;

    private MorePopupwindow mMorePopupwindow;
    private PayDialog mPayDialog;

    private Business business;
    private IPayService mPayService;
    private IBusinessService mBusinessService;


    @Override
    protected void initSubView() {
        setTitleText("详情");
        setCancelText("消息");

        business = (Business) getIntent().getSerializableExtra("object");
        if (business == null) {
            ViewUtil.showToast(this, "商机对象丢失，请重试");
            finish();
            return;
        }

        mPayService = new PayServiceImpl(this);
        mBusinessService = new BusinessServiceImpl(this);
        mPayService.getAccountBalance();

        User user = PreferencesUtil.getInstance(this).get(User.class);
        mPayDialog = new PayDialog(this, user.getHas_alipay_password() == 0 ? false : true,user.getInfo_price()+"");
        mPayDialog.setOnPayListener(this);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
        params.height = metrics.widthPixels;
        mViewPager.setLayoutParams(params);
        mViewPager.setUrls(business.getImgpiclist_link());

        mMorePopupwindow = new MorePopupwindow(this);
        mMorePopupwindow.setItemClickListener(this);

        //mBtnContact.setVisibility(View.GONE);
        mLayoutDetails.setVisibility(View.GONE);

        BitmapUtil.getInstance(this).displayImage(mCirclePortrait, business.getHead_link());

        mTextImgNum.setText(business.getImgpiclist_link().length + "张");
        mTextName.setText(business.getTitle());
        mTextDate.setText(business.getDate());
        mTextCategory.setText(business.getCategory_title());
        mTextContactName.setText(business.getReal_name());
        mTextNumber.setText(business.getNum());
        mTextProperty.setText(IBusinessService.TYPE_HALL.equals(business.getPriority_selection()) ? "规模" : "附近");
        mTextContact.setText(business.getContact());
        mTextAddress.setText(business.getAddress());
        mTextContent.setText(business.getContent());

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_message_details;
    }

    @Override
    public void onActionClick(View view) {
        mMorePopupwindow.showAsDropDown(mBtnAction);
    }

    @Override
    protected void initListener() {
        mBtnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (business.getHas_pay() == 0) {
                    mPayDialog.startDialog();
                } else {
                    mLayoutDetails.setVisibility(View.VISIBLE);
                    mBtnContact.setVisibility(View.GONE);
                }
            }
        });

        mTextActionAddress.setOnClickListener(this);
        mTextActionCall.setOnClickListener(this);
        mTextActionSession.setOnClickListener(this);

    }

    @Override
    public void click(View v, int position) {
        switch (position) {
            case 0:
                mMorePopupwindow.closePopupWindow();
                mBusinessService.collect(business.getId());
                break;
            case 1:
                Intent intent = new Intent(this, ComplaintActivity.class);
                intent.putExtra("id", business.getId());
                startActivity(intent);
                break;
        }

        mMorePopupwindow.closePopupWindow();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_session:
                break;
            case R.id.tv_address:
                break;
            case R.id.tv_call:
                break;
        }
    }

    @Override
    public void onDismiss(PopupWindow popupWindow) {
    }

    @Override
    public void onPay(boolean isPayed, String passwd, String passwdRepeat) {
        mPayDialog.closeDialog();
        if (!isPayed) {
            mPayService.setPayPasswd(passwd, "");
        } else {
            mBusinessService.payBusiness(business.getId(), passwd);
        }
    }

    @Override
    public void onAccountBalance(PayBalance payBalance) {
        mPayDialog.setBalance(payBalance.getBalance());
    }

    @Override
    public void onComplete() {
        mPayDialog.setPayed();
        User user = PreferencesUtil.getInstance(this).get(User.class);
        user.setHas_alipay_password(1);
        PreferencesUtil.getInstance(this).put(user, User.class);
    }

    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {

    }

    @Override
    public void onCompleteBusiness(Business business,Business_Action action) {
        this.business.setHas_pay(1);
        mLayoutDetails.setVisibility(View.VISIBLE);
        mBtnContact.setVisibility(View.GONE);
    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {

    }

    @Override
    public void onComplete(Business_Type type) {

    }
}
