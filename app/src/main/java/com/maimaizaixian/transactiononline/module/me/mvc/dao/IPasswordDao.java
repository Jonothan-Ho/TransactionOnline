package com.maimaizaixian.transactiononline.module.me.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/11.
 */
public interface IPasswordDao extends IBaseDao {

    public static final String API_LOGIN_PASSWD = "api/users/resetPassword";
    public static final String API_PAY_PASSWD = "api/users/alipayPassword";

    void updateLoginPasswd(RequestParams params);

    void updatePayPasswd(RequestParams params);

}
