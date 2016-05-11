package com.maimaizaixian.transactiononline.module.home.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;
import com.maimaizaixian.transactiononline.module.home.domain.Poster;

/**
 * Created by Administrator on 2015/11/25.
 */
public interface IHomeService extends IBaseService<Poster> {

    void getAdImages();

}
