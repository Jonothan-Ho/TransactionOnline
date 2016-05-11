package com.maimaizaixian.transactiononline.module.common.mvc.controller;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.domain.Product;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 */
public interface IProductController extends IBaseController {

    enum Type {
        Delete
    }

    void onCompleteArray(List<Product> products, int page);

    void onComplete(Type type);

    void onComplete(Product product);

}
