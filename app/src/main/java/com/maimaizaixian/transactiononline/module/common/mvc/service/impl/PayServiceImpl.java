package com.maimaizaixian.transactiononline.module.common.mvc.service.impl;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IPayDao;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.impl.PayDaoImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IPayService;

/**
 * Created by Administrator on 2015/12/3.
 */
public class PayServiceImpl extends BaseService implements IPayService {

    private IPayDao dao;

    public PayServiceImpl(IBaseController controller) {
        super(controller);
        dao = new PayDaoImpl(controller);
    }

    @Override
    public void getAccountBalance() {
        dao.getAccountBalance();
    }

    @Override
    public void setPayPasswd(String newPasswd, String oldPasswd) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("alipay_password", newPasswd);
        params.addBodyParameter("old_alipay_password", oldPasswd);
        params.addBodyParameter("plat", "app");
        mController.openDialog();
        dao.setPayPasswd(params);
    }
}
