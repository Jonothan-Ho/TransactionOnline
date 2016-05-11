package com.maimaizaixian.transactiononline.module.me.mvc.service.impl;

import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.ISystemNoticeDao;
import com.maimaizaixian.transactiononline.module.me.mvc.dao.impl.SystemNoticeDaoImpl;
import com.maimaizaixian.transactiononline.module.me.mvc.service.ISystemNoticeService;

/**
 * Created by Administrator on 2015/12/10.
 */
public class SystemNoticeServiceImpl extends BaseService implements ISystemNoticeService {

    private ISystemNoticeDao dao;

    public SystemNoticeServiceImpl(IBaseController controller) {
        super(controller);
        dao = new SystemNoticeDaoImpl(controller);
    }

    @Override
    public void getSystemNoticeList(int page) {
        StringBuilder builder = new StringBuilder(ISystemNoticeDao.SYSTEM_NOTICE);
        builder.append("?model=systemNotice").append("&p=").append(page);
        dao.getList(builder.toString());
    }
}
