package com.maimaizaixian.transactiononline.module.common.mvc.dao;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

/**
 * Created by Administrator on 2015/12/1.
 */
public interface IProductDao extends IBaseDao {

    public static final String API_PRODUCT_LIST = "api/product?";
    public static final String API_PRODUCT = "api/product";

    void getProductList(String url);

    void deleteProduct(String url);

    void addProduct(RequestParams params);

}
