package com.maimaizaixian.transactiononline.module.me.mvc.service.impl;

import android.text.TextUtils;

import com.lidroid.xutils.http.RequestParams;
import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.IMoreDao;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.impl.MoreDaoImpl;
import com.maimaizaixian.transactiononline.module.me.mvc.service.IMoreService;
import com.maimaizaixian.transactiononline.utils.ViewUtil;

/**
 * Created by Administrator on 2015/12/11.
 */
public class MoreServiceImpl extends BaseService implements IMoreService {

    private IMoreDao dao;

    public MoreServiceImpl(IBaseController controller) {
        super(controller);
        dao = new MoreDaoImpl(controller);
    }

    @Override
    public void feedBack(String content) {

        if (TextUtils.isEmpty(content)) {
            ViewUtil.showSnackbar(mController.getRootView(), "意见反馈不能为空");
            return;
        }

        RequestParams params = new RequestParams();
        params.addBodyParameter("model", "suggestion");
        params.addBodyParameter("content", content);
        mController.openDialog();
        dao.feedBack(params);
    }

    @Override
    public void getRecruitList(int page) {
        StringBuilder builder = new StringBuilder(IMoreDao.API_ITEM);
        builder.append("?model=recruit&").append("p=").append(page);
        dao.getRecruitList(builder.toString());
    }

    @Override
    public void makeResume(RequestParams params) {
        mController.openDialog();
        dao.makeResume(params);
    }

    @Override
    public void updateResume(RequestParams params) {
        mController.openDialog();
        dao.makeResume(params);
    }

    @Override
    public void getResume() {
        StringBuilder builder = new StringBuilder(IMoreDao.API_ITEM);
        builder.append("?model=resume");
        mController.openDialog();
        dao.getResume(builder.toString());
    }

    @Override
    public void sendResume(String id) {
        RequestParams params = new RequestParams();
        params.addBodyParameter("recruit_id", id);
        params.addBodyParameter("model", "deliverResume");
        mController.openDialog();
        dao.sendResume(params);
    }
}
