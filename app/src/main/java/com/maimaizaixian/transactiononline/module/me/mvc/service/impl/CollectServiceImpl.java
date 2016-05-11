package com.maimaizaixian.transactiononline.module.me.mvc.service.impl;

import android.text.TextUtils;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IBusinessDao;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.ICollectDao;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.impl.CollectDaoImpl;
import com.maimaizaixian.transactiononline.module.me.mvc.service.ICollectService;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/12/8.
 */
public class CollectServiceImpl extends BaseService implements ICollectService {

    private ICollectDao dao;

    public CollectServiceImpl(IBaseController controller) {
        super(controller);
        dao = new CollectDaoImpl(controller);
    }

    @Override
    public void getCollectList(int page) {
        StringBuilder builder = new StringBuilder(ICollectDao.API_COLLECT);
        builder.append("?p=").append(page);
        dao.getCollectList(builder.toString());
    }

    @Override
    public void collect(String id) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("chance_id", id);
        mController.openDialog();
        dao.collect(params);
    }

    @Override
    public void unCollect(String id) {
        if (TextUtils.isEmpty(id)) {
            ViewUtil.showSnackbar(mController.getRootView(), "至少选择一个收藏");
            return;
        }

        StringBuilder builder = new StringBuilder(IBusinessDao.API_COLLECT);
        builder.append("?chance_id=").append(id);
        mController.openDialog();
        dao.unCollect(builder.toString());
    }
}
