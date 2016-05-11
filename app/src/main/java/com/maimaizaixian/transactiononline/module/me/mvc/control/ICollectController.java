package com.maimaizaixian.transactiononline.module.me.mvc.control;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.domain.Business;

import java.util.List;


/**
 * Created by Administrator on 2015/12/8.
 */
public interface ICollectController extends IBaseController{

    void onComplete(List<Business> collects,int page);

    void onComplete();

}
