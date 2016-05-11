package com.maimaizaixian.transactiononline.module.common.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/3.
 */
public interface IPayDao extends IBaseDao {

    public static final String API_BALANCE = "api/users/balance";
    public static final String API_SET_PASSWD = "api/users/alipayPassword";

    void getAccountBalance();

    void setPayPasswd(RequestParams params);


}
