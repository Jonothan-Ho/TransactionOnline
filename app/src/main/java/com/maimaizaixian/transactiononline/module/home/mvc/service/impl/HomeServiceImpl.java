package com.maimaizaixian.transactiononline.module.home.mvc.service.impl;

import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.home.domain.Poster;
import com.maimaizaixian.transactiononline.module.home.mvc.dao.IHomeDao;
import com.maimaizaixian.transactiononline.module.home.mvc.dao.impl.HomeDaoImpl;
import com.maimaizaixian.transactiononline.module.home.mvc.service.IHomeService;

/**
 * Created by Administrator on 2015/11/25.
 */
public class HomeServiceImpl extends BaseService<Poster> implements IHomeService {

    private IHomeDao dao;

    public HomeServiceImpl(IBaseController controller) {
        super(controller);
        dao = new HomeDaoImpl(controller);
    }

    @Override
    public void getAdImages() {
        dao.getAdImages();
    }

}
