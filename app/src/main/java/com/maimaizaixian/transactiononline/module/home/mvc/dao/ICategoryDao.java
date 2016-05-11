package com.maimaizaixian.transactiononline.module.home.mvc.dao;

import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;
import com.maimaizaixian.transactiononline.module.home.domain.Category;

/**
 * Created by Administrator on 2015/11/26.
 */
public interface ICategoryDao extends IBaseDao<Category> {

    void getCategoryList();
}
