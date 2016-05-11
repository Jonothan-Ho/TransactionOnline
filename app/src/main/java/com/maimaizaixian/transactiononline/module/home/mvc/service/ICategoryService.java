package com.maimaizaixian.transactiononline.module.home.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;
import com.maimaizaixian.transactiononline.module.home.domain.Category;

/**
 * Created by Administrator on 2015/11/26.
 */
public interface ICategoryService extends IBaseService<Category> {

    void getCategoryList();

}
