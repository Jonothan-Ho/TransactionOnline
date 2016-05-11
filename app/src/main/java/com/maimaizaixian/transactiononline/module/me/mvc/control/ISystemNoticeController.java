package com.maimaizaixian.transactiononline.module.me.mvc.control;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.me.domain.SystemNotice;

import java.util.List;

/**
 * Created by Administrator on 2015/12/10.
 */
public interface ISystemNoticeController extends IBaseController {


    void onComplete(List<SystemNotice> systemNotices, int page);

}
