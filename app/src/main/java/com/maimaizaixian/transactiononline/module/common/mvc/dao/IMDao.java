package com.maimaizaixian.transactiononline.module.common.mvc.dao;

import com.easemob.EMCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/15.
 */
public interface IMDao extends IBaseDao {

    void login(String username, String passed,EMCallBack callBack);

}
