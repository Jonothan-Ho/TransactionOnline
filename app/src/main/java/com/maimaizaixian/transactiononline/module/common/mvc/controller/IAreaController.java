package com.maimaizaixian.transactiononline.module.common.mvc.controller;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.common.domain.Area;

import java.util.List;

/**
 * Created by Administrator on 2015/12/2.
 */
public interface IAreaController extends IBaseController {

    void onCompleteArea(List<Area> areas);

}
