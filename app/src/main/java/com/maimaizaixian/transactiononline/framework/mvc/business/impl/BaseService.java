package com.maimaizaixian.transactiononline.framework.mvc.business.impl;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;
import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/21.
 */
public class BaseService<T extends Serializable> implements IBaseService<T> {

    protected IBaseController mController;

    public BaseService(IBaseController controller) {
        this.mController = controller;
    }


}
