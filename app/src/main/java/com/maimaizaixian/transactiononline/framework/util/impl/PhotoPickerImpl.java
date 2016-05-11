package com.maimaizaixian.transactiononline.framework.util.impl;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;

import com.maimaizaixian.transactiononline.framework.util.IPhotoPicker;
import com.maimaizaixian.transactiononline.utils.BitmapUtil;
import com.maimaizaixian.transactiononline.utils.FileUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.io.File;
import java.io.IOException;

/**
 * choose photo by System Album
 * Created by Administrator on 2015/10/19.
 */
public class PhotoPickerImpl implements IPhotoPicker {

    private boolean isKitkat;
    private Activity mActivity;
    private File mCameraFile;
    private IPhotoResult mPhotoResult;

    public PhotoPickerImpl(Activity activity,IPhotoResult result) {
        this.mActivity = activity;
        this.mPhotoResult = result;
        isKitkat = Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT;
    }


    @Override
    public void openAlbumPicker() {
        Intent intent = null;
        if (isKitkat) {
            intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            mActivity.startActivityForResult(intent, REQUESTCODE_KITKAT_LATER);
        } else {
            intent = new Intent(Intent.ACTION_GET_CONTENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("image/*");
            mActivity.startActivityForResult(intent, REQUESTCODE_KITKAT_BEFORE);
        }
    }

    @Override
    public void openCamera() {
        mCameraFile = FileUtil.getCacheBitmapFile(mActivity, FileUtil.Image_Format.JPG);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mCameraFile));
        mActivity.startActivityForResult(intent, REQUESTCODE_CAMERA);
    }

    @Override
    public void onActivityResult(int requestCode, int responseCode, Intent data) {

        if (responseCode == Activity.RESULT_OK && data != null && requestCode != REQUESTCODE_CAMERA) {

            Uri uri = data.getData();

            if (uri == null) {
                ViewUtil.showSnackbar(mActivity.getWindow().getDecorView(), "Not choose any pictures");
                return;
            }

            File cacheFile = null;
            Bitmap bitmap = null;
            try {
                cacheFile = FileUtil.getCacheBitmapFile(mActivity, FileUtil.Image_Format.JPEG);
                bitmap =  BitmapUtil.compressBitmapToCacheFile(mActivity, uri, cacheFile);
            } catch (Exception e) {
                e.printStackTrace();
                ViewUtil.showToast(mActivity, e.getMessage());
            }

            if (mPhotoResult != null) {
                mPhotoResult.handResult(bitmap, cacheFile, requestCode);
            }

        }else if (responseCode == Activity.RESULT_OK && mCameraFile != null && requestCode == REQUESTCODE_CAMERA) {

            Bitmap bitmap = null;
            try {
                bitmap = BitmapUtil.compressFileToBitmap(mActivity, mCameraFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (mPhotoResult != null) {
                mPhotoResult.handResult(bitmap, mCameraFile, requestCode);
            }

        } else if (data == null) {
            ViewUtil.showSnackbar(mActivity.getWindow().getDecorView(), "Not choose any pictures");
        }
    }


}
