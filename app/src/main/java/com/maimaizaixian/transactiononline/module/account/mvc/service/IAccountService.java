package com.maimaizaixian.transactiononline.module.account.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;
import com.maimaizaixian.transactiononline.module.account.domain.User;

/**
 * Created by Administrator on 2015/10/23.
 */
public interface IAccountService extends IBaseService<User> {

    /**
     * @param username
     * @param password
     */
    void login(String username, String password);

    /**
     * get valid code
     */
    void getValidCode(String phoneNumber);

    /**
     * @param name
     * @param passwd
     * @param code
     */
    void regist(String name, String passwd, String code);


    /**
     * @param name
     * @param passwd
     * @param code
     */
    void findPasswd(String name, String passwd, String code);


}
