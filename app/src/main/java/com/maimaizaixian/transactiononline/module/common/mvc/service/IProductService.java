package com.maimaizaixian.transactiononline.module.common.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/12/1.
 */
public interface IProductService extends IBaseService {

    public static final String MODEL_PRODUCT = "product";
    public static final String MODEL_CASE = "case";
    public static final String MODEL_APTITUDE = "aptitude";

    void getProductList(String model,String uid,int page);

    void deleteProduct(String id);

    void addProduct(String model, String title, String imgpiclist, String description);

}
