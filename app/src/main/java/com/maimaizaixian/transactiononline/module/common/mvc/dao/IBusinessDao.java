package com.maimaizaixian.transactiononline.module.common.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/11/26.
 */
public interface IBusinessDao extends IBaseDao {

    public static final String API_BUSINESS_LIST = "api/trade?";
    public static final String API_PAY = "api/trade/buyContact";
    public static final String API_REPORT = "api/trade/report";
    public static final String API_COLLECT = "api/collect";
    public static final String API_BUSINESS_PUSH = "api/tradePush?";

    void getCategoryByKey(String url);

    void publishBuy(RequestParams params);

    void updateBusiness(RequestParams params);


    void getBusinessList(String url);

    void pay(RequestParams params);

    void report(RequestParams params);

    void collect(RequestParams params);

    void unCollect(String url);

    void getBusinessPush(String url);

    void getBusinessInfo(String url);

    void closeBusiness(RequestParams params);

    void deleteBusiness(String url);


}
