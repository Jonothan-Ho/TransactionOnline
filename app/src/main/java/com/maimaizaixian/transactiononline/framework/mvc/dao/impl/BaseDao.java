package com.maimaizaixian.transactiononline.framework.mvc.dao.impl;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/21.
 */
public class BaseDao<T extends Serializable> implements IBaseDao<T> {

    protected IBaseController mBaseController;

    public BaseDao(IBaseController baseController) {
        this.mBaseController = baseController;
    }
}
