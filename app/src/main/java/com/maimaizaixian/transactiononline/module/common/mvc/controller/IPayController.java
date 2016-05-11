package com.maimaizaixian.transactiononline.module.common.mvc.controller;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.domain.PayBalance;

import java.util.List;

/**
 * Created by Administrator on 2015/12/3.
 */
public interface IPayController extends IBaseController {

    void onAccountBalance(PayBalance payBalance);

    void onComplete();

}
