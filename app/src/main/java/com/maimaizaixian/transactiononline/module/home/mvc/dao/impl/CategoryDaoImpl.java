package com.maimaizaixian.transactiononline.module.home.mvc.dao.impl;

import com.maimaizaixian.transactiononline.framework.http.Domain;
import com.maimaizaixian.transactiononline.framework.http.HttpUtil;
import com.maimaizaixian.transactiononline.framework.http.ObjectRequestCallBack;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.impl.BaseDao;
import com.maimaizaixian.transactiononline.module.home.domain.Category;
import com.maimaizaixian.transactiononline.module.home.mvc.controller.ICategoryController;
import com.maimaizaixian.transactiononline.module.home.mvc.dao.ICategoryDao;
import com.maimaizaixian.transactiononline.module.home.mvc.parser.CategoryParser;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public class CategoryDaoImpl extends BaseDao<Category> implements ICategoryDao {

    public static final String API_CATEGORY_LIST = "api/category/category_group";

    private ICategoryController mCategoryController;

    public CategoryDaoImpl(IBaseController baseController) {
        super(baseController);
        mCategoryController = (ICategoryController) baseController;
    }

    @Override
    public void getCategoryList() {
        HttpUtil.get(API_CATEGORY_LIST, new ObjectRequestCallBack<Category>(ObjectRequestCallBack.Json_parser.Array, new CategoryParser()) {
            @Override
            public void onResponse(Category category, Domain domain) {

            }

            @Override
            public void onResponse(List<Category> array, Domain domain) {
                mCategoryController.closeDialog();
                if (domain.isSuccess() && array!=null) {
                    mCategoryController.onCategoryData(array);
                }
            }
        });
    }
}
