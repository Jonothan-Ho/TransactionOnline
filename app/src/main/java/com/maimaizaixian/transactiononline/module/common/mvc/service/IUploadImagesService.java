package com.maimaizaixian.transactiononline.module.common.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;
import com.maimaizaixian.transactiononline.module.home.domain.LocalBitmap;

import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
public interface IUploadImagesService extends IBaseService {

    void uploadImages(List<LocalBitmap> localBitmaps);

}
