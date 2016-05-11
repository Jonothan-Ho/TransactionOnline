package com.maimaizaixian.transactiononline.module.common.mvc.service.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IUploadImagesDao;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.impl.UploadImagesDaoImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IUploadImagesService;
import com.maimaizaixian.transactiononline.module.home.domain.LocalBitmap;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
public class UploadImagesServiceImpl extends BaseService implements IUploadImagesService {

    private IUploadImagesDao dao;

    public UploadImagesServiceImpl(IBaseController controller) {
        super(controller);
        dao = new UploadImagesDaoImpl(controller);
    }

    @Override
    public void uploadImages(List<LocalBitmap> localBitmaps) {
        if (localBitmaps == null || localBitmaps.size() == 0) {
            ViewUtil.showSnackbar(mController.getRootView(), "上传图片不能为空");
        }

        RequestParams params = new RequestParams();
        for (int i = 0; i < localBitmaps.size(); i++) {
            params.addBodyParameter("image["+i+"]", localBitmaps.get(i).getFile());
        }
        mController.openDialog();
        dao.uploadImages(params);
    }
}
