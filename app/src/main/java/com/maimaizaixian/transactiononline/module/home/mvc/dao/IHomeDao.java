package com.maimaizaixian.transactiononline.module.home.mvc.dao;

import com.maimaizaixian.transactiononline.framework.mvc.dao.IBaseDao;
import com.maimaizaixian.transactiononline.module.home.domain.Poster;

/**
 * Created by Administrator on 2015/11/25.
 */
public interface IHomeDao extends IBaseDao<Poster> {

    void getAdImages();

}
