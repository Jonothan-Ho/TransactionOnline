package com.maimaizaixian.transactiononline.module.home.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amap.api.location.AMapLocationListener;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.fragment.BaseNormalFragment;
import com.maimaizaixian.transactiononline.framework.listener.OnItemClickListener;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.map.AMapLocation;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.home.dialog.MultiCategoryDialog;
import com.maimaizaixian.transactiononline.module.home.dialog.NoCategoryDialog;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.home.ui.CategoryActivity;
import com.maimaizaixian.transactiononline.module.home.ui.DescriptionActivity;
import com.maimaizaixian.transactiononline.module.home.ui.MatchResultActivity;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.lang.ref.SoftReference;
import java.util.List;

/**
 * Sell fragment in publish activity
 */
public class RentOutFragment extends BaseNormalFragment implements AMapLocationListener, View.OnClickListener, IBusinessController, OnItemClickListener {

    public static final int REQUEST_CATEGORY = 10;
    public static final int REQUEST_DESC = 20;

    @ViewInject(R.id.et_key)
    private EditText mEditKey;
    @ViewInject(R.id.et_num)
    private EditText mEditNum;

    @ViewInject(R.id.layout_need)
    private RelativeLayout mLayoutNeed;
    @ViewInject(R.id.layout_description)
    private RelativeLayout mLayoutDesc;
    @ViewInject(R.id.tv_category_name)
    private TextView mTextCategory;
    @ViewInject(R.id.tv_desc_status)
    private TextView mTextDescStatus;

    @ViewInject(R.id.et_phone)
    private EditText mEditPhone;
    @ViewInject(R.id.et_address)
    private EditText mEditAddress;

    @ViewInject(R.id.btn_publish)
    private Button mBtnPublish;

    private IBusinessService mPublishService;
    private MultiCategoryDialog mMultiCategoryDialog;
    private NoCategoryDialog mNoCategoryDialog;
    private SellHandler sellHandler;
    private int mParamCategoryID = 0;
    private String mParamDesc;
    private String mParamDescImages;

    private AMapLocation mapLocation;
    private String longitude;
    private String latitude;
    private String mListBitmaps[];
    private String mBusinessID;

    @Override
    public void initSubView() {
        mPublishService = new BusinessServiceImpl(this);
        sellHandler = new SellHandler(this);

        mapLocation = new AMapLocation(getActivity().getApplicationContext());
        mapLocation.startLocation(this, true);
    }

    @Override
    protected int getCustomView() {
        return R.layout.fragment_home_sell;
    }

    @Override
    protected void initListener() {
        mBtnPublish.setOnClickListener(this);
        mLayoutNeed.setOnClickListener(this);
        mLayoutDesc.setOnClickListener(this);

        mEditKey.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {

                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    ((InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE))
                            .hideSoftInputFromWindow(
                                    getActivity()
                                            .getCurrentFocus()
                                            .getWindowToken(),
                                    InputMethodManager.HIDE_NOT_ALWAYS);

                    mPublishService.matchKeyToCategory(mEditKey.getText().toString().trim());

                }

                return false;
            }
        });

        Bundle bundle = getArguments();
        if (bundle != null) {
            Business business = (Business) bundle.getSerializable("object");
            initData(business);
        }

    }

    /**
     * @param business
     */
    private void initData(Business business) {
        mEditKey.setText(business.getTitle());
        mEditNum.setText(business.getNum());
        mTextCategory.setText(business.getCategory_title());
        mParamCategoryID = business.getCid();
        mParamDesc = business.getNeed_desc();
        mParamDescImages = business.getImgpiclist();
        mTextDescStatus.setText("已填写");
        mEditAddress.setText(business.getAddress());
        mEditPhone.setText(business.getContact());
        mBusinessID = business.getId();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_publish:
                publish();
                break;
            case R.id.layout_description:
                Intent descIntent = new Intent(getActivity(), DescriptionActivity.class);
                descIntent.putExtra("object", mParamDesc);
                descIntent.putExtra("file", mListBitmaps);
                startActivityForResult(descIntent, REQUEST_DESC);
                break;
            case R.id.layout_need:
                Intent categoryIntent = new Intent(getActivity(), CategoryActivity.class);
                startActivityForResult(categoryIntent, REQUEST_CATEGORY);
                break;
        }
    }

    /**
     * @param text
     */
    public void setCategoryText(String text) {
        mTextCategory.setText(text);
    }

    /**
     * @param id
     */
    public void setCategoryID(int id) {
        mParamCategoryID = id;
    }


    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {
        if (categoryContents.size() > 1) {
            if (mMultiCategoryDialog == null) {
                mMultiCategoryDialog = new MultiCategoryDialog(getActivity(), categoryContents, this);
            } else {
                mMultiCategoryDialog.setCategories(categoryContents);
            }
            mMultiCategoryDialog.startDialog();
        } else if (categoryContents.size() == 1) {
            CategoryContent content = categoryContents.get(0);
            mTextCategory.setText(content.getTitle());
            mParamCategoryID = content.getId();
        } else {
            if (mNoCategoryDialog == null) {
                mNoCategoryDialog = new NoCategoryDialog(getActivity());
            }
            mNoCategoryDialog.startDialog();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapLocation.stopLocation();
    }

    /**
     *
     */
    private void publish() {
        String address = mEditAddress.getText().toString().trim();
        String contact = mEditPhone.getText().toString().trim();
        String num = mEditNum.getText().toString().trim();
        if (TextUtils.isEmpty(mBusinessID)) {
            mPublishService.publishBusiness(IBusinessService.MODE_RENTOUT, mParamCategoryID, 0, 0, mParamDesc, address, contact, num, mParamDescImages, longitude, latitude);
        } else {
            mPublishService.updateBusiness(mBusinessID, IBusinessService.MODE_RENTOUT, mParamCategoryID, 0, 0, mParamDesc, address, contact, num, mParamDescImages, longitude, latitude);
        }

    }

    @Override
    public void onCompleteBusiness(Business business, Business_Action action) {
        Intent intent = new Intent(getActivity(), MatchResultActivity.class);
        intent.putExtra("object", business);
        startActivity(intent);
        getActivity().finish();
    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {

    }

    @Override
    public void onComplete(Business_Type type) {

    }

    @Override
    public void click(View v, int position) {
        CategoryContent content = mMultiCategoryDialog.get(position);
        Message message = sellHandler.obtainMessage();
        message.obj = content;
        sellHandler.sendMessage(message);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case REQUEST_CATEGORY:
                    CategoryContent categoryContent = data.getParcelableExtra("object");
                    mTextCategory.setText(categoryContent.getTitle());
                    mParamCategoryID = categoryContent.getId();
                    break;
                case REQUEST_DESC:
                    mParamDesc = data.getStringExtra("object");
                    mParamDescImages = data.getStringExtra("image");
                    mListBitmaps = data.getStringArrayExtra("file");
                    mTextDescStatus.setText("已填写");
                    break;
            }
        }
    }

    @Override
    public void onLocationChanged(com.amap.api.location.AMapLocation aMapLocation) {
        if (aMapLocation != null) {
            if (aMapLocation.getErrorCode() == 0) {
                longitude = aMapLocation.getLongitude() + "";
                latitude = aMapLocation.getLatitude() + "";
            } else {
                ViewUtil.showSnackbar(getRootView(), "定位失败：" + aMapLocation.getErrorInfo());
            }
        }
    }

    private class SellHandler extends Handler {

        private SoftReference<RentOutFragment> mSoftReference;

        public SellHandler(RentOutFragment fragment) {
            mSoftReference = new SoftReference<RentOutFragment>(fragment);
        }

        @Override
        public void dispatchMessage(Message msg) {
            CategoryContent content = (CategoryContent) msg.obj;
            RentOutFragment fragment = mSoftReference.get();
            fragment.setCategoryText(content.getTitle());
            fragment.setCategoryID(content.getId());
            mMultiCategoryDialog.closeDialog();
        }
    }
}
