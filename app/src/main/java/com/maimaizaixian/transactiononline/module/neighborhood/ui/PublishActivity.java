package com.maimaizaixian.transactiononline.module.neighborhood.ui;


import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.common.dialog.GalleryDialog;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarTwoActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.framework.util.IPhotoPicker;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IBusinessController;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IUploadImagesController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IBusinessService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IUploadImagesService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.BusinessServiceImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.UploadImagesServiceImpl;
import com.maimaizaixian.transactiononline.module.home.adapter.UploadImageAdapter;
import com.maimaizaixian.transactiononline.module.home.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;
import com.maimaizaixian.transactiononline.module.home.domain.LocalBitmap;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PublishActivity extends ActionBarTwoActivity implements IPhotoPicker.IPhotoResult, IUploadImagesController,IBusinessController {


    @ViewInject(R.id.et_desc)
    private EditText mEditDesc;

    @ViewInject(R.id.edit_title)
    private EditText mEditTitle;

    @ViewInject(R.id.tv_upload)
    private TextView mTextUpload;

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;

    private OnDialogLauncherAble mGalleryDialog;
    private UploadImageAdapter mUploadImageAdapter;

    private ArrayList<LocalBitmap> mLocalBitmaps;

    private IBusinessService mBusinessService;
    private IUploadImagesService mUploadImagesService;

    @Override

    protected void initSubView() {

        setTitleText("发布商机");
        setActionText("发布");

        mGalleryDialog = new GalleryDialog(this, this);
        mLocalBitmaps = new ArrayList<>();

        mUploadImageAdapter = new UploadImageAdapter(this, mLocalBitmaps);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        mRecyclerView.setAdapter(mUploadImageAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        mBusinessService = new BusinessServiceImpl(this);
        mUploadImagesService = new UploadImagesServiceImpl(this);
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_neighborhood_publish;
    }

    @Override
    protected void initListener() {
        mTextUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGalleryDialog.startDialog();
            }
        });
    }

    @Override
    public void onActionClick(View view) {
        // perform click
        if (TextUtils.isEmpty(mEditDesc.getText().toString().trim())) {
            ViewUtil.showSnackbar(getRootView(), "需求描述不能为空");
            return;
        }

        if (TextUtils.isEmpty(mEditTitle.getText().toString().trim())) {
            ViewUtil.showSnackbar(getRootView(), "商家标题不能为空");
            return;
        }

        if (mLocalBitmaps.size() == 0) {
            ViewUtil.showSnackbar(getRootView(), "商机图片不能为空");
            return;
        }

        mUploadImagesService.uploadImages(mLocalBitmaps);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ((GalleryDialog) mGalleryDialog).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void handResult(Bitmap bitmap, File cacheFile, int requestCode) {
        LocalBitmap localBitmap = new LocalBitmap(bitmap, cacheFile);
        mUploadImageAdapter.addData(localBitmap);
    }

    @Override
    public void onCompleteUpload(String result) {
        String title = mEditTitle.getText().toString().trim();
        String content = mEditDesc.getText().toString().trim();
        mBusinessService.publishBusiness(title, content, result,"0", "0");
    }

    @Override
    public void onMatchResult(List<CategoryContent> categoryContents) {

    }

    @Override
    public void onCompleteBusiness(Business business,Business_Action action) {
        finish();
    }

    @Override
    public void onCompleteBusiness(List<Business> business, int page) {

    }

    @Override
    public void onComplete(Business_Type type) {

    }
}
