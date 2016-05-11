package com.maimaizaixian.transactiononline.module.me.mvc.dao.impl;

import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.me.domain.SystemNotice;
import com.maimaizaixian.transactiononline.module.me.mvc.control.ISystemNoticeController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.ISystemNoticeDao;

import java.util.List;

/**
 * Created by Administrator on 2015/12/10.
 */
public class SystemNoticeDaoImpl extends BaseDao implements ISystemNoticeDao {

    private ISystemNoticeController mController;

    public SystemNoticeDaoImpl(IBaseController baseController) {
        super(baseController);
        mController = (ISystemNoticeController) baseController;
    }

    @Override
    public void getList(String url) {
        HttpUtil.get(url, new ObjectRequestCallBack<SystemNotice>(ObjectRequestCallBack.Json_parser.Array, SystemNotice.class) {
            @Override
            public void onResponse(List<SystemNotice> array, Domain domain) {
                if (domain.isSuccess() && array != null) {
                    mController.onComplete(array, domain.getPage());
                }
            }
        });
    }
}
