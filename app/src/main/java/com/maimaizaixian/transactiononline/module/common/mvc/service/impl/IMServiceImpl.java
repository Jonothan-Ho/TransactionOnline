package com.maimaizaixian.transactiononline.module.common.mvc.service.impl;

import android.text.TextUtils;

import com.easemob.EMCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IMDao;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.impl.IMDaoImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IMService;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/12/15.
 */
public class IMServiceImpl extends BaseService implements IMService {

    private IMDao dao;

    public IMServiceImpl(IBaseController controller) {
        super(controller);
        dao = new IMDaoImpl(controller);
    }

    @Override
    public void login(String username, String passwd, EMCallBack callBack) {
        if (TextUtils.isEmpty(username)) {
            ViewUtil.showSnackbar(mController.getRootView(), "聊天账号不能为空");
            return;
        }
        if (TextUtils.isEmpty(passwd)) {
            ViewUtil.showSnackbar(mController.getRootView(), "聊天账号密码不能为空");
            return;
        }

        dao.login(username, passwd, callBack);
    }
}
