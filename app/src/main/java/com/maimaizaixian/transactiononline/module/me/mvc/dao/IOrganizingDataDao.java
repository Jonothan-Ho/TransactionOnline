package com.maimaizaixian.transactiononline.module.me.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/7.
 */
public interface IOrganizingDataDao extends IBaseDao {

    public static final String API_ORGANIZING = "api/item";

    void submit(RequestParams params);

    void applyAgent(RequestParams params);

}
