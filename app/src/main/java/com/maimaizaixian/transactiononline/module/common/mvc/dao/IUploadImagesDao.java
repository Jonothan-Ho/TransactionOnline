package com.maimaizaixian.transactiononline.module.common.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/11/27.
 */
public interface IUploadImagesDao extends IBaseDao {

    void uploadImages(RequestParams params);

}
