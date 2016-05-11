package com.maimaizaixian.transactiononline.module.me.mvc.service.impl;

import android.text.TextUtils;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.IPasswordDao;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.impl.PasswordDaoImpl;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IPasswordService;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/12/11.
 */
public class PasswordServiceImpl extends BaseService implements IPasswordService {

    private IPasswordDao dao;

    public PasswordServiceImpl(IBaseController controller) {
        super(controller);
        dao = new PasswordDaoImpl(controller);
    }

    @Override
    public void updateLoginPasswd(String oldPasswd, String passwd) {
        if (!TextUtil.isValidPassword(oldPasswd, mController.getRootView())) {
            return;
        }
        if (!TextUtil.isValidPassword(passwd, mController.getRootView())) {
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("old_password", oldPasswd);
        params.addBodyParameter("password", oldPasswd);

        mController.openDialog();

        dao.updateLoginPasswd(params);


    }

    @Override
    public void updatePayPasswd(String oldPasswd, String passwd) {
        if (!TextUtil.isValidPassword(oldPasswd, mController.getRootView())) {
            return;
        }
        if (!TextUtil.isValidPassword(passwd, mController.getRootView())) {
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("old_alipay_password", oldPasswd);
        params.addBodyParameter("alipay_password", oldPasswd);

        mController.openDialog();

        dao.updatePayPasswd(params);

    }

    @Override
    public void updatePayPasswd(String phone, String passwd, String code) {
        if (!TextUtil.isValidPhone(phone, mController.getRootView())) {
            return;
        }

        if (!TextUtil.isValidPassword(passwd, mController.getRootView())) {
            return;
        }

        if (TextUtils.isEmpty(code)) {
            ViewUtil.showSnackbar(mController.getRootView(), "验证码不能为空");
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("mobile", phone);
        params.addBodyParameter("alipay_password", passwd);
        params.addBodyParameter("code", code);

        mController.openDialog();

        dao.updatePayPasswd(params);


    }
}
