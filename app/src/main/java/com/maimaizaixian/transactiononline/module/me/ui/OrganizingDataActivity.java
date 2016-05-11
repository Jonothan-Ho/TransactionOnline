package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.common.dialog.GalleryDialog;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.framework.util.IPhotoPicker;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IUploadImagesController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IUploadImagesService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.UploadImagesServiceImpl;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.home.domain.LocalBitmap;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IOrganizingDataController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IOrganizingDataService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.OrganizingDataServiceImpl;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.PreferencesUtil;
import com.maimaizaixian.transactiononline.view.CircleImageView;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class OrganizingDataActivity extends ActionBarOneActivity implements IUploadImagesController, IOrganizingDataController, View.OnClickListener, IPhotoPicker.IPhotoResult, DatePickerDialog.OnDateSetListener {

    public static final int REQUEST_INDUSTY = 10;
    public static final int REQUEST_SERVICE = 11;
    public static final int REQUEST_ADVANTAGE = 12;

    @ViewInject(R.id.iv_portrait)
    private CircleImageView mImgPortrait;

    @ViewInject(R.id.layout_portrait)
    private RelativeLayout mLayoutPortrait;
    @ViewInject(R.id.layout_honor)
    private RelativeLayout mLayoutHonor;
    @ViewInject(R.id.layout_regist_date)
    private RelativeLayout mLayoutRegistDate;
    @ViewInject(R.id.layout_industry)
    private RelativeLayout mLayoutIndustry;
    @ViewInject(R.id.layout_advantage)
    private RelativeLayout mLayoutAdvantage;
    @ViewInject(R.id.layout_service)
    private RelativeLayout mLayoutService;
    @ViewInject(R.id.layout_product)
    private RelativeLayout mLayoutProduct;
    @ViewInject(R.id.layout_customer)
    private RelativeLayout mLayoutCustomer;

    @ViewInject(R.id.et_name)
    private EditText mEditName;
    @ViewInject(R.id.et_company_short)
    private EditText mEditCompanyShort;
    @ViewInject(R.id.et_company_all)
    private EditText mEditCompanyAll;
    @ViewInject(R.id.et_regist_money)
    private EditText mEditRegistMoney;
    @ViewInject(R.id.et_people_num)
    private EditText mEditPeopleNum;
    @ViewInject(R.id.et_phone)
    private EditText mEditPhone;
    @ViewInject(R.id.et_address)
    private EditText mEditAddress;
    @ViewInject(R.id.et_qq)
    private EditText mEditQQ;
    @ViewInject(R.id.et_email)
    private EditText mEditEmail;

    @ViewInject(R.id.tv_industry)
    private TextView mTextIndustry;
    @ViewInject(R.id.tv_regist_date)
    private TextView mTextRegistDate;

    @ViewInject(R.id.btn_submit)
    private Button mBtnSubmit;


    private OnDialogLauncherAble mGalleryDialog;
    // private OnDialogLauncherAble mCalendarDialog;
    private DatePickerDialog mCalendarDialog;


    private IUploadImagesService mUploadImagesService;
    private IOrganizingDataService mOrganizingDataService;

    /**
     * params
     **/
    private String mImagePath;
    private String mCategoryId;
    private String mAdavantage;
    private String mService;

    @Override
    protected void initSubView() {
        setTitleBarHeadline("完善资料");
        setTitleBarHint("我");

        initData();

        mGalleryDialog = new GalleryDialog(this, this);
        Calendar now = Calendar.getInstance();
        mCalendarDialog = DatePickerDialog.newInstance(
                this,
                now.get(Calendar.YEAR),
                now.get(Calendar.MONTH),
                now.get(Calendar.DAY_OF_MONTH)
        );
        mCalendarDialog.setAccentColor(getResources().getColor(R.color.color_main));

        //mCalendarDialog = new CalendarDialog(this);
        mUploadImagesService = new UploadImagesServiceImpl(this);
        mOrganizingDataService = new OrganizingDataServiceImpl(this);


    }

    private void initData() {
        User user = PreferencesUtil.getInstance(this).get(User.class);
        BitmapUtil.getInstance(this).displayImage(mImgPortrait, user.getHead());
        mEditName.setText(user.getReal_name());
        mEditCompanyShort.setText(user.getShort_company_name());
        mEditCompanyAll.setText(user.getCompany_name());
        mEditRegistMoney.setText(user.getRegister_money());
        mEditPeopleNum.setText(user.getEnterprise_people());
        mTextRegistDate.setText(user.getRegister_time());
        mEditPhone.setText(user.getUsername());
        mEditAddress.setText(user.getAddress());
        mEditQQ.setText(user.getQq());
        mEditEmail.setText(user.getMail());
        mService = user.getService();
        mAdavantage = user.getAdvantage();
        List<User.Category> categories = user.getCategories();
        if (categories != null && categories.size() > 0) {
            StringBuilder category = new StringBuilder();
            StringBuilder categoryId = new StringBuilder();
            for (int i = 0; i < categories.size(); i++) {
                category.append(categories.get(i).getTitle()).append(" ");
                categoryId.append(categories.get(i).getId());
                if (i != categories.size() - 1) {
                    categoryId.append(",");
                }
            }
            mTextIndustry.setText(category.toString());
            mCategoryId = categoryId.toString();
        }

        int status = user.getUser_auth();
        switch (status) {
            case 0:
                break;
            case 1:
                setAudit();
                break;
            case 2:
                break;
            case -1:
                break;
        }

    }


    @Override
    protected int getCustomView() {
        return R.layout.activity_me_organizing_data;
    }

    @Override
    protected void initListener() {
        mLayoutPortrait.setOnClickListener(this);
        mBtnSubmit.setOnClickListener(this);
        mLayoutRegistDate.setOnClickListener(this);
        mLayoutIndustry.setOnClickListener(this);
        mLayoutCustomer.setOnClickListener(this);
        mLayoutProduct.setOnClickListener(this);
        mLayoutService.setOnClickListener(this);
        mLayoutAdvantage.setOnClickListener(this);
        mLayoutHonor.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.layout_portrait:
                mGalleryDialog.startDialog();
                break;
            case R.id.layout_regist_date:
                //mCalendarDialog.startDialog();
                mCalendarDialog.show(getFragmentManager(), "CalendarDialog");
                break;
            case R.id.btn_submit:
                //ViewUtil.showToast(this, "===>");
                submit();
                break;
            case R.id.layout_industry:
                Intent intentIndustry = new Intent(this, FocusCategoryActivity.class);
                startActivityForResult(intentIndustry, REQUEST_INDUSTY);
                break;
            case R.id.layout_customer:
                Intent intentCustomer = new Intent(this, CustomerDisplayActivity.class);
                startActivity(intentCustomer);
                break;
            case R.id.layout_product:
                Intent intentProduct = new Intent(this, ProductDisplayActivity.class);
                startActivity(intentProduct);
                break;
            case R.id.layout_service:
                Intent intentService = new Intent(this, OurServiceActivity.class);
                startActivityForResult(intentService, REQUEST_SERVICE);
                break;
            case R.id.layout_advantage:
                Intent intentAdvantage = new Intent(this, OurAdvantageActivity.class);
                startActivityForResult(intentAdvantage, REQUEST_ADVANTAGE);
                break;
            case R.id.layout_honor:
                Intent intentHonor = new Intent(this, HonorActivity.class);
                startActivity(intentHonor);
                break;

        }
    }

    /**
     *
     */
    private void submit() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("model", "aptitude");
        params.addBodyParameter("head", mImagePath);
        params.addBodyParameter("real_name", mEditName.getText().toString().trim());
        params.addBodyParameter("short_company_name", mEditCompanyShort.getText().toString().trim());
        params.addBodyParameter("company_name", mEditCompanyAll.getText().toString().trim());
        params.addBodyParameter("register_money", mEditRegistMoney.getText().toString().trim());
        params.addBodyParameter("enterprise_people", mEditPeopleNum.getText().toString().trim());
        params.addBodyParameter("register_time", mTextRegistDate.getText().toString().trim());
        params.addBodyParameter("mobile", mEditPhone.getText().toString().trim());
        params.addBodyParameter("address", mEditAddress.getText().toString().trim());
        params.addBodyParameter("qq", mEditQQ.getText().toString().trim());
        params.addBodyParameter("email", mEditEmail.getText().toString().trim());
        params.addBodyParameter("trade_cid", mCategoryId);
        params.addBodyParameter("advantage", mAdavantage);
        params.addBodyParameter("service", mService);
        mOrganizingDataService.submit(params);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_INDUSTY) {
                ArrayList<CategoryContent> contents = (ArrayList<CategoryContent>) data.getSerializableExtra("object");
                if (contents != null && contents.size() > 0) {
                    StringBuilder builder = new StringBuilder();
                    StringBuilder titleBuilder = new StringBuilder();
                    for (int i = 0; i < contents.size(); i++) {
                        CategoryContent content = contents.get(i);
                        builder.append(content.getId());
                        if (i != contents.size() - 1) {
                            builder.append(",");
                        }
                        titleBuilder.append(content.getTitle()).append(" ");
                    }
                    mTextIndustry.setText(titleBuilder.toString().toString());
                    mCategoryId = builder.toString();
                }
            } else if (requestCode == REQUEST_ADVANTAGE) {
                mAdavantage = data.getStringExtra("object");
            } else if (requestCode == REQUEST_SERVICE) {
                mService = data.getStringExtra("object");
            } else {
                ((GalleryDialog) mGalleryDialog).onActivityResult(requestCode, resultCode, data);
            }

        }


    }

    @Override
    public void handResult(Bitmap bitmap, File cacheFile, int requestCode) {
        mImgPortrait.setImageBitmap(bitmap);
        ArrayList<LocalBitmap> localBitmaps = new ArrayList<>();
        localBitmaps.add(new LocalBitmap(bitmap, cacheFile));
        mUploadImagesService.uploadImages(localBitmaps);
    }


    /**
     * set of information os the audit
     */
    public void setAudit() {
        mBtnSubmit.setClickable(false);
        mBtnSubmit.setEnabled(false);
        mBtnSubmit.setBackgroundColor(getResources().getColor(R.color.color_grey_middle));
        mBtnSubmit.setText("审核中");
    }


   /* @Override
    public void onDateSelected(MaterialCalendarView widget, CalendarDay date, boolean selected) {
        ViewUtil.showSnackbar(getWindow().getDecorView(), date.getYear() + "==>" + date.getMonth() + "==>" + date.getDay());
        if (selected) {
            mCalendarDialog.closeDialog();
        }
    }*/

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        mTextRegistDate.setText(year + "/" + (monthOfYear + 1) + "/" + dayOfMonth);
    }

    @Override
    public void onCompleteUpload(String result) {
        mImagePath = result;
    }

    @Override
    public void onComplete(User user) {
        PreferencesUtil.getInstance(this).put(user, User.class);
    }
}

