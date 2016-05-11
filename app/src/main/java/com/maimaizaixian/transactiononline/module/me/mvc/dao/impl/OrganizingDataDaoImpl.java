package com.maimaizaixian.transactiononline.module.me.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IOrganizingDataController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.IOrganizingDataDao;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/12/7.
 */
public class OrganizingDataDaoImpl extends BaseDao implements IOrganizingDataDao {

    private IOrganizingDataController controller;

    public OrganizingDataDaoImpl(IBaseController baseController) {
        super(baseController);
        controller = (IOrganizingDataController) baseController;
    }

    @Override
    public void submit(RequestParams params) {
        HttpUtil.post(API_ORGANIZING, params, new ObjectRequestCallBack<User>(ObjectRequestCallBack.Json_parser.Object, User.class) {
            @Override
            public void onResponse(User obj, Domain domain) {
                controller.closeDialog();
                ViewUtil.showSnackbar(controller.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.onComplete(obj);
                }
            }
        });
    }

    @Override
    public void applyAgent(RequestParams params) {
        HttpUtil.post(API_ORGANIZING, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                controller.closeDialog();
                ViewUtil.showSnackbar(controller.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.onComplete(null);
                }
            }
        });
    }
}
