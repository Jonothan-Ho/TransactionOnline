package com.maimaizaixian.transactiononline.module.me.mvc.control;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;

/**
 * Created by Administrator on 2015/12/11.
 */
public interface IPasswordController extends IBaseController {
    enum Passwd_Action{
        UpdateLoginPasswd,UpdatePayPasswd
    }

    void onComplete(Passwd_Action action);

}
