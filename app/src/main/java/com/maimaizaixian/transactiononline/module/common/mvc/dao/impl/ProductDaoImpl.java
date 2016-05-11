package com.maimaizaixian.transactiononline.module.common.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.common.domain.Product;
import com.maimaizaixian.transactiononline.module.common.mvc.controller.IProductController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IProductDao;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/12/1.
 */
public class ProductDaoImpl extends BaseDao implements IProductDao {

    private IProductController controller;

    public ProductDaoImpl(IBaseController baseController) {
        super(baseController);
        controller = (IProductController) baseController;
    }

    @Override
    public void getProductList(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<Product>(ObjectRequestCallBack.Json_parser.Array, Product.class) {
            @Override
            public void onResponse(List<Product> array, Domain domain) {
                controller.closeDialog();
                if (domain.isSuccess() && array != null) {
                    controller.onCompleteArray(array, domain.getPage());
                }
            }
        });
    }

    @Override
    public void deleteProduct(String url) {
        HttpUtil.delete(url, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.Object, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                mBaseController.closeDialog();
                ViewUtil.showSnackbar(controller.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.onComplete(IProductController.Type.Delete);
                }
            }
        });
    }

    @Override
    public void addProduct(RequestParams params) {
        HttpUtil.post(API_PRODUCT, params, new ObjectRequestCallBack<Product>(ObjectRequestCallBack.Json_parser.Object, Product.class) {
            @Override
            public void onResponse(Product obj, Domain domain) {
                mBaseController.closeDialog();
                ViewUtil.showSnackbar(controller.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.onComplete(obj);
                }
            }
        });
    }
}
