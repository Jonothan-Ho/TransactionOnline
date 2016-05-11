package com.maimaizaixian.transactiononline.module.me.mvc.service;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/12/7.
 */
public interface IOrganizingDataService extends IBaseService {

    void submit(RequestParams params);

    void applyAgent(String name, String mobile, String idCard, String IDCard_license);

}
