package com.maimaizaixian.transactiononline.module.me.mvc.service;

import com.maimaizaixian.transactiononline.framework.mvc.business.IBaseService;

/**
 * Created by Administrator on 2015/12/8.
 */
public interface ICollectService extends IBaseService {

    void getCollectList(int page);

    void collect(String id);

    void unCollect(String id);

}
