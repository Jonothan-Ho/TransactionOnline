package com.maimaizaixian.transactiononline.module.me.mvc.service.impl;

import android.text.TextUtils;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.IOrganizingDataDao;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.impl.OrganizingDataDaoImpl;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IOrganizingDataService;
import com.maimaizaixian.transactiononline.utils.TextUtil;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/12/7.
 */
public class OrganizingDataServiceImpl extends BaseService implements IOrganizingDataService {

    private IOrganizingDataDao dao;

    public OrganizingDataServiceImpl(IBaseController controller) {
        super(controller);
        dao = new OrganizingDataDaoImpl(controller);
    }

    @Override
    public void submit(RequestParams params) {
        mController.openDialog();
        dao.submit(params);
    }

    @Override
    public void applyAgent(String name, String mobile, String idCard, String IDCard_license) {
        if (TextUtils.isEmpty(name)) {
            ViewUtil.showSnackbar(mController.getRootView(), "名字不能为空");
            return;
        }

        if (!TextUtil.isValidPhone(mobile, mController.getRootView())) {
            return;
        }

        if (TextUtils.isEmpty(idCard)) {
            ViewUtil.showSnackbar(mController.getRootView(), "身份证不能为空");
            return;
        }

        if (TextUtils.isEmpty(IDCard_license)) {
            ViewUtil.showSnackbar(mController.getRootView(), "身份证照片不能为空");
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("model", "agent");
        params.addBodyParameter("name", name);
        params.addBodyParameter("mobile", mobile);
        params.addBodyParameter("IDCard", idCard);
        params.addBodyParameter("IDCard_license", IDCard_license);

        mController.openDialog();
        dao.applyAgent(params);
    }
}
