package com.maimaizaixian.transactiononline.module.me.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/12/11.
 */
public interface IPasswordService extends IBaseService {


    void updateLoginPasswd(String oldPasswd, String passwd);

    void updatePayPasswd(String oldPasswd, String passwd);

    void updatePayPasswd(String phone, String passwd, String code);


}
