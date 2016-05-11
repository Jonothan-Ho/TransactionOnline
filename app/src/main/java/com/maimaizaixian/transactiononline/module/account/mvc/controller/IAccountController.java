package com.maimaizaixian.transactiononline.module.account.mvc.controller;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;

/**
 * Created by Administrator on 2015/11/20.
 */
public interface IAccountController<T> extends IBaseController {


    void completeValidCode(String code);

    void complete(T t);

    void closeActivity();
}
