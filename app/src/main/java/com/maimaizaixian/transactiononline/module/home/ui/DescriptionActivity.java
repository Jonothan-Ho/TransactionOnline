package com.maimaizaixian.transactiononline.module.home.ui;

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
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IUploadImagesController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IUploadImagesService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.UploadImagesServiceImpl;
import com.maimaizaixian.transactiononline.module.home.adapter.UploadImageAdapter;
import com.maimaizaixian.transactiononline.module.home.adapter.decoration.SpaceItemDecoration;
import com.maimaizaixian.transactiononline.module.home.domain.LocalBitmap;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DescriptionActivity extends ActionBarTwoActivity implements IPhotoPicker.IPhotoResult, IUploadImagesController {

    @ViewInject(R.id.et_content)
    private EditText mEditDesc;
    @ViewInject(R.id.tv_upload)
    private TextView mTextUpload;

    @ViewInject(R.id.recycler_view)
    private RecyclerView mRecyclerView;

    private OnDialogLauncherAble mGalleryDialog;
    private UploadImageAdapter mUploadImageAdapter;

    private ArrayList<LocalBitmap> mLocalBitmaps;

    private IUploadImagesService mUploadImagesService;


    @Override
    protected void initSubView() {
        setTitleText("描述");
        String content = getIntent().getStringExtra("object");
        if (!TextUtils.isEmpty(content)) {
            mEditDesc.setText(content);
        }

        mLocalBitmaps = new ArrayList<>();
        String paths[] = getIntent().getStringArrayExtra("file");
        if (paths != null && paths.length > 0) {
            for (int i = 0; i < paths.length; i++) {
                File file = new File(paths[i]);
                Bitmap bitmap = null;
                try {
                     bitmap = BitmapUtil.compressFileToBitmap(this, file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                mLocalBitmaps.add(new LocalBitmap(bitmap, file));
            }
        }

        mGalleryDialog = new GalleryDialog(this, this);
        mUploadImageAdapter = new UploadImageAdapter(this, mLocalBitmaps);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));

        mRecyclerView.setAdapter(mUploadImageAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.addItemDecoration(new SpaceItemDecoration(10));

        mUploadImagesService = new UploadImagesServiceImpl(this);


    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_home_description;
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
        String desc = mEditDesc.getText().toString().trim();
        if (TextUtils.isEmpty(desc)) {
            ViewUtil.showSnackbar(getRootView(), "描述不能为空");
            return;
        }

        if (mLocalBitmaps.size() > 0) {
            mUploadImagesService.uploadImages(mLocalBitmaps);
        } else {
            Intent intent = new Intent();
            intent.putExtra("object", desc);
            intent.putExtra("image", "");
            setResult(RESULT_OK, intent);
            finish();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ((GalleryDialog) mGalleryDialog).onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void handResult(Bitmap bitmap, File cacheFile, int requestCode) {
        if (mLocalBitmaps.size() > 5) {
            ViewUtil.showSnackbar(getRootView(), "只能上传五张图片");
            return;
        }
        LocalBitmap localBitmap = new LocalBitmap(bitmap, cacheFile);
        mUploadImageAdapter.addData(localBitmap);
    }

    @Override
    public void onCompleteUpload(String result) {
        Intent intent = new Intent();
        intent.putExtra("object", mEditDesc.getText().toString().trim());
        intent.putExtra("image", result);
        String[] filePath = new String[mLocalBitmaps.size()];
        for (int i = 0; i < mLocalBitmaps.size(); i++) {
            filePath[i] = mLocalBitmaps.get(i).getFile().getAbsolutePath();
        }
        intent.putExtra("file", filePath);
        setResult(RESULT_OK, intent);
        finish();
    }
}
