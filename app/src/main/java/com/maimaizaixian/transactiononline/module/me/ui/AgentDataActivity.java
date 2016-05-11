package com.maimaizaixian.transactiononline.module.me.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.lidroid.xutils.view.annotation.ViewInject;
import com.maimaizaixian.transactiononline.R;
import com.maimaizaixian.transactiononline.common.dialog.GalleryDialog;
import com.maimaizaixian.transactiononline.framework.activity.ActionBarOneActivity;
import com.maimaizaixian.transactiononline.framework.util.IPhotoPicker;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IUploadImagesController;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IUploadImagesService;
import com.maimaizaixian.transactiononline.module.common.mvc.service.impl.UploadImagesServiceImpl;
import com.maimaizaixian.transactiononline.module.home.domain.LocalBitmap;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IOrganizingDataController;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IOrganizingDataService;
import com.maimaizaixian.transactiononline.module.me.mvc.service.impl.OrganizingDataServiceImpl;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.io.File;
import java.util.ArrayList;

public class AgentDataActivity extends ActionBarOneActivity implements IPhotoPicker.IPhotoResult, IUploadImagesController, IOrganizingDataController {

    @ViewInject(R.id.et_name)
    private EditText mEditName;
    @ViewInject(R.id.et_phone)
    private EditText mEditPhone;
    @ViewInject(R.id.et_id_card)
    private EditText mEditID;

    @ViewInject(R.id.layout_upload)
    private RelativeLayout mLayoutUpload;

    @ViewInject(R.id.btn_ok)
    private Button mBtnOk;

    private GalleryDialog mGalleryDialog;

    private ArrayList<LocalBitmap> mLocalBitmaps;

    private IUploadImagesService mUploadImagesService;
    private IOrganizingDataService mOriorganizingDataService;

    @Override
    protected void initSubView() {
        setTitleBarHeadline("代理人资料");
        setTitleBarHint("代理人设置");

        mGalleryDialog = new GalleryDialog(this, this);
        mLocalBitmaps = new ArrayList<>();
        mUploadImagesService = new UploadImagesServiceImpl(this);
        mOriorganizingDataService = new OrganizingDataServiceImpl(this);

    }

    @Override
    protected int getCustomView() {
        return R.layout.activity_me_agent_data;
    }

    @Override
    protected void initListener() {
        mLayoutUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGalleryDialog.startDialog();
            }
        });

        mBtnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mLocalBitmaps.size() < 2) {
                    ViewUtil.showSnackbar(getRootView(), "必须上传正面和背面2张身份证照片");
                    return;
                }

                mUploadImagesService.uploadImages(mLocalBitmaps);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mGalleryDialog.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void handResult(Bitmap bitmap, File cacheFile, int requestCode) {
        mLocalBitmaps.add(new LocalBitmap(bitmap, cacheFile));
    }

    @Override
    public void onCompleteUpload(String result) {
        String name = mEditName.getText().toString().trim();
        String phone = mEditPhone.getText().toString().trim();
        String idCard = mEditID.getText().toString().trim();
        mOriorganizingDataService.applyAgent(name, phone, idCard, result);
    }

    @Override
    public void onComplete(User user) {
        finish();
    }
}
