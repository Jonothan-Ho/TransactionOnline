package com.maimaizaixian.transactiononline.module.me.mvc.control;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.account.domain.User;

/**
 * Created by Administrator on 2015/12/7.
 */
public interface IOrganizingDataController extends IBaseController {

    void onComplete(User user);

}
