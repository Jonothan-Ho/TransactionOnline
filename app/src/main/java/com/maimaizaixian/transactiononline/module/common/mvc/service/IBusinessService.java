package com.maimaizaixian.transactiononline.module.common.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/11/26.
 */
public interface IBusinessService extends IBaseService {

    public static final String MODE_BUY = "buy";
    public static final String MODE_SELL = "sell";
    public static final String MODE_RENT = "rent";
    public static final String MODE_RENTOUT = "rentOut";

    public static final String TYPE_HALL = "1";
    public static final String TYPE_NEIGHBORHOOD = "2";

    public static final String ORDER_DISTANCE = "distinct";
    public static final String ORDER_TIME = "create_time";
    public static final String ORDER_SCALE = "scale";

    public static final String ORDER_DESC = "desc";
    public static final String ORDER_ASC = "asc";


    void matchKeyToCategory(String key);

    void publishBusiness(String mode, int id, int property, int push_num, String desc, String address, String contact, String num, String images, String longitude, String latitude);

    void updateBusiness(String businessID, String mode, int id, int property, int push_num, String desc, String address, String contact, String num, String images, String longitude, String latitude);


    void publishBusiness(String title, String desc, String images, String longitude, String latitude);

    void getBusinessList(String model, String type, String cid, int page, String order_name, String order_type, int cityID);

    void getBusinessList(String title, String longitude, String latitude, int page);

    void payBusiness(String id, String passwd);

    void report(String id, String content);

    void collect(String id);

    void unCollect(String id);

    void getBusinessPushList(int p);

    void getBusinessInfo(String id);

    void getHistoryList(String id, String model, int p);

    void closeBusiness(String id);

    void deleteBusiness(String id);


}
