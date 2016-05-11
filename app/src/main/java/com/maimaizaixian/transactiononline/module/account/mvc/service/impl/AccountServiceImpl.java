package com.maimaizaixian.transactiononline.module.account.mvc.service.impl;

import android.text.TextUtils;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.account.mvc.dao.IAccountDao;
import com.maimaizaixian.transactiononline.module.account.mvc.dao.impl.AccountDaoImpl;
import com.maimaizaixian.transactiononline.module.account.mvc.service.IAccountService;
import com.maimaizaixian.transactiononline.module.account.domain.User;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/10/23.
 */
public class AccountServiceImpl extends BaseService<User> implements IAccountService {

    private IAccountDao dao;

    public AccountServiceImpl(IBaseController controller) {
        super(controller);
        dao = new AccountDaoImpl(controller);
    }

    @Override
    public void login(String username, String password) {
        boolean isValid = TextUtil.isValidUser(username, password, mController.getRootView());
        if (!isValid) {
            return;
        }

        mController.openDialog();
        RequestParams params = new RequestParams();
        params.addBodyParameter("password", password);
        params.addBodyParameter("mobile", username);
        dao.login(params);

    }

    @Override
    public void getValidCode(String phoneNumber) {
        boolean isValid = TextUtil.isValidPhone(phoneNumber, mController.getRootView());
        if (!isValid) {
            return;
        }
        mController.openDialog();
        dao.getValidCode(phoneNumber);
    }

    @Override
    public void regist(String name, String passwd, String code) {
        boolean isPhoneValid = TextUtil.isValidPhone(name, mController.getRootView());
        if (!isPhoneValid) {
            return;
        }

        boolean isPasswdValid = TextUtil.isValidPassword(passwd, mController.getRootView());
        if (!isPasswdValid) {
            return;
        }

        if (TextUtils.isEmpty(code)) {
            ViewUtil.showSnackbar(mController.getRootView(), "验证码不能为空");
            return;
        }

        mController.openDialog();
        RequestParams params = new RequestParams();
        params.addBodyParameter("password", passwd);
        params.addBodyParameter("mobile", name);
        params.addBodyParameter("code", code);
        dao.regist(params);
    }

    @Override
    public void findPasswd(String name, String passwd, String code) {
        boolean isPhoneValid = TextUtil.isValidPhone(name, mController.getRootView());
        if (!isPhoneValid) {
            return;
        }

        boolean isPasswdValid = TextUtil.isValidPassword(passwd, mController.getRootView());
        if (!isPasswdValid) {
            return;
        }

        if (TextUtils.isEmpty(code)) {
            ViewUtil.showSnackbar(mController.getRootView(), "验证码不能为空");
            return;
        }

        mController.openDialog();
        RequestParams params = new RequestParams();
        params.addBodyParameter("password", passwd);
        params.addBodyParameter("mobile", name);
        params.addBodyParameter("code", code);
        dao.findPasswd(params);
    }
}
