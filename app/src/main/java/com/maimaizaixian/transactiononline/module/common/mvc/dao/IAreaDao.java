package com.maimaizaixian.transactiononline.module.common.mvc.dao;

import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/2.
 */
public interface IAreaDao extends IBaseDao {

    public static final String API_AREA = "api/city?";
    public static final String API_AREA_GROUP = "api/city/cities_by_group";


    void getAreaList(String url);


}
