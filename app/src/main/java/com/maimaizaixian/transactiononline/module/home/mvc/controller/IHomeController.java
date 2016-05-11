package com.maimaizaixian.transactiononline.module.home.mvc.controller;

import com.maimaizaixian.transactiononline.framework.mvc.control.IBaseController;
import com.maimaizaixian.transactiononline.module.home.domain.Poster;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/25.
 */
public interface IHomeController extends IBaseController {

    void onAdImages(List<Poster> posters);

}
