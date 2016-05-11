package com.maimaizaixian.transactiononline.module.common.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/11/27.
 */
public interface IMerchantService extends IBaseService {

    /************
     * Publish
     **********/
    public static final String TYPE_SCALE = "1";
    public static final String TYPE_NEIGHBORHOOD = "2";

    /*************
     * Condition
     ************/
    public static final String ORDER_DISTANCE = "distance";
    public static final String ORDER_TIME = "create_time";
    public static final String ORDER_SCALE = "scale";
    public static final String ORDER_DESC = "desc";
    public static final String ORDER_ASC = "asc";

    void getMerchantList(String type, String cid, int recommend_num, int p, String order_name, String order_type);

    void getMerchantInfo(String id);

}
