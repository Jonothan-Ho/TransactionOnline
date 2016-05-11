package com.maimaizaixian.transactiononline.module.account.mvc.dao.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.account.domain.parser.UserParser;
import com.maimaizaixian.transactiononline.module.account.mvc.controller.IAccountController;
import com.maimaizaixian.transactiononline.module.account.mvc.dao.IAccountDao;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/11/20.
 */
public class AccountDaoImpl extends BaseDao<User> implements IAccountDao {

    public static final String API_VALID_CODE = "api/users/smsSendCode";
    public static final String API_REGIST = "api/users/register";
    public static final String API_LOGIN = "api/users/login";
    public static final String API_FIND_PASSWD = "api/users/resetPassword";

    private IAccountController controller;


    public AccountDaoImpl(IBaseController baseController) {
        super(baseController);
        this.controller = (IAccountController) baseController;
    }

    @Override
    public void getValidCode(String phoneNumber) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", phoneNumber);
        HttpUtil.post(API_VALID_CODE, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                controller.closeDialog();
                ViewUtil.showSnackbar(mBaseController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.completeValidCode(domain.getData());
                }
            }
        });

    }

    @Override
    public void regist(RequestParams params) {
        HttpUtil.post(API_REGIST, params, new ObjectRequestCallBack<User>(ObjectRequestCallBack.Json_parser.Object, new UserParser()) {

            @Override
            public void onResponse(User obj, Domain domain) {
                controller.closeDialog();
                ViewUtil.showSnackbar(mBaseController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.complete(obj);
                }
            }
        });
    }

    @Override
    public void login(RequestParams params) {
        HttpUtil.post(API_LOGIN, params, new ObjectRequestCallBack<User>(ObjectRequestCallBack.Json_parser.Object, new UserParser()) {

            @Override
            public void onResponse(User obj, Domain domain) {
                controller.closeDialog();
                ViewUtil.showSnackbar(mBaseController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.complete(obj);
                }
            }
        });
    }

    @Override
    public void findPasswd(RequestParams params) {
        HttpUtil.post(API_FIND_PASSWD, params, new ObjectRequestCallBack<String>(ObjectRequestCallBack.Json_parser.NULL, String.class) {
            @Override
            public void onResponse(String obj, Domain domain) {
                ViewUtil.showSnackbar(mBaseController.getRootView(), domain.getMsg());
                if (domain.isSuccess()) {
                    controller.closeActivity();
                }
            }
        });
    }
}
