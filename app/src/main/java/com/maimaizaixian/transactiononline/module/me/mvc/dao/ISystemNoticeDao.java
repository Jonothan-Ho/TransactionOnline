package com.maimaizaixian.transactiononline.module.me.mvc.dao;

import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/10.
 */
public interface ISystemNoticeDao extends IBaseDao {

    public static final String SYSTEM_NOTICE = "/api/item";

    void getList(String url);


}
