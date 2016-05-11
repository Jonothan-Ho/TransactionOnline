package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.common.dialog.GalleryDialog;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarTwoActivity;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.framework.util.IPhotoPicker;
import com.maimaizaixian.transactiononline.module.common.domain.Product;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IProductController;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IUploadImagesController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IProductService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IUploadImagesService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.ProductServiceImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.UploadImagesServiceImpl;
import com.maimaizaixian.transactiononline.module.home.adapter.UploadImageAdapter;
import com.maimaizaixian.transactiononline.module.home.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.domain.LocalBitmap;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends ActionBarTwoActivity implements IPhotoPicker.IPhotoResult, IUploadImagesController, IProductController {

    @ViewInject(R.id.et_title)
    private EditText mEditTitle;
    @ViewInject(R.id.et_content)
    private EditText mEditContent;

    @ViewInject(R.id.tv_upload)
    private TextView mTextUpload;
    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;
    private OnDialogLauncherAble mGalleryDialog;
    private UploadImageAdapter mUploadImageAdapter;
    private ArrayList<LocalBitmap> mLocalBitmaps;

    private IProductService mProductService;
    private IUploadImagesService mUploadImagesService;


    @Override
    protected void initSubView() {
        setTitleText("添加");
        setCancelText("取消");

        mGalleryDialog = new GalleryDialog(this, this);
        mLocalBitmaps = new ArrayList<>();

        mUploadImageAdapter = new UploadImageAdapter(this, mLocalBitmaps);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mUploadImageAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        mProductService = new ProductServiceImpl(this);
        mUploadImagesService = new UploadImagesServiceImpl(this);
    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_add_product;
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
    public void onActionClick(View view) {
        mUploadImagesService.uploadImages(mLocalBitmaps);
    }

    @Override
    public void onCompleteArray(List<Product> products, int page) {

    }

    @Override
    public void onComplete(Type type) {

    }

    @Override
    public void onComplete(Product product) {
        finish();
    }

    @Override
    public void onCompleteUpload(String result) {
        mProductService.addProduct(IProductService.MODEL_PRODUCT, mEditTitle.getText().toString().trim(), result, mEditContent.getText().toString().trim());
    }
}
