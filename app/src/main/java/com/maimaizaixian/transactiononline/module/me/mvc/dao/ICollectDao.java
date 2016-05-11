package com.maimaizaixian.transactiononline.module.me.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/8.
 */
public interface ICollectDao extends IBaseDao {

    public static final String API_COLLECT = "api/collect";

    void getCollectList(String url);

    void collect(RequestParams params);

    void unCollect(String url);

}
