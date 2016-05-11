package com.maimaizaixian.transactiononline.module.me.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.me.mvc.control.ICollectController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.ICollectDao;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

import java.util.List;

/**
 * Created by Administrator on 2015/12/8.
 */
public class CollectDaoImpl extends BaseDao implements ICollectDao {

    private ICollectController controller;

    public CollectDaoImpl(IBaseController baseController) {
        super(baseController);
        controller = (ICollectController) baseController;
    }

    @Override
    public void getCollectList(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<Business>(ObjectRequestCallBack.Json_parser.Array, Business.class) {
            @Override
            public void onResponse(List<Business> array, Domain domain) {
                if (array != null && domain.isSuccess()) {
                    controller.onComplete(array, domain.getPage());
                }
            }
        });
    }

    @Override
    public void collect(RequestParams params) {
        HttpUtil.post(API_COLLECT, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                controller.closeDialog();
                ViewUtil.showSnackbar(controller.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.onComplete();
                }
            }
        });
    }

    @Override
    public void unCollect(String url) {
        HttpUtil.delete(url, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                controller.closeDialog();
                ViewUtil.showSnackbar(controller.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.onComplete();
                }
            }
        });
    }
}
