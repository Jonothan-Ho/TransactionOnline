package com.maimaizaixian.transactiononline.module.common.mvc.service.impl;

import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.IAreaDao;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.impl.AreaDaoImpl;
import com.maimaizaixian.transactiononline.module.common.mvc.service.IAreaService;

/**
 * Created by Administrator on 2015/12/2.
 */
public class AreaServiceImpl extends BaseService implements IAreaService {

    private IAreaDao dao;

    public AreaServiceImpl(IBaseController controller) {
        super(controller);
        dao = new AreaDaoImpl(controller);
    }

    @Override
    public void getAreaList(String pid, String type, String id) {
        StringBuilder builder = new StringBuilder(IAreaDao.API_AREA);
        builder.append("pid=").append(pid).append("&type=").append(type).append("&id=").append(id);
        dao.getAreaList(builder.toString());
    }

    @Override
    public void getAreaList() {
        dao.getAreaList(IAreaDao.API_AREA_GROUP);
    }
}
