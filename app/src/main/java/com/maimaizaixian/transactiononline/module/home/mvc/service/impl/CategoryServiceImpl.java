package com.maimaizaixian.transactiononline.module.home.mvc.service.impl;

import com.maimaizaixian.transactiononline.framework.mvc.business.impl.BaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.mvc.dao.ICategoryDao;
import com.maimaizaixian.transactiononline.module.home.mvc.dao.impl.CategoryDaoImpl;
import com.maimaizaixian.transactiononline.module.home.mvc.service.ICategoryService;

/**
 * Created by Administrator on 2015/11/26.
 */
public class CategoryServiceImpl extends BaseService<Category> implements ICategoryService {

    private ICategoryDao dao;

    public CategoryServiceImpl(IBaseController controller) {
        super(controller);
        dao = new CategoryDaoImpl(controller);
    }

    @Override
    public void getCategoryList() {
        mController.openDialog();
        dao.getCategoryList();
    }
}
