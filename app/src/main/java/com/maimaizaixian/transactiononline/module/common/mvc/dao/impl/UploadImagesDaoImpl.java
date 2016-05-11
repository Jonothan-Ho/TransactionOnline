package com.maimaizaixian.transactiononline.module.common.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.common.domain.ImagesResult;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IUploadImagesController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IUploadImagesDao;

/**
 * Created by Administrator on 2015/11/27.
 */
public class UploadImagesDaoImpl extends BaseDao implements IUploadImagesDao {

    public static final String API_UPLOAD_IMAGE = "files/image/upload";

    private IUploadImagesController mImagesController;

    public UploadImagesDaoImpl(IBaseController baseController) {
        super(baseController);
        mImagesController = (IUploadImagesController) baseController;
    }

    @Override
    public void uploadImages(RequestParams params) {
        HttpUtil.post(API_UPLOAD_IMAGE, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.Object, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                mImagesController.closeDialog();
                if (domain.isSuccess()) {
                    mImagesController.onCompleteUpload(domain.getData());
                }
            }
        });


    }
}
