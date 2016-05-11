package com.maimaizaixian.transactiononline.module.home.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.easemob.easeui.EaseConstant;
import com.easemob.easeui.ui.EaseChatFragment;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IMerchantController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IMerchantService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.MerchantServiceImpl;
import com.maimaizaixian.transactiononline.module.common.ui.ChatActivity;
import com.maimaizaixian.transactiononline.module.home.domain.Merchants;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.LogUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;
import com.maimaizaixian.transactiononline.view.CircleImageView;

import java.util.List;


public class MerchantsHomeActivity extends ActionBarOneActivity implements View.OnClickListener {


    @ViewInject(R.id.iv_portrait)
    private CircleImageView mImgPortrait;

    @ViewInject(R.id.layout_honor)
    private RelativeLayout mLayoutHonor;
    @ViewInject(R.id.layout_customer)
    private RelativeLayout mLayoutCustomer;
    @ViewInject(R.id.layout_advantage)
    private RelativeLayout mLayoutAdvantage;
    @ViewInject(R.id.layout_service)
    private RelativeLayout mLayoutService;
    @ViewInject(R.id.layout_product)
    private RelativeLayout mLayoutProduct;

    @ViewInject(R.id.tv_name)
    private TextView mTextName;
    @ViewInject(R.id.tv_enterprise_all)
    private TextView mTextCompanyAll;
    @ViewInject(R.id.tv_enterprise_short)
    private TextView mTextCompanyShort;
    @ViewInject(R.id.tv_regist_money)
    private TextView mTextRegistMoney;
    @ViewInject(R.id.tv_people)
    private TextView mTextPeopleNum;
    @ViewInject(R.id.tv_year)
    private TextView mTextYear;
    @ViewInject(R.id.tv_industry)
    private TextView mTextIndustry;
    @ViewInject(R.id.btn_talk)
    private Button mBtnTalk;


    private User user;


    @Override
    protected int getCustomView() {
        return R.layout.activity_home_merchants_main;
    }

    @Override
    protected void initListener() {
        mLayoutHonor.setOnClickListener(this);
        mLayoutAdvantage.setOnClickListener(this);
        mLayoutService.setOnClickListener(this);
        mLayoutCustomer.setOnClickListener(this);
        mLayoutProduct.setOnClickListener(this);

        mBtnTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent talkIntent = new Intent(MerchantsHomeActivity.this, ChatActivity.class);
                talkIntent.putExtra("id", user.getId());
                startActivity(talkIntent);
            }
        });

    }

    @Override
    protected void initSubView() {
        setTitleBarHeadline("商户主页");
        setTitleBarHint("匹配界面");

        user = (User) getIntent().getSerializableExtra("object");
        if (user == null) {
            ViewUtil.showToast(this, "商户对象丢失，请重试");
            finish();
            return;
        }

        initViewData();
    }

    @Override
    public void onClick(View v) {
        Intent actionIntent = new Intent();
        switch (v.getId()) {
            case R.id.layout_honor:
                actionIntent.setClass(this, MerchantsHonorActivity.class);
                actionIntent.putExtra("id", user.getId());
                break;
            case R.id.layout_product:
                actionIntent.setClass(this, MerchantsProductActivity.class);
                actionIntent.putExtra("id", user.getId());
                break;
            case R.id.layout_customer:
                actionIntent.setClass(this, MerchantsCustomerActivity.class);
                actionIntent.putExtra("id", user.getId());
                break;
            case R.id.layout_service:
                actionIntent.setClass(this, MerchantsServiceActivity.class);
                actionIntent.putExtra("object", user.getService());
                break;
            case R.id.layout_advantage:
                actionIntent.setClass(this, MerchantsAdvantageActivity
                        .class);
                actionIntent.putExtra("object", user.getAdvantage());
                break;
        }

        startActivity(actionIntent);
    }

    /**
     *
     */
    private void initViewData() {
        BitmapUtil.getInstance(this).displayImage(mImgPortrait, user.getHead());
        mTextName.setText(user.getUsername());
        mTextCompanyAll.setText(user.getCompany_name());
        mTextCompanyShort.setText(user.getShort_company_name());
        mTextRegistMoney.setText(user.getRegister_money() + "元");
        mTextPeopleNum.setText(user.getEnterprise_people() + "人");
        mTextYear.setText(user.getRegister_time());
        List<User.Category> categories = user.getCategories();

        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < categories.size(); i++) {
            builder.append(categories.get(i).getTitle()).append(" ");
        }
        mTextIndustry.setText(builder.toString());

    }


}
