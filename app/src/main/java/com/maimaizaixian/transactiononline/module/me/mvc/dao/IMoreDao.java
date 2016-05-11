package com.maimaizaixian.transactiononline.module.me.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/11.
 */
public interface IMoreDao extends IBaseDao {

    public static final String API_ITEM = "api/item";

    void feedBack(RequestParams params);

    void getRecruitList(String url);

    void makeResume(RequestParams params);

    void updateResume(RequestParams params);

    void getResume(String url);

    void sendResume(RequestParams params);

}
