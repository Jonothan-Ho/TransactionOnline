package com.maimaizaixian.transactiononline.module.common.mvc.service;

import com.easemob.EMCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/12/15.
 */
public interface IMService extends IBaseService {
    void login(String username, String passwd, EMCallBack callBack);
}
