package com.maimaizaixian.transactiononline.module.common.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/12/3.
 */
public interface IPayService extends IBaseService {

    void getAccountBalance();

    void setPayPasswd(String newPasswd,String oldPasswd);

}
