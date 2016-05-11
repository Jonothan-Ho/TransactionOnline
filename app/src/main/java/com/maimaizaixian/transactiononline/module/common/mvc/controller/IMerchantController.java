package com.maimaizaixian.transactiononline.module.common.mvc.controller;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.account.domain.User;

import java.util.List;

/**
 * Created by Administrator on 2015/11/27.
 */
public interface IMerchantController extends IBaseController {


    void onCompleteMerchants(List<User> users,int page);

    void onCompleteMerchantInfo();

}
