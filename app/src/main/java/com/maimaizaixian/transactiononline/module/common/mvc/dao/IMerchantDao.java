package com.maimaizaixian.transactiononline.module.common.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/11/27.
 */
public interface IMerchantDao extends IBaseDao {

    public static final String API_MERCHANT_INFO = "api/users/";

    void getMerchantList(String url);

    void getMerchantInfo(String url);

}
