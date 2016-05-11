package com.maimaizaixian.transactiononline.module.common.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/12/2.
 */
public interface IAreaService extends IBaseService {


    public static final String TYPE_PROVICE = "1";
    public static final String TYPE_CITY = "2";
    public static final String TYPE_COUNTY = "3";

    void getAreaList(String pid, String type, String id);

    void getAreaList();

}
