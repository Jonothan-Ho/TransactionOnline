package com.maimaizaixian.transactiononline.framework.mvc.control;

import android.view.View;

import java.io.Serializable;

/**
 * Created by Administrator on 2015/10/23.
 */
public interface IBaseController {

    View getRootView();

    void openDialog();

    void closeDialog();

}
