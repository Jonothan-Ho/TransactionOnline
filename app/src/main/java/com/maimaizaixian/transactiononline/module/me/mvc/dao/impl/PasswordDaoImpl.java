package com.maimaizaixian.transactiononline.module.me.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.me.mvc.control.IPasswordController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.IPasswordDao;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/12/11.
 */
public class PasswordDaoImpl extends BaseDao implements IPasswordDao {

    private IPasswordController controller;

    public PasswordDaoImpl(IBaseController baseController) {
        super(baseController);
        controller = (IPasswordController) baseController;
    }

    @Override
    public void updateLoginPasswd(RequestParams params) {
        HttpUtil.post(API_LOGIN_PASSWD, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                ViewUtil.showSnackbar(controller.getRootView(), domain.getMsg());
                controller.closeDialog();
                if (domain.isSuccess()) {
                    controller.onComplete(IPasswordController.Passwd_Action.UpdateLoginPasswd);
                }
            }
        });
    }

    @Override
    public void updatePayPasswd(RequestParams params) {
        HttpUtil.post(API_PAY_PASSWD, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                ViewUtil.showSnackbar(controller.getRootView(), domain.getMsg());
                controller.closeDialog();
                if (domain.isSuccess()) {
                    controller.onComplete(IPasswordController.Passwd_Action.UpdatePayPasswd);
                }
            }
        });
    }
}
