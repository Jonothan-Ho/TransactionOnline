package com.maimaizaixian.transactiononline.module.common.mvc.controller;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.domain.Business;
import com.maimaizaixian.transactiononline.module.common.mvc.dao.impl.BusinessDaoImpl;
import com.maimaizaixian.transactiononline.module.home.domain.CategoryContent;

import java.util.List;

/**
 * Created by Administrator on 2015/11/26.
 */
public interface IBusinessController extends IBaseController {

    enum Business_Type {
        Report, Collect,Delete
    }

    enum Business_Action {
        Pay, Info,Publish,Close
    }


    void onMatchResult(List<CategoryContent> categoryContents);

    void onCompleteBusiness(Business business, Business_Action action);

    void onCompleteBusiness(List<Business> business, int page);

    void onComplete(Business_Type type);

}
