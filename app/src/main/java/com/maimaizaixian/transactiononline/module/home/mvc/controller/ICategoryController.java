package com.maimaizaixian.transactiononline.module.home.mvc.controller;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.home.domain.Category;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public interface ICategoryController extends IBaseController {

    void onCategoryData(List<Category> categories);

}
