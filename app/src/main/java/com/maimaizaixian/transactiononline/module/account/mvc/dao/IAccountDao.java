package com.maimaizaixian.transactiononline.module.account.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;
import com.maimaizaixian.transactiononline.module.account.domain.User;

/**
 * Created by Administrator on 2015/11/20.
 */
public interface IAccountDao extends IBaseDao<User> {


    void getValidCode(String phoneNumber);

    void regist(RequestParams params);

    void login(RequestParams params);

    void findPasswd(RequestParams params);


}
