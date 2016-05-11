package com.maimaizaixian.transactiononline.common.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;

import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.framework.dialog.BaseDialog;
import com.maimaizaixian.transactiononline.framework.listener.OnDialogLauncherAble;
import com.maimaizaixian.transactiononline.framework.util.IPhotoPicker;
import com.maimaizaixian.transactiononline.framework.util.impl.PhotoPickerImpl;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.io.File;

/**
 * check picture dialog
 * Created by Administrator on 2015/10/28.
 */
public class GalleryDialog extends BaseDialog implements View.OnClickListener {

    private Button mBtnGallery;
    private Button mBtnCamera;
    private IPhotoPicker mPhotoPicker;
    private IPhotoPicker.IPhotoResult mPhotoResult;

    public GalleryDialog(Activity context, IPhotoPicker.IPhotoResult photoResult) {
        super(context);
        this.mPhotoResult = photoResult;
    }

    @Override
    public int getCustomView() {
        return R.layout.dialog_take_picture;
    }

    @Override
    public void initView() {
        mBtnGallery = (Button) findViewById(R.id.btn_gallery);
        mBtnCamera = (Button) findViewById(R.id.btn_camera);

        mPhotoPicker = new PhotoPickerImpl(activity, mPhotoResult);

        mBtnCamera.setOnClickListener(this);
        mBtnGallery.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_gallery:
                mPhotoPicker.openAlbumPicker();
                break;
            case R.id.btn_camera:
                mPhotoPicker.openCamera();
                break;
        }
        closeDialog();
    }

    public void onActivityResult(int requestCode, int responseCode, Intent data) {
        mPhotoPicker.onActivityResult(requestCode, responseCode, data);
    }
}
